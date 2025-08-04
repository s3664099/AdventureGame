/* Location Class
 * Created: 25 August 2023
 * Updated: 4 August 2025
 * Version: 1.5
 * The class that holds the details of the locations and handles
 * any actions that deal with the location
 * 
 * The check noun should be done elsewhere
 */

package Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import Model.Player;

import java.io.Serializable;

public class Location implements Serializable {

	private static final long serialVersionUID = -3015577760020605932L;
	private static final Logger logger = Logger.getLogger(Location.class.getName());
	
	private final String name;
	private final String description;
	private final List<String> nouns;
	private final List<Exit> exits;
	private final List<Item> items;
	private final boolean treasureStore;
	private final boolean endRoom;
	private final String endComment;
	private Player player;
	private boolean firstVisit = true;
	private boolean scoreRoom = false;
	
	public Location(String name, String description) {
		this.name = Objects.requireNonNull(name, "Name cannot be null");
		this.description = Objects.requireNonNull(description, "Description cannot be null");
		this.nouns = new ArrayList<String>();
		this.exits = new ArrayList<Exit>();
		this.items = new ArrayList<Item>();
		this.treasureStore = false;
		this.endRoom = false;
		this.endComment = "";
	}
	
	public Location(String name, String description, String endComment) {
		this.name = Objects.requireNonNull(name, "Name cannot be null");
		this.description = Objects.requireNonNull(description, "Description cannot be null");
		this.nouns = new ArrayList<String>();
		this.exits = new ArrayList<Exit>();
		this.items = new ArrayList<Item>();
		this.treasureStore = false;
		this.endRoom = true;
		this.endComment = Objects.requireNonNull(endComment, "End Comment cannot be null");
	}
	
	public Location(String name, String description, String endComment,boolean treasureStore) {
		this.name = Objects.requireNonNull(name, "Name cannot be null");
		this.description = Objects.requireNonNull(description, "Description cannot be null");
		this.nouns = new ArrayList<String>();
		this.exits = new ArrayList<Exit>();
		this.items = new ArrayList<Item>();
		this.treasureStore = Objects.requireNonNull(treasureStore, "Treasure Store Comment cannot be null");
		this.endRoom = true;
		this.endComment = Objects.requireNonNull(endComment, "End Comment cannot be null");
	}
	
	public void addExit(Exit exit) {
		this.exits.add(Objects.requireNonNull(exit, "Exit Comment cannot be null"));
		this.nouns.add(Objects.requireNonNull(exit.getName(), "Exit Name Comment cannot be null"));
		logger.fine("Added exit: " + exit.getName() + " to location: " + this.name);
	}
	
	public List<Exit> getExits() {
		 return Collections.unmodifiableList(this.exits);
	}
	
	public List<String> getNouns() {
		 return Collections.unmodifiableList(this.nouns);
	}
	
	public void addItem(Item item) {		
		this.items.add(Objects.requireNonNull(item, "Item cannot be null"));
		logger.fine("Added exit: " + item.getName() + " to location: " + this.name);
	}
	
	public List<Item> getItems() {
		return Collections.unmodifiableList(this.items);
	}
	
	public String getName(boolean displayFull) {
		
		String description = name;
	
		//Checks if the player has been here before, and if so displays the full description.
		if ((firstVisit) || (displayFull)) {
			description = String.format("%s%n%n%s%n",description, this.description);
			firstVisit = false;
		}
		
		if (items.size()>0) {
			description = String.format("%s%nYou See: %s", description, this.getItemList());
		}
		
		if (exits.size()>0) {
			description = String.format("%s%nExits: %s", description, this.getExitList());
		}		
		
		return description;
	}
	
	//Returns a list of exits to display
	private String getExitList() {
	    StringBuilder sb = new StringBuilder();
	    exits.forEach(exit -> sb.append(exit.getName()).append(", "));
	    return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
	}
	
	private String getItemList() {
	    StringBuilder sb = new StringBuilder();
	    items.forEach(item -> sb.append(item.getName()).append(", "));
	    return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
	}
	
	public boolean checkNoun(String nounCheck) {
		
		boolean foundNoun = false;
	    if (nounCheck == null || nounCheck.isBlank()) {
	        logger.warning("Invalid noun check: input was null/empty");
	    } else {
	    	for (String noun:nouns) {
	    		if (nounCheck.equals(noun)) {
	    			foundNoun = true;
	    		}
	    	}
	    }
		return foundNoun;	
	}
		
	public boolean getTreasureStore() {
		return this.treasureStore;
	}
	
	//Saves the players details when the game is saved
	public void cachePlayer(Player player) {
		this.player = player;
		logger.fine("Cached player data in location: " + this.name);
	}
	
	//Loads player's score and inventory. Clears it afterwards
	public Player getPlayer() {		
		Player player = this.player;
		return player;
	}
		
	//Makes the room a score room
	public void setScore(boolean scoreRoom) {
		this.scoreRoom = scoreRoom;
	}
	
	//Checks if the room is a score room
	public boolean checkScore() {
		
		boolean isScore = false;
		
		if (scoreRoom) {
			isScore = true;
			this.scoreRoom = false;
		}
		
		return isScore;
	}
	
	//Checks if this is an end room
	public boolean checkEnd() {
		return this.endRoom;
	}
	
	public String getEndComment() {
		return this.endComment;
	}
} 
/* 25 August 2023 - Created file
* 27 August 2023 - Added comments
* 29 August 2023 - Reconfigured move command to allow for an array list
* 4 September 2023 - Added function to open and exit. Moved exit check to separate private
*                    method
* 6 September 2023 - Modified the code to handle the exits and finalised the openExit method
* 7 September 2023 - Changed code to handle redefined exit
* 8 September 2023 - Removed command processing from class. Added detailed description
*                    to the location.
* 15 September 2023 - Added methods to handle items, and reduced to single item list. 
* 					  reworked display method to handle all displays
* 25 January 2024 - Added methods to handle treasure stores. Made class serializable. Added inventory to store
*                   player's inventory when saving. Also score 
* 19 August 2024 - Added scoring for entering a room and also creating an end condition
* 					for entering a room
* 20 August 2024 - End condition now works.  
* 4 January 2025 - Created temporary fix for retrieving the inventory, though for some strange reason it won't make a deep copy.
* 3 August 2025 - Removed warnings and made non-changing variable final
* 				- Add null checks
* 				- Made lists unmodifiable
* 				- Fixed strings being returned
* 4 August 2025 - Added logging
*/