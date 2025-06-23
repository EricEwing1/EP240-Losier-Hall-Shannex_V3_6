package pdb;

public class PDBsw extends PDBi {

	private native boolean pdbswnew(String name, String desc,  
			String s1, String s2, 
			String s3, String s4, 
			String s5, String s6);

	/**
	 * References an existing SW in PDB.
	 * 
	 * @param  name The name of the point. Ex. la01.heatpump.sw
	 */
	public PDBsw(String name) {
		super(name);
	}
	
	/**
	 * Creates a new PDB_SW in PDB.
	 * 
	 * @param  name  The name of the point. la01.suptemp.cm
	 * @param  desc  Description of the point.
	 */
	public PDBsw(String name, String desc) {
		super(name);
		if(pdbswnew(name, desc, "Från", "Till", "", "", "", ""))
			pdbiset(_name, 999);
	}

	/**
	 * Creates a new PDB_SW in PDB.
	 * 
	 * @param  name  The name of the point. la01.suptemp.cm
	 * @param  desc  Description of the point.
	 * @param  off   Text for OFF state
	 * @param  on1   Text for ON-1 state
	 * @param  on2   Text for ON-2 state
	 * @param  on3   Text for ON-3 state
	 * @param  on4   Text for ON-4 state
	 * @param  on5   Text for ON-5 state
	 */
	public PDBsw(String name, String desc, String off, String on1, String on2, String on3, String on4, String on5) {
		super(name);
		if(pdbswnew(name, desc, off, on1, on2, on3, on4, on5))
			pdbiset(_name, 999);
	}

}
