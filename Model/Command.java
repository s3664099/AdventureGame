/* Command Function
 * Created: 25 August 2023
 * Updated: 12 January 2025
 * Version: 1.16
 * Class that handles functions that deal with commands that are entered.
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

import Data.Bag;
import Data.CarriableItem;
import Data.Container;
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
	public String processCommand(UserCommand command, Location location, ArrayList<Item> inventory,int score) {
		
		//Sets the location
		this.currentLocation = location;
		this.displayLocation = false;
		this.inventory = inventory;

		//Standard response
		response = "I'm sorry, I do not understand";
		
		//Goes through the verbs
		String noun = command.getSubject();
		String verb = command.getVerb();
		String object = command.getObject();
		
		//Is with being used, but no object
		if (command.getWith() && object.length()==0) {
			response = "With what?";
					
		//Go
		} else if (verb.equals("go") && !command.getWith()) {
			response = changeLocation(noun);
			this.displayLocation = true;
		
		//Open/Close
		} else if ((verb.equals("open") || verb.equals("close")) && !command.getWith()) {
			response = openExit(noun,verb);
		
		//Inventory
		} else if (verb.equals("inventory") && !command.getWith()) {
			if (inventory.size() == 0) {
				response = "I am carrying nothing";
			} else {
				response = "I am carrying:";
				for (Item item:inventory) {
					response = response.format("%s %s", response, item.getName());
				}
			}
		
		//Look
		} else if (verb.equals("look") && !command.getWith()) {
			response = look(command,location,inventory);

		//Take/Get
		} else if ((verb.equals("take") || verb.equals("get")) && !command.getWith()) {
			if (noun.length() == 0) {
				response = "I need a noun";
			} else {
				response = switchList(location.getItems(), inventory, noun, "I picked up the",false,true);
				this.score = changeScore(false,noun,inventory,location); 
			}
			
		//Drop
		} else if (verb.equals("drop") && !command.getWith()) {
			
			if (noun.length() == 0) {
				response = "I need a noun";
			} else {
				response = switchList(inventory,location.getItems(),noun, "I dropped the",false,false);
				this.score = changeScore(true,noun,location.getItems(),location);
			}
		
		//Put something in something
		} else if (verb.equals("put") && command.getPutIn()) {
			
			if (object.length()==0) {
				response = "What should I put it in";
			} else {
				response = putIn(inventory,command,location.getItems());
			}
		
		//Lock/Unlock
		} else if (verb.equals("unlock") || verb.equals("lock")) {

			if (noun.length()==0) {
				response = "I need a noun";
			} else {
				response = unlock(inventory,location.getExits(),location.getItems(), command);
			}

		//Move
		} else if (verb.equals("move") && !command.getWith()) {
			
			if (noun.length()==0) {
				response = "I need a noun";
			} else {
				response = move(location,noun);
			}

		//Save/Load Game
		} else if (verb.equals("save") && !command.getWith()) {
			
			//Checks if there is a name, and calls the save game function
			if (noun.length()==0) {
				response = "Please include a name for the saved game";
			} else {
				response = saveGame(location,inventory,score,noun);
			}
			
		} else if (verb.equals("load") && !command.getWith()) {
			
			//Checks if there is a name, and calls the load game function
			if (noun.length()==0) {
				
				//If only one name, displays a list of save game files.
				response = getGameList();
				
			} else {
				response = loadGame(noun);
			}
		
		} else if (verb.equals("read")) {
			
			response = "I'm not carrying that";
			
			for (Item item:inventory) {
				if (item.equals(noun)) {
					response = item.readItem();
				}
			}

		//Conversation
		} else if ((verb.equals("talk")) || (verb.equals("speak"))) {
			response = conversation(noun, location.getItems());

		//Scoring
		} else if (verb.equals("score") && noun.length()==0 && !command.getWith()) {
			response = response.format("Your score is %s/%s",this.score,this.topScore);

		//End Game
		} else if (verb.equals("quit") && noun.length()==0 && !command.getWith()) {
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
	private String switchList(ArrayList<Item> listOne, ArrayList<Item> listTwo, String command, String statement, boolean itemTaken,boolean taking) {

		//Sets counter for items
		int itemNo = -1;
		ArrayList<Integer> itemsFound = new ArrayList<Integer>();
		String response;
		boolean allSelected = false;
		
		if (taking) {
			response = "I don't see that here";
		} else {
			response = "I'm not carrying that";
		}
		
		//Sets up if player picking all everythinmg in the room
		if (command.equals("all") || command.equals("everything")) {
			response = "";
			allSelected = true;
		}
		
		//Goes through the items at the location
		for (Item item: listOne) {
			
			itemNo +=1;
			
			if ((item.equals(command) && !itemTaken) || (allSelected)) {
				
				if (item instanceof CarriableItem) {
					
					//Add it to the player's inventory
					itemsFound.add(itemNo);
					listTwo.add(item);
					
					if (itemsFound.size()==1) {
						response = response.format("%s %s",statement, item.getBasicName());
					} else {
						response = response.format("%s, %s %s",response, statement, item.getBasicName());
					}
					
					if (item.equals(command)) {
						itemTaken = true;
					}
					
				} else {
					
					if (!allSelected) {
						response = "I cannot pick that up";
					}
				}
			} else if ((item instanceof Container) && (!itemTaken)) {
				if ((((Container) item).getViewed()) && (!((Container) item).getClosed())) {
					ArrayList<Item> contents = ((Container) item).getContents();
					response = this.switchList(contents,listTwo,command,statement,itemTaken,taking);
				} 
			}
		}
		
		//Has an item been picked up - Remove it from the location
		if (itemsFound.size()>0) {
			
			for (int i=itemsFound.size()-1;i>=0;i--) {
				int itemIndex = itemsFound.get(i);
				listOne.remove(itemIndex);				
			}
			
		} else if (allSelected) {

			if (taking) {
				response = "I don't see anything here I can take";
			} else {
				response = "I'm not carrying anything";
			}		
		}
		
		return response;
	}
	
	//Put an item into a container
	private String putIn(ArrayList<Item> inventory, UserCommand command, ArrayList<Item> localItems) {
		response = "I can't do that";
		
		String subject = command.getSubject();
		String object = command.getObject();
		CarriableItem subjectItem = null;
		int subjectItemNo = 0;
		boolean objectPresent = false;
		
		//Goes through the inventory to find the item
		for (Item item:inventory) {
			
			//If found, takes it, and flags that object found
			if (item.equals(subject)) {
				subjectItem = (CarriableItem) item;
				
			//Moves to the next one
			} else if (subjectItem == null) {
				subjectItemNo++;
			}
		}
		
		//Looks for the container in the room
		if (subjectItem != null) {
			
			for(Item item:localItems) {
				
				//Found item - has to be a container
				if (item.equals(object) && item instanceof Container && !item.getClosed()) {
					
					//Adds the item to the container and removes it from the inventory
					item.addItem(subjectItem);
					inventory.remove(subjectItemNo);
					objectPresent = true;
					response = "Done";
				
				} else if (item.equals(object) && item instanceof Container && item.getClosed()) {
					response = String.format("The %s is closed.", item.getBasicName());
					objectPresent = true;
				} else if (item.equals(object) && item instanceof Bag) {
					response = String.format("You need to be carrying the %s to put the %s into it.", item.getBasicName(),subjectItem.getName());
					objectPresent = true;
				} else if (item.equals(object)) {
					response = String.format("I can't put the %s into the %s",subjectItem.getName(),item.getName());
					objectPresent = true;
				}
			}
			
			if (!objectPresent) {
				
				//Goes through inventory and looks for a bag
				
				response = String.format("I do not see a %s here", object);
			}
		} else {
			response = String.format("I do not have a %s", subject);
		}
				
		return response;
	}
	
	//Checks if the item is available
	private Item getItems(String command) {
		
		Item item = null;
		
		//Gets all items from location
		ArrayList<Item> items = currentLocation.getItems();
		
		for (Item itemSearch:items) {
			if (itemSearch.equals(command)) {
				item = itemSearch;
			} 	
		}
		return item;
	}
	
	//Checks if the item is in player's inventory
	private Item checkInventory(String command) {
		
		Item item = null;
		
		for (Item itemSearch:inventory) {
			if (itemSearch.equals(command)) {
				item = itemSearch;
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
			if (exitSearch.equals(command)) {
				exit = exitSearch;
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
				
				if (currentLocation.checkScore()) {
					this.score ++;
				}
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

			//Checks if the item is present
			if (item != null) {
				
				//Is the item openable?
				if (item.getCloseable() && !item.getLocked() && !(item instanceof Bag)) {
					response = openItem(item,verb);
				} else if (item instanceof Bag) {
					response = response.format("You need to pick the %s up to open it.", item.getBasicName());
				} else {
					response = response.format("You cannot open the %s", item.getBasicName());
				}
			} else {
				
				item = checkInventory(command);
				
				//Checks if the item is being carried
				if (item != null) {
					if (item.getCloseable() && !item.getLocked()) {
						response = openItem(item,verb);
					} else {
						response = response.format("You cannot open the %s", item.getBasicName());
					}
				}
			}
		}
				
		return response;
	}
	
	private String openItem(Item item, String verb) {
	
		if (verb.equals("open")) {
				
			//Is the exit open - if not the exit is opened.
			if (item.getClosed()) {
				item.setClosed();
				response = response.format("You open the %s",item.getBasicName());
			} else {
				response = response.format("The %s is already open",item.getBasicName());
			}
		} else if (verb.equals("close")) {
				
			//Is the exit open - if not the exit is opened.
			if (!item.getClosed()) {
				item.setClosed();
				response = response.format("You close the %s",item.getBasicName());
			} else {
				response = response.format("The %s is already closed",item.getBasicName());
			}					
		}
		
		return response;
	}
		
	private String look(UserCommand command, Location location, ArrayList<Item> inventory) {
		String response = "";
		String noun = command.getSubject();
		
		//Is the player just looking around the room
		if ((noun.length()==0) || (noun.equals("around")) ||
			 (noun.equals("room")) || (noun.equals("location"))) {
			response = response.format("%s%n=======================",location.getName(true));
		} else {
			
			boolean lookAll = false;
			
			if (noun.equals("all")||noun.equals("everything")) {
				lookAll = true;
				response = "You look at everything:";
			}
						
			//Checks if the player is looking at the exit/Items
			ArrayList<Exit> exits = location.getExits();
			ArrayList<Item> items = location.getItems();
			
			boolean removeItem = false;
			int itemIndex = 0;
			int index = 0;
						
			//Exits
			for (Exit exit:exits) {
				if (!command.getInside()) {
					if (exit.equals(noun)) {
					
						if(command.getThrough()) {
							if (!exit.getOpen()) {
								response = exit.getDestination().getName(true);
							} else {
								response = response.format("You cannot look through a closed %s", exit.getName());
							}
						} else {
							if (command.getCardinal()) {
								response = exit.getDestination().getName(true);
							} else {									
								response = exit.getDescription();
							}
						}
					} else if (lookAll) {
						response = response.format("%s%n%s: %s", response,exit.getName(),exit.getDescription());
					}
				}
			}
			
			//Inventory
			if (response.length()==0 || lookAll) {
				for (Item item:inventory) {
					if (item.equals(noun)) {
						response = item.getDescription();
						itemIndex = index;
					} else if (lookAll) {
						response = response.format("%s%n%s - carrying: %s", 
									response,item.getName(),item.getDescription());		
					}
				}
			}
			
			//Items
			if (response.length()==0 || lookAll) {
				for (Item item:items) {
					
					if (item.equals(noun)) {
						
						if (command.getInside() && (item.getClosed() || item.getLocked())) {
							response = "I'm sorry, it is closed";
						} else if (item.checkIsCover() || item.getCloseable() || !command.getInside()) {
							response = item.getDescription();
							itemIndex = index;
						} else {
							response = "I cannot look inside that";
						}
							
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
					} else if (lookAll) {
						response = response.format("%s%n%s: %s", 
								response,item.getName(),item.getDescription());
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
			response = "I don't quite understand what you mean";
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
	
	private String unlock(ArrayList<Item> inventory, ArrayList<Exit>exits, ArrayList<Item>items, UserCommand command) {
	
		response = "I do not see that here";
		boolean found = false;
		boolean foundItem = false;
		String verb = command.getVerb();
		String subject = command.getSubject();
		Item key = null;
		
		//checks if player has included the with object
		if (!command.getWith()) {
		
			response = "with what?";
			
		} else {
			
			//Checks if player is carrying the item
			for (Item item:inventory) {
				if (item.equals(command.getObject())) {
					key = item;
				}
			}
			
			if (key == null) {
				response = "You don't have it";
			} else {
				
				//Checks to see if the player is attempting to unlock an exit
				for (Exit exit:exits) {
			
					if (exit.equals(subject)) {
				
						//Compares Key
						if (!key.equals(exit.getKey()) || !exit.isLockable()) {
							response = String.format("The %s doesn't work", key.getName());
						} else {
							
							//Acts on either lock/unlock, which does the opposite.
							if (verb.equals("unlock")) {
								if (exit.getLocked()) {
									response = exit.lockUnlock((CarriableItem) key, verb);
								} else {
									response = response.format("The %s is already unlocked",exit.getName());
								}
							} else if (verb.equals("lock")) {
								if (!exit.getLocked()) {
									
									//If exit is open, cannot be locked
									if (!exit.getOpen()) {
										response = response.format("The %s is open. Please close it first", exit.getName());
									} else {
										response = exit.lockUnlock((CarriableItem) key, verb);
									}
									
								} else {
									response = response.format("The %s is already locked",exit.getName());
								}								
							}
						}
						foundItem = true;
					}
				}
		
				//It wasn't an exit, so we now check the items.
				if (!foundItem) {
					for (Item container:items) {
						if (container.equals(subject)) {
					
							//The container isn't lockable
							if(!container.checkKey(key) || !container.getLockable()) {
								response = String.format("The %s doesn't work", key.getName());
							} else {
								
								found = true;
								
								//Acts on either lock/unlock, which does the opposite.
								if (verb.equals("unlock")) {
										
									//Checks if the container is already locked.
									if (container.getLocked()) {
										container.setLocked();
										response = response.format("You unlock the %s",container.getBasicName());
									} else {
										response = response.format("The %s is already unlocked",container.getBasicName());
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
							foundItem = true;
						}
						
						//Unlock a carriable item
						//Unlock/Open a bag - need to exclude a bag from above as can only open/unlock a baq when carrying
					}
				}
			}
		}
		return response;
	}
	
	//Function to move an item that can be moved
	private String move(Location location, String command) {
		
		ArrayList<Item> items = location.getItems();
		String response = "";
		response = response.format("I do not see a %s here", command);
		Item hiddenItem = null;
		Boolean foundItem = false;
		Boolean itemRemoved = false;
		Boolean exitRemoved = false;
		int itemIndex = 0;
		int itemRemove = -1;
				
		for (Item item:items) {
			
			if (item.equals(command)) {
				
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
				itemIndex ++;
			}
		}
		
		if(!foundItem) {
			for (Exit exit:location.getExits()) {
				if (exit.equals(command)) {
					response = response.format("You cannot move the %s",exit.getName());
					foundItem = true;
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
	
	//Compares the score to determine whether the game is completed
	public boolean compareScore() {
		
		boolean gameOver = false;
		
		if (this.score == this.topScore) {
			gameOver = true;
		}
		
		return gameOver;
		
	}
	
	//save Game method
	private String saveGame(Location location, ArrayList<Item> inventory, int score, String saveName) {

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
				
				for (Item x:this.inventory) {
					System.out.println(x.getDescription());
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
			if (item.equals(verb)) {
				
				itemFound = itemNo;
					
				if (!item.getExtended()) {
					response = response.format("%s: %s",item.getName(),item.talk().getResponse());
				} else {
					endConv = item.talk().displayConversation();
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
 * 18 August 2024 - Scoring by dropping items now works. Added function to compare
 * 					player score.
 * 19 August 2024 - Added code to increase score for entering room.
 * 22 August 2024 - Modified look command to handle new parser
 * 26 August 2024 - Added comments to main method and moved inventory parsing out
 * 					Updated look to handle multi word objects
 * 27 August 2024 - Updated the lock/unlock and move commands
 * 29 August 2024 - Updated Conversation
 * 1 September 2024 - Added ability to pick/drop all items.
 * 31 December 2024 - Added the Look at Everything function.
 * 					- Added look in and fixed problem with short nouns
 * 3 January 2025 - The look through an exit now works.
 * 4 January 2025 - Completed the look through cardinal directions
 * 5 January 2025 - Added check to make sure an object is included with 'with'
 * 					Added checks to each of the command to see if 'with' being used
 * 7 January 2025 - Made the lock/unlock command to require the item to be described.
 * 8 January 2025 - Did the unlock for containers
 * 9 Janaury 2025 - Added more description to when unlocking doesn't work.
 * 10 Jaunary 2025 - Started the put in functionality
 * 11 January 2025 - Used basic name for when picking up and dropping object
 * 12 January 2025 - Added method to check if player carrying the item. Moved open/close item to separate method. Added open/close bag.
 */