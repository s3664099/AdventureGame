package Model;

public class Item extends Objects {
	
	/**
	 * The item class can be carried by the player. Items can be keys
	 * but can also be used with other items to change them into different items.
	 * 
	 */
	
	private int location;
	
	public Item(String name, int location) {
		super(name);
		this.location = location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	public int getLocation () {

		return location;
	}
		
	public String getName () {
		return super.getName();
	}
	
}
