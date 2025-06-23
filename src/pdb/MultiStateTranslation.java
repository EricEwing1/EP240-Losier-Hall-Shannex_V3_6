/**
 * 
 */
package pdb;

import java.util.List;

/**
 * @author johf
 *
 */
public class MultiStateTranslation {
	List<MultiStateValue> translationMatrix;
	
	/**
	 * @throws PdbException 
	 * 
	 */
	public MultiStateTranslation(List<MultiStateValue> translationMatrix) throws PdbException {
		if (translationMatrix.size() > 5)
			throw new PdbException("too many states for PDB");

		for (MultiStateValue msv : translationMatrix) {
			switch (msv.sPdb) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 999: // PDB auto
				break;
			default:
				throw new PdbException("invalid PDB state value (0-5, 999): " + String.valueOf(msv.sPdb));
			}
		}

		this.translationMatrix = translationMatrix;
	}
	
	public String[] pdbStateTexts() {
		String texts[] = { "", "", "", "", "", ""};
		for (MultiStateValue msv : translationMatrix) {
			switch (msv.sPdb) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				texts[msv.sPdb] = msv.pdbText;
				break;
			case 999: // PDB auto
				// No text, but valid
				break;
			}
		}
		return texts;
	}
	
	public int size() {
		return this.translationMatrix.size();
	}
	
	public String getPdbText(int index) throws IndexOutOfBoundsException {
		return this.translationMatrix.get(index).pdbText;
	}

	public int getPdbState(int index) throws IndexOutOfBoundsException {
		return this.translationMatrix.get(index).sPdb;
	}
	
	public int extToPdbState(int extState) throws Exception{
		for (MultiStateValue msv : this.translationMatrix) {
			if (msv.sExt == extState) {
				return msv.sPdb;
			}
		}
		throw new Exception("Unknown external state: " + String.valueOf(extState));
	}
	public int pdbToExtState(int pdbState) throws Exception{
		for (MultiStateValue msv : this.translationMatrix) {
			if (msv.sPdb == pdbState) {
				return msv.sExt;
			}
		}
		throw new Exception("Unknown PDB state: " + String.valueOf(pdbState));
	}
}
