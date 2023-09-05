/* Ordinary Exit Class
 * Created: 25 August 2023
 * Updated: 5 September 2023
 * Version 0.4
 * Class to handle everything to do with an exit.
 */

package Data;

import java.util.ArrayList;

public class OrdinaryExit extends AbstractExit implements Exit {
	
	//Standard Exit
	public OrdinaryExit(String description, Location destination, boolean direction) {
		
		super(description, destination, direction);
	}
		
	//Exit with multiple commands
	public OrdinaryExit(String description, String command, Location destination, boolean direction) {
		super(description, destination, direction, command);
	}
		
	public boolean haveMoved() {
		return true;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public ArrayList<String> getCommands() {
		return this.commands;
	}
	
	public Location getDestination() {
		return this.destination;
	}
	
	public void openClose() {}

	@Override
	public String moveDescription(String Command) {
		
		return null;
	}

	@Override
	public void lockUnlock() {};

}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 29 August 2023 - Added open/close lock/unlock. Added multiple commands for exits
 * 4 September 2023 - Created subclass for closeable Exit
 * 5 September 2023 - Refactored class & created Abstract and Interface
*/