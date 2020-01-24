package Model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Objects implements Serializable {
	
	/**
	 * This is an abstract class that represents all types of objects
	 * in the game, whether they be doors, containers, or items. This is also
	 * expandable so that they might also be other types. It might be best to
	 * turn item into an interface if I end up expanding it beyond simple items.
	 */
	
	private String name;
	private int location;
	String key;
	
	public Objects(String name)
	{
		this.name = name;
	}
	
	public Objects(String name, int location)
	{
		this(name);
		
		this.location = location;
	}
	
	public Objects(String name, int location, String key) {

		this(name, location);

		this.key = key;
		
	}
	
	public int getLocation()
	{
		return location;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean checkLocked() {
		return false;
	}
	
	public String unlock(ArrayList<Item> objectsCarried, Location[] locations) {
		
		return "";
		
	}
	
	public void addItem() {}
		
	public int getKey() {
		return -1;
	}

}
