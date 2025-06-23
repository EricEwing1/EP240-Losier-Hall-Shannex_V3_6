package pdb;

public class MultiStateValue {
	public int sPdb;
	public int sExt;
	public String pdbText;
	public String extText;
	
	public MultiStateValue(int sPdb, String pdbText, int sExt, String extText) {
		this.sPdb = sPdb;
		this.sExt = sExt;
		this.pdbText = pdbText;
		this.extText = extText;
	}

}
