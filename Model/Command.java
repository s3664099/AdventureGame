/* Command Function
 * Created: 25 August 2023
 * Updated: 27 August 2023
 * Class that handles fuctions that deal with commands that are entered.
 */

package Model;

import Data.Location;

public class Command {
	
	Location currentLocation;
	
	public Location processCommand(String [] commands, Location location) {
		
		this.currentLocation = location;
				
		if (commands[0].equals("go")) {
			location = changeLocation(commands[1]);
		}
		return currentLocation;
	}
	
	private Location changeLocation(String command) {

		this.currentLocation = currentLocation.checkMove(command);
		
		return currentLocation;
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 */