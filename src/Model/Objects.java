package Model;

public abstract class Objects {
	
	/**
	 * This is an abstract class that represents all types of objects
	 * in the game, whether they be doors, containers, or items. This is also
	 * expandable so that they might also be other types. It might be best to
	 * turn item into an interface if I end up expanding it beyond simple items.
	 */
	
	private String name;
	private int location;
	
	public Objects(String name)
	{
		this.name = name;
	}
	
	public Objects(String name, int location)
	{
		this.name = name;
		this.location = location;
	}
	
	public int getLocation()
	{
		return location;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addItem() {}
	
	public boolean checkLocked() {
		return false;
	}
	
	public int getKey() {
		return -1;
	}

}
