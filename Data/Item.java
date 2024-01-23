/* Item Interface
 * Created: 12 September 2023
 * Updated: 23 January 2024
 * Version: 0.3
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
};

/* 12 September 2023 - Created File
 * 14 September 2023 - Began to add methods
 * 23 Janaury 2024 - Added methods for moveable items
 */