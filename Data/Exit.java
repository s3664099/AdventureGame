/* Exit Interface
 * Created: 5 September 2023
 * Updated: 7 April 2024
 * Version 0.7
 * Interface to govern exits
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public interface Exit {

	String moveDescription(String Command);
	String getName();
	String getDescription();
	ArrayList<String> getCommands();
	boolean haveMoved();
	Location getDestination();
	void openClose();
	boolean getOpen();
	boolean isOpenable();
	boolean isLockable();
	boolean getLocked();
	Item getKey();
	String lockUnlock(CarriableItem item, String action);
	void addDescription(String string);
	boolean checkItem();
	void setItem(boolean updateReveal);
	Item getItem();
}

/*
 * 5 September 2023 - Created File 
 * 12 September 2023 - Added functionality to handle exit description
 * 11 October 2023 - Added lockable functions
 * 13 October 2023 - Added methods for lock and close
 * 25 January 2024 - Made class serializable
 * 21 February 2024 - Added an addDescription method
 * 7 April 2024 - Added functions for items to be revealed
*/