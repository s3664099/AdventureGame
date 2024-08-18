/* Ordinary Exit Class
 * Created: 25 August 2023
 * Updated: 21 February 2024
 * Version 1.0
 * Class to handle everything to do with an exit.
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public class OrdinaryExit extends AbstractExit implements Exit,Serializable {
	
	//Standard Exit
	public OrdinaryExit(String name, Location destination, boolean direction) {
		super(name, destination, direction);
	}
		
	//Exit with multiple commands
	public OrdinaryExit(String name, String command, Location destination, 
						boolean direction, String description) {
		super(name, destination, direction, command, description);
	}
	
	public void addDescription(String description) {
		super.addDescription(description);
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
	
	public ArrayList<String> getCommands() {
		return super.getCommands();
	}
	
	public Location getDestination() {
		return super.getDestination();
	}
	
	public void openClose() {}

	//Returns the description of the player attempts to move
	@Override
	public String moveDescription(String Command) {
		
		String moveDescription = "";
		
		if (super.getDirection()) {
			moveDescription.format("You head %s%n",getName());
		} else {
			moveDescription.format("You enter the %s%n",getName());
		}
		
		return moveDescription;
	}

	@Override
	public boolean getOpen() {
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lockUnlock(CarriableItem item, String action) {
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
*/