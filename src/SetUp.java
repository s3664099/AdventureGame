import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import Model.Container;
import Model.Door;
import Model.Item;
import Model.Location;
import Model.Objects;

/**
 * Setup Class
 * @author archimedes
 *
 * This class is designed to set up the adventure game
 * The purpose is for it to create a data file where all of the data for the
 * adventure is stored. Once that has been completed then
 * this class is to be discarded.
 *
 */

public class SetUp {
	
	int NUMBER_LOCATIONS = 12;
	int NUMBER_OBJECTS = 5;
	int NUMBER_ITEMS = 5;
	
	Location[] location = new Location[NUMBER_LOCATIONS];
	Objects[] object = new Objects[NUMBER_OBJECTS];
	Item[] item = new Item[NUMBER_ITEMS];
	
	//The constructor sets up all of the data
	public SetUp()
	{
		location[0] = new Location("My Backpack");
		location[1] = new Location("outside a hut");
		location[1].addExit("West", 3);
		location[2] = new Location("inside a hut");	
		location[2].addExit("Out", 1);
		location[3] = new Location("in a paddock");
		location[3].addExit("East", 1);
		location[3].addExit("South", 4);
		location[4] = new Location("in a forest");
		location[4].addExit("North", 3);
		location[4].addExit("South", 5);
		location[5] = new Location("walking along the edge of a swamp");	
		location[5].addExit("North", 4);
		location[5].addExit("East", 6);
		location[6] = new Location("in the hills");
		location[6].addExit("West", 5);
		location[6].addExit("East", 8);
		location[6].addExit("South", 7);
		location[7] = new Location("in a granite quarry");
		location[7].addExit("North", 6);
		location[8] = new Location("at the entrance to a mine");
		location[8].addExit("West", 6);
		location[9] = new Location("inside the mine");
		location[9].addExit("East", 10);
		location[9].addExit("West", 8);
		location[10] = new Location("before a rockfall");	
		location[10].addExit("West", 9);
		location[11] = new Location("on the other side of a rockfall");
		
		item[0] = new Item("key",-1);
		item[1] = new Item("lamp",-2);
		item[2] = new Item("oil",5);
		item[3] = new Item("pickaxe",-4);
		item[4] = new Item("flint",7);
		
		object[0] = new Container("Rug", 2);
		object[1] = new Container("Cabinet", 2, true, item[0], 0);
		object[2] = new Door("Door", 1);
		object[3] = new Container("Mining Cart", 8);
		object[4] = new Door("Rockfall", 10);
				
		for (Objects objects:object) {
			location[objects.getLocation()].addObjects(objects);
		}
		
		for (Item items:item) {
			if (items.getLocation()>0)
				location[items.getLocation()].addItems(items);
			else if (items.getLocation()<0) {
				int itemLocation = (items.getLocation()+1)*-1;
				object[itemLocation].addItem();
			}
		}
		
	}
	
	//This method then writes the contents of the objects to file.
	//Currently it is a txt file, but it should be turned into a binary file.
	public void writeToFile() throws IOException {
		
		String filename = "datafile.txt";
		String fileToWrite = String.format("%d%n%d%n%d%n*****%n", NUMBER_LOCATIONS,
				NUMBER_OBJECTS, NUMBER_ITEMS);
		
		for (Location locations:location) {
			
			fileToWrite = String.format("%s%s%n", fileToWrite, locations.getName());
			
			for (Map.Entry<String, Integer> exits:locations.getExits().entrySet()) {
				
				fileToWrite = String.format("%s%s, %d%n", fileToWrite,exits.getKey(),exits.getValue());
				
			}
			
			fileToWrite = String.format("%s*****%n", fileToWrite);
			
		}
				
		fileToWrite = String.format("%s====%n", fileToWrite);
		
		
		for (Item items:item) {
			
			fileToWrite = String.format("%s%s,%d%n", fileToWrite, items.getName(), items.getLocation());
			
		}
		
		fileToWrite = String.format("%s====%n", fileToWrite);
		
		for (Objects objects:object) {
			
			if (objects.checkLocked()) {
				fileToWrite = String.format("%sLOCKED,%s,%d,%d%n", fileToWrite,
						objects.getName(), objects.getLocation(), objects.getKey());
			} else if (objects instanceof Container){
				fileToWrite = String.format("%sUNLOCK,%s,%d%n", fileToWrite, objects.getName(), objects.getLocation());
			} else {
				fileToWrite = String.format("%s%s,%d%n", fileToWrite, objects.getName(), objects.getLocation());
			}
		}
		
		System.out.println(fileToWrite);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		
		writer.write(fileToWrite);
		
		writer.close();
		
		
	}
	
	/**
	 * Getters & Setters
	 * 
	 */
	public Objects[] getObject()
	{
		return object;
	}
	
	public Item[] getItem() {
		
		return item;
	}
	
	public Location[] getLocation()
	{
		return location;
	}

}
