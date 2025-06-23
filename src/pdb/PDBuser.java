package pdb;

public class PDBuser {
	private native double pdbuser(String name);

	public PDBuser(String name) {
		pdbuser(name);
	}

}
