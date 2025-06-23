/**
 * 
 */
package pdb.mock;

import pdb.PdbAlarm;

/**
 * @author tgu
 *
 */
public class PdbAlarmMock implements PdbAlarm {

	/**
	 * 
	 */
	public PdbAlarmMock(boolean value) {
		_value = value;
	}

	/* (non-Javadoc)
	 * @see pdb.PdbBoolean#get()
	 */
	@Override
	public boolean get() {
		return _value;
	}

	/* (non-Javadoc)
	 * @see pdb.PdbBoolean#set(boolean)
	 */
	@Override
	public boolean set(boolean to) {
		_value = to;
		return _value;
	}
	
	private boolean _value;
}
