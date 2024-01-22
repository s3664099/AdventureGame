/* Command Function
 * Created: 25 August 2023
 * Updated: 22 January 2024
 * Version: 0.11
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
		
		String noun = "";
		String verb = commands[0];
		
		if (commands.length>1) {
			noun = commands[1];
		}
				
		//Goes through the verbs
		if (verb.equals("go")) {
			response = changeLocation(noun);
			this.displayLocation = true;
		} else if (verb.equals("open") || verb.equals("close")) {
			response = openExit(noun,verb);
		} else if (verb.equals("i") || (verb.equals("inventory")
					|| (verb.equals("inv")))) {
			
			if (inventory.size() == 0) {
				response = "I am carrying nothing";
			} else {
				response = "I am carrying:";
				for (Item item:inventory) {
					response = response.format("%s %s", response, item.getName());
				}
			}
		} else if (verb.equals("look")) {
			response = look(commands,location);
		} else if ((verb.equals("take")) ||(verb.equals("get"))) {
			
			if (commands.length == 1) {
				response = "I need a verb";
			} else {
				response = switchList(location.getItems(), inventory, commands[1], "I picked up the",false);
			}
			
		} else if (verb.equals("drop")) {
			
			if (commands.length == 1) {
				response = "I need a verb";
			} else {
				response = switchList(inventory,location.getItems(),commands[1], "I dropped the",false);
			}			
		} else if (verb.equals("unlock") || verb.equals("lock")) {

			if (commands.length == 1) {
				response = "I need a verb";
			} else {
				response = unlock(inventory,location.getExits(),location.getItems(), noun, verb);
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
	private String openExit(String command,String verb) {
		
		response = response.format("You cannot open the %s",command);
		
		Exit exit = getExits(command);
		
		//Checks if the exit is present
		if (exit == null) {
			response = response.format("I do not see a ", command);
		} else {
			
			//Is the exit openable?
			if (exit.isOpenable() && !exit.getLocked()) {

				if (verb.equals("open")) {
					//Is the exit open - if not the exit is opened.
					if (exit.getOpen()) {
						exit.openClose();
						response = response.format("You open the %s",exit.getName());
					} else {
						response = response.format("The %s is already open",exit.getName());
					}
				} else if (verb.equals("close")) {
					//Is the exit open - if not the exit is opened.
					if (!exit.getOpen()) {
						exit.openClose();
						response = response.format("You close the %s",exit.getName());
					} else {
						response = response.format("The %s is already closed",exit.getName());
					}					
				}
			} else if (exit.getLocked()) {
				response = response.format("The %s is locked",exit.getName());
			} else {
				response = response.format("You cannot open the %s", exit.getName());
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
			
			//Exits
			for (Exit exit:exits) {
				ArrayList<String> nouns = exit.getCommands();
				
				for (String noun:nouns) {
					if (noun.equals(commands[1])) {
						response = exit.getDescription();
					}
				}
			}
			
			//Items
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
	
	private String unlock(ArrayList<Item> inventory, ArrayList<Exit>exits, ArrayList<Item>items,String command, String verb) {
	
		response = "I do not see that here";
		boolean found = false;
		boolean foundItem = false;
		
		//Checks to see if the player is attempting to unlock an exit
		for (Exit exit:exits) {
			for (String noun:exit.getCommands()) {
				
				//The item is an exit in the area
				if (command.equals(noun) && (!found)) {
					foundItem = true;
					for (Item item:inventory) {
						
						if (item == exit.getKey()) {
							found = true;
							
							//Acts on either lock/unlock, which does the opposite.
							if (verb.equals("unlock")) {
								if (exit.getLocked()) {
									response = exit.lockUnlock((CarriableItem) item, verb);
								} else {
									response = response.format("The %s is already unlocked",exit.getName());
								}
							} else if (verb.equals("lock")) {
								if (!exit.getLocked()) {
									response = exit.lockUnlock((CarriableItem) item, verb);
								} else {
									response = response.format("The %s is already locked",exit.getName());
								}								
							}
						}
					}
					if (!found) {
						response = "You don't have the key";
					}
				}
			}
		}
		
		//It wasn't an exit, so we now check the items.
		if (!foundItem) {
			for (Item container:items) {
				for (String noun:container.getNouns()) {
					
					//The container is an item in the area
					if (command.equals(noun) && (!found)) {

						//The container isn't lockable
						if(!container.getLockable()) {
							response = response.format("The %s isn't lockable",container.getName());
						} else {
							
							//Checks if player has the key
							for (Item item:inventory) {
								if (container.checkKey(item)) {
									found = true;
								
									//Acts on either lock/unlock, which does the opposite.
									if (verb.equals("unlock")) {
										if (container.getLocked()) {
											container.setLocked();
											response = response.format("You unlock the %s",container.getName());
										} else {
											response = response.format("The %s is already unlocked",container.getName());
										}
									} else if (verb.equals("lock")) {
										if (!container.getLocked()) {
											container.setLocked();
											response = response.format("You lock the %s",container.getName());
										} else {
											response = response.format("The %s is already locked",container.getName());
										}
									}								
								}
							}
							if (!found) {
								response = "You don't have the key";
							}		
						}
					}
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
 * 11 October 2023 - Started Unlock Command  
 * 13 October 2023 - Began working on the unlock command    
 * 16 November 2023 - Tightend code to make open/close lock/unlock the same function    
 * 22 January 2023 - Added the lock/unlock for the containers
 */