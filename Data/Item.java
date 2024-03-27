/* Item Interface
 * Created: 12 September 2023
 * Updated: 27 March 2024
 * Version: 0.8
 * The main class for objects. Since we can't call it an object (reserved word)
 * we have to call it a thing.
 */

package Data;

import java.util.ArrayList;

public interface Item {
	
	public String getDescription();
	public String[] getNouns();
	public String getName();
	public void updateItem(String name, String description);
	public boolean getCloseable();
	public boolean getClosed();
	public boolean getLockable();
	public boolean getLocked();
	public void setClosed();
	public void setLocked();
	public boolean checkKey(Item key);
	public boolean getMoveable();
	public boolean getMoved();
	public void setMoved();
	public Exit getHiddenExit();
	public Item getHiddenItem();
	public void setTreasure();
	public boolean getTreasure();
	public boolean checkIsCover();
	public boolean checkHidden(boolean whatItems);
	public boolean checkRemove();
	public boolean checkHiddenExits();
	public boolean checkHiddenItems();
	public void addExit(Exit hiddenExit);
	public void addItem(Item hiddenItem);
	public Conversation talk();
};

/* 12 September 2023 - Created File
 * 14 September 2023 - Began to add methods
 * 23 Janaury 2024 - Added methods for moveable items
 * 25 January 2024 - Added functions to handle treasures
 * 28 January 2024 - Added functions for covers
 * 29 January 2024 - Added further functions for covers
 * 13 March 2024 - Added function definitions for adding items and exits
 * 27 March 2024 - Added the talk function
 */