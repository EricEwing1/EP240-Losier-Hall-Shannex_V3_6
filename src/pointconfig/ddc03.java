package pointconfig;

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


public class ddc03 {

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
	
	//BACnet points	Device 10300
	// Room Temps
	pointList.add(new PointConnnectionContainer(pdb.createFloat("afl22.roomtemp2201.cm","22nd Floor Room Temp. ","C"), new ObjectIdentifier(ObjectType.analogInput, 4),true));

	pointList.add(new PointConnnectionContainer(pdb.createMultiState("cu19.day.tc.sw","Schedule (Time Switch) ",msTranslation1), new ObjectIdentifier(ObjectType.multiStateValue, 0)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu19.ecomode.cm","Eco Mode "," 1=OFF 2=ON"), new ObjectIdentifier(ObjectType.multiStateValue, 0),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu19.roomtemp19.cm","Space Temperature ","C"), new ObjectIdentifier(ObjectType.analogInput, 12),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu19.coolvavle19.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 7),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("cu19.supfan19.di","Fan Status "), new ObjectIdentifier(ObjectType.binaryInput, 10523),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("cu20.day.tc.sw","Schedule (Time Switch) ",msTranslation1), new ObjectIdentifier(ObjectType.multiStateValue, 1)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu20.ecomode.cm","Eco Mode"," 1=OFF 2=ON"), new ObjectIdentifier(ObjectType.multiStateValue, 1),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu20.roomtemp20.cm","Space Temperature ","C"), new ObjectIdentifier(ObjectType.analogInput, 10),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu20.coolvavle20.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 5),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("cu20.supfan20.di","Fan Status "), new ObjectIdentifier(ObjectType.binaryInput, 10623),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("cu21.day.tc.sw","Schedule (Time Switch) ",msTranslation1), new ObjectIdentifier(ObjectType.multiStateValue, 2)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu21.ecomode.cm","Eco Mode "," 1=OFF 2=ON"), new ObjectIdentifier(ObjectType.multiStateValue, 2),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu21.roomtemp21.cm","Space Temperature ","C"), new ObjectIdentifier(ObjectType.analogInput, 7),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu21.coolvavle21.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 3),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("cu21.supfan21.di","Fan Status "), new ObjectIdentifier(ObjectType.binaryInput, 10723),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("cu22.day.tc.sw","Schedule (Time Switch) ",msTranslation1), new ObjectIdentifier(ObjectType.multiStateValue, 3)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu22.decomode.cm","Eco Mode "," 1=OFF 2=ON"), new ObjectIdentifier(ObjectType.multiStateValue, 3),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu22.roomtemp22.cm","Space Temperature ","C"), new ObjectIdentifier(ObjectType.analogInput, 4),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu22.coolvavle22.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 1),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("cu22.supfan22.di","Fan Status "), new ObjectIdentifier(ObjectType.binaryInput, 10823),true));
	
	pointList.add(new PointConnnectionContainer(pdb.createMultiState("cu23.day.tc.sw","Schedule (Time Switch) ",msTranslation1), new ObjectIdentifier(ObjectType.multiStateValue, 4)));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu23.ecomode.cm","SEco Mode "," 1=OFF 2=ON"), new ObjectIdentifier(ObjectType.multiStateValue, 4),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu23.roomtemp23.cm","Space Temperature ","C"), new ObjectIdentifier(ObjectType.analogInput, 2),true));
	pointList.add(new PointConnnectionContainer(pdb.createFloat("cu23.coolvavle23.out","Cooling Valve ","%"), new ObjectIdentifier(ObjectType.analogOutput, 0),true));
	pointList.add(new PointConnnectionContainer(pdb.createBoolean("cu23.supfan23.di","Fan Status "), new ObjectIdentifier(ObjectType.binaryInput, 10923),true));

	
	return pointList;
}
}
