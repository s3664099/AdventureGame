/* Location Class
 * Created: 25 August 2023
 * Updated: 4 January 2025
 * Version: 1.3
 * The class that holds the details of the locations and handles
 * any actions that deal with the location
 */

package Data;
import java.util.ArrayList;
import java.io.Serializable;

public class Location implements Serializable {

	private String name;
	private String description;
	private ArrayList<String> nouns;
	private ArrayList<Exit> exits;
	private ArrayList<Item> items;
	private ArrayList<Item> inventory;
	private int score;
	private boolean firstVisit = true;
	private boolean treasureStore = false;
	private boolean scoreRoom = false;
	private boolean endRoom = false;
	private String endComment;
	
	public Location(String name, String description) {
		this.name = name;
		this.description = description;
		this.nouns = new ArrayList<String>();
		this.exits = new ArrayList<Exit>();
		this.items = new ArrayList<Item>();
	}
	
	public Location(String name, String description, String endComment) {
		this.name = name;
		this.description = description;
		this.nouns = new ArrayList<String>();
		this.exits = new ArrayList<Exit>();
		this.items = new ArrayList<Item>();
		this.endRoom = true;
		this.endComment = endComment;
	}
	
	public void addExit(Exit exit) {
		this.exits.add(exit);
		this.nouns.add(exit.getName());
	}
	
	public ArrayList<Exit> getExits() {
		return this.exits;
	}
	
	public ArrayList<String> getNouns() {
		return this.nouns;
	}
	
	public void addItem(Item item) {
		
		this.items.add(item);
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	public String getName(boolean displayFull) {
		
		String description = name;
	
		//Checks if the player has been here before, and if so displays the full description.
		if ((firstVisit) || (displayFull)) {
			description = description.format("%s%n%n%s%n",description, this.description);
			firstVisit = false;
		}
		
		if (items.size()>0) {
			description = description.format("%s%nYou See: %s", description, this.getItemList());
		}
		
		if (exits.size()>0) {
			description = description.format("%s%nExits: %s", description, this.getExitList());
		}		
		
		return description;
	}
	
	//Returns a list of exits to display
	private String getExitList() {
		
		String exit_list = "";
		int count = 0;
		
		for (Exit exit:this.exits) {

			if (count>0) {
				exit_list +=", ";
			}
			
			exit_list += exit.getName();
			count ++;
		}
		
		return exit_list;
		
	}
	
	private String getItemList() {
		
		String item_list = "";
		
		int count = 0;
		
		for (Item item:this.items) {

			if (count>0) {
				item_list += ", ";
			}
			
			item_list += item.getName();
			count ++;
		}
		return item_list;
	}
	
	public boolean checkNoun(String nounCheck) {
		
		boolean foundNoun = false;
		
		for (String noun:nouns) {
			System.out.println(noun);
			if (nounCheck.equals(noun)) {
				foundNoun = true;
			}
		}
		return foundNoun;	
	}
	
	public void setTreasureStore() {
		this.treasureStore = !this.treasureStore;
	}
	
	public boolean getTreasureStore() {
		return this.treasureStore;
	}
	
	//Saves the players details when the game is saved
	public void savePlayer(ArrayList<Item> inventory,int score) {
		this.inventory = inventory;
		this.score = score;
	}
	
	//Loads player's score and inventory. Clears it afterwards
	public ArrayList<Item> getInventory() {
		
		ArrayList<Item> inventory = this.inventory;
			
		return inventory;
	}
	
	public int getScore() {
		int score = this.score;
		this.score = 0;
		return score;
	}
	
	//Makes the room a score room
	public void setScore() {
		this.scoreRoom = !this.scoreRoom;
	}
	
	//Checks if the room is a score room
	public boolean checkScore() {
		
		boolean isScore = false;
		
		if (scoreRoom) {
			isScore = true;
			this.scoreRoom = !this.scoreRoom;
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
*/