package main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.event.DeviceEventAdapter;
import com.serotonin.bacnet4j.service.confirmed.SubscribeCOVRequest;
import com.serotonin.bacnet4j.service.confirmed.WritePropertyRequest;
import com.serotonin.bacnet4j.type.constructed.PropertyValue;
import com.serotonin.bacnet4j.type.constructed.SequenceOf;
import com.serotonin.bacnet4j.type.enumerated.BinaryPV;
import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.Real;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.bacnet4j.type.primitive.Boolean;

import pdb.PdbAlarm;
import pdb.PdbException;
import pdb.PdbInterface;

public class PointManager extends DeviceEventAdapter {
	final static Logger logger = LoggerFactory.getLogger(PointManager.class);
	private static final int SUBSCRIPTION_DURATION = 1000*60*10;
	private static final int PDB_POLL_INTERVAL = 10000;
	private static final int PDB_POLL_START_DELAY = 20000;
	private PdbInterface pdb;
	private List<PointConnnectionContainer> points = new ArrayList<PointConnnectionContainer>();
	private LocalDevice localDevice;
	private RemoteDevice remoteDevice;
	private int remoteDeviceIdent;
	private long timeSinceLastActivityOnBACnetDevice = 0;
	private long comErrorDelay = 120*5*10;
	private long timeStampLastSubscription = 0;
	private long timeStampLastPdbPoll = 0;
	private long timeStampStarted = 0;
	private long subscriptionCount = 0;
	private PdbAlarm comError;
	private boolean configured = false;
	private boolean firstRun = true;
	private boolean pollEnable = false;
	
	public PointManager(PdbInterface pdb, List<PointConnnectionContainer> points, LocalDevice localdevice, RemoteDevice remotedevice) throws Exception {
		this.pdb = pdb;
		this.localDevice = localdevice;
		this.remoteDevice = remotedevice;
		this.points = points;
		configured = true;
		createComError();
	}
	public PointManager(int deviceIdetifier, PdbInterface pdb, List<PointConnnectionContainer> points) throws Exception {
		this.pdb = pdb;
		this.points = points;
		this.remoteDeviceIdent = deviceIdetifier;
		createComError();
	}
	public int getRemoteDeviceIdentifier() {
		return this.remoteDeviceIdent;
	}
	public void setLocalDevice(LocalDevice ld) {
		this.localDevice= ld;
		if (this.localDevice != null && this.remoteDevice != null) {
			this.configured = true;
		} else {
			this.configured = false;
		}
	}
	public void setRemoteDevice(RemoteDevice rd) {
		this.remoteDevice= rd;
		if (this.localDevice != null && this.remoteDevice != null) {
			this.configured = true;
		} else {
			this.configured = false;
		}
	}
	private void resetComErrorTimer() {
		timeSinceLastActivityOnBACnetDevice = 0;
	}
	public boolean isConfigured() {
		return this.configured;
	}
	public long getComErrorTimer() {
		return timeSinceLastActivityOnBACnetDevice;
	}
	public void addComErrorTime(long timer) {
		timeSinceLastActivityOnBACnetDevice += timer;
	}
	
	public boolean getComError() {
		return (timeSinceLastActivityOnBACnetDevice > comErrorDelay);
	}
	
	public void setComErrorDelay(long time) {
		comErrorDelay = time;
	}
	
	private void createComError() throws PdbException {
		comError = pdb.createAlarm("sys.bacnetdev" + String.valueOf(remoteDeviceIdent) + ".al", "Com-error BACnet device "
						+ String.valueOf(remoteDeviceIdent), "ELEC", "CRIT");
	}

	public String toString() {
		return String.valueOf(this.remoteDeviceIdent);
	}
	
