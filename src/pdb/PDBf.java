package pdb;


//import java.io.UnsupportedEncodingException;


/**
 * PDB FVAL
 */
public class PDBf implements PdbFloat {
	String	_name;

	private native double pdbfget(String name);
	private native double pdbfset(String name, double to);
	private native boolean pdbfnew(String name, String desc, String unit);

	/**
	 * References an existing FVAL in PDB.
	 * 
	 * @param  name The name of the point. Ex. la01.suptemp.cm
	 */
	public PDBf(String name) {
		_name = new String(name);
	}
	
	/**
	 * Creates a new FVAL in PDB.
	 * 
	 * @param  name  The name of the point. la01.suptemp.cm
	 * @param  desc  Description of the point.
	 * @param  unit  The engineering unit.
	 */
	public PDBf(String name, String desc, String unit) {
		_name = new String(name);
		/*
		byte[] def = { 0x3F, 0x3F };
		byte[] _desc;
		try {
			_desc = desc.getBytes("8859_1");
		} catch (UnsupportedEncodingException e) {
			_desc = def;
		}
		byte[] _unit;
		try {
			_unit = unit.getBytes("8859_1");
		} catch (UnsupportedEncodingException e) {
			_unit = def;
		}
		pdbfnew(_name, _desc, _unit);
		*/
		pdbfnew(_name, desc, unit);
	}
	/**
	 * Creates a new FVAL with default value in PDB.
	 * 
	 * @param  name    The name of the point. la01.suptemp.cm
	 * @param  desc    Description of the point.
	 * @param  unit    The engineering unit.
	 * @param  defval  Default value.
	 */
	public PDBf(String name, String desc, String unit, double defval) {
		_name = new String(name);
		/*
		byte[] def = { 0x3F, 0x3F };
		byte[] bdesc;
		try {
			bdesc = desc.getBytes("8859_1");
		} catch (UnsupportedEncodingException e) {
			bdesc = def;
		}
		byte[] bunit;
		try {
			bunit = unit.getBytes("8859_1");
		} catch (UnsupportedEncodingException e) {
			bunit = def;
		}
		if(pdbfnew(_name, bdesc, bunit))
			pdbfset(_name, defval);
		*/
		if(pdbfnew(_name, desc, unit))
			pdbfset(_name, defval);
	}
	
	
	/* (non-Javadoc)
	 * @see pdb.impl.PdbFloat#get()
	 */	
	@Override
	public double get() {
		return pdbfget(_name);
	}

	/* (non-Javadoc)
	 * @see pdb.impl.PdbFloat#set(double)
	 */	
	@Override
	public double set(double to) {
		return pdbfset(_name, to);
	}
}
