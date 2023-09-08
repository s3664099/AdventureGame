/* Location Class
 * Created: 25 August 2023
 * Updated: 7 September 2023
 * Version: 0.6
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
	
	public Location(String name) {
		this.name = name;
		this.nouns = new ArrayList<String>();
		this.exits = new ArrayList<Exit>();
		this.items = new ArrayList<Thing>();
		this.objects = new ArrayList<Thing>();
	}
	
	public void addExit(Exit exit) {
		this.exits.add(exit);
		this.nouns.add(exit.getDescription());
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
		return name;
	}
	
	//Returns a list of exits to display
	public String getExitList() {
		
		String exit_list = "";
		
		for (Exit exit:this.exits) {
			exit_list = exit_list + exit.getDescription()+", ";
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
			
	//Checks whether the exit can be opened
/*	public String openExit(String command) {
		
		String response = "";
		
		Exit exit = getExit(command);
		
		if (exit != null) {
			
			if (exit.isOpenable()) {
				
				if (exit.getOpen()) {
					exit.openClose();
					response = response.format("You open the %s%n", exit.getDescription());
				} else {
					response = response.format("The %s is already open%n", exit.getDescription());
				}
				
			} else {
				response = response.format("I cannot open %s%n", exit.getDescription());
			}
		} else {
			response = response.format("I cannot see a %s%n",command);
		}
		
		return response;
	}*/
} 
/* 25 August 2023 - Created file
* 27 August 2023 - Added comments
* 29 August 2023 - Reconfigured move command to allow for an array list
* 4 September 2023 - Added function to open and exit. Moved exit check to separate private
*                    method
* 6 September 2023 - Modified the code to handle the exits and finalised the openExit method
* 7 September 2023 - Changed code to handle redefined exit
*/