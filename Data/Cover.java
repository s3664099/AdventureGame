/* Cover Item Class
 * Created: 28 January 2024
 * Updated: 4 September 2025
 * Version: 1.1
 * Class for items either cover items or exits
 */

package Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.io.Serializable;

public class Cover extends ImmoveableItem implements Item,Serializable {
	
	private static final long serialVersionUID = -1835310517980029996L;
	private final List<Exit> hiddenExits = new ArrayList<Exit>();
	private final List<Item> hiddenItems = new ArrayList<Item>();
	private final boolean removeObject;
	
	public Cover(Builder builder) {
		super(builder);
		this.removeObject = builder.removeObject;
	}
	
	public static class Builder extends ImmoveableItem.Builder {
		
		private boolean removeObject;
		
		public Builder(String name, String description) {
			super(name,description);
			removeObject = false;
		}
		
		public Builder setRemoveObject(boolean removeObject) {
			this.removeObject = removeObject;
			return this;
		}
				
        @Override
        protected Builder self() {
            return this;
        }
		
		public Cover build() {
			return new Cover(this);
		}
	}
	
	public void addHiddenExit(Exit hiddenExit) {
		this.hiddenExits.add(Objects.requireNonNull(hiddenExit, "Exit Comment cannot be null"));
	}
	
	public void addHiddenItem(Item hiddenItem) {
		this.hiddenItems.add(Objects.requireNonNull(hiddenItem, "Item Comment cannot be null"));
	}
	
	@Override
	public String[] getNouns() {
		return super.getNouns();
	}

	@Override
	public String getName() {
		return super.getName();
	}

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
		return true;
	}

	//Checks if there are any hidden exits of items
	//True: check for items
	//False: check for exits
	public boolean hasHiddenContent (boolean checkItems) {
		return checkItems ? !hiddenItems.isEmpty() : !hiddenExits.isEmpty();
	}
	
	//Checks to see if the item is to be removed if there are no more items/exits
	public boolean checkRemove() {
		return this.removeObject;
	}
	
	//Removes a hidden item. Sends null if no item available
	@Override
	public Optional<Item> getHiddenItem() {
		
		Item returnItem = null;
		
		if (hiddenItems.size()>0) {
			int itemPosition = (int)(Math.random() * hiddenItems.size());
			returnItem = hiddenItems.remove(itemPosition);
		}
		
		return Optional.ofNullable(returnItem);
	}

	//Removes a hidden exits. Sends null if no exit available
	@Override
	public Optional<Exit> getHiddenExit() {
		
		Exit returnExit = null;
		
		if (hiddenExits.size()>0) {
			int itemPosition = (int)(Math.random() * hiddenExits.size());
			returnExit = hiddenExits.remove(itemPosition);
		}
		
		return Optional.ofNullable(returnExit);
	}
	
	//Checks if there are any exits
	public boolean checkHiddenExits() {
		return hiddenExits.size()>0;
	}

	//Checks if there are any exits
	public boolean checkHiddenItems() {		
		return hiddenItems.size()>0;
	}

	@Override
	public boolean getMoved() {
		return false;
	}

	@Override
	public void setMoved() {}

}

/* 28 January 2024 - Created file
 * 21 February 2024 - Added methods to make the class work properly
 * 13 March 2024 - Added methods to add more hidden items
 * 10 May 2024 - Made Variables private
 * 4 September 2025 - Updated class with builder.
*/
