/* Exit Interface
 * Created: 5 September 2023
 * Updated: 12 September 2023
 * Version 0.2
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
	void lockUnlock();
	boolean getOpen();
	boolean isOpenable();
}

/*
 * 5 September 2023 - Created File 
 * 12 September 2023 - Added functionality to handle exit description
*/