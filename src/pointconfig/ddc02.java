package pointconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;

import main.FahrenheitCelsiusScaler;
import main.OffsetFahrenheitCelsiusScaler;
import main.PointConnnectionContainer;
import main.Scaler;
import pdb.PdbException;
import pdb.PdbInterface;

//import main.FahrenheitCelsiusScaler;
//import main.OffsetFahrenheitCelsiusScaler;
//import main.Scaler;
import pdb.MultiStateTranslation;
import pdb.MultiStateValue;
import pdb.PDBb;


public class ddc02 {

public static List<PointConnnectionContainer> points(PdbInterface pdb) throws PdbException {
	List<PointConnnectionContainer> pointList = new ArrayList<PointConnnectionContainer>();
	
	
	MultiStateTranslation msTranslation1 =  new MultiStateTranslation(Arrays.asList(
			new MultiStateValue(999, "Auto", 3, "Auto"),
			new MultiStateValue(0, "Off", 1, "Off"),
			new MultiStateValue(1, "On", 2, "On")
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
	Scaler scalerCF = new FahrenheitCelsiusScaler();
	Scaler scalerCFdsp = new OffsetFahrenheitCelsiusScaler();
	//BACnet points Device 10200
	// Victoria OAT
	pointList.add(new PointConnnectionContainer(pdb.createFloat("met.outdoortemp02.cm","Outdoor Air Temperature","C"), new ObjectIdentifier(ObjectType.analogInput, 4),scalerCF,true));

	
	// Rooms
	pointList.add(new PointConnnectionContainer(pdb.createFloat("afl25.roomtemp2501.cm","25th Floor Room Temp. ","C"), new ObjectIdentifier(ObjectType.analogInput, 10),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("afl28.roomtemp2801.cm","28th Floor Room Temp. ","C"), new ObjectIdentifier(ObjectType.analogInput, 2),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("afl30.co230.cm","30th Floor Room CO2 ","ppm"), new ObjectIdentifier(ObjectType.analogInput, 19),true));

	pointList.add(new PointConnnectionContainer(pdb.createMultiState("cu24.day.tc.sw","Schedule (Time Switch) ",msTranslation1), new ObjectIdentifier(ObjectType.multiStateValue, 0)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu24.ecomode.cm","Eco Mode ","1=OFF 2=ON"), new ObjectIdentifier(ObjectType.multiStateValue, 0),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu24.roomtemp24.cm","Space Temperature ","C"), new ObjectIdentifier(ObjectType.analogInput, 14),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu24.coolvavle24.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 6),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("cu24.supfan24.di","Fan Status "), new ObjectIdentifier(ObjectType.binaryInput, 11323),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("cu25.day.tc.sw","Schedule (Time Switch) ",msTranslation1), new ObjectIdentifier(ObjectType.multiStateValue, 1)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu25.ecomode.cm","Eco Mode" ,"1=OFF 2=ON"), new ObjectIdentifier(ObjectType.multiStateValue, 1),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu25.roomtemp25.cm","Space Temperature ","C"), new ObjectIdentifier(ObjectType.analogInput, 10),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu25.coolvavle25.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 5),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("cu25.supfan25.di","Fan Status "), new ObjectIdentifier(ObjectType.binaryInput, 10623),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("cu26.day.tc.sw","Schedule (Time Switch) ",msTranslation1), new ObjectIdentifier(ObjectType.multiStateValue, 2)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu26.ecomode.cm","Eco Mode "," 1=OFF 2=ON"), new ObjectIdentifier(ObjectType.multiStateValue, 2),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu26.roomtemp26.cm","Space Temperature ","C"), new ObjectIdentifier(ObjectType.analogInput, 8),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu26.coolvavle26.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 3),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("cu26.supfan26.di","Fan Status "), new ObjectIdentifier(ObjectType.binaryInput, 10723),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("cu27.day.tc.sw","Schedule (Time Switch) ",msTranslation1), new ObjectIdentifier(ObjectType.multiStateValue, 3)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu27.ecomode.cm","Eco Mode "," 1=OFF 2=ON"), new ObjectIdentifier(ObjectType.multiStateValue, 3),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu27.roomtemp27.cm","Space Temperature ","C"), new ObjectIdentifier(ObjectType.analogValue, 74),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu27.coolvavle27.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 1),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("cu27.supfan27.di","Fan Status "), new ObjectIdentifier(ObjectType.binaryInput, 10823),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("cu28.day.tc.sw","Schedule (Time Switch) ",msTranslation1), new ObjectIdentifier(ObjectType.multiStateValue, 4)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu28.ecomode.cm","Eco MOde "," 1=OFF 2=ON"), new ObjectIdentifier(ObjectType.multiStateValue, 4),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu28.roomtemp28.cm","Space Temperature ","C"), new ObjectIdentifier(ObjectType.analogInput, 2),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu28.coolvavle28.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 0),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("cu28.supfan28.di","Fan Status "), new ObjectIdentifier(ObjectType.binaryInput, 10923),true));

	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu29.coolvavle29.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 8),true));
    // CU 30
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu30.coolvavle30.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 7),true));

	
	
	return pointList;
}
}