	//Call this function every loop in your main application
	public void run() throws InterruptedException {
		//Setup event handler
		if (firstRun) {
			localDevice.getEventHandler().addListener(this);
			Thread.sleep(5000); // We might be able to remove this at a later point
			firstRun = false;
			timeStampStarted = System.currentTimeMillis();
		} else {
			if (timeStampStarted < System.currentTimeMillis() - PDB_POLL_START_DELAY) {
				pollEnable = true;
			}
		}
		//Start or renew subscription
		if ((timeStampLastSubscription < System.currentTimeMillis() - 3*SUBSCRIPTION_DURATION) & subscriptionCount > 0) {
			logger.warn("Subscription expired: {}, {}, {}", String.valueOf(remoteDeviceIdent), (System.currentTimeMillis() - timeStampLastSubscription)/1000, SUBSCRIPTION_DURATION);
		}
		if (timeStampLastSubscription < System.currentTimeMillis() - SUBSCRIPTION_DURATION) {
			logger.trace("PM: RemoteDevice: {}, Time since last subscription: {} seconds, Duration: {}", String.valueOf(remoteDeviceIdent), (System.currentTimeMillis() - timeStampLastSubscription)/1000, SUBSCRIPTION_DURATION);
			Subscribe();
			
		}
	
		addComErrorTime(10);
		comError.set(getComError());
		if (pollEnable && (timeStampLastPdbPoll < System.currentTimeMillis() - PDB_POLL_INTERVAL)) {
			CheckForUpdateFromPdb();
		}
		
	}
	
	//Start subscription
	public void Subscribe() throws InterruptedException {
		if (localDevice != null && remoteDevice != null) {
			for (PointConnnectionContainer point : points) {
				Thread.sleep(20);
				localDevice.send(remoteDevice, new SubscribeCOVRequest(new UnsignedInteger(1), point.bacnetPoint, Boolean.FALSE, new UnsignedInteger(3*SUBSCRIPTION_DURATION/1000)));   
				}
			timeStampLastSubscription = System.currentTimeMillis();
			subscriptionCount++;
		}
	}
	
	//End subscription
	public void unSubscribe() throws InterruptedException {
		if (localDevice != null && remoteDevice != null) {
			for (PointConnnectionContainer point : points) {
				Thread.sleep(20);
				localDevice.send(remoteDevice, new SubscribeCOVRequest(new UnsignedInteger(1), point.bacnetPoint, null, null));   
			}
		logger.info("PM: {}, Ending Subscription", String.valueOf(remoteDeviceIdent));
		}
	}
	
