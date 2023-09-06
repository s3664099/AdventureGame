/* Exit Interface
 * Created: 5 September 2023
 * Updated: 5 September 2023
 * Version 0.1
 * Interface to govern exits
 */

package Data;

import java.util.ArrayList;

public interface Exit {

	String moveDescription(String Command);
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
*/