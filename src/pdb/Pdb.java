/**
 * 
 */
package pdb;

/**
 * @author Tobias Gustafsson <tgu@kabona.com>
 *
 */
public class Pdb implements PdbInterface {
	
	static {
		System.loadLibrary("jpdb");
	}
	
	/**
	 *  Point database implementation
	 */
	public Pdb(String name) {
		_user = new PDBuser(name);
	}

	@Override
	public PdbBoolean createBoolean(String name, String desc) throws PdbException {
		return new PDBb(name.toLowerCase(), desc);
	}

	@Override
	public PdbAlarm createAlarm(String name, String desc, String domain,
			String priority) throws PdbException {
		return new PDBa(name.toLowerCase(), desc, domain, priority);
	}
	
	@Override
	public PdbFeedBack createFeedBack(String name, String desc) throws PdbException {
		return new PDBb(name.toLowerCase(), desc);
	}

	@Override
	public PdbInteger createInteger(String name, String desc, String unit) throws PdbException {
		return new PDBi(name.toLowerCase(), desc, unit);
	}

	@Override
	public PdbInteger createSwitch(String name, String desc) throws PdbException {
		return new PDBsw(name.toLowerCase(), desc);
	}

	@Override
	public PdbInteger createSwitch(String name, String desc, String off,
			String on1, String on2, String on3, String on4, String on5)
			throws PdbException {
		return new PDBsw(name.toLowerCase(), desc, off, on1, on2, on3, on4, on5);
	}
	
	@Override
	public PdbInteger createMultiState(String name, String desc, MultiStateTranslation translation) throws PdbException {
		String s[] = translation.pdbStateTexts();
		return new PDBms(name.toLowerCase(), desc, s[0], s[1], s[2], s[3], s[4], s[5], translation);
	}

	@Override
	public PdbInteger createTimer(String name, String desc, int defval) throws PdbException {
		return new PDBti(name.toLowerCase(), desc, defval);
	}
	
	@Override
	public PdbFloat createFloat(String name, String desc, String unit) throws PdbException {
		return new PDBf(name.toLowerCase(), desc, unit);
	}

	@Override
	public PdbBoolean getBoolean(String name) throws PdbException {
		return new PDBb(name.toLowerCase());
	}

	@Override
	public PdbInteger getInteger(String name) throws PdbException {
		return new PDBi(name.toLowerCase());
	}

	@Override
	public PdbFloat getFloat(String name) throws PdbException {
		return new PDBf(name.toLowerCase());
	}

	@SuppressWarnings("unused")
	private PDBuser _user;
}
