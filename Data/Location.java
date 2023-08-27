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
	
	public Location checkMove(String command) {
		
		Location location = this;
		
		for (Exit exit:exits) {
			
			if (command.equals(exit.getCommand())) {
				
				System.out.println(exit.move());
				
				if (exit.haveMoved()) {
					System.out.println("Hello");
					location = exit.getDestination();
				}
				
			}
		}
		
		return location;
	}
}
