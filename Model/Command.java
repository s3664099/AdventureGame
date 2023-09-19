/* Command Function
 * Created: 25 August 2023
 * Updated: 12 September 2023
 * Version: 0.6
 * Class that handles fuctions that deal with commands that are entered.
 */

package Model;

import java.util.ArrayList;

import Data.Exit;
import Data.Item;
import Data.Location;

public class Command {
	
	private Location currentLocation;
	private String response = "";
	private boolean displayLocation = true;
	
	public Location getCurrentLocation() {
		return currentLocation;
	}
	
	//Executes the command
	public String processCommand(String [] commands, Location location) {
		
		//Sets the location
		this.currentLocation = location;
		this.displayLocation = false;

		//Standard response
		response = "I'm sorry, I do not understand";
		
		String verb = "";
		
		if (commands.length>1) {
			verb = commands[1];
		}
		
		//Goes through the verbs
		if (commands[0].equals("go")) {
			response = changeLocation(verb);
			this.displayLocation = true;
		} else if (commands[0].equals("open")) {
			response = openExit(verb);
		} else if (commands[0].equals("close")) {
			response = closeExit(verb);
		} else if (commands[0].equals("look")) {
			
			response = "";
			
			//Is the player just looking around the room
			if ((commands.length==1) || (commands[1].equals("around")) ||
				 (commands[1].equals("room")) || (commands[1].equals("location"))) {
				response = response.format("%s%n=======================",location.getName(true));
			} else {
				
				//Checks if the player is looking at the exit/Items
				ArrayList<Exit> exits = location.getExits();
				ArrayList<Item> items = location.getItems();
				
				for (Exit exit:exits) {
					ArrayList<String> nouns = exit.getCommands();
					
					for (String noun:nouns) {
						if (noun.equals(commands[1])) {
							response = exit.getDescription();
						}
					}
				}
				
				if (response.length()==0) {
					for (Item item:items) {
						String[] nouns = item.getNouns();
						
						for (String noun:nouns) {
							if (noun.equals(commands[1])) {
								response = item.getDescription();
							}
						}
					}
				}
			}
			
			if (response.length()==0) {
				response = "I do not see that";
			}
		}
		
		return response;
	}
	
	private Exit getExits(String command) {
		
		Exit exit = null;
		
		//Gets all exits from location
		ArrayList<Exit> exits = currentLocation.getExits();
		
		for (Exit exitSearch:exits) {
			
			//Gets the commands
			for (String x:exitSearch.getCommands()) {
				
				//Checks if the exit matches the command
				if (command.equals(x)) {
					exit = exitSearch;
				}
			}
		}
		return exit;
	}
	
	public boolean displayLocation() {
		return displayLocation;
	}
	
	//Movement method
	private String changeLocation(String command) {

		response = "You are unable to move there";

		Exit exit = getExits(command);
		
		if (exit !=  null) {
			response = exit.moveDescription(command);
			if (exit.haveMoved()) {
				currentLocation = exit.getDestination();
			}
				
		}
		
		return response;
	}
	
	//Method to open an exit
	private String openExit(String command) {
		
		response = response.format("You cannot open the %s",command);
		
		Exit exit = getExits(command);
		
		//Checks if the exit is present
		if (exit == null) {
			response = response.format("I do not see a ", command);
		} else {
			
			//Is the exit openable?
			if (exit.isOpenable()) {
				
				//Is the exit open - if not the exit is opened.
				if (exit.getOpen()) {
					exit.openClose();
					response = response.format("You open the %s",exit.getName());
				} else {
					response = response.format("The %s is already open",exit.getName());
				}
			} else {
				response = response.format("You cannot open the %s", exit.getName());
			}
		}
		return response;
	}
	
	//Method for closing an exit
	private String closeExit(String command) {
		
		response = response.format("You cannot close the %s",command);
		
		Exit exit = getExits(command);
		
		//Checks if the exit is present
		if (exit == null) {
			response = response.format("I do not see a ", command);
		} else {
			
			//Is the exit openable?
			if (exit.isOpenable()) {
				
				//Is the exit open - if not the exit is opened.
				if (!exit.getOpen()) {
					exit.openClose();
					response = response.format("You close the %s",exit.getName());
				} else {
					response = response.format("The %s is already closed",exit.getName());
				}
			} else {
				response = response.format("You cannot close the %s", exit.getName());
			}
		}
		return response;
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 4 August 2023 - Added the open and began the close command
 * 8 September 2023 - Moved movement processing and added open and close.
 * 12 September 2023 - Started look at added response to open and close. Added the look
 *                     command for exits.
 */