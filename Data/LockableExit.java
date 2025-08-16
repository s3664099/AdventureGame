/* LockableExit Class
 * Created: 11 October 2023
 * Updated: 16 August 2025
 * Version 1.6
 * Class to handle and exit that can be locked
 */

package Data;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.io.Serializable;

public class LockableExit extends CloseableExit implements Exit,Serializable {
	
	private static final long serialVersionUID = 5219530774578813240L;
	
	private final CarriableItem key;
	private boolean locked;
		
	//Exit with multiple commands
	private LockableExit(Builder builder) {
	
		super(builder);
		this.locked = builder.locked;
		this.key = Objects.requireNonNull(builder.key,"Key cannot be null");
	}
	
	public static class Builder extends CloseableExit.Builder {
		
		private boolean locked = false;
		private CarriableItem key = null;
		
		public Builder(String name, Location destination, boolean direction) {
			super (name,destination,direction);
		}
		
		public Builder setLocked(boolean locked) {
			this.locked = locked;
			return this;
		}
		
		public Builder setKey(CarriableItem key) {
			this.key = Objects.requireNonNull(key);
			return this;
		}
		
		@Override
		public LockableExit build() {
			if (key==null) {
				throw new IllegalStateException("Key must be set for lockable");
			}
			return new LockableExit(this);
		}
	}
	
	// === Locking behavoir ===
	public String attemptLockUnlock(CarriableItem attemptedKey, String action) {

		String response = "";
		
		if(!attemptedKey.equals(key)) {
			response = String.format("The %s doesn't work", attemptedKey.getName());
		} else {
			this.locked = !this.locked;
			response = String.format("You %s the %s with the %s", action, this.getName(), this.key.getName());
		}
		return response;
	}
	
	public boolean isLockable() {
		return true;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public Item getKey() {
		return key;
	}
	
	
	// === Movement ===
	public boolean canMoveThrough() {
		return locked
				? false
				: super.canMoveThrough();
	}
	
	public String getMoveDescription(String command) {
        return locked 
                ? String.format("The %s is locked%n", super.getName())
                : super.getMoveDescription();
	}
	
	// === Inherited Methods ===
	public boolean isOpen() {
		return super.isOpen();
	}
	
	@Override
	public boolean isOpenable() {
		return true;
	}
	
	public void toggleOpenClose() {
		super.toggleOpenClose();
	}
	
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
	
	public boolean isItemRevealed() {
		return super.isItemRevealed();
	}
	
	public void setItem(boolean updateReveal) {
		super.setItem(updateReveal);
	}
	
	public Optional<Item> getHiddenItem() {
		return super.getHiddenItem();
	}
}

/* 11 October 2023 - Created File
 * 13 October 2023 - Added Lockable specific methods
 * 26 January 2024 - Made class serializable
 * 7 April 2024 - Added functions for items to be revealed
 * 7 January 2025 - Added lockable to confirm that this object can be locked.
 * 11 August 2025 - Added serialised id
 * 12 August 2025 - Stated updating class to handle builder.
 * 13 August 2025 - Fixed remaining errors and finalised builder
 * 15 August 2025 - Sorted methods
 * 16 August 2025 - Removed setDescription
*/