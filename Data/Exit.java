/* Exit Interface
 * Created: 5 September 2023
 * Updated: 13 October 2023
 * Version 0.4
 * Interface to govern exits
 */

package Data;

import java.util.ArrayList;

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
}

/*
 * 5 September 2023 - Created File 
 * 12 September 2023 - Added functionality to handle exit description
 * 11 October 2023 - Added lockable functions
 * 13 October 2023 - Added methods for lock and close
*/