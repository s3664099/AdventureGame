/* Item Interface
 * Created: 12 September 2023
 * Updated: 23 August 2025
 * Version: 1.6
 * The main class for objects. Since we can't call it an object (reserved word)
 * we have to call it a thing.
 */

package Data;

import java.util.Optional;

public interface Item {
	
	public String getDescription();
	public String[] getNouns();
	public String getName();
	public String getBasicName();
	public boolean getCloseable();
	public boolean getClosed();
	public boolean getLockable();
	public boolean getLocked();
	public void setClosed();
	public void setLocked();
	public boolean checkKey(Item key);
	public boolean getMoveable();
	public boolean getMoved();
	public boolean getExtended();
	public void setMoved();
	public Optional<Exit> getHiddenExit();
	public Optional<Item> getHiddenItem();
	public boolean getTreasure();
	public boolean checkIsCover();
	public boolean checkHidden(boolean whatItems);
	public boolean checkRemove();
	public boolean checkHiddenExits();
	public boolean checkHiddenItems();
	public boolean equals(String command);
	public Conversation talk();
	public String getLeave();
	public String getLeaveConvo();
	public String readItem();
	void addItem(CarriableItem item);
};

/* 12 September 2023 - Created File
 * 14 September 2023 - Began to add methods
 * 23 Janaury 2024 - Added methods for moveable items
 * 25 January 2024 - Added functions to handle treasures
 * 28 January 2024 - Added functions for covers
 * 29 January 2024 - Added further functions for covers
 * 13 March 2024 - Added function definitions for adding items and exits
 * 27 March 2024 - Added the talk function
 * 4 March 2024 - Added function to determine whether conversation is extended
 * 11 May 2024 - Added setExtended function definition
 * 8 June 2024 - Added leave convo methods
 * 24 August 2024 - Added the equals method
 * 9 Janaury 2025 - Added a read method. Added basic name for containers that list contents
 * 11 January 2025 - Added an addItem method
 * 16 August 2025 - Cleaned up unused sections
 * 21 August 2025 - Removed setters that set up initial item
 * 23 August 2024 - Updated getHiddenItem & getHiddenExit
 */