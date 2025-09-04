/* Immoveable Item Class
 * Created: 25 August 2023
 * Updated: 28 August 2025
 * Version: 1.3
 * Class for items that can't be picked up and carried.
 */

package Data;

import java.util.Optional;

import java.io.Serializable;

public class ImmoveableItem extends AbstractItem implements Item,Serializable {
	
	private static final long serialVersionUID = 8882947786140522306L;

	protected ImmoveableItem(Builder builder) {
		super(builder);
	}
	
	public static class Builder extends AbstractItem.Builder {
		
		public Builder(String name, String description) {
			super(name,description);
		}
		
		public Builder setRead(String read) {
			super.setRead(read);
			return this;
		}
		
		public Builder setTreasure() {
			super.setTreasure();
			return this;
		}
		
        @Override
        protected Builder self() {
            return this;
        }
		
		public ImmoveableItem build() {
			return new ImmoveableItem(this);
		}
		
	}

	@Override
	public String[] getNouns() {
		
		return super.getNouns();
	}

	@Override
	public String getName() {
		return super.getName();
	}

    // === Container-related methods (all return default values) ===
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
	public void setClosed() {	
		// No operation for immoveable items
	}

	@Override
	public void setLocked() {
		// No operation for immoveable items
	}

	@Override
	public boolean checkKey(Item key) {
		return false;
	}

	@Override
	public boolean getMoveable() {
		return false;
	}

	@Override
	public Optional<Exit> getHiddenExit() {
		return super.getHiddenExit();
	}

	@Override
	public Optional<Item> getHiddenItem() {
		return super.getHiddenItem();
	}

	@Override
	public boolean getMoved() {
		return false;
	}

	@Override
	public void setMoved() {
		// No operation for Immoveable items		
	}

	@Override
	public boolean checkHiddenExits() {
		return false;
	}

	@Override
	public boolean checkHiddenItems() {
		return false;
	}

	@Override
	public void addItem(CarriableItem item) {
		throw new UnsupportedOperationException("Immoveable items cannot contain other items");
	}
}

/* 25 August 2023 - Created file
 * 27 August 2023 - Added Comments
 * 14 September 2023 - Began adding item methods.
 * 23 January 2024 - Added methods to handle the moveable items
 * 25 January 2024 - Made class serialisable.
 * 11 Janaury 2025 - Added an addItem method
 * 23 August 2025 - Updated to have builder
 * 28 August 2025 - Changed visibility of constructor to protected.
 * 					Updated getHiddenItem & getHiddenExit
*/
