/**
 * 
 */
package pdb;

/**
 * @author johf
 *
 */
public class PDBms extends PDBsw {

	private MultiStateTranslation translation;
	
	/**
	 * @param name
	 * @param desc
	 * @param off
	 * @param on1
	 * @param on2
	 * @param on3
	 * @param on4
	 * @param on5
	 */
	public PDBms(String name, String desc, String off, String on1, String on2, String on3, String on4, String on5, MultiStateTranslation translation) {
		super(name, desc, off, on1, on2, on3, on4, on5);
		this.translation = translation;
	}

	/* (non-Javadoc)
	 * @see pdb.PDBi#get()
	 */
	@Override
	public int get() {
		int v = super.get();
		try {
			v = translation.pdbToExtState(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	/* (non-Javadoc)
	 * @see pdb.PDBi#set(int)
	 */
	@Override
	public int set(int to) {
		int v = to;
		try {
			v = translation.extToPdbState(to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.set(v);
	}

	
}
