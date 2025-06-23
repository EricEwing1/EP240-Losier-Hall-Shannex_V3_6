package pdb;


/**
 * PDB BOOL
 */
public class PDBb implements PdbBoolean, PdbFeedBack {
	String	_name;

	private native boolean pdbbget(String name);
	private native boolean pdbbset(String name, boolean to);
	private native void    pdbbnew(String name, String desc);

	public PDBb(String name) {
		_name = new String(name);
	}
	
	public PDBb(String name, String desc) {
		_name = new String(name);
		pdbbnew(name, desc);
	}
	
	/* (non-Javadoc)
	 * @see pdb.impl.PdbBoolean#get()
	 */
	@Override
	public boolean get() {
		return pdbbget(_name);
	}

	/* (non-Javadoc)
	 * @see pdb.impl.PdbBoolean#set(boolean)
	 */
	@Override
	public boolean set(boolean to) {
		return pdbbset(_name, to);
	}
}
