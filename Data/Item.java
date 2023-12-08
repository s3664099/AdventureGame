/* Item Interface
 * Created: 12 September 2023
 * Updated: 14 September 2023
 * Version: 0.2
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
};

/* 12 September 2023 - Created File
 * 14 September 2023 - Began to add methods
 */