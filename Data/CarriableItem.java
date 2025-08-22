/* Carriable Item Class
 * Created: 25 August 2023
 * Updated: 22 August 2025
 * Version: 1.1
 * The class for things that can be picked up and carried
 */

package Data;

import java.io.Serializable;

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
	
	//Methods for containers
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
	}

	@Override
	public void setLocked() {		
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
	public Exit getHiddenExit() {
		return null;
	}

	@Override
	public Item getHiddenItem() {
		return null;
	}

	@Override
	public boolean getMoved() {
		return false;
	}

	@Override
	public void setMoved() {}

	@Override
	public boolean checkHiddenExits() {
		return false;
	}

	@Override
	public boolean checkHiddenItems() {
		return false;
	}
}

/* 25 August 2023 - Created file.
 * 27 August 2023 - Added comments
 * 23 Janaury 2024 - Added methods for moveable item
 * 25 January 2024 - Made class serializable
 * 22 August 2025 - Created Builder subclass
 */