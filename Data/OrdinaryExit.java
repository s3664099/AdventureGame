/* Ordinary Exit Class
 * Created: 25 August 2023
 * Updated: 12 August 2025
 * Version 1.4
 * Class to handle everything to do with an exit.
 */

package Data;

import java.util.List;
import java.util.Optional;
import java.io.Serializable;

public class OrdinaryExit extends AbstractExit implements Exit,Serializable {
		
	private static final long serialVersionUID = 5320873411231936585L;

	public OrdinaryExit(Builder builder) {
		super(builder);
	}
	
	public static class Builder extends AbstractExit.Builder {
				
		public Builder(String name,Location destination,
						boolean direction) {
			super(name,destination,direction);
		}
		
		public Builder addCommand(String command) {
			super.addCommand(command);
			return this;
		}
		
		public Builder addDescritpion(String description) {
			super.addDescription(description);
			return this;
		}
		
		public OrdinaryExit build() {
			return new OrdinaryExit(this);
		}
	}
		
	//Exit is always moveable
	public boolean canMoveThrough() {
		return true;
	}
	
	public String getName() {
		return super.getName();
	}
	
	public String getDescription() {
		return super.getDescription();
	}
	
	public List<String> getCommandSynonyms() {
		return super.getCommandSynonyms();
	}
	
	public Location getDestination() {
		return super.getDestination();
	}
	
	public void toggleOpenClose() {}

	//Returns the description of the player attempts to move
	public String getMoveDirection() {
        return super.getDirection() 
                ? String.format("You head %s%n", super.getName())
                : String.format("You enter the %s%n", super.getName());
	}

	@Override
	public boolean isOpen() {
		return true;
	}

	//Is the exit openable
	@Override
	public boolean isOpenable() {
		return false;
	};
	
	public boolean isLockable() {
		return false;
	}

	@Override
	public boolean isLocked() {
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

	@Override
	public Optional<Item> getHiddenItem() {
		return null;
	}

	@Override
	public void setItem(boolean updateReveal) {		
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 29 August 2023 - Added open/close lock/unlock. Added multiple commands for exits
 * 4 September 2023 - Created subclass for closeable Exit
 * 5 September 2023 - Refactored class & created Abstract and Interface
 * 6 September 2023 - Fixed up Abstract class and add description
 * 12 September 2023 - Added functionality to handle exit description
 * 11 October 2023 - Added lockable function
 * 13 October 2023 - Added methods for exits to close and lock
 * 26 January 2024 - Made Object Serializable
 * 21 February 2024 - Added an add description method
 * 3 January 2025 - Changed getOpen to default to true
 * 8 August 2025 - Added Builder Class. Added null protections.
 * 9 August 2025 - Fixed moveDescription
 * 11 August 2025 - Fixed problem with builder not matching
 * 12 August 2025 - Updated get item to optional
*/