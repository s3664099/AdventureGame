/* Ordinary Exit Class
 * Created: 25 August 2023
 * Updated: 8 August 2025
 * Version 1.2
 * Class to handle everything to do with an exit.
 * 
 * Remove parameter from move description
 */

package Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;

public class OrdinaryExit extends AbstractExit implements Exit,Serializable {
		
	private static final long serialVersionUID = 5320873411231936585L;

	public OrdinaryExit(Builder builder) {
		super(builder);
	}
	
	public static class Builder {
		
		private final String name;
		private final List<String> command = new ArrayList<String>();
		private final Location destination;
		private final boolean direction;
		
		private String description;
		
		public Builder(String name,Location destination,
						boolean direction,String description) {
			this.name = Objects.requireNonNull(name, "Name cannot be null");
			this.command.add(Objects.requireNonNull(name, "Name cannot be null"));
			this.destination = Objects.requireNonNull(destination, "Destination cannot be null");
			this.description = Objects.requireNonNull(description, "Description cannot be null");
			this.direction = Objects.requireNonNull(direction, "Direction cannot be null");
		}
		
		public Builder addCommand(String command) {
			this.command.add(Objects.requireNonNull(command, "Command cannot be null"));
			return this;
		}
		
		public Builder addDescritpion(String description) {
			this.description = Objects.requireNonNull(description, "Description cannot be null");
			return this;
		}
	}
		
	//Exit is always moveable
	public boolean haveMoved() {
		return true;
	}
	
	public String getName() {
		return super.getName();
	}
	
	public String getDescription() {
		return super.getDescription();
	}
	
	public List<String> getCommands() {
		return super.getCommands();
	}
	
	public Location getDestination() {
		return super.getDestination();
	}
	
	public void openClose() {}

	//Returns the description of the player attempts to move
	public String moveDescription() {
		
		String moveDescription = "";
		
		if (super.getDirection()) {
			moveDescription = String.format("You head %s%n",getName());
		} else {
			moveDescription = String.format("You enter the %s%n",getName());
		}
		
		return moveDescription;
	}

	@Override
	public boolean getOpen() {
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
	public boolean getLocked() {
		return false;
	}

	@Override
	public Item getKey() {
		return null;
	}

	@Override
	public String lockUnlock(CarriableItem item, String action) {
		return null;
	}

	@Override
	public String moveDescription(String Command) {
		// TODO Auto-generated method stub
		return null;
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
*/