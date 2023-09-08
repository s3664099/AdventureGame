/* Command Function
 * Created: 25 August 2023
 * Updated: 4 September 2023
 * Version: 0.3
 * Class that handles fuctions that deal with commands that are entered.
 */

package Model;

import java.util.ArrayList;

import Data.Exit;
import Data.Location;

public class Command {
	
	private Location currentLocation;
	
	public Location getCurrentLocation() {
		return currentLocation;
	}
	
	//Executes the command
	public String processCommand(String [] commands, Location location) {
		
		//Sets the location
		this.currentLocation = location;

		//Standard response
		String response = "I'm sorry, I do not understand";
		
		String verb = "";
		
		if (commands.length>1) {
			verb = commands[1];
		}
		
		//Goes through the verbs
		if (commands[0].equals("go")) {
			response = changeLocation(verb);
		} else if (commands[0].equals("open")) {
			openExit(verb);
		} else if (commands[0].equals("close")) {
			closeExit(verb);
		}
		
		return response;
	}
	
	//Movement method
	private String changeLocation(String command) {

		String response = "You are unable to move there";

		//Gets all exits from location
		ArrayList<Exit> exits = currentLocation.getExits();
		
		for (Exit exit:exits) {
			for (String x:exit.getCommands()) {
				
				//Checks if the exit matches the command
				if (command.equals(x)) {
					
					//Checks if the player can move through the direction
					response = exit.moveDescription(command);
					if (exit.haveMoved()) {
						currentLocation = exit.getDestination();
					}
				}
			}
		}
		
		return response;
	}
	
	private void openExit(String command) {
		
		//currentLocation.openExit(command);
	}
	
	private void closeExit(String command) {
		System.out.println("Opened");
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 4 August 2023 - Added the open and began the close command
 */