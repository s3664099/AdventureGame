/* Command Function
 * Created: 25 August 2023
 * Updated: 10 October 2023
 * Version: 0.9
 * Class that handles fuctions that deal with commands that are entered.
 */

package Model;

import java.util.ArrayList;

import Data.CarriableItem;
import Data.Container;
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
	public String processCommand(String [] commands, Location location, ArrayList<Item> inventory) {
		
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
		} else if (commands[0].equals("i") || (commands[0].equals("inventory")
					|| (commands[0].equals("inv")))) {
			
			if (inventory.size() == 0) {
				response = "I am carrying nothing";
			} else {
				response = "I am carrying:";
				for (Item item:inventory) {
					response = response.format("%s %s", response, item.getName());
				}
			}
		} else if (commands[0].equals("look")) {
			response = look(commands,location);
		} else if ((commands[0].equals("take")) ||(commands[0].equals("get"))) {
			
			if (commands.length == 1) {
				response = "I need a verb";
			} else {
				response = switchList(location.getItems(), inventory, commands[1], "I picked up the",false);
			}
			
		} else if (commands[0].equals("drop")) {
			
			if (commands.length == 1) {
				response = "I need a verb";
			} else {
				response = switchList(inventory,location.getItems(),commands[1], "I dropped the",false);
			}			
		}
		
		return response;
	}
	
	//Method to move item from one list to another
	private String switchList(ArrayList<Item> listOne, ArrayList<Item> listTwo, String command, String statement, boolean itemTaken) {

		//Sets counter for items
		int itemNo = -1;
		int itemFound = -1;
		
		String response = "I don't see that here";
		
		//Goes through the items at the location
		for (Item item: listOne) {
			
			itemNo +=1;
			
			for (String noun:item.getNouns()) {
								
				//Is the item in this location
				if ((command.equals(noun)) && (!itemTaken)) {
					
					//Is it a carriable item.
					if (item instanceof CarriableItem) {

						//Add it to the player's inventory
						itemFound = itemNo;
						listTwo.add(item);
						response = response.format("%s %s",statement, item.getName());
						itemTaken = true;
					} else {
						response = "I cannot pick that up";
					}
				} else if ((item instanceof Container) && (!itemTaken)) {
					if (((Container) item).getViewed()) {
						
						ArrayList<Item> contents = ((Container) item).getContents();
						response = this.switchList(contents,listTwo,command,statement,itemTaken);
					}					
				} 
			}
		}
		
		//Has an item been picked up - Remove it from the location
		if (itemFound != -1) {
			listOne.remove(itemFound);
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
	
	private String look(String[] commands, Location location) {
		String response = "";
		
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
		
		int position = 0;
		boolean space = false; 
		
		for (int i=0;i<response.length();i++) {
			
			if ((position<100) && (!space)) {
				position += 1;
			} else {
				if (response.charAt(i) != ' ') {
					space = true;
				} else {
					response = response.substring(0,i+1) + "\n" + response.substring(i+1);
					position = 0;
					space = false;
				}
				
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
 * 2 October 2023 - Moved look into a separate method      
 * 9 October 2023 - Added ability to take items from a container once looked in them.
 * 10 October 2023 - Reworked take and drop so only one item is taken and dropped.              
 */