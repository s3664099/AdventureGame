/* LockableExit Class
 * Created: 11 October 2023
 * Updated: 12 August 2025
 * Version 1.3
 * Class to handle and exit that can be locked
 */

package Data;

import java.util.ArrayList;
import java.util.Objects;
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
		
		private boolean locked;
		private CarriableItem key;
		
		public Builder(String name, Location destination, boolean direction) {
			super (name,destination,direction);
			locked = false;
			key = null;
		}
	}
	
	//Returns the description of what happens when attempt to move
	public String moveDescription(String command) {
		
        return locked 
                ? String.format("The %s is closed%n", super.getName())
                : super.getMoveDirection();
	}
	
	//Checks if it is possible to move in that direction
	public boolean canMoveThrough() {
		return locked
				? false
				: super.canMoveThrough();
	}
	
	public boolean isOpen() {
		return super.isOpen();
	}
	
	public void toggleOpenClose() {
		super.toggleOpenClose();
	}

	@Override
	public String getName() {
		
		return super.getName();
	}

	@Override
	public ArrayList<String> getCommandSynonyms() {
		
		return super.getCommandSynonyms();
	}

	@Override
	public Location getDestination() {
		
		return super.getDestination();
	}

	public String attemptLockUnlock(CarriableItem item, String action) {
		this.locked = !this.locked;
		return String.format("You %s the %s with the %s", action, this.getName(), this.key.getName());
	}

	//Flags that the exit can be opened/closed
	@Override
	public boolean isOpenable() {
		return true;
	}
	
	public boolean isLockable() {
		return true;
	}

	@Override
	public String getDescription() {
		return super.getDescription();
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public Item getKey() {
		return key;
	}
	
	public boolean isItemRevealed() {
		return super.isItemRevealed();
	}
	
	public void setItem(boolean updateReveal) {
		super.setItem(updateReveal);
	}
	
	public Item getHiddenItem() {
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
*/