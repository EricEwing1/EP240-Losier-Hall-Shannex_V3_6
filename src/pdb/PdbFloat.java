package pdb;

public interface PdbFloat {

	/**
	 * Get the value of a FVAL in PDB
	 * 
	 * @return      the current value
	 */
	public abstract double get();

	/**
	 * Set the value of a FVAL in PDB
	 * 
	 * @param  to   the new value
	 * @return      the value after set
	 */
	public abstract double set(double to);

}