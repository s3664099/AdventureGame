/* Exit Interface
 * Created: 5 September 2023
 * Updated: 11 August 2025
 * Version 1.3
 * Interface to govern exits
 */

package Data;

import java.util.List;
import java.util.Optional;

public interface Exit {

	//== Core Movement ==
	public String getName();
	public Location getDestination();
	public boolean canMoveThrough();
	public String getMoveDirection();
	
	//=== State Inspection
	public String getDescription();
	public List<String> getCommandSynonyms();
	
	//== Open/Close Mechanism ===
	public boolean isOpenable();
	public boolean isOpen();
	public void toggleOpenClose();
	
	//== Lock Mechanisms ==
	public boolean isLockable();
	public boolean isLocked();
	public Item getKey();
	public String attemptLockUnlock(CarriableItem item, String action);
	
	//== Item Interaction
	public void setItem(boolean revealed);
	public boolean isItemRevealed();
	Optional <Item> getHiddenItem();
	
	//== Command Processing
	public boolean equals(String command);
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
 * 8 August 2025 - removed add direction
 * 9 August 2025 - Changed getCommands to List
 * 11 August 2025 - Updated moveDescription function
 * 				  - Refactored to set code readable
*/