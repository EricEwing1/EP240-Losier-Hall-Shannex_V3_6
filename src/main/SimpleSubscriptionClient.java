package main;
// EP240Losier Hall Version 3_6

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.npdu.ip.IpNetwork;
import com.serotonin.bacnet4j.npdu.ip.IpNetworkBuilder;
import com.serotonin.bacnet4j.npdu.ip.IpNetworkUtils;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.service.confirmed.ConfirmedRequestService;
import com.serotonin.bacnet4j.service.unconfirmed.WhoIsRequest;
import com.serotonin.bacnet4j.transport.DefaultTransport;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.util.DiscoveryUtils;
import com.serotonin.bacnet4j.util.PropertyReferences;
import com.serotonin.bacnet4j.util.PropertyValues;
import com.serotonin.bacnet4j.util.RemoteDeviceDiscoverer;
import com.serotonin.bacnet4j.util.RequestUtils;
import pointconfig.*;
import pdb.PDBa;
import pdb.PDBf;
import pdb.PDBi;
import pdb.Pdb;
import pdb.PdbInterface;
//import pdb.mock.PdbMock;

public class SimpleSubscriptionClient {
	final static Logger logger = LoggerFactory.getLogger(SimpleSubscriptionClient.class);
	public static void main(String[] args) throws Exception {
		
		final List<RemoteDevice> allDevices = new ArrayList<>();
	    final Queue<RemoteDevice> latestDevices = new LinkedList<>();
	    PdbInterface pdb = new Pdb("/usr/local/bin/modbus");
		//PdbInterface pdb = new PdbMock();
	    
    	
    	LocalDevice localDevice = null;
    	final ArrayList<PointManager> deviceManagers = new ArrayList<>();
    	//Add devices here by entering the BACnet device ID
    	deviceManagers.add(new PointManager(2731, pdb, ddc01.points(pdb)));//
    	//deviceManagers.add(new PointManager(10200, pdb, ddc02.points(pdb)));//
        //deviceManagers.add(new PointManager(10300, pdb, ddc03.points(pdb)));//

    	logger.info("Starting");
    	long timeLastDeviceConfigured = 0;
    	try {
    		
    		IpNetworkBuilder networkBuilder = new IpNetworkBuilder();
    		networkBuilder
    			.withSubnet("172.24.75.0", 24)//LAN, Network Address
    			//.withSubnet("10.14.8.0", 24)//WAN, Network Address
    			.withPort(0xBAC1)//BACnet port
    			.withLocalNetworkNumber(2);//BACnet network number
    		IpNetwork network = networkBuilder.build(); 
    		
    		System.err.println("Local device configured");
    		localDevice = new LocalDevice(12372, new DefaultTransport(network));//WDC id
    		logger.info("Local device created");
    		
    		localDevice.initialize();
    		logger.info("Local device initialized");
    		
    		//Start discovery
    		localDevice.startRemoteDeviceDiscovery();
    		logger.info("Sent discovery will wait 60seconds for respones");
    		Thread.sleep(60000);
    		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

	//Floating Output Calculations - designed for any AHU that with binary/multistate status for heating/cooling/heat recovery outputs (ex, staged gas heating, DX cooling). 
	//Converts digital values to floating values so Ecopilot can better understand what the unit is doing and control accordingly


	//Point Declaration 
    		
	PDBf ERV06CLG = new PDBf("erv06.suptempcont01.out1","Cooling Output","%");
	PDBf ERV06HRC = new PDBf("erv06.suptempcont01.out2","Heat Recovery Signal","%");
	PDBf ERV07CLG = new PDBf("erv07.suptempcont01.out1","Cooling Output","%");
	PDBf ERV07HRC = new PDBf("erv07.suptempcont01.out2","Heat Recovery Signal","%");

	//Cooling PID Calculation


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
/*
	//Command Reference/Override Check Alarms
	//Designed to compare Ecopilot's command (On, Off, Auto) to what the system is actually doing and send an alarm if the 



	PDBa hs01OVR = new PDBa("misc3.hs01override.al","HS01 Manual Override","HEAT","ALERT");
	PDBa cs01OVR = new PDBa("misc3.cs01override.al","CS01 Manual Override","COOL","ALERT");
	PDBa mua01OVR = new PDBa("misc3.mua01override.al","MUA01 Manual Override","VENT","ALERT");


	//Heating Manual Override Alarm - the points taken from the ddc file and logic will be different depending on what's available in the system.
	//If Eco mode = 0 AND is in winter mode AND one of the three boiler statuses = true, send an alarm ELSE IF Eco mode = 1 AND the system is in winter mode AND none of the three boiler statuses = true, send an alarm

	if (pdb.getFloat("hs01.ecomode01.cm").get() == 0 && pdb.getFloat("hs01.seasonalmode01.cm").get() == 0 && pdb.getBoolean("hs01.boiler01.di").get() || pdb.getBoolean("hs01.boiler02.di").get() || pdb.getBoolean("hs01.boiler03.di").get()) {
		Thread.sleep(100000);
		hs01OVR.set(true);
	} else if (pdb.getFloat("hs01.ecomode01.cm").get() == 1 &&  pdb.getFloat("hs01.seasonalmode01.cm").get() == 0 && !pdb.getBoolean("hs01.heatingenable01.di").get() )
	{
		;
		hs01OVR.set(true);
	} else { 
    	hs01OVR.set(false);
	}

	//Cooling Manual Override Alarm - the points taken from the ddc file and logic will be different depending on what's available in the system.
	//If Eco mode = 0 AND is in Summer mode AND chiller control status = true, send an alarm OR if Eco mode = 1 AND the system is in summer mode AND chiller control status = false , send alarm 

	if (pdb.getFloat("cs01.ecomode01.cm").get() == 0 && pdb.getFloat("hs01.seasonalmode01.cm").get() == 1 && pdb.getBoolean("cs01.chillerstatus01.di").get()) {
		Thread.sleep(100000);
		cs01OVR.set(true);
	} else if (pdb.getFloat("cs01.ecomode01.cm").get() == 1 &&  pdb.getFloat("hs01.seasonalmode01.cm").get() == 1 && !pdb.getBoolean("cs01.chillerstatus01.di").get())
	{
		;
		cs01OVR.set(true);
	} else { 
    	cs01OVR.set(false);
	}

	//MUA Manual Override Alarm
	//If Eco mode = 0 and fan power is greater than 0.8kW, send an alarm OR if Eco mode = 1 and fan power is < 0.79kW, send alarm 

	if (pdb.getFloat("mua01.ecomode01.cm").get() == 0 && pdb.getFloat("mua01.suppower01.cm").get() > 0.8) {
		Thread.sleep(100000);
		mua01OVR.set(true);
	} else if (pdb.getFloat("mua01.ecomode01.cm").get() == 1 && pdb.getFloat("mua01.suppower01.cm").get() < 0.79)
	{
		;
		mua01OVR.set(true);
	} else { 
    	mua01OVR.set(false);

*/

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 		
    		
    		/*
    		//Forecast, can be used to validate the OAT (outside air temperature) or setting up the zones before the OAT exist
    		PDBf misc_fcprec_cm = new PDBf("misc.fcprec.cm","Forecast - precipitation","mm");
            PDBf misc_fctemp_cm = new PDBf("misc.fctemp.cm","Forecast - outside temperature","C");
            PDBf misc_fcwind_cm = new PDBf("misc.fcwind.cm","Forecast - wind speed","m/s");
            PDBf misc_fchum_cm = new PDBf("misc.fchum.cm","Forecast - humidity","%rH");
            PDBf misc_fcsun_cm = new PDBf("misc.fcsun.cm","Forecast - solar radiation","W/m2");

            PDBi misc_forecastfc_area	= new PDBi("misc.forecast.fc.area","Forecast ID","",47001);
            PDBi misc_forecastfc_del	= new PDBi("misc.forecast.fc.del","Forecast del","h",0);
            PDBf misc_forecastfc_prec	= new PDBf("misc.forecast.fc.prec","Forecast prec","mm");
            PDBf misc_forecastfc_temp	= new PDBf("misc.forecast.fc.temp","Forecast temp","C");
            PDBf misc_forecastfc_wind	= new PDBf("misc.forecast.fc.wind","Forecast wind","m/s");
            PDBf misc_forecastfc_hum	= new PDBf("misc.forecast.fc.hum","Forecast hum","%rH");
            PDBf misc_forecastfc_sun	= new PDBf("misc.forecast.fc.sun","Forecast sun","W/m2");
    		
            //PDBf misc_outdoortemp01_cm = new PDBf("misc.outdoortemp01.cm","Outside air temperature","C");
            */
    		
    		//Debug
    	    long loopCounter = 0;
    	    long startTime = 0;
    	    long lastDiscoverTime = 0;
    	    
        	while(true) {
        		
        		loopCounter++;
        		startTime = System.currentTimeMillis();
        		/*
        		//Forecast
            	misc_fcprec_cm.set(misc_forecastfc_prec.get());
            	misc_fctemp_cm.set(misc_forecastfc_temp.get());
            	misc_fcwind_cm.set(misc_forecastfc_wind.get());
            	misc_fchum_cm.set(misc_forecastfc_hum.get());
            	misc_fcsun_cm.set(misc_forecastfc_sun.get());
            	
            	//misc_outdoortemp01_cm.set(misc_forecastfc_temp.get());
            	*/
        		
        		
        		/*
        		 * Modified, changed criteria for sending who-is
        		 * Sending Who-is every 10 minutes and the first 4 loop cycles
        		 */
        		if (loopCounter < 5 || (startTime - lastDiscoverTime) > 10*60*1000) {
        		//if (loopCounter < 5 | loopCounter % 5 == 0) {
        			localDevice.sendGlobalBroadcast(new WhoIsRequest());
        			logger.info("Sending new WhoIs request");
        			lastDiscoverTime = startTime;
        			//Thread.sleep(15000);
        		}
        		
        		//Fetch discovered devices
        		for (RemoteDevice rd : localDevice.getRemoteDevices()) {
        
        			if (!allDevices.contains(rd) && deviceManagers.stream().anyMatch((pm) -> pm.getRemoteDeviceIdentifier() == rd.getInstanceNumber())) {
        				//Add to device list
        				allDevices.add(rd);
						//Add to latest discovered
        				latestDevices.add(rd);
        				logger.info("Found new device {}", rd.getInstanceNumber());
					}
				}

        		//Pop device from latest discovered
        		while (!latestDevices.isEmpty()) {
        			//Pop device from the discovery list

        			RemoteDevice rd = latestDevices.poll();
        			logger.info("Found new device {}", rd.toString());
        			// Prepare device
        			localDevice.getRemoteDeviceBlocking(rd.getInstanceNumber());
        			//Get extended info
        			boolean success = false;
        			try {
        				DiscoveryUtils.getExtendedDeviceInformation(localDevice, rd);
        				Thread.sleep(5000);
        				//Get device object list
        				final PropertyReferences propRefs = new PropertyReferences() //
        	                    .add(rd.getObjectIdentifier(), PropertyIdentifier.objectList);
        				final PropertyValues actualValues = RequestUtils.readProperties(localDevice, rd, propRefs, false, null);
        				logger.trace("Device object {} list count: {}", rd.getInstanceNumber(), actualValues.toString().split(",").length);
        				success = true;
					} catch (BACnetException e) {
						success = false;
					}
        			
        			if (success) {
        				logger.info("Successfully identified device: {}", rd.getInstanceNumber());
        				for (PointManager pointManager : deviceManagers) {
							if (!pointManager.isConfigured() && pointManager.getRemoteDeviceIdentifier() == rd.getInstanceNumber()) {
								pointManager.setLocalDevice(localDevice);
								pointManager.setRemoteDevice(rd);
								if (pointManager.isConfigured()) {
									timeLastDeviceConfigured = System.currentTimeMillis();
									logger.info("Paired device: {} with pointManger: {}", rd.getInstanceNumber(), pointManager.toString());
									pointManager.run();
								} else {
									logger.error("Failed to pair device: {} with pointManger: {}", rd.getInstanceNumber(), pointManager.toString());
								}
								
								
							}
						}
        			} else {
        				// Check if unreachable device in our list of interest, if so reschedule it
        				if (deviceManagers.stream().anyMatch((pm) -> pm.getRemoteDeviceIdentifier() == rd.getInstanceNumber() )) {
        					latestDevices.add(rd);
        					logger.debug("Could not add device: {} Readded for retry", rd.getInstanceNumber());
        				} else {
        					logger.debug("Could not add device: {} Discarded because not in list of devices of intrerest",
        							rd.getInstanceNumber());
        				}
        			}
        		}
        		/*
        		 * Modified, removed thread sleep
        		 */
        		for (PointManager pointManager : deviceManagers) {
        			if (pointManager.isConfigured()) {
            			pointManager.run();
            			//Thread.sleep(2500);
            			//ERV06	
            			if (pdb.getBoolean("erv06.coolstage01.di").get() == false && pdb.getBoolean("erv06.coolstage02.di").get() == false) {
            				Thread.sleep(600);
            				ERV06CLG.set(0);
            			} else if (pdb.getBoolean("erv06.coolstage01.di").get() == true ^ pdb.getBoolean("erv06.coolstage02.di").get() == true)
            			{
            				;
            				ERV06CLG.set(50);
            			} else if (pdb.getBoolean("erv06.coolstage01.di").get() == true && pdb.getBoolean("erv06.coolstage02.di").get() == true) { 
            				ERV06CLG.set(100);
            			}

            			//Heat Recovery PID Calculation

            			if (pdb.getBoolean("erv06.coolstage01.di").get() == true || (pdb.getBoolean("erv06.coolstage02.di").get() == true)) 
            			{
            				Thread.sleep(600);
            				ERV06HRC.set(0);
            			} else 
            			{
            				;
            				pdb.getFloat("erv06.suptempcont01.out2").set(pdb.getFloat("erv06.heatwheelspeed01.cm").get());  
            				
            			}
            		//ERV7	
            			//Cooling PID Calculation
            				//If no stages are on, PID = 0. If one stage is on, PID = 50, if both are on, PID = 100. 
            				if (pdb.getBoolean("erv07.coolstage01.di").get() == false && pdb.getBoolean("erv07.coolstage02.di").get() == false) {
            					//Thread.sleep(600);
            					ERV07CLG.set(0);
            				} else if (pdb.getBoolean("erv07.coolstage01.di").get() == true ^ pdb.getBoolean("erv07.coolstage02.di").get() == true)
            				{
            					;
            					ERV07CLG.set(50);
            				} else if (pdb.getBoolean("erv07.coolstage01.di").get() == true && pdb.getBoolean("erv07.coolstage02.di").get() == true) { 
            					ERV07CLG.set(100);
            				}


            				//Heat Recovery PID Calculation
            				//Assuming system is always in heat recovery mode. If supply fan is off, PID = 0. If supply fan is on, PID = 100. 
            				if (pdb.getBoolean("erv07.coolstage01.di").get() == true || (pdb.getBoolean("erv07.coolstage02.di").get() == true)) 
            				{
            					//Thread.sleep(600);
            					ERV07HRC.set(0);
            				} else 
            				{
            					;
            					pdb.getFloat("erv07.suptempcont01.out2").set(pdb.getFloat("erv07.heatwheelspeed01.cm").get());  
            					
            				}
          			
            			
        			}
				}
        		
        		final ArrayList<PointManager> unConfiguredDevices = deviceManagers
        				.stream()
        				.filter(pm -> !pm.isConfigured())
        				.collect(Collectors.toCollection(ArrayList::new));
        		
        		logger.info("Unconfigured devices: {} Count: {}",
        				unConfiguredDevices.size() > 0 ? unConfiguredDevices.stream().map((pm)-> pm.getRemoteDeviceIdentifier() + ", ") : "none",
        				unConfiguredDevices.size()
        		);
        		
        		if (loopCounter % 1 == 0 ) {
        			/*
            		 * Modified, Changed logger from info to debug
            		 */
        			logger.debug("Loopcounter: {}, LoopTime: {}, Number of devices {}",
        					loopCounter,
        					(System.currentTimeMillis() - startTime),
        					deviceManagers
            					.stream()
            					.filter(pm -> pm.isConfigured())
            					.collect(Collectors.toCollection(ArrayList::new))
            					.size()
            		);
        		}
        		
        		/*
        		 * Modified, added loop time sleep
        		 */
        		Thread.sleep(1000);//Loop time
        	}
        }
    	
    	catch (Exception e) {
                	System.err.println("Exception caught in main:");
                	e.printStackTrace();
        }
        catch(Throwable e) {
        	System.err.println("Throwable caught in main:");
            e.printStackTrace();
        }
        finally {
        	if (localDevice != null)
        		// Unsubscribe
        		for (PointManager pM : deviceManagers) {
        			if (pM.isConfigured()) {
        				pM.unSubscribe();
				}
                localDevice.terminate();
                }
        }
    }
    public static AcknowledgementService send(LocalDevice d, ConfirmedRequestService s) throws Exception {
        Address a = IpNetworkUtils.toAddress("localhost", 0xbac0);
        return d.send(a, s).get();
    }
}