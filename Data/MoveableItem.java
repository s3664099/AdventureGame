/* Moveable Item Class
 * Created: 5 October 2023
 * Updated: 28 August 2025
 * Version: 1.2
 * Class for items that hide exits and items.
 */

package Data;

import java.util.Objects;
import java.util.Optional;
import java.io.Serializable;

public class MoveableItem extends ImmoveableItem implements Item, Serializable {
	
	private static final long serialVersionUID = 6462752323832943503L;
	private final Exit exit;
	private final Item item;
	private boolean isMoveable = true;
	private boolean hasMoved = false;
	private boolean itemRevealed;
	private boolean exitRevealed;
	
	private MoveableItem(Builder builder) {
		super(builder);
		this.exit = builder.exit;
		this.exitRevealed = builder.exitRevealed;
		this.item = builder.item;
		this.itemRevealed = builder.itemRevealed;
	}
	
	public static class Builder extends ImmoveableItem.Builder {
		
		public Exit exit;
		public Item item;
		public boolean itemRevealed;
		public boolean exitRevealed;
		
		public Builder(String name, String description) {
			super(name,description);
			this.exit = null;
			this.exitRevealed = true;
			this.item = null;
			this.itemRevealed = true;
		}
		
		public Builder setExit(Exit exit) {
			this.exit = Objects.requireNonNull(exit, "Exit cannot be null");
			this.exitRevealed = false;
			return this;
		}
		
		public Builder setItem(Item item) {
			this.item = Objects.requireNonNull(item, "Item cannot be null");
			this.itemRevealed = true;
			return this;
		}
		
		public Builder setRead(String read) {
			super.setRead(read);
			return this;
		}
		
        @Override
        protected Builder self() {
            return this;
        }
		
		public AbstractItem build() {
			return new MoveableItem(this);
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
	
	public boolean getMoveable() {
		return isMoveable;
	}
	
	public boolean getMoved() {
		return hasMoved;
	}
	
	public void setMoved() {
		hasMoved = true;
	}
	
	public Exit getExit() {
		return exit;
	}
	
	public Item getItem() {
		return item;
	}
	
	public Optional<Exit> getHiddenExit() {
		
		Optional<Exit> hiddenExit = null;
		if (!exitRevealed) {
			exitRevealed = true;
			hiddenExit = Optional.ofNullable(this.exit);
		}
		return hiddenExit;
	}
	
	public Optional<Item> getHiddenItem() {
		
		Optional<Item> hiddenItem = null;
		if(!itemRevealed) {
			hiddenItem = Optional.ofNullable(this.item);
			itemRevealed = true;
		}
		
		return hiddenItem;
	}
	
	@Override
	public boolean checkHiddenExits() {
		return exitRevealed;
	}

	@Override
	public boolean checkHiddenItems() {
		return itemRevealed;
	}	
}

/* 5 October 2023 - created class
 * 23 January 2024 - Added functionality
 * 26 January 2024 - Made class serializable
 * 10 May 2024 - May variables private
 * 29 Aug 2024 - Fixed problem where items/exits not being revealed. Added necessary
 * 				 methods.
 * 28 August 2025 - Updated class with builder
*/
