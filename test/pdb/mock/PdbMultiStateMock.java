/**
 * 
 */
package pdb.mock;

import pdb.MultiStateTranslation;
import pdb.PdbInteger;

/**
 * @author johf
 *
 */
public class PdbMultiStateMock implements PdbInteger {

	private MultiStateTranslation translation;
	private int value;
	
	/**
	 * 
	 */
	public PdbMultiStateMock(int value, MultiStateTranslation translation) {
		this.value = value;
		this.translation = translation;
	}


	/* (non-Javadoc)
	 * @see pdb.PdbInteger#get()
	 */
	@Override
	public int get() {
		int value = this.value;
		try {
			value = translation.pdbToExtState(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("PdbMultiState get(): " +
				String.valueOf(this.value) + " -> " + String.valueOf(value));
		return value;
	}

	/* (non-Javadoc)
	 * @see pdb.PdbInteger#set(int)
	 */
	@Override
	public int set(int to) {
		try {
			value = translation.extToPdbState(to);
		} catch (Exception e) {
			e.printStackTrace();
			value = to;
		}
		System.out.println("PdbMultiState set(): " +
				String.valueOf(to) + " -> " + String.valueOf(value));
		return value;
	}

}
