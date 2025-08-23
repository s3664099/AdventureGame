/* Carriable Item Class
 * Created: 25 August 2023
 * Updated: 23 August 2025
 * Version: 1.2
 * The class for things that can be picked up and carried
 */

package Data;

import java.io.Serializable;
import java.util.Optional;

public class CarriableItem extends AbstractItem implements Item, Serializable {

	private static final long serialVersionUID = -8200700530371084935L;

	private CarriableItem(Builder builder) {
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
		
		public AbstractItem build() {
			return new CarriableItem(this);
		}
		
	}
	
	public String getDescription() {
		return super.getDescription();
	}
	
	public String[] getNouns() {
		return super.getNouns();
	}
	
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
        // No operation for carriable items
	}

	@Override
	public void setLocked() {
        // No operation for carriable items
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
        // No operation for basic carriable items
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
        throw new UnsupportedOperationException("Carriable items cannot contain other items");
    }
}

/* 25 August 2023 - Created file.
 * 27 August 2023 - Added comments
 * 23 Janaury 2024 - Added methods for moveable item
 * 25 January 2024 - Made class serializable
 * 22 August 2025 - Created Builder subclass
 * 23 August 2025 - Updated class based on recommendations
 */