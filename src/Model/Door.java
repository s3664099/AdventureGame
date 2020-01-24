package Model;

import java.util.ArrayList;

public class Door extends Objects {
	
	/**
	 * This represents an obstruction to the player than prevents
	 * them from entering another location. Further work needs to be done on this.
	 * 
	 * 
	 */
	
	private boolean isLocked = false;
	private int toLocation = 0;
	private String direction = "";
	
	public Door(String name, int location) {
		
		super(name, location);
	}
	
	public Door(String name, int location, boolean isLocked, int toLocation, String direction, String key) {
		super(name, location, key);
		
		this.isLocked = isLocked;
		this.toLocation = toLocation;
		this.direction = direction;
	}
	
	public boolean checkLocked() {
		return isLocked;
	}
	
	public String unlock(ArrayList<Item> objectsCarried, Location[] location) {
		
		String response = "You do not have the key";
		boolean canOpen = false;
		
		if (!isLocked) {
			
			canOpen = true;
			
		} else {
		
			for(Item items:objectsCarried) {
			
				if (items.equals(key)) {
				
					canOpen = true;
				}
			}
		}
		
		if (canOpen) {
			location[super.getLocation()].addExit(direction, toLocation);
			response = "The "+super.getName()+" has been opened";
		}
		
		return response;
		
	}
	

}
