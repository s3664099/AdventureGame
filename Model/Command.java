/* Command Function
 * Created: 25 August 2023
 * Updated: 17 August 2024
 * Version: 0.26
 * Class that handles fuctions that deal with commands that are entered.
 */

package Model;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Data.CarriableItem;
import Data.Container;
import Data.Conversation;
import Data.Exit;
import Data.Item;
import Data.Location;
import Control.Input;

public class Command {
	
	private Location currentLocation;
	private String response = "";
	private boolean displayLocation = true;
	private int score = 0;
	private int topScore = 0;
	private ArrayList<Item> inventory;
	
	public Command(int topScore) {
		this.topScore = topScore;
	}
	
	public Location getCurrentLocation() {
		return currentLocation;
	}
		
	//Executes the command
	public String processCommand(String [] commands, Location location, ArrayList<Item> inventory,int score) {
		
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
			response = look(commands,location,inventory);
		} else if ((verb.equals("take")) ||(verb.equals("get"))) {
			
			if (commands.length == 1) {
				response = "I need a verb";
			} else {
				response = switchList(location.getItems(), inventory, commands[1], "I picked up the",false);
				this.score = changeScore(false,verb,inventory,location); 
			}
			
		} else if (verb.equals("drop")) {
			
			if (commands.length == 1) {
				response = "I need a verb";
			} else {
				response = switchList(inventory,location.getItems(),commands[1], "I dropped the",false);
				this.score = changeScore(true,verb,inventory,location);
			}			
		} else if (verb.equals("unlock") || verb.equals("lock")) {

			if (commands.length == 1) {
				response = "I need a verb";
			} else {
				response = unlock(inventory,location.getExits(),location.getItems(), noun, verb);
			}
		} else if (verb.equals("move")) {
			
			if (commands.length == 1) {
				response = "I need a verb";
			} else {
				response = move(location,noun);
			}
			
		} else if (verb.equals("save")) {
			
			//Checks if there is a name, and calls the save game function
			if (commands.length == 1) {
				response = "Please include a name for the saved game";
			} else {
				response = saveGame(location,inventory,score,noun);
			}
			
		} else if (verb.equals("load")) {
			
			//Checks if there is a name, and calls the load game function
			if (commands.length == 1) {
				
				//If only one name, displays a list of save game files.
				response = getGameList();
				
			} else {
				response = loadGame(noun);
			}
			
		} else if ((verb.equals("talk")) || (verb.equals("speak"))) {
			response = conversation(noun, location.getItems());
		} else if (verb.equals("score")) {
			response = response.format("Your score is %s/%s",this.score,this.topScore);
		} else if (verb.equals("quit")) {
			response = "END";
		}
		
