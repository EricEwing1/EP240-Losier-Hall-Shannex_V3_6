/**
 * 
 */
package pdb.mock;

import pdb.PdbInteger;

/**
 * @author tgu
 *
 */
public class PdbIntegerMock implements PdbInteger {

	/**
	 * 
	 */
	public PdbIntegerMock(int value) {
		_value = value;
	}

	/* (non-Javadoc)
	 * @see pdb.PdbInteger#get()
	 */
	@Override
	public int get() {
		return _value;
	}

	/* (non-Javadoc)
	 * @see pdb.PdbInteger#set(int)
	 */
	@Override
	public int set(int to) {
		_value = to;
		return _value;
	}
	
	private int _value;

}
