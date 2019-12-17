package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Location {
	
	private String name;
	private Map<String, Integer> exits = new HashMap<String, Integer>();
	private List<Objects> objects = new Stack<Objects>();
	private List<Item> items = new ArrayList<Item>();
	
	public Location(String name)
	{
		this.name = name;
	}
	
	public void addExit(String name, int destination)
	{
		exits.put(name, destination);
	}
	
	public void addObjects(Objects object) {
		
		objects.add(object);
	}
	
	public void addItems(Item item) {
		
		items.add(item);
	}
	
	public Item getItem(String name) {
		
		Item itemToReturn = null;
		boolean itemFound = false;
		
		for (Item item:items) {
			if (name.equals(item.getName())) {
				itemToReturn = item;
			}
		}
		
		if(itemFound) {
			items.remove(itemToReturn);
			itemToReturn.setLocation(0);
		}
		
		return itemToReturn;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Map<String,Integer> getExits()
	{
		return exits;
	}
	
	public int getExit(String direction) throws InvalidDirectionException
	{
		if (direction.startsWith("n"))
			direction = "North";
		else if (direction.startsWith("s"))
			direction = "South";
		else if (direction.startsWith("e"))
			direction = "East";
		else if (direction.startsWith("w"))
			direction = "West";
		else if (direction.startsWith("u"))
			direction = "Up";
		else if (direction.startsWith("d"))
			direction = "Down";
		else if (direction.startsWith("i"))
			direction = "Inside";
		else if (direction.startsWith("o"))
			direction = "Outside";
		
		if (!exits.containsKey(direction)) 
			throw new InvalidDirectionException();
		return exits.get(direction);
	}
	
	public String toString()
	{
		String description = "You are "+name+"\n";
		
		if (objects.size()>0) {
		
			description+="You can see here: \n";

			for (Objects object:objects) {
				description+=object.getName()+"\n";
			}
			description+="\n";
			
		}

		if (items.size()>0) {

			description+="Items here are: \n";
		
			for (Item item:items) {
				description+=item.getName()+"\n";
			}
			description+="\n";
		}
		
		description+="Exits are: \n";
		
		for (Map.Entry<String, Integer> exit:exits.entrySet())
			if (exit.getValue()!=0)
				description+=exit.getKey()+", ";
		
		return description;
	}

}
