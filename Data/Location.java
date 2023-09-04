/* Location Class
 * Created: 25 August 2023
 * Updated: 4 September 2023
 * Version: 0.4
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
	
	//Checks whether is exit is present in the location
	private Exit getExit(String command) {
		
		Exit foundExit = null;
		
		for (Exit exit:exits) {
			
			for (String x:exit.getCommand()) {
				if (command.equals(x)) {
					foundExit = exit;
				}
			}
		}
		
		return foundExit;
	}
	
	//Checks whether movement is possible.
	public Location checkMove(String command) {
		
		Location location = this;
		
		Exit exit = getExit(command);
		
		if (exit != null) {
			
			System.out.println(exit.move());
			
			if (exit.haveMoved()) {
				location = exit.getDestination();
			}
			
		} else {
			System.out.println("You cannot go in that direction");
		}
		
		return location;
	}
	
	//Checks whether the exit can be opened
	public void openExit(String command) {
		
		Exit exit = getExit(command);
		
		if (exit != null) {
			
			if (exit instanceof CloseableExit) {
				
				if (exit.getOpen()) {
					exit.openClose();
				}
				
			} else {
				System.out.printf("I cannot open %s%n", command);
			}
		} else {
			System.out.printf("I cannot see a %s%n",command);
		}
		
	}
}
/* 25 August 2023 - Created file
* 27 August 2023 - Added comments
* 29 August 2023 - Reconfigured move command to allow for an array list
* 4 September 2023 - Added function to open and exit. Moved exit check to separate private
*                    method
*/