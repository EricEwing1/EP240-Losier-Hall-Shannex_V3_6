package pdb;


public class PDBi implements PdbInteger {
	String	_name;

	protected native int 		pdbiget(String name);
	protected native int 		pdbiset(String name, int to);
	protected native boolean 	pdbinew(String name, String desc, String unit);

	public PDBi(String name) {
		_name = new String(name);
	}
	
	public PDBi(String name, String desc, String unit) {
		_name = new String(name);
		pdbinew(name, desc, unit);
	}
	/**
	 * Creates a new IVAL with default value in PDB.
	 * 
	 * @param  name    The name of the point. la01.suptemp.cm
	 * @param  desc    Description of the point.
	 * @param  unit    The engineering unit.
	 * @param  defval  Default value.
	 */
	public PDBi(String name, String desc, String unit, int defval) {
		_name = new String(name);
		if(pdbinew(_name, desc, unit))
			pdbiset(_name, defval);
	}
	
	
	
	/* (non-Javadoc)
	 * @see pdb.impl.PdbInteger#get()
	 */
	@Override
	public int get() {
		return pdbiget(_name);
	}

	/* (non-Javadoc)
	 * @see pdb.impl.PdbInteger#set(int)
	 */
	@Override
	public int set(int to) {
		return pdbiset(_name, to);
	}

}
