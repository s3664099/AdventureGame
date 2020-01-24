import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	
	private int NUMBER_LOCATIONS = 12;
	private int NUMBER_OBJECTS = 6;
	private int NUMBER_ITEMS = 5;
	
	private Location[] location = new Location[NUMBER_LOCATIONS];
	private Objects[] object = new Objects[NUMBER_OBJECTS];
	private Item[] item = new Item[NUMBER_ITEMS];
	
	//The constructor sets up all of the data
	public SetUp()
	{
		location[0] = new Location("My Backpack");
		location[1] = new Location("outside a hut");
		location[1].addExit("West", 3);
		location[2] = new Location("inside a hut");	
		location[2].addExit("Outside", 1);
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
		location[9].addExit("Outside", 8);
		location[10] = new Location("before a rockfall");	
		location[10].addExit("West", 9);
		location[11] = new Location("on the other side of a rockfall");
		location[11].addExit("West", 10);
		
		item[0] = new Item("key",-1);
		item[1] = new Item("lamp",-2);
		item[2] = new Item("oil",5);
		item[3] = new Item("pickaxe",-4);
		item[4] = new Item("flint",7);
		
		object[0] = new Container("Rug", 2);
		object[1] = new Container("Cabinet", 2, true, item[0], 0);
		object[2] = new Door("Door", 1, false, 2, "Inside", "");
		object[3] = new Container("Mining Cart", 8);
		object[4] = new Door("Rockfall", 10, true, 11, "east","pickaxe");
		object[5] = new Door("Cave Entrance", 9, true, 10, "Inside", "lit lamp");
				
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
	//The objects are written as data, namely because it is not human
	//readable, as this should be the case with adventures games - otherwise
	//it gives too much away.
	public void writeToFile() throws IOException {

		ObjectOutputStream objout = createFile("locations.dat");
		
		for (int i=0;i<location.length; i++) {	
			objout.writeObject(location[i]);
		}
		
		closeOutputStream(objout);
		
		objout = createFile("objects.dat");
		
		for (int i=0;i<object.length; i++) {	
			objout.writeObject(object[i]);
		}
		
		closeOutputStream(objout);
		
		objout = createFile("items.dat");
		
		for (int i=0;i<item.length; i++) {	
			objout.writeObject(item[i]);
		}
		
		closeOutputStream(objout);
		
	}
	
	/**
	 * 
	 * The following two methods were created to elimiate code 
	 * duplication. The first creates a file, which the second closes
	 * the file off.
	 * 
	 */
	
	public ObjectOutputStream createFile(String fileName) throws IOException {
		
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream objout = new ObjectOutputStream(fileOut);
		
		return objout;
	}
	
	public void closeOutputStream(ObjectOutputStream objout) throws IOException {
		
		objout.flush();
		objout.close();
		
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
