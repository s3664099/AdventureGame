/* CloseableExit Class
 * Created: 4 September 2023
 * Updated: 11 August 2025
 * Version 1.2
 * Class to handle and exit that can be closed
 */

package Data;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.io.Serializable;

public class CloseableExit extends AbstractExit implements Exit, Serializable  {
	
	private static final long serialVersionUID = 8276805139765346452L;
	
	private final Item item;
	
	private boolean closed;
	private boolean itemRevealed;
		
	//Exit with multiple commands
	protected CloseableExit(Builder builder) {
	
		super(builder);
		this.closed = builder.closed;
		this.itemRevealed = builder.itemRevealed;
		this.item = builder.item;

	}
	
	public static class Builder extends AbstractExit.Builder {
		
		private boolean closed;
		private Item item;
		private boolean itemRevealed;
		
		public Builder(String name, Location destination, boolean direction) {
			super(name,destination,direction);		
			this.closed = false;
			this.itemRevealed = false;
			this.item = null;
		}
		
		public Builder addCommand(String command) {
			super.addCommand(command);
			return this;
		}
		
		public Builder addDescritpion(String description) {
			super.addDescription(description);
			return this;
		}
		
		public Builder addClosed(boolean closed) {
			this.closed = closed;
			return this;
		}
		
		public Builder addItem(Item item) {
			this.item = Objects.requireNonNull(item, "Item cannot be null");
			return this;
		}
		
		public Builder addItemRevealed(boolean itemRevealed) {
			this.itemRevealed = itemRevealed;
			return this;
		}
		
	    public CloseableExit build() {
	        return new CloseableExit(this);
	    }
	}
	
	//=== Basic Getters ===
	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public List<String> getCommandSynonyms() {
		return super.getCommandSynonyms();
	}

	@Override
	public Location getDestination() {
		return super.getDestination();
	}
	
	@Override
	public String getDescription() {
		return super.getDescription();
	}
	
	// === Open/Close behaviour ===
	@Override
	public boolean isOpenable() {
		return true;
	}
	
	public boolean isOpen() {
		return this.closed;
	}
	
	public void toggleOpenClose() {
		this.closed = !this.closed;
	}
	
	public boolean canMoveThrough() {
		return !closed;
	}
	
	public String getMoveDirection() {
		
        return closed 
                ? String.format("The %s is closed%n", super.getName())
                : String.format("You enter the %s%n", super.getName());
	}
	
	// === Item Management ===
    @Override
    public Optional<Item> getHiddenItem() {
        return Optional.ofNullable(this.item);
    }
    	
	public boolean isItemRevealed() {
		return this.itemRevealed;
	}
	
	public void setItem(boolean itemRevealed) {
		this.itemRevealed = itemRevealed;
	}

	//=== Unsupported Operations ===
	public boolean isLocked() {
		return false;
	}
	
	public boolean isLockable() {
		return false;
	}
	
	@Override
	public Item getKey() {
		return null;
	}

	@Override
	public String attemptLockUnlock(CarriableItem item, String action) {
		return null;
	}
}

/* 4 September 2023 - Created File
 * 6 September 2023 - Fixed file to call abstractExit and match interface
 * 12 September 2023 - Added functionality to handle exit descriptions
 * 11 October 2023 - Added check for lockable
 * 13 October 2023 - Added unimplemented methods
 * 25 January 2024 - Made Class Serializable
 * 5 August 2025 - Fixed minor issues
 * 11 August 2025 - Updated class to use builder
 */