/* Location Class
 * Created: 25 August 2023
 * Updated: 8 September 2023
 * Version: 0.7
 * The class that holds the details of the locations and handles
 * any actions that deal with the location
 */

package Data;
import java.util.ArrayList;

public class Location {

	private String name;
	private String description;
	private ArrayList<String> nouns;
	private ArrayList<Exit> exits;
	private ArrayList<Thing> items;
	private ArrayList<Thing> objects;
	private boolean firstVisit = true;
	
	public Location(String name, String description) {
		this.name = name;
		this.description = description;
		this.nouns = new ArrayList<String>();
		this.exits = new ArrayList<Exit>();
		this.items = new ArrayList<Thing>();
		this.objects = new ArrayList<Thing>();
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
	
	public void addItem(Thing item) {
		
		if(item instanceof Item) {
			this.items.add(item);
		} else {
			this.objects.add(item);
		}
		this.nouns.add(item.getDescription());
	}
	
	public String getName() {
		
		String description = name;
	
		//Checks if the player has been here before, and if so displays the full description.
		if (firstVisit) {
			description = description.format("%s%n%n%s%n",description, this.description);
			firstVisit = false;
		}
		return description;
	}
	
	public String getDescription() {
		return String.format("%s%n%n%s",this.name,this.description);
	}
	
	//Returns a list of exits to display
	public String getExitList() {
		
		String exit_list = "";
		
		for (Exit exit:this.exits) {
			exit_list = exit_list + exit.getName()+", ";
		}
		
		return exit_list;
		
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
*/