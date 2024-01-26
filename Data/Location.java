/* Location Class
 * Created: 25 August 2023
 * Updated: 25 January 2024
 * Version: 0.9
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
	
	public Location(String name, String description) {
		this.name = name;
		this.description = description;
		this.nouns = new ArrayList<String>();
		this.exits = new ArrayList<Exit>();
		this.items = new ArrayList<Item>();
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
		this.inventory = null;
		
		return inventory;
	}
	
	public int getScore() {
		int score = this.score;
		this.score = 0;
		return score;
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
*/