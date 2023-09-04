/* Command Function
 * Created: 25 August 2023
 * Updated: 4 September 2023
 * Version: 0.3
 * Class that handles fuctions that deal with commands that are entered.
 */

package Model;

import Data.Location;

public class Command {
	
	Location currentLocation;
	
	public Location processCommand(String [] commands, Location location) {
		
		this.currentLocation = location;
		
		String verb = "";
		
		if (commands.length>1) {
			verb = commands[1];
		}
				
		if (commands[0].equals("go")) {
			location = changeLocation(verb);
		} else if (commands[0].equals("open")) {
			openExit(verb);
		} else if (commands[0].equals("close")) {
			closeExit(verb);
		}
		
		return currentLocation;
	}
	
	private Location changeLocation(String command) {

		this.currentLocation = currentLocation.checkMove(command);
		
		return currentLocation;
	}
	
	private void openExit(String command) {
		
		currentLocation.openExit(command);
	}
	
	private void closeExit(String command) {
		System.out.println("Opened");
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 4 August 2023 - Added the open and began the close command
 */