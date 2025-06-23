package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.constructed.PropertyValue;
import com.serotonin.bacnet4j.type.constructed.StatusFlags;
import com.serotonin.bacnet4j.type.enumerated.BinaryPV;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.Real;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.bacnet4j.util.RequestUtils;

import pdb.PdbBoolean;
import pdb.PdbFeedBack;
import pdb.PdbFloat;
import pdb.PdbInteger;
import pdb.PdbAlarm;

public class PointConnnectionContainer {
	public ObjectIdentifier bacnetPoint;
	public Object pdbPoint;
	private Scaler scale;//Scaler
	private double lastPdbValue = 0.0;
	private double lastBacnetValue = 0.0;
	private boolean readOnly = false;
	final static Logger logger = LoggerFactory.getLogger(PointConnnectionContainer.class);
	
	//Scaler
	private class NoScaler implements Scaler {
		public double inboundScale(double in) {
			return in;
		}

		public double outboundScale(double out) {
			return out;
		}
	}
	
	public PointConnnectionContainer(Object pdbPoint, ObjectIdentifier bacnetPoint) {
		this.pdbPoint = pdbPoint;
		this.bacnetPoint = bacnetPoint;
		this.scale = new NoScaler();//Scaler
	}
	//Scaler
	public PointConnnectionContainer(Object pdbPoint, ObjectIdentifier bacnetPoint, Scaler scale) {
		this.pdbPoint = pdbPoint;
		this.bacnetPoint = bacnetPoint;
		this.scale = scale;
	}
	
	//Scaler Plus read only
	public PointConnnectionContainer(Object pdbPoint, ObjectIdentifier bacnetPoint, Scaler scale, boolean readOnly) {
		this.pdbPoint = pdbPoint;
		this.bacnetPoint = bacnetPoint;
		this.scale = scale;
		this.readOnly = readOnly;	
	}
	//Avoid writing to values like outputs that are analog output.
		public PointConnnectionContainer(Object pdbPoint, ObjectIdentifier bacnetPoint, boolean readOnly) {
			this.pdbPoint = pdbPoint;
			this.bacnetPoint = bacnetPoint;
			this.readOnly = readOnly;
			this.scale = new NoScaler();//Scaler
		}
	
	//Update pdb with actual value
	public void updatePdbPoint(double value) {
		value = this.scale.inboundScale(value);//Scaler
		if (pdbPoint instanceof PdbFloat) {
			((PdbFloat)this.pdbPoint).set(value);
			lastPdbValue = value;
		} else if (pdbPoint instanceof PdbInteger) {
			PdbInteger PdbTemp = (PdbInteger) this.pdbPoint;
			PdbTemp.set((int)Math.round(value));
			lastPdbValue = Math.round(value);
		} else if (pdbPoint instanceof PdbAlarm){
			((PdbAlarm) this.pdbPoint).set(Math.round(value) != 0.0 ? true : false);
			lastPdbValue = Math.round(value) != 0.0 ? 1.0 : 0.0;
		} else if (pdbPoint instanceof PdbFeedBack){
			((PdbFeedBack) this.pdbPoint).set(Math.round(value) != 0.0 ? true : false);
			lastPdbValue = Math.round(value) != 0.0 ? 1.0 : 0.0;
		} else if (pdbPoint instanceof PdbBoolean){
			((PdbBoolean) this.pdbPoint).set(Math.round(value) != 0.0 ? true : false);
			lastPdbValue = Math.round(value) != 0.0 ? 1.0 : 0.0;
		} else {
		//ToDo: Throw Ex
			logger.error("PCC: PointConnnectionContainer, updatePdbPoint: Unknown pdb point type");
		}
	}
	
	public boolean isReadOnly() {
		return this.readOnly;
	}

	public String getPdbLastValue() {
		return String.valueOf(lastPdbValue);
	}
	
	public String getBACnetLastValue() {
		return String.valueOf(lastBacnetValue);
	}
	
	public void setPdbLastValue(double value) {
		this.lastPdbValue = value;
	}
	
	//Scaler
	public String getScaledPdbPointValue() {
		if (pdbPoint instanceof PdbFloat) {
			double value = this.scale.outboundScale(((PdbFloat) this.pdbPoint).get());
			return String.valueOf(value);
		} else if (pdbPoint instanceof PdbInteger) {
			double value = this.scale.outboundScale((double) ((PdbInteger) this.pdbPoint).get());
			return String.valueOf(value);
		}
		
		return this.getPdbPointValue(); // Default (value not scalable)
	}
	public String getPdbPointValue() {
		if (pdbPoint instanceof PdbFloat) {
			return String.valueOf(((PdbFloat) this.pdbPoint).get());
		} else if (pdbPoint instanceof PdbInteger) {
			return String.valueOf((double)((PdbInteger) this.pdbPoint).get());
		} else if (pdbPoint instanceof PdbAlarm){
			return String.valueOf(((PdbAlarm) this.pdbPoint).get()? 1.0 : 0.0);
		} else if (pdbPoint instanceof PdbBoolean){
			return String.valueOf(((PdbBoolean) this.pdbPoint).get()? 1.0 : 0.0);
		} else {
			logger.error("PCC: PointConnnectionContainer, getPdbPointValue: Unknown pdb point type");
			return "";
		}
		
	}
	//Update from Bacnet
	public void updateFromBacnet(ObjectIdentifier bacnetPoint, PropertyValue property) {
		if (property.getValue() instanceof Real) {
			this.lastBacnetValue = ((Real)property.getValue()).floatValue();
			updatePdbPoint(((Real)property.getValue()).floatValue());
		}
		else if (property.getValue() instanceof BinaryPV) {
			BinaryPV binaryPv = BinaryPV.forId(1);
			this.lastBacnetValue = (property.getValue().toString() == binaryPv.toString()) ? 1.0 : 0.0;
			updatePdbPoint(property.getValue().toString() == binaryPv.toString() ? 1.0 : 0.0);
		}
		else if (property.getValue() instanceof UnsignedInteger) {
			this.lastBacnetValue = (double)((UnsignedInteger)property.getValue()).longValue();
			updatePdbPoint((double)((UnsignedInteger)property.getValue()).longValue());
		}
		else {
			if (property.getValue() instanceof StatusFlags) {
				//System.err.println("PCC: Yes, status flags :)");
				StatusFlags statusflags = new StatusFlags(false,false,false,false);
				if (property.getValue().toString().equals(statusflags.toString())) {
					updatePdbPoint(0.0);
				} else {
					updatePdbPoint(1.0);
				}
			} else {
				logger.error("PCC: PointConnnectionContainer, updateFromBacnet: Unknown BACnet primitive (value type): {}", property.getValue().getClass().toString());
			}
		}
	}
	
	public void readBacnetPoint(LocalDevice localDevice, RemoteDevice remoteDevice) throws Exception {
		Encodable value = RequestUtils.getProperty(localDevice, remoteDevice, this.bacnetPoint, PropertyIdentifier.presentValue);
		try {
			lastBacnetValue = Double.parseDouble(value.toString());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			logger.error("PCC: BacNet getProperty presentValue: String not convertable to double");
		}	
	}
	public String toString() {
		return pdbPoint.getClass().toString() + bacnetPoint.toString();
	}

}
