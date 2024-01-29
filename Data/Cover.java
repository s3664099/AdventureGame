/* Cover Item Class
 * Created: 28 January 2024
 * Updated: 28 January 2024
 * Version: 0.0
 * Class for items either cover items or exits
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public class Cover extends ImmoveableItem implements Item,Serializable {
	
	ArrayList<Exit> hiddenExits;
	ArrayList<Item> hiddenItems;
	private boolean isCover = true;
	private boolean removeObject;
	
	//Adds a hidden exit to the list
	public Cover(String name, String description, Exit hiddenExit, boolean remove) {
		super(name,description);
		this.hiddenExits.add(hiddenExit);
		this.removeObject = remove;
	}
	
	//Adds a hidden item to the list
	public Cover(String name, String description, Item hiddenItem, boolean remove) {
		super(name,description);
		this.hiddenItems.add(hiddenItem);
		this.removeObject = remove;
	}
	
	//Adds both hidden exits and items
	public Cover(String name, String description, Exit hiddenExit, Item hiddenItem, boolean remove) {
		super(name,description);
		this.hiddenExits.add(hiddenExit);
		this.hiddenItems.add(hiddenItem);
		this.removeObject = remove;
	}

	@Override
	public String[] getNouns() {
		return super.getNouns();
	}

	@Override
	public String getName() {
		return super.getName();
	}

	//Methods for items that are closeable and lockable
	@Override
	public boolean getCloseable() {
		return false;
	}

	@Override
	public boolean getClosed() {
		return false;
	}

	@Override
	public boolean getLockable() {
		return false;
	}

	@Override
	public boolean getLocked() {
		return false;
	}

	@Override
	public void setClosed() {}

	@Override
	public void setLocked() {}

	@Override
	public boolean checkKey(Item key) {
		return false;
	}

	@Override
	public boolean getMoveable() {
		return false;
	}

	//Checks if there are any hidden exits of items
	//True: check for items
	//False: check for exits
	public boolean checkHidden (boolean whatItems) {
		
		boolean anyItems = false;
		
		if (whatItems) {
			if (this.hiddenItems.size()>0) {
				anyItems = true;
			}
		} else {
			if (this.hiddenExits.size()>0) {
				anyItems = true;
			}
		}
		
		return anyItems;
		
	}
	
	//Checks to see if the item is to be removed if there are no more items/exits
	public boolean checkRemove() {
		return this.removeObject;
	}
	
	//Removes a hidden item. Sends null if no item available
	@Override
	public Item getHiddenItem() {
		
		Item returnItem = null;
		
		if (hiddenItems.size()>0) {
			int itemPosition = (int)(Math.random() * hiddenItems.size());
			returnItem = hiddenItems.remove(itemPosition);
		}
		
		return returnItem;
	}

	//Removes a hidden exits. Sends null if no exit available
	@Override
	public Exit getHiddenExit() {
		
		Exit returnExit = null;
		
		if (hiddenExits.size()>0) {
			int itemPosition = (int)(Math.random() * hiddenExits.size());
			returnExit = hiddenExits.remove(itemPosition);
		}
		
		return returnExit;
	}

	@Override
	public boolean getMoved() {
		return false;
	}

	@Override
	public void setMoved() {}

}

/* 28 January 2024 - Created file
*/