		return response;
	}
	
	//Checks if the player has completed the game
	public boolean checkScore() {
		
		boolean success = false;
		return success;
	}
	
	private Item findItem(String command, ArrayList<Item> itemList) {
		Item foundItem = null;
		
		for (Item item:itemList) {
			for (String noun:item.getNouns()) {
				if((command.equals(noun)) && (foundItem == null)) {
					foundItem = item;
				}
			}
		}
		
		return foundItem;
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
					if ((((Container) item).getViewed()) && (!((Container) item).getClosed())) {
						
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
	
	//Checks if the item is available
	private Item getItems(String command) {
		
		Item item = null;
		
		//Gets all items from location
		ArrayList<Item> items = currentLocation.getItems();
		
		for (Item itemSearch:items) {
			
			//Gets the commands
			for (String x:itemSearch.getNouns()) {
				
				if (command.equals(x)) {
					item = itemSearch;
				}
			}		
		}
		return item;
	}
	
	//Checks if the exit is available
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
	
	//Method to open an exit/container
	private String openExit(String command,String verb) {
		
		response = response.format("You do not see a %s",command);
		
		Exit exit = getExits(command);
		
		//Checks if the exit is present
		if (exit != null) {
			
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

		//There were no exits
		} else {
			
			Item item = getItems(command);

			//Checks if the exit is present
			if (item != null) {
				
				//Is the exit openable?
				if (item.getCloseable() && !item.getLocked()) {

					if (verb.equals("open")) {
						
						//Is the exit open - if not the exit is opened.
						if (item.getClosed()) {
							item.setClosed();
							response = response.format("You open the %s",item.getName());
						} else {
							response = response.format("The %s is already open",item.getName());
						}
					} else if (verb.equals("close")) {
						
						//Is the exit open - if not the exit is opened.
						if (!item.getClosed()) {
							item.setClosed();
							response = response.format("You close the %s",item.getName());
						} else {
							response = response.format("The %s is already closed",item.getName());
						}					
					}
				} else if (item.getLocked()) {
					response = response.format("The %s is locked",item.getName());
				} else {
					response = response.format("You cannot open the %s", item.getName());
				}
			}
		}
				
		return response;
	}
		
	private String look(String[] commands, Location location, ArrayList<Item> inventory) {
		String response = "";
		
		//Is the player just looking around the room
		if ((commands.length==1) || (commands[1].equals("around")) ||
			 (commands[1].equals("room")) || (commands[1].equals("location"))) {
			response = response.format("%s%n=======================",location.getName(true));
		} else {
			
			//Checks if the player is looking at the exit/Items
			ArrayList<Exit> exits = location.getExits();
			ArrayList<Item> items = location.getItems();
			
			boolean removeItem = false;
			int itemIndex = 0;
			int index = 0;
			
			//Exits
			for (Exit exit:exits) {
				ArrayList<String> nouns = exit.getCommands();
				
				for (String noun:nouns) {
					if (noun.equals(commands[1])) {
						response = exit.getDescription();
					}
				}
			}
			
			//Inventory
			if (response.length()==0) {
				for (Item item:inventory) {
					String[] nouns = item.getNouns();
					
					for (String noun:nouns) {
						if (noun.equals(commands[1])) {
							response = item.getDescription();
							itemIndex = index;
						}
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
							itemIndex = index;
							
							//Is the item hiding something
							if (item.checkIsCover()) {
								
								int noTypes = 0;
								int objectType = 0;
								
								//Checks whether item hiding items, exits, or both
								if (item.checkHidden(false)) {
									noTypes +=1;
									objectType = 1;
								} else if (item.checkHidden(true)) {
									noTypes +=1;
									objectType = 2;
								}
								
								//Picks either a random item or exit
								if (noTypes == 2) {
									
									int option = (int)(Math.random()*noTypes);
									
									if (option == 0) {
										objectType = 1;
									} else {
										objectType = 2;
									}									
								}
								
								//Selects a random object from the list
								if (objectType == 1) {
									
									Item foundItem = item.getHiddenItem();
									response = response.format("%s%nYou found %s",response,foundItem.getName());
									location.addItem(foundItem);
								
								//Selects a random exit from the list
								} else if (objectType == 2) {
									
									Exit foundExit = item.getHiddenExit();
									response = response.format("%s%nYou found %s",response,foundExit.getName());
									location.addExit(foundExit);
								}
								
								//Checks if the item is to be removed from the location
								if (item.checkRemove()) {
								
									//if the exit and item lists are empty. If so it flags to remove the item
									if ((!item.checkHidden(true)) && (!item.checkHidden(false))) {
										removeItem = true;
									}
								}
							} 
						}
					}
					index ++;
				}
			}
			
			//Checks if the item is to be removed, and removes it.
			if (removeItem) {
				items.remove(itemIndex);
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
									
									//If exit is open, cannot be locked
									if (!exit.getOpen()) {
										response = response.format("The %s is open. Please close it first", exit.getName());
									} else {
										response = exit.lockUnlock((CarriableItem) item, verb);
									}
									
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
										
										//Checks if the container is already locked.
										if (container.getLocked()) {
											container.setLocked();
											response = response.format("You unlock the %s",container.getName());
										} else {
											response = response.format("The %s is already unlocked",container.getName());
										}
										
									} else if (verb.equals("lock")) {
										
										//Checks if the container is already locked
										if (!container.getLocked()) {
											
											//Checks if the container is open. Cannot lock and open conmtainer
											if (!container.getClosed()) {
												response = response.format("The %s is still open. Please close it first", container.getName());
											} else {
											container.setLocked();
											response = response.format("You lock the %s",container.getName());
											}
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
	
	//Function to move an item that can be moved
	private String move(Location location, String verb) {
		
		ArrayList<Item> items = location.getItems();
		String response = "";
		response = response.format("I do not see a %s here", verb);
		Item hiddenItem = null;
		Boolean foundItem = false;
		Boolean itemRemoved = false;
		Boolean exitRemoved = false;
		int itemIndex = 0;
		int itemRemove = -1;
		
		for (Item item:items) {
						
			for (String noun:item.getNouns()) {
							
				if ((verb.equals(noun)) && (!foundItem)) {
					if ((!item.getMoveable()) || (item.getMoved())) {
						response = response.format("I cannot move the %s", item.getName());
					} else {
						
						//Flags the item that has been moved and adds the hidden item to the location
						item.setMoved();
						foundItem = true;

						if (item.checkHiddenItems()) {
							hiddenItem = item.getHiddenItem();
							response = response.format("You move the %s and discover a %s",
									item.getName(),hiddenItem.getName());
							itemRemoved = true;
						} else {
							if (item.checkHiddenExits()) {
								Exit exit = item.getHiddenExit();
								location.addExit(exit);
								response = response.format("You move the %s and discover a %s",
									item.getName(),exit.getDescription());
								exitRemoved = true;
							} else {
								response = response.format("You move the %s and don't find anything",
										item.getName());
							}
						}
						
						//Checks if item to be removed
						if (item.checkRemove()) {

							//Has an item been removed and there are no exits
							if ((itemRemoved) && (!item.checkHiddenItems())) {
								if (!item.checkHiddenExits()) {
									itemRemove = itemIndex;
								}
							}
							
							//Has an exit been removed and there are no items
							if ((exitRemoved) && (!item.checkHiddenExits())) {
								if (!item.checkHiddenItems()) {
									itemRemove = itemIndex;
								}
							}
						}
					}
				}
				itemIndex ++;
			}
		}
		
		if(!foundItem) {
			for (Exit exit:location.getExits()) {
				for (String noun:exit.getCommands()) {
					if ((verb.equals(noun)) && (!foundItem)) {
						response = response.format("You cannot move the %s",exit.getName());
						foundItem = true;
					}
				}
			}
		}
		
		if (itemRemoved) {
			location.addItem(hiddenItem);
		}

		//Checks if the item is to be removed. If so, removes the item from the list
		if (itemRemove != -1) {
			items.remove(itemRemove);
		}
		
		return response;
	}
	
	//Handles increasing/decreasing the score based on whether the player is taking an item from
	//a treasure store, or dropping it.
	private int changeScore(boolean action,String verb, ArrayList<Item> items,Location location) {
		
		int score = 0;
		
		//Drop action
		if (action) {
			
			//If the player in the treasure store
			if (location.getTreasureStore()) {
				
				//Checks if the item is a treasure
				for (Item item:items) {
					for (String noun:item.getNouns()) {
						if (noun.equals(verb)) {
							if (item.getTreasure()) {
								score =1;
							}
						}
					}
				}
			}
		
		//The player is removing a treasure
		} else {
			
			//If the player in the treasure store
			if (location.getTreasureStore()) {
				
				//Checks if the item is a treasure
				for (Item item:items) {
					for (String noun:item.getNouns()) {
						if (noun.equals(verb)) {
							if (item.getTreasure()) {
								score =-1;
							}
						}
					}
				}
			}			
		}
		return score;
	}
	
	//Gets the score and resets it to zero
	public int getScore() {
		int score = this.score;
		this.score = 0;
		return score;
	}
	
	//save Game method
	public String saveGame(Location location, ArrayList<Item> inventory, int score, String saveName) {

		boolean writeFile = false;
				
		//Adds the inventory and score to the location.
		location.savePlayer(inventory, score);
		
		File saveGameDirectory = new File("savegames");
				
		//Checks to see if the directory exists. If it doesn't it creates the directory
		if(!saveGameDirectory.exists()) {
			saveGameDirectory.mkdir();
		}
				
		File saveFile = new File(saveGameDirectory+"/"+saveName+".sav");
				
		//Checks to see if the file exists
		if (saveFile.exists()) {
					
			//If it is, asks if the user would like to overwrite it
			System.out.printf("The file %s already exists, do you wish to overwrite it (Y/n)? ",saveName);
					
			Input query = new Input();
			writeFile = query.getYesNo();
		} else {
			writeFile = true;
		}
		
		//Writes file	
		if (writeFile) {
			
			try {
				FileOutputStream file = new FileOutputStream(saveGameDirectory+"/"+saveName+".sav");
				ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(location);
				out.close();
				file.close();
				response = response.format("Game saved as %s.sav",saveName);
			} catch (IOException e) {
				response = "Game failed to save";
				e.printStackTrace();
			}
		} else {
			response = "Game not saved";
		}
				
		return response;
	}
	
	//Load Game Function
	private String loadGame(String gameName) {

		response = "";
		
		boolean loadFile = false;
				
		//Checks to see if the file exists
		File saveGameDirectory = new File("savegames");				
		File saveFile = new File(saveGameDirectory+"/"+gameName+".sav");		
		
		//If not available, displays list of games
		if (!saveFile.exists()) {			
			response = response.format("Sorry %s does not exist. The files that exist are %n%s", gameName,getGameList());
		} else {
			loadFile = true;
		}
		
		if (loadFile) {
		
			//Attempts to load the file
			try {
				FileInputStream file = new FileInputStream(saveGameDirectory+"/"+gameName+".sav");
				ObjectInputStream fileIn = new ObjectInputStream(file);
				
				//Load successful. Sets location, inventory & score
				this.currentLocation = (Location) fileIn.readObject();
				this.score = this.currentLocation.getScore();
				
				if (this.currentLocation.getInventory() != null) {
					this.inventory = this.currentLocation.getInventory();
				}
				
				fileIn.close();
				file.close();
				response = response.format("%s.sav successfully loaded",gameName);
							
			//Location failed to load
			} catch (IOException|ClassNotFoundException e) {
				response = response.format("%s.sav failed to load",gameName);
				e.printStackTrace();
			}
			
		}
		return response;
	}
	
	//Get load game list
	private String getGameList() {
		response = "";
		
		File saveGameDirectory = new File("savegames");
		
		//Checks if the directory exists
		if ((saveGameDirectory.exists()) && (saveGameDirectory.isDirectory())) {
			File[] savFiles = saveGameDirectory.listFiles( new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".sav");
				};
			});
			
			//Returns a list of saved games
			response = response.format("The available save games are:%n");
			
			for (File file:savFiles) {
				response = response.format("%s%s%n", response,file.getName());
			}
			
			if (savFiles.length == 0) {
				response = response.format("%sEmpty", response);
			}
		}
		
		return response;
	}
	
	//Basic conversation function
	private String conversation(String verb, ArrayList<Item> items) {
		String response = "I dont see that here";
		String endConvo = "";
		ArrayList<String> endConv = new ArrayList<String>();
		int itemNo = 0;
		int itemFound = 0;
		
		//Checks if the item is a person
		for (Item item:items) {
			for (String noun:item.getNouns()) {
				if (noun.equals(verb)) {
					
					itemFound = itemNo;
					
					if (!item.getExtended()) {
						response = response.format("%s: %s",item.getName(),item.talk().getResponse());
					} else {
						endConv = item.talk().displayConversation();
					}
				}
			}
			itemNo ++;
		}
				
		//Conversation has come to an end
		if (endConv.size() == 2) {
			response = endConv.get(0);
			
			//Will the being leave at the end
			if (endConv.get(1).equals("leave")) {
				items.remove(itemFound);
			} else if (endConv.get(1).equals("finish")) {
				items.get(itemFound).setExtended();
			} else if (endConv.get(1).equals("end")) {
				
				//Checks if the being has an adverse reaction to ending the conversation
				if (items.get(itemFound).getLeave().equals("pissed")) {					
					response = items.get(itemFound).getLeaveConvo();
				} else if (items.get(itemFound).getLeave().equals("leave")) {
					response = items.get(itemFound).getLeaveConvo();
					items.remove(itemFound);
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
 * 22 January 2024 - Added the lock/unlock and the open/close for the containers
 * 24 January 2024 - Added the function to move an item
 * 25 January 2024 - Added scoring for treasures
 * 26 January 2024 - Added the save function
 * 27 January 2024 - Save game works, and tested. Load game works, and is tested too.
 * 29 January 2024 - Completed the cover functionality
 * 30 January 2024 - Displays list of saved games if player doesn't enter correct name
 * 21 February 2024 - Fixed some issues with the move functionality
 * 14 March 2024 - Fixed issue with finding items under a cover
 * 21 March 2024 - Allowed ability to examine items carried
 * 23 March 2024 - Fixed error causing the game to crash when opening - changed exit to
 * 					item for item. Changed code so cannot lock and open container/exit
 * 					prevented picking up and item from a closed container
 * 27 March 2024 - Added the talk functionality. Completed a single response.
 * 30 March 2024 - Fixed up the response to the talk function
 * 9 May 2024 - Finished basic conversation
 * 10 May 2024 - Added functionality for a conversation to end
 * 17 August 2024 - Added the score command
 */