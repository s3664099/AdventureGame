/* Exit Interface
 * Created: 5 September 2023
 * Updated: 24 Augsut 2024
 * Version 1.1
 * Interface to govern exits
 */

package Data;

import java.util.ArrayList;

public interface Exit {

	public String moveDescription(String Command);
	public String getName();
	public String getDescription();
	public ArrayList<String> getCommands();
	public boolean haveMoved();
	public Location getDestination();
	public void openClose();
	public boolean getOpen();
	public boolean isOpenable();
	public boolean isLockable();
	public boolean getLocked();
	public boolean equals(String command);
	public Item getKey();
	public String lockUnlock(CarriableItem item, String action);
	public void addDescription(String string);
	public boolean checkItem();
	public void setItem(boolean updateReveal);
	public Item getItem();
}

/*
 * 5 September 2023 - Created File 
 * 12 September 2023 - Added functionality to handle exit description
 * 11 October 2023 - Added lockable functions
 * 13 October 2023 - Added methods for lock and close
 * 25 January 2024 - Made class serializable
 * 21 February 2024 - Added an addDescription method
 * 7 April 2024 - Added functions for items to be revealed
 * 24 August 2024 - Added the equals method
*/