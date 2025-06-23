package pdb;

/**
 * PDB ALARM
 *
 * Inherits PDB BOOL
 *  
 * @see         PDBb
 */
public class PDBa extends PDBb implements PdbAlarm{

	private native void    pdbanew(String name, String desc, String domain, String priority);

	/**
	 * References an existing alarm in PDB.
	 * 
	 * @param  name The name of the point. Ex. la01.suptemphigh.al
	 */
	public PDBa(String name) {
		super(name);
	}
	
	/**
	 * Creates a new alarm in PDB.
	 * 
	 * @param  name     The name of the point. la01.suptemphigh.al
	 * @param  desc     Description of the point.
	 * @param  domain   (HEAT,VENT,COOL,ELEC,FIRE,SECU,USER)
	 * @param  priority (EMERG,ALERT,CRIT,ERR,WARN,NOT,INFO)
	 */
	public PDBa(String name, String desc, String domain, String priority) {
		super(name);
		pdbanew(name, desc, domain, priority);
	}
	
}
