package pdb;

public class PDBti extends PDBi {
	private native boolean 	pdbtinew(String name, String desc);

	/**
	 * References an existing PDB_TIMER in PDB.
	 * 
	 * @param  name The name of the point. Ex. la01.suptempcon1.parar.ti
	 */
	public PDBti(String name) {
		super(name);
	}
	
	/**
	 * Creates a new PDB_TIMER in PDB.
	 * 
	 * @param  name  	The name of the point. la01.suptemp.cm
	 * @param  desc  	Description of the point.
	 * @param  defval   Default value
	 */
	public PDBti(String name, String desc, int defval) {
		super(name);
		if(pdbtinew(name, desc))
			pdbiset(name, defval);
	}

}