	//Update from pdb
	public void CheckForUpdateFromPdb() {
		for (PointConnnectionContainer point : points) {
			if (!point.isReadOnly() && !(point.getPdbPointValue().equals(point.getPdbLastValue()))) {
				
				//TODO: Write new value to BACnet device
				if (point.bacnetPoint.getObjectType().equals(ObjectType.analogOutput) || point.bacnetPoint.getObjectType().equals(ObjectType.analogValue) || point.bacnetPoint.getObjectType().equals(ObjectType.multiStateValue) || point.bacnetPoint.getObjectType().equals(ObjectType.multiStateOutput)) {
					WritePropertyRequest request = new WritePropertyRequest(point.bacnetPoint, PropertyIdentifier.presentValue, null, new Real((float)Double.parseDouble(point.getPdbPointValue())),new UnsignedInteger(8));
					if (point.bacnetPoint.getObjectType().equals(ObjectType.multiStateValue) || point.bacnetPoint.getObjectType().equals(ObjectType.multiStateOutput)) {
						request = new WritePropertyRequest(point.bacnetPoint, PropertyIdentifier.presentValue, null, new UnsignedInteger((int)Double.parseDouble(point.getPdbPointValue())),new UnsignedInteger(8));
					}else {//Scaler
						double v = Double.parseDouble(point.getScaledPdbPointValue());
						//System.err.println("Updating analog: " + point.bacnetPoint.toString() +" with scaled value: " + v);
						request = new WritePropertyRequest(point.bacnetPoint, PropertyIdentifier.presentValue, null, new Real((float)v),new UnsignedInteger(8));
					}
					localDevice.send(remoteDevice, request);
					point.setPdbLastValue(Double.parseDouble(point.getPdbPointValue()));
				//Binary values
				} else if (point.bacnetPoint.getObjectType().equals(ObjectType.binaryOutput) || point.bacnetPoint.getObjectType().equals(ObjectType.binaryValue) || point.bacnetPoint.getObjectType().equals(ObjectType.binaryInput)) {
					WritePropertyRequest request = new WritePropertyRequest(point.bacnetPoint, PropertyIdentifier.presentValue, null, (point.getPdbPointValue().equals("1.0") ? BinaryPV.active : BinaryPV.inactive),new UnsignedInteger(10));
					localDevice.send(remoteDevice, request);
					point.setPdbLastValue((point.getPdbPointValue().equals("1.0") ? 1.0 : 0.0));
				} else {
					logger.error("PM: {} - CheckForUpdateFromPdb, Updating of unknown BACnet type discarded: {}",
							String.valueOf(remoteDeviceIdent),
							point.bacnetPoint.toString());
				}
			}
		}
		timeStampLastPdbPoll = System.currentTimeMillis();
	}
	@Override
    public void covNotificationReceived(
    		UnsignedInteger subscriberProcessIdentifier,
    		ObjectIdentifier initiatingDeviceIdentifier,
            ObjectIdentifier monitoredObjectIdentifier,
            UnsignedInteger timeRemaining,
            SequenceOf<PropertyValue> listOfValues) {
		
    	//System.out.println("Received COV notification: " + subscriberProcessIdentifier + ": " + monitoredObjectIdentifier.toString());
		if (remoteDevice.getObjectIdentifier().equals(initiatingDeviceIdentifier) ) {
			//System.out.println("PM: " + String.valueOf(remoteDeviceIdent) + " - Received COV notification: " + subscriberProcessIdentifier + ": " + monitoredObjectIdentifier.toString());
			for(PropertyValue property : listOfValues) {
				if (property.getPropertyIdentifier().equals(PropertyIdentifier.presentValue)) {
					//System.out.println("Prop value: " + ((Real)property.getValue()).floatValue());
					for (PointConnnectionContainer point : points) {
						if (point.bacnetPoint.getObjectType().equals(monitoredObjectIdentifier.getObjectType())&& point.bacnetPoint.getInstanceNumber() == monitoredObjectIdentifier.getInstanceNumber() && !(point.pdbPoint instanceof PdbAlarm)) {
							//System.err.println("PM: " + String.valueOf(remoteDeviceIdent) + " - Recived update from BACnet device: " + initiatingDeviceIdentifier.getInstanceNumber() + " Obj: " + monitoredObjectIdentifier.toString() + " Updating pdbPoint: " + point.pdbPoint.toString() + " Bacnet Value: " + property.getValue().toString());
							point.updateFromBacnet(monitoredObjectIdentifier, property);
							//System.err.println("PM: " + String.valueOf(remoteDeviceIdent) + " - Recived update from BACnet device: " + initiatingDeviceIdentifier.getInstanceNumber() + " Obj: " + monitoredObjectIdentifier.toString() + " Updating pdbPoint: " + point.pdbPoint.toString() + " Value: " + (String)point.getPdbPointValue());
							this.resetComErrorTimer();
						}
					}
				//COV for status flags = Alarm from objects
				} else if (property.getPropertyIdentifier().equals(PropertyIdentifier.statusFlags)){
					//System.out.println("PM: " + String.valueOf(remoteDeviceIdent) + " - Received COV status flag notification: " + subscriberProcessIdentifier + ": " + monitoredObjectIdentifier.toString() + " Value: " + property.getValue().toString());
					for (PointConnnectionContainer point : points) {
						if (point.bacnetPoint.getObjectType().equals(monitoredObjectIdentifier.getObjectType())&& point.bacnetPoint.getInstanceNumber() == monitoredObjectIdentifier.getInstanceNumber() && (point.pdbPoint instanceof PdbAlarm)) {
							point.updateFromBacnet(monitoredObjectIdentifier, property);
							//System.err.println("PM: " + String.valueOf(remoteDeviceIdent) + " - Recived update from BACnet device: " + initiatingDeviceIdentifier.getInstanceNumber() + " Obj: " + monitoredObjectIdentifier.toString() + " Updating pdbPoint: " + point.pdbPoint.toString() + " Value: " + property.getValue().toString());
							this.resetComErrorTimer();
						}
					}
				} else {
					System.out.println("PM: " + String.valueOf(remoteDeviceIdent) + " - Received COV notification: " + property.getPropertyIdentifier().toString());
				}
				
	    	}
		}
    }
}

