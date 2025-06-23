/**
 * 
 */
package pdb.mock;

import java.util.HashMap;
import java.util.Map;

import pdb.PdbBoolean;
import pdb.PdbFeedBack;
import pdb.MultiStateTranslation;
import pdb.PdbAlarm;
import pdb.PdbException;
import pdb.PdbFloat;
import pdb.PdbInteger;
import pdb.PdbInterface;

/**
 * @author tgu
 *
 */
public class PdbMock implements PdbInterface {

	public PdbMock() {
		_values = new HashMap<String, Object>();
	}

	public void dump() {
		for (Map.Entry<String, Object> entry : _values.entrySet()) {
			Object o = entry.getValue();
			if (o instanceof PdbInteger)
				System.out.println("PDB: [INTEGER] " + entry.getKey() + "=" + ((PdbInteger)o).get());
			else if (o instanceof PdbFloat)
				System.out.println("PDB: [FLOAT  ] " + entry.getKey() + "=" + ((PdbFloat)o).get());
			else if (o instanceof PdbBoolean)
				System.out.println("PDB: [BOOLEAN] " + entry.getKey() + "=" + ((PdbBoolean)o).get());
		}
	}
	
	@Override
	public PdbBoolean createBoolean(String name, String desc) {
		PdbBoolean b = new PdbBooleanMock(false);
		_values.put(name, b);
		return b;
	}

	@Override
	public PdbAlarm createAlarm(String name, String desc, String domain,
			String priority) {
		PdbAlarm b = new PdbAlarmMock(false);
		_values.put(name, b);		
		return b;
	}
	
	@Override
	public PdbFeedBack createFeedBack(String name, String desc) {
		PdbFeedBack b = new PdbFeedBackMock(false);
		_values.put(name, b);		
		return b;
	}

	@Override
	public PdbInteger createInteger(String name, String desc, String unit) {
		PdbInteger i = new PdbIntegerMock(0);
		_values.put(name, i);
		return i;
	}

	@Override
	public PdbInteger createSwitch(String name, String desc) {
		PdbInteger i = new PdbIntegerMock(999);
		_values.put(name, i);
		return i;
	}

	@Override
	public PdbInteger createSwitch(String name, String desc, String off,
			String on1, String on2, String on3, String on4, String on5)
			throws PdbException {
		PdbInteger i = new PdbIntegerMock(999);
		_values.put(name, i);
		return i;
	}

	@Override
	public PdbInteger createMultiState(String name, String desc, MultiStateTranslation translation)
			throws PdbException {
		PdbInteger i = new PdbMultiStateMock(999, translation);
		_values.put(name, i);
		return i;
	}

	@Override
	public PdbInteger createTimer(String name, String desc, int defval) {
		PdbInteger i = new PdbIntegerMock(defval);
		_values.put(name, i);
		return i;
	}

	@Override
	public PdbFloat createFloat(String name, String desc, String unit) {
		PdbFloat f = new PdbFloatMock(0.0);
		_values.put(name, f);
		return f;
	}

	@Override
	public PdbBoolean getBoolean(String name) throws PdbException {
		if (!_values.containsKey(name))
			throw new PdbException("point name does not exist in pdb: " + name);
		
		Object o = _values.get(name);
		if (!(o instanceof PdbBoolean))
			throw new PdbException("point not of boolean type: " + name);

		return (PdbBoolean)o;
	}

	@Override
	public PdbInteger getInteger(String name) throws PdbException {
		if (!_values.containsKey(name))
			throw new PdbException("point name does not exist in pdb: " + name);
		
		Object o = _values.get(name);
		if (!(o instanceof PdbInteger))
			throw new PdbException("point not of integer type: " + name);

		return (PdbInteger)o;			
	}

	@Override
	public PdbFloat getFloat(String name) throws PdbException {
		if (!_values.containsKey(name))
			throw new PdbException("point name does not exist in pdb: " + name);
		
		Object o = _values.get(name);
		if (!(o instanceof PdbFloat))
			throw new PdbException("point not of float type: " + name);

		return (PdbFloat)o;
	}

	private Map<String, Object> _values;
}
