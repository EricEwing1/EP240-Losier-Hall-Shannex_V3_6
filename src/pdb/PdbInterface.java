/**
 * 
 */
package pdb;

/**
 * @author Tobias Gustafsson <tgu@kabona.com>
 *
 */
public interface PdbInterface {
	
	PdbBoolean createBoolean(String name, String desc) throws PdbException;
	PdbAlarm createAlarm(String name, String desc, String domain, String priority) throws PdbException;
	PdbFeedBack createFeedBack(String name, String desc) throws PdbException;
	PdbInteger createInteger(String name, String desc, String unit) throws PdbException;
	PdbInteger createSwitch(String name, String desc) throws PdbException;
	PdbInteger createMultiState(String name, String desc, MultiStateTranslation translation) throws PdbException;
	PdbInteger createSwitch(String name, String desc, String off, String on1, String on2, String on3, String on4, String on5) throws PdbException;
	PdbInteger createTimer(String name, String desc, int defval) throws PdbException;
	PdbFloat createFloat(String name, String desc, String unit) throws PdbException;
	
	PdbBoolean getBoolean(String name) throws PdbException;
	PdbInteger getInteger(String name) throws PdbException;
	PdbFloat getFloat(String name) throws PdbException;
	
}
