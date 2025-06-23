package pointconfig;
// 1 Adelaide_20 Victoria V 3.3
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;

import main.PointConnnectionContainer;
import pdb.PdbException;
import pdb.PdbInterface;

//import main.FahrenheitCelsiusScaler;
//import main.OffsetFahrenheitCelsiusScaler;
//import main.Scaler;
import pdb.MultiStateTranslation;
import pdb.MultiStateValue;
import pdb.PDBb;


public class ddc01 {

public static List<PointConnnectionContainer> points(PdbInterface pdb) throws PdbException {
	List<PointConnnectionContainer> pointList = new ArrayList<PointConnnectionContainer>();
	
	
	MultiStateTranslation msTranslation1 =  new MultiStateTranslation(Arrays.asList(
			new MultiStateValue(999, "Auto", 2, "Auto"),
			new MultiStateValue(0, "Off", 0, "Off"),
			new MultiStateValue(1, "On", 1, "On")
	));

	
	/*
	MultiStateTranslation msTranslation2 =  new MultiStateTranslation(Arrays.asList(
			new MultiStateValue(0, "Off", 1, "Off"),
			new MultiStateValue(1, "On", 2, "On")
	));
	*/
	
	/*
	MultiStateTranslation msTranslation3 =  new MultiStateTranslation(Arrays.asList(
			new MultiStateValue(2, "Pump2", 4, "P2"),
			new MultiStateValue(1, "Pump1", 3, "P1"),
			new MultiStateValue(0, "Off", 2, "Off"),
			new MultiStateValue(999, "Auto", 1, "Automatic")
	));
	*/
	
	
	
	//PDBb system = new PDBb("system", "System, Description");
	// Device 2371
	//BACnet points 
	// Outside Air Temp
	pointList.add(new PointConnnectionContainer(pdb.createFloat("met.outdoortemp01.cm","Outdoor Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 49),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("met.heatlockspt01.cm","OATHeat Lockout Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 60),true));
    
	// Main Boiler Plant
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("hs01.heatpump01.osw","Heating Control Enable Point ",msTranslation1), new ObjectIdentifier(ObjectType.analogValue, 400)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.ecomode.cm","Eco Mode ","0=OFF 1=ON"), new ObjectIdentifier(ObjectType.analogValue, 400),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suptempcont01.sp","Existing Heating Supply Water BAS Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 401),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suptempcont01.ms","Main Supply Water Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 402),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.rettemp01.cm","Main Return Water Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 409),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.infsuptemp01.cm","Infloor Supply Water Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 433),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.ifrettemp01.cm","Infloor Return Water Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 434),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suptempcont01.mn","Supply Temp Minimum Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 403)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suptempcont01.mx","Supply Temp Maximum Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 404)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suptempcont01.dsp","Ecopilot Offset Supply Temp SP ","C"), new ObjectIdentifier(ObjectType.analogValue, 405)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suptempcont01.csp","New Supply Temp w/ Ecopilot Offset ","C"), new ObjectIdentifier(ObjectType.analogValue, 406),true));
	//pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suptempcont01.out3","Total Heating PID Output ","%"), new ObjectIdentifier(ObjectType.analogValue, 407),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.heatlockout01.di","Heating Lockout Mode "), new ObjectIdentifier(ObjectType.binaryValue, 408),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.boiler01.di","Heating Boiler 1 Status "), new ObjectIdentifier(ObjectType.binaryValue, 410),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.boiler01.al","Heating Boiler 1 Alarm "), new ObjectIdentifier(ObjectType.binaryValue, 411),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.boiler01.out","Heating Boiler 1 Modulation ","%"), new ObjectIdentifier(ObjectType.analogValue, 412),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.heatpump01.di","Heating Boiler 1 Pump Status "), new ObjectIdentifier(ObjectType.binaryValue, 413),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.boilersuptemp01.cm","Heating Boiler 1 Supply Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 414),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.boiler02.di","Heating Boiler 2 Status "), new ObjectIdentifier(ObjectType.binaryValue, 415),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.boiler02.al","Heating Boiler 2 Alarm "), new ObjectIdentifier(ObjectType.binaryValue, 416),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.boiler02.out","Heating Boiler 2 Modulation ","%"), new ObjectIdentifier(ObjectType.analogValue, 417),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.heatpump02.di","Heating Boiler 2 Pump Status "), new ObjectIdentifier(ObjectType.binaryValue, 418),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.boilersuptemp02.cm","Heating Boiler 2 Supply Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 419),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.boiler03.di","Heating Boiler 3 Status "), new ObjectIdentifier(ObjectType.binaryValue, 420),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.boiler03.al","Heating Boiler 3 Alarm "), new ObjectIdentifier(ObjectType.binaryValue, 421),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.boiler03.out","Heating Boiler 3 Modulation ","%"), new ObjectIdentifier(ObjectType.analogValue, 422),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.heatpump03.di","Heating Boiler 3 Pump Status "), new ObjectIdentifier(ObjectType.binaryValue, 423),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.boilersuptemp03.cm","Heating Boiler 3 Supply Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 424),true));

	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.heatpump01.do","InFloor Heating Pump 1 Command "), new ObjectIdentifier(ObjectType.binaryValue, 425),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.heatpumpamps01.cm","Infloor Heating Pump 1 Current ","amps"), new ObjectIdentifier(ObjectType.analogValue, 426),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suppresscont01.out","InFloor Heating Pump 1 Speed ","%"), new ObjectIdentifier(ObjectType.analogValue, 427),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("hs01.heatpump02.do","InFloor Heating Pump 2 Command "), new ObjectIdentifier(ObjectType.binaryValue, 428),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.heatpumpamps02.cm","Infloor Heating Pump 2 Current ","amps"), new ObjectIdentifier(ObjectType.analogValue, 429),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suppresscont02.out","InFloor Heating Pump 2 Speed ","%"), new ObjectIdentifier(ObjectType.analogValue, 430),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suppresscont01.ms","InFloor Heating Diff. Pressure ","psi"), new ObjectIdentifier(ObjectType.analogValue, 431),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("hs01.suppresscont01.sp","InFloor Heating Diff. Pressure Setpoint ","psi"), new ObjectIdentifier(ObjectType.analogValue, 432),true));
    
	// ERV#6
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("erv06.day.tc.sw","ERV06 Control Enable Point ",msTranslation1), new ObjectIdentifier(ObjectType.analogValue, 436)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.ecomode.cm","Eco Mode","0=OFF 1=ON"), new ObjectIdentifier(ObjectType.analogValue, 436),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.suptempcont01.sp","Existing Supply Air Temp BAS Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 437),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.suptempcont01.ms","Main Supply Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 438),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.suptempcont01.mn","Supply Temp Minimum Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 439)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.suptempcont01.mx","Supply Temp Maximum Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 440)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.suptempcont01.dsp","Ecopilot Offset Supply Temp SP ","C"), new ObjectIdentifier(ObjectType.analogValue, 441)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.suptempcont01.csp","New Supply Temp w/ Ecopilot Offset ","C"), new ObjectIdentifier(ObjectType.analogValue, 442),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.oadamper01.di","Fresh/Exhaust Air Damper "), new ObjectIdentifier(ObjectType.binaryValue, 443),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.lowtemp01.al","Freeze Stat "), new ObjectIdentifier(ObjectType.binaryValue, 444),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.supfan01.do","Supply Fan  "), new ObjectIdentifier(ObjectType.binaryValue, 445),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.supfan01.cm","Supply Fan Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 446),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.supfan01.out","Supply Fan Speed ","%"), new ObjectIdentifier(ObjectType.analogValue, 447),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.supfan01.al","Supply Fan Alarm "), new ObjectIdentifier(ObjectType.binaryValue, 448),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.retfan01.do","Return Fan  "), new ObjectIdentifier(ObjectType.binaryValue, 449),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.retfan01.cm","Return Fan Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 450),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.retfan01.out","Return Fan Speed ","%"), new ObjectIdentifier(ObjectType.analogValue, 451),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.retfan01.al","Return Fan Alarm "), new ObjectIdentifier(ObjectType.binaryValue, 452),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.mechcool01.di","Mechanical Cooling Enable "), new ObjectIdentifier(ObjectType.binaryValue, 453),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.coolstage01.di","Cooling Stage 1 "), new ObjectIdentifier(ObjectType.binaryValue, 454),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.coolstage02.di","Cooling Stage 2 "), new ObjectIdentifier(ObjectType.binaryValue, 455),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.lowfire01.di","Heating Low Fire "), new ObjectIdentifier(ObjectType.binaryValue, 456),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.suptempcont01.out3","Heating Valve ","%"), new ObjectIdentifier(ObjectType.analogValue, 457),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.rettemp01.cm"," Return Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 458),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.exhtemp01.cm","Exhaust Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 459),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv06.heatwheel01.di","Heat Wheel Status "), new ObjectIdentifier(ObjectType.binaryValue, 460),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.suptempcont01.out2","Heatwheel Exhaust Speed ","%"), new ObjectIdentifier(ObjectType.analogValue, 461),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.facebypass01.out","Face and Bypass Damper ","%"), new ObjectIdentifier(ObjectType.analogValue, 462),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.rethumid01.ms","Return Air Humidity ","%"), new ObjectIdentifier(ObjectType.analogValue, 463),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.rethumid01.sp","Return Air Humidity Setpoint ","%"), new ObjectIdentifier(ObjectType.analogValue, 464),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv06.rethumid01.out","Humidifier Signal ","%"), new ObjectIdentifier(ObjectType.analogValue, 465),true));

	// ERV#7
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("erv07.day.tc.sw","ERV07 Control Enable Point ",msTranslation1), new ObjectIdentifier(ObjectType.analogValue, 466)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.ecomode.cm","Eco Mode","0=OFF 1=ON"), new ObjectIdentifier(ObjectType.analogValue, 466),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.suptempcont01.sp","Existing Supply Air Temp BAS Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 467),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.suptempcont01.ms","Main Supply Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 468),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.suptempcont01.mn","Supply Temp Minimum Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 469)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.suptempcont01.mx","Supply Temp Maximum Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 470)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.suptempcont01.dsp","Ecopilot Offset Supply Temp SP ","C"), new ObjectIdentifier(ObjectType.analogValue, 471)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.suptempcont01.csp","New Supply Temp w/ Ecopilot Offset ","C"), new ObjectIdentifier(ObjectType.analogValue, 472),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.oadamper01.di","Fresh/Exhaust Air Damper "), new ObjectIdentifier(ObjectType.binaryValue, 473),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.lowtemp01.al","Freeze Stat "), new ObjectIdentifier(ObjectType.binaryValue, 474),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.supfan01.do","Supply Fan  "), new ObjectIdentifier(ObjectType.binaryValue, 475),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.supfan01.cm","Supply Fan Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 476),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.supfan01.out","Supply Fan Speed ","%"), new ObjectIdentifier(ObjectType.analogValue, 477),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.supfan01.al","Supply Fan Alarm "), new ObjectIdentifier(ObjectType.binaryValue, 478),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.retfan01.do","Return Fan  "), new ObjectIdentifier(ObjectType.binaryValue, 479),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.retfan01.cm","Return Fan Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 480),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.retfan01.out","Return Fan Speed ","%"), new ObjectIdentifier(ObjectType.analogValue, 481),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.retfan01.al","Return Fan Alarm "), new ObjectIdentifier(ObjectType.binaryValue, 482),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.mechcool01.di","Mechanical Cooling Enable "), new ObjectIdentifier(ObjectType.binaryValue, 483),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.coolstage01.di","Cooling Stage 1 "), new ObjectIdentifier(ObjectType.binaryValue, 484),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.coolstage02.di","Cooling Stage 2 "), new ObjectIdentifier(ObjectType.binaryValue, 485),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.lowfire01.di","Heating Low Fire "), new ObjectIdentifier(ObjectType.binaryValue, 486),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.suptempcont01.out3","Heating Valve ","%"), new ObjectIdentifier(ObjectType.analogValue, 487),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.rettemp01.cm"," Return Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 488),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.exhtemp01.cm","Exhaust Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 489),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("erv07.heatwheel01.di","Heat Wheel Status "), new ObjectIdentifier(ObjectType.binaryValue, 490),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.suptempcont01.out2","Heatwheel Exhaust Speed ","%"), new ObjectIdentifier(ObjectType.analogValue, 491),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.facebypass01.out","Face and Bypass Damper ","%"), new ObjectIdentifier(ObjectType.analogValue, 492),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.rethumid01.ms","Return Air Humidity ","%"), new ObjectIdentifier(ObjectType.analogValue, 493),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.rethumid01.sp","Return Air Humidity Setpoint ","%"), new ObjectIdentifier(ObjectType.analogValue, 494),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("erv07.rethumid01.out","Humidifier Signal ","%"), new ObjectIdentifier(ObjectType.analogValue, 495),true));

    // AC Units 1 to 5
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac01.suptempcont01.sp","Existing Supply Air Temp BAS Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 500),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac01.suptempcont01.ms"," Supply Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 501),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac01.matemp01.cm","Mixed Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 502),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac01.rettemp01.cm","Return Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 503),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac01.oadamper01.out","Fresh/Exhaust Air Damper ","%"), new ObjectIdentifier(ObjectType.analogValue, 504),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac01.lowtemp01.al","Freeze Stat "), new ObjectIdentifier(ObjectType.binaryValue, 505),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac01.supfan01.do","Supply Fan  "), new ObjectIdentifier(ObjectType.binaryValue, 506),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac01.supfan01.cm","Supply Fan Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 507),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac01.mechcool01.di","Mechanical Cooling Enable "), new ObjectIdentifier(ObjectType.binaryValue, 508),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac01.revvlv01.di","Reversing Valve ON=Cool "), new ObjectIdentifier(ObjectType.binaryValue, 509),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac01.compressor01.di","Compressor 1 "), new ObjectIdentifier(ObjectType.binaryValue, 510),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac01.suptemp01.di","Heating Coil "), new ObjectIdentifier(ObjectType.binaryValue, 511),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac02.suptempcont01.sp","Existing Supply Air Temp BAS Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 520),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac02.suptempcont01.ms"," Supply Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 521),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac02.matemp01.cm","Mixed Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 522),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac02.rettemp01.cm","Return Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 523),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac02.oadamper01.out","Fresh/Exhaust Air Damper ","%"), new ObjectIdentifier(ObjectType.analogValue, 524),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac02.lowtemp01.al","Freeze Stat "), new ObjectIdentifier(ObjectType.binaryValue, 525),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac02.supfan01.do","Supply Fan  "), new ObjectIdentifier(ObjectType.binaryValue, 526),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac02.supfan01.cm","Supply Fan Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 527),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac02.mechcool01.di","Mechanical Cooling Enable "), new ObjectIdentifier(ObjectType.binaryValue, 528),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac02.revvlv01.di","Reversing Valve  ON=Cool "), new ObjectIdentifier(ObjectType.binaryValue, 529),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac02.compressor01.di","Compressor 1 "), new ObjectIdentifier(ObjectType.binaryValue, 530),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac02.compressor02.di","Compressor 2 "), new ObjectIdentifier(ObjectType.binaryValue, 532),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac02.suptemp01.di","Heating Coil "), new ObjectIdentifier(ObjectType.binaryValue, 531),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac03.suptempcont01.sp","Existing Supply Air Temp BAS Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 540),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac03.suptempcont01.ms"," Supply Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 541),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac03.matemp01.cm","Mixed Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 542),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac03.rettemp01.cm","Return Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 543),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac03.oadamper01.out","Fresh/Exhaust Air Damper ","%"), new ObjectIdentifier(ObjectType.analogValue, 544),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac03.lowtemp01.al","Freeze Stat "), new ObjectIdentifier(ObjectType.binaryValue, 545),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac03.supfan01.do","Supply Fan  "), new ObjectIdentifier(ObjectType.binaryValue, 546),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac03.supfan01.cm","Supply Fan Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 547),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac03.mechcool01.di","Mechanical Cooling Enable "), new ObjectIdentifier(ObjectType.binaryValue, 548),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac03.revvlv01.di","Reversing Valve  ON=Cool "), new ObjectIdentifier(ObjectType.binaryValue, 549),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac03.compressor01.di","Compressor 1 "), new ObjectIdentifier(ObjectType.binaryValue, 550),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac03.suptemp01.di","Heating Coil "), new ObjectIdentifier(ObjectType.binaryValue, 551),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac04.suptempcont01.sp","Existing Supply Air Temp BAS Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 560),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac04.suptempcont01.ms"," Supply Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 561),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac04.matemp01.cm","Mixed Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 562),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac04.rettemp01.cm","Return Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 563),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac04.oadamper01.out","Fresh/Exhaust Air Damper ","%"), new ObjectIdentifier(ObjectType.analogValue, 564),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac04.lowtemp01.al","Freeze Stat "), new ObjectIdentifier(ObjectType.binaryValue, 565),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac04.supfan01.do","Supply Fan  "), new ObjectIdentifier(ObjectType.binaryValue, 566),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac04.supfan01.cm","Supply Fan Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 567),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac04.mechcool01.di","Mechanical Cooling Enable "), new ObjectIdentifier(ObjectType.binaryValue, 568),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac04.revvlv01.di","Reversing Valve  ON=Cool "), new ObjectIdentifier(ObjectType.binaryValue, 569),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac04.compressor01.di","Compressor 1 "), new ObjectIdentifier(ObjectType.binaryValue, 570),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac04.suptemp01.di","Heating Coil "), new ObjectIdentifier(ObjectType.binaryValue, 571),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac05.suptempcont01.sp","Existing Supply Air Temp BAS Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 580),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac05.suptempcont01.ms"," Supply Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 581),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac05.matemp01.cm","Mixed Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 582),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac05.rettemp01.cm","Return Air Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 583),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac05.oadamper01.out","Fresh/Exhaust Air Damper ","%"), new ObjectIdentifier(ObjectType.analogValue, 584),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac05.lowtemp01.al","Freeze Stat "), new ObjectIdentifier(ObjectType.binaryValue, 585),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac05.supfan01.do","Supply Fan  "), new ObjectIdentifier(ObjectType.binaryValue, 586),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("ac05.supfan01.cm","Supply Fan Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 587),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac05.mechcool01.di","Mechanical Cooling Enable "), new ObjectIdentifier(ObjectType.binaryValue, 588),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac05.revvlv01.di","Reversing Valve  ON=Cool "), new ObjectIdentifier(ObjectType.binaryValue, 589),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac05.compressor01.di","Compressor 1 "), new ObjectIdentifier(ObjectType.binaryValue, 590),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("ac05.suptemp01.di","Heating Coil "), new ObjectIdentifier(ObjectType.binaryValue, 591),true));

    // Fans
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("sf02.supfan01.do","Supply Fan 2 "), new ObjectIdentifier(ObjectType.binaryValue, 600),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sf02.supfan01.cm","Supply Fan 2 Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 601),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("sf04.supfan01.do","Supply Fan 4 "), new ObjectIdentifier(ObjectType.binaryValue, 602),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sf04.supfan01.cm","Supply Fan 4 Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 603),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("sf05.supfan01.do","Wing Booster Supply Fan 5 "), new ObjectIdentifier(ObjectType.binaryValue, 604),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sf05.supfan01.cm","Wing Booster Supply Fan 5 Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 605),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("sf06.supfan01.do","Supply Fan 6 "), new ObjectIdentifier(ObjectType.binaryValue, 606),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sf06.supfan01.cm","Supply Fan 6 Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 607),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("sf07.supfan01.do","Wing Booster Supply Fan 7 "), new ObjectIdentifier(ObjectType.binaryValue, 608),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sf07.supfan01.cm","Wing Booster Supply Fan 7 Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 609),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("sf08.supfan01.do","Wing Booster Supply Fan 8 "), new ObjectIdentifier(ObjectType.binaryValue, 610),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sf08.supfan01.cm","Wing Booster Supply Fan 8 Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 611),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("sf09.supfan01.do","Supply Fan 9 "), new ObjectIdentifier(ObjectType.binaryValue, 612),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sf09.supfan01.cm","Supply Fan 9 Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 613),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("sf10.supfan01.do","Wing Booster Supply Fan 10 "), new ObjectIdentifier(ObjectType.binaryValue, 614),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sf10.supfan01.cm","Wing Booster Supply Fan 10 Status ","amp"), new ObjectIdentifier(ObjectType.analogValue, 615),true));
 
	// DHW
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.suptemp01.sp","DHW Supply Water BAS Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 620),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.suptemp01.ms","DHW Supply Water Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 621),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("dhw01.boiler04.di","DHW Boiler 4 Status "), new ObjectIdentifier(ObjectType.binaryValue, 622),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.boiler04.out","DHW Boiler 4 Modulation ","%"), new ObjectIdentifier(ObjectType.analogValue, 623),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("dhw01.boiler04.al","DHW Boiler 4 Alarm "), new ObjectIdentifier(ObjectType.binaryValue, 624),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.heatpump04.cm","DHW Pump 4 Status ","amps"), new ObjectIdentifier(ObjectType.analogValue, 625),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.boilersuptemp04.cm","DHW Boiler 4 Water Supply Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 626),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.boilervlvmode04.di","DHW Boiler 4 Divirt Valve Mode ","0=DHW 1=Heat"), new ObjectIdentifier(ObjectType.analogValue, 627),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("dhw01.boiler05.di","DHW Boiler 5 Status "), new ObjectIdentifier(ObjectType.binaryValue, 628),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.boiler05.out","DHW Boiler 5 Modulation ","%"), new ObjectIdentifier(ObjectType.analogValue, 629),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("dhw01.boiler05.al","DHW Boiler 5 Alarm "), new ObjectIdentifier(ObjectType.binaryValue, 630),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.heatpump05.cm","DHW Pump 5 Status ","amps"), new ObjectIdentifier(ObjectType.analogValue, 631),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.boilersuptemp05.cm","DHW Boiler 5 Water Supply Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 632),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.boilervlvmode05.di","DHW Boiler 5 Divirt Valve Mode ","0=DHW 1=Heat"), new ObjectIdentifier(ObjectType.analogValue, 633),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.dhwheatpump01.sp","DHW Heatpump 1 Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 634),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.dhwheatpump01.ms","DHW Heatpump 1 Temp. ","C"), new ObjectIdentifier(ObjectType.analogValue, 635),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.dhwheatpump02.sp","DHW Heatpump 2 Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 636),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.dhwheatpump02.ms","DHW Heatpump 2 Temp. ","C"), new ObjectIdentifier(ObjectType.analogValue, 637),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.dhwheatpump03.sp","DHW Heatpump 3 Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 638),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.dhwheatpump03.ms","DHW Heatpump 3 Temp. ","C"), new ObjectIdentifier(ObjectType.analogValue, 639),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.dhwheatpump04.sp","DHW Heatpump 4 Setpoint ","C"), new ObjectIdentifier(ObjectType.analogValue, 640),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("dhw01.dhwheatpump04.ms","DHW Heatpump 4 Temp. ","C"), new ObjectIdentifier(ObjectType.analogValue, 641),true));

    // Rooms
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1001.cm","Room 1001 ","C"), new ObjectIdentifier(ObjectType.analogValue, 701),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1002.cm","Room 1002 ","C"), new ObjectIdentifier(ObjectType.analogValue, 702),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1003.cm","Room 1003 ","C"), new ObjectIdentifier(ObjectType.analogValue, 703),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1004.cm","Room 1004 ","C"), new ObjectIdentifier(ObjectType.analogValue, 704),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1005.cm","Room 1005 ","C"), new ObjectIdentifier(ObjectType.analogValue, 705),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1006.cm","Room 1006 ","C"), new ObjectIdentifier(ObjectType.analogValue, 706),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1007.cm","Room 1007 ","C"), new ObjectIdentifier(ObjectType.analogValue, 707),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1008.cm","Room 1008 ","C"), new ObjectIdentifier(ObjectType.analogValue, 708),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1009.cm","Room 1009 ","C"), new ObjectIdentifier(ObjectType.analogValue, 709),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1010.cm","Room 1010 ","C"), new ObjectIdentifier(ObjectType.analogValue, 710),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1011.cm","Room 1011 ","C"), new ObjectIdentifier(ObjectType.analogValue, 711),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1012.cm","Room 1012 ","C"), new ObjectIdentifier(ObjectType.analogValue, 712),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1013.cm","Room 1013 ","C"), new ObjectIdentifier(ObjectType.analogValue, 713),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1014.cm","Room 1014 ","C"), new ObjectIdentifier(ObjectType.analogValue, 714),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1015.cm","Room 1015 ","C"), new ObjectIdentifier(ObjectType.analogValue, 715),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1016.cm","Room 1016 ","C"), new ObjectIdentifier(ObjectType.analogValue, 716),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1017.cm","Room 1017 ","C"), new ObjectIdentifier(ObjectType.analogValue, 717),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1018.cm","Room 1018 ","C"), new ObjectIdentifier(ObjectType.analogValue, 718),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1019.cm","Room 1019 ","C"), new ObjectIdentifier(ObjectType.analogValue, 719),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1020.cm","Room 1020 ","C"), new ObjectIdentifier(ObjectType.analogValue, 720),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl01.roomtemp1021.cm","Room 1021 ","C"), new ObjectIdentifier(ObjectType.analogValue, 721),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1022.cm","Room 1022 ","C"), new ObjectIdentifier(ObjectType.analogValue, 722),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1023.cm","Room 1023 ","C"), new ObjectIdentifier(ObjectType.analogValue, 723),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1024.cm","Room 1024 ","C"), new ObjectIdentifier(ObjectType.analogValue, 724),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1025.cm","Room 1025 ","C"), new ObjectIdentifier(ObjectType.analogValue, 725),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1026.cm","Room 1026 ","C"), new ObjectIdentifier(ObjectType.analogValue, 726),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1027.cm","Room 1027 ","C"), new ObjectIdentifier(ObjectType.analogValue, 727),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1028.cm","Room 1028 ","C"), new ObjectIdentifier(ObjectType.analogValue, 728),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1029.cm","Room 1029 ","C"), new ObjectIdentifier(ObjectType.analogValue, 729),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1030.cm","Room 1030 ","C"), new ObjectIdentifier(ObjectType.analogValue, 730),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1031.cm","Room 1031 ","C"), new ObjectIdentifier(ObjectType.analogValue, 731),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1032.cm","Room 1032 ","C"), new ObjectIdentifier(ObjectType.analogValue, 732),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1033.cm","Room 1033 ","C"), new ObjectIdentifier(ObjectType.analogValue, 733),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1034.cm","Room 1034 ","C"), new ObjectIdentifier(ObjectType.analogValue, 734),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1035.cm","Room 1035 ","C"), new ObjectIdentifier(ObjectType.analogValue, 735),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1036.cm","Room 1036 ","C"), new ObjectIdentifier(ObjectType.analogValue, 736),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1037.cm","Room 1037 ","C"), new ObjectIdentifier(ObjectType.analogValue, 737),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1038.cm","Room 1038 ","C"), new ObjectIdentifier(ObjectType.analogValue, 738),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1039.cm","Room 1039 ","C"), new ObjectIdentifier(ObjectType.analogValue, 739),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1040.cm","Room 1040 ","C"), new ObjectIdentifier(ObjectType.analogValue, 740),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1041.cm","Room 1041 ","C"), new ObjectIdentifier(ObjectType.analogValue, 741),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl01.roomtemp1042.cm","Room 1042 ","C"), new ObjectIdentifier(ObjectType.analogValue, 742),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1043.cm","Room 1043 ","C"), new ObjectIdentifier(ObjectType.analogValue, 743),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1044.cm","Room 1044 ","C"), new ObjectIdentifier(ObjectType.analogValue, 744),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1045.cm","Room 1045 ","C"), new ObjectIdentifier(ObjectType.analogValue, 745),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1046.cm","Room 1046 ","C"), new ObjectIdentifier(ObjectType.analogValue, 746),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1047.cm","Room 1047 ","C"), new ObjectIdentifier(ObjectType.analogValue, 747),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1048.cm","Room 1048 ","C"), new ObjectIdentifier(ObjectType.analogValue, 748),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1049.cm","Room 1049 ","C"), new ObjectIdentifier(ObjectType.analogValue, 749),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1050.cm","Room 1050 ","C"), new ObjectIdentifier(ObjectType.analogValue, 750),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1051.cm","Room 1051 ","C"), new ObjectIdentifier(ObjectType.analogValue, 751),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1052.cm","Room 1052 ","C"), new ObjectIdentifier(ObjectType.analogValue, 752),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1053.cm","Room 1053 ","C"), new ObjectIdentifier(ObjectType.analogValue, 753),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1054.cm","Room 1054 ","C"), new ObjectIdentifier(ObjectType.analogValue, 754),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1055.cm","Room 1055 ","C"), new ObjectIdentifier(ObjectType.analogValue, 755),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1056.cm","Room 1056 ","C"), new ObjectIdentifier(ObjectType.analogValue, 756),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1057.cm","Room 1057 ","C"), new ObjectIdentifier(ObjectType.analogValue, 757),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1058.cm","Room 1058 ","C"), new ObjectIdentifier(ObjectType.analogValue, 758),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1059.cm","Room 1059 ","C"), new ObjectIdentifier(ObjectType.analogValue, 759),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1060.cm","Room 1060 ","C"), new ObjectIdentifier(ObjectType.analogValue, 760),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1061.cm","Room 1061 ","C"), new ObjectIdentifier(ObjectType.analogValue, 761),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl01.roomtemp1062.cm","Room 1062 ","C"), new ObjectIdentifier(ObjectType.analogValue, 762),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1063.cm","Room 1063 ","C"), new ObjectIdentifier(ObjectType.analogValue, 763),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1064.cm","Room 1064 ","C"), new ObjectIdentifier(ObjectType.analogValue, 764),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1065.cm","Room 1065 ","C"), new ObjectIdentifier(ObjectType.analogValue, 765),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1066.cm","Room 1066 ","C"), new ObjectIdentifier(ObjectType.analogValue, 766),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1067.cm","Room 1067 ","C"), new ObjectIdentifier(ObjectType.analogValue, 767),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1068.cm","Room 1068 ","C"), new ObjectIdentifier(ObjectType.analogValue, 768),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1069.cm","Room 1069 ","C"), new ObjectIdentifier(ObjectType.analogValue, 769),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1070.cm","Room 1070 ","C"), new ObjectIdentifier(ObjectType.analogValue, 770),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1071.cm","Room 1071 ","C"), new ObjectIdentifier(ObjectType.analogValue, 771),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1072.cm","Room 1072 ","C"), new ObjectIdentifier(ObjectType.analogValue, 772),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1073.cm","Room 1073 ","C"), new ObjectIdentifier(ObjectType.analogValue, 773),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1074.cm","Room 1074 ","C"), new ObjectIdentifier(ObjectType.analogValue, 774),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1075.cm","Room 1075 ","C"), new ObjectIdentifier(ObjectType.analogValue, 775),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1076.cm","Room 1076 ","C"), new ObjectIdentifier(ObjectType.analogValue, 776),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl01.roomtemp1077.cm","Room 1077 ","C"), new ObjectIdentifier(ObjectType.analogValue, 777),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2001.cm","Room 2001 ","C"), new ObjectIdentifier(ObjectType.analogValue, 801),true));//test
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2002.cm","Room 2002 ","C"), new ObjectIdentifier(ObjectType.analogValue, 802),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2003.cm","Room 2003 ","C"), new ObjectIdentifier(ObjectType.analogValue, 803),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2004.cm","Room 2004 ","C"), new ObjectIdentifier(ObjectType.analogValue, 804),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2005.cm","Room 2005 ","C"), new ObjectIdentifier(ObjectType.analogValue, 805),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2006.cm","Room 2006 ","C"), new ObjectIdentifier(ObjectType.analogValue, 806),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2007.cm","Room 2007 ","C"), new ObjectIdentifier(ObjectType.analogValue, 807),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2008.cm","Room 2008 ","C"), new ObjectIdentifier(ObjectType.analogValue, 808),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2009.cm","Room 2009 ","C"), new ObjectIdentifier(ObjectType.analogValue, 809),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2010.cm","Room 2010 ","C"), new ObjectIdentifier(ObjectType.analogValue, 810),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2011.cm","Room 2011 ","C"), new ObjectIdentifier(ObjectType.analogValue, 811),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2012.cm","Room 2012 ","C"), new ObjectIdentifier(ObjectType.analogValue, 812),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2013.cm","Room 2013 ","C"), new ObjectIdentifier(ObjectType.analogValue, 813),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2014.cm","Room 2014 ","C"), new ObjectIdentifier(ObjectType.analogValue, 814),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2015.cm","Room 2015 ","C"), new ObjectIdentifier(ObjectType.analogValue, 815),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2016.cm","Room 2016 ","C"), new ObjectIdentifier(ObjectType.analogValue, 816),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2017.cm","Room 2017 ","C"), new ObjectIdentifier(ObjectType.analogValue, 817),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2018.cm","Room 2018 ","C"), new ObjectIdentifier(ObjectType.analogValue, 818),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2019.cm","Room 2019 ","C"), new ObjectIdentifier(ObjectType.analogValue, 819),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2020.cm","Room 2020 ","C"), new ObjectIdentifier(ObjectType.analogValue, 820),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("sfl02.roomtemp2021.cm","Room 2021 ","C"), new ObjectIdentifier(ObjectType.analogValue, 821),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2022.cm","Room 2022 ","C"), new ObjectIdentifier(ObjectType.analogValue, 822),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2023.cm","Room 2023 ","C"), new ObjectIdentifier(ObjectType.analogValue, 823),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2024.cm","Room 2024 ","C"), new ObjectIdentifier(ObjectType.analogValue, 824),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2025.cm","Room 2025 ","C"), new ObjectIdentifier(ObjectType.analogValue, 825),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2026.cm","Room 2026 ","C"), new ObjectIdentifier(ObjectType.analogValue, 826),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2027.cm","Room 2027 ","C"), new ObjectIdentifier(ObjectType.analogValue, 827),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2028.cm","Room 2028 ","C"), new ObjectIdentifier(ObjectType.analogValue, 828),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2029.cm","Room 2029 ","C"), new ObjectIdentifier(ObjectType.analogValue, 829),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2030.cm","Room 2030 ","C"), new ObjectIdentifier(ObjectType.analogValue, 830),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2031.cm","Room 2031 ","C"), new ObjectIdentifier(ObjectType.analogValue, 831),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2032.cm","Room 2032 ","C"), new ObjectIdentifier(ObjectType.analogValue, 832),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2033.cm","Room 2033 ","C"), new ObjectIdentifier(ObjectType.analogValue, 833),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2034.cm","Room 2034 ","C"), new ObjectIdentifier(ObjectType.analogValue, 834),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2035.cm","Room 2035 ","C"), new ObjectIdentifier(ObjectType.analogValue, 835),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2036.cm","Room 2036 ","C"), new ObjectIdentifier(ObjectType.analogValue, 836),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2037.cm","Room 2037 ","C"), new ObjectIdentifier(ObjectType.analogValue, 837),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2038.cm","Room 2038 ","C"), new ObjectIdentifier(ObjectType.analogValue, 838),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2039.cm","Room 2039 ","C"), new ObjectIdentifier(ObjectType.analogValue, 839),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2040.cm","Room 2040 ","C"), new ObjectIdentifier(ObjectType.analogValue, 840),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2041.cm","Room 2041 ","C"), new ObjectIdentifier(ObjectType.analogValue, 841),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("wfl02.roomtemp2042.cm","Room 2042 ","C"), new ObjectIdentifier(ObjectType.analogValue, 842),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2043.cm","Room 2043 ","C"), new ObjectIdentifier(ObjectType.analogValue, 843),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2044.cm","Room 2044 ","C"), new ObjectIdentifier(ObjectType.analogValue, 844),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2045.cm","Room 2045 ","C"), new ObjectIdentifier(ObjectType.analogValue, 845),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2046.cm","Room 2046 ","C"), new ObjectIdentifier(ObjectType.analogValue, 846),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2047.cm","Room 2047 ","C"), new ObjectIdentifier(ObjectType.analogValue, 847),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2048.cm","Room 2048 ","C"), new ObjectIdentifier(ObjectType.analogValue, 848),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2049.cm","Room 2049 ","C"), new ObjectIdentifier(ObjectType.analogValue, 849),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2050.cm","Room 2050 ","C"), new ObjectIdentifier(ObjectType.analogValue, 850),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2051.cm","Room 2051 ","C"), new ObjectIdentifier(ObjectType.analogValue, 851),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2052.cm","Room 2052 ","C"), new ObjectIdentifier(ObjectType.analogValue, 852),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2053.cm","Room 2053 ","C"), new ObjectIdentifier(ObjectType.analogValue, 853),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2054.cm","Room 2054 ","C"), new ObjectIdentifier(ObjectType.analogValue, 854),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2055.cm","Room 2055 ","C"), new ObjectIdentifier(ObjectType.analogValue, 855),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2056.cm","Room 2056 ","C"), new ObjectIdentifier(ObjectType.analogValue, 856),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2057.cm","Room 2057 ","C"), new ObjectIdentifier(ObjectType.analogValue, 857),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2058.cm","Room 2058 ","C"), new ObjectIdentifier(ObjectType.analogValue, 858),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2059.cm","Room 2059 ","C"), new ObjectIdentifier(ObjectType.analogValue, 859),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2060.cm","Room 2060 ","C"), new ObjectIdentifier(ObjectType.analogValue, 860),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2061.cm","Room 2061 ","C"), new ObjectIdentifier(ObjectType.analogValue, 861),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("nfl02.roomtemp2062.cm","Room 2062 ","C"), new ObjectIdentifier(ObjectType.analogValue, 862),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2063.cm","Room 2063 ","C"), new ObjectIdentifier(ObjectType.analogValue, 863),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2064.cm","Room 2064 ","C"), new ObjectIdentifier(ObjectType.analogValue, 864),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2065.cm","Room 2065 ","C"), new ObjectIdentifier(ObjectType.analogValue, 865),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2066.cm","Room 2066 ","C"), new ObjectIdentifier(ObjectType.analogValue, 866),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2067.cm","Room 2067 ","C"), new ObjectIdentifier(ObjectType.analogValue, 867),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2068.cm","Room 2068 ","C"), new ObjectIdentifier(ObjectType.analogValue, 868),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2069.cm","Room 2069 ","C"), new ObjectIdentifier(ObjectType.analogValue, 869),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2070.cm","Room 2070 ","C"), new ObjectIdentifier(ObjectType.analogValue, 870),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2071.cm","Room 2071 ","C"), new ObjectIdentifier(ObjectType.analogValue, 871),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2072.cm","Room 2072 ","C"), new ObjectIdentifier(ObjectType.analogValue, 872),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2073.cm","Room 2073 ","C"), new ObjectIdentifier(ObjectType.analogValue, 873),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2074.cm","Room 2074 ","C"), new ObjectIdentifier(ObjectType.analogValue, 874),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2075.cm","Room 2075 ","C"), new ObjectIdentifier(ObjectType.analogValue, 875),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2076.cm","Room 2076 ","C"), new ObjectIdentifier(ObjectType.analogValue, 876),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2077.cm","Room 2077 ","C"), new ObjectIdentifier(ObjectType.analogValue, 877),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2078.cm","Room 2078 ","C"), new ObjectIdentifier(ObjectType.analogValue, 878),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2079.cm","Room 2079 ","C"), new ObjectIdentifier(ObjectType.analogValue, 879),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2080.cm","Room 2080 ","C"), new ObjectIdentifier(ObjectType.analogValue, 880),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2081.cm","Room 2081 ","C"), new ObjectIdentifier(ObjectType.analogValue, 881),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("efl02.roomtemp2082.cm","Room 2082 ","C"), new ObjectIdentifier(ObjectType.analogValue, 882),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createFloat("fl03.roomtemp3001.cm","Room 3001 ","C"), new ObjectIdentifier(ObjectType.analogValue, 901),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("fl03.roomtemp3003.cm","Room 3003 ","C"), new ObjectIdentifier(ObjectType.analogValue, 903),true));

	
	return pointList;
}
}
