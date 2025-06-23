/**
 * 
 */
package pdb.mock;

import pdb.PdbFloat;

/**
 * @author tgu
 *
 */
public class PdbFloatMock implements PdbFloat {

	/**
	 * 
	 */
	public PdbFloatMock(double value) {
		_value = value;
	}

	/* (non-Javadoc)
	 * @see pdb.PdbFloat#get()
	 */
	@Override
	public double get() {
		return _value;
	}

	/* (non-Javadoc)
	 * @see pdb.PdbFloat#set(double)
	 */
	@Override
	public double set(double to) {
		_value = to;
		return _value;
	}
	
	private double _value;

}
