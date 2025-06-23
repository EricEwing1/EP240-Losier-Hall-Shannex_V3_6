/**
 * 
 */
package pdb.mock;

import pdb.PdbBoolean;

/**
 * @author tgu
 *
 */
public class PdbBooleanMock implements PdbBoolean {

	/**
	 * 
	 */
	public PdbBooleanMock(boolean value) {
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
