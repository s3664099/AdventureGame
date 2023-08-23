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
	}
	
	public void addExit(Exit exit) {
		exits.add(exit);
		nouns.add(exit.getDescription());
	}
	
	public void addItem(Thing item) {
		
		if(item instanceof Item) {
			items.add(item);
		} else {
			objects.add(item);
		}
		nouns.add(item.getDescription());
	}
	
	public String getName() {
		return name;
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
