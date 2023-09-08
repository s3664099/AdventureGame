/* Command Function
 * Created: 25 August 2023
 * Updated: 8 September 2023
 * Version: 0.5
 * Class that handles fuctions that deal with commands that are entered.
 */

package Model;

import java.util.ArrayList;

import Data.Exit;
import Data.Location;

public class Command {
	
	private Location currentLocation;
	private String response = "";
	
	public Location getCurrentLocation() {
		return currentLocation;
	}
	
	//Executes the command
	public String processCommand(String [] commands, Location location) {
		
		//Sets the location
		this.currentLocation = location;

		//Standard response
		response = "I'm sorry, I do not understand";
		
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
	private void openExit(String command) {
		
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
					response = response.format("You open the %s",exit.getDescription());
				} else {
					response = response.format("The %s is already open",exit.getDescription());
				}
			} else {
				response = response.format("You cannot open the %s", exit.getDescription());
			}
		}
	}
	
	//Method for closing an exit
	private void closeExit(String command) {
		
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
					response = response.format("You close the %s",exit.getDescription());
				} else {
					response = response.format("The %s is already closed",exit.getDescription());
				}
			} else {
				response = response.format("You cannot close the %s", exit.getDescription());
			}
		}
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 4 August 2023 - Added the open and began the close command
 * 8 September 2023 - Moved movement processing and added open and close.
 */