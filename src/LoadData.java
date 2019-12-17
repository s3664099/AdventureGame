import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Model.Container;
import Model.Door;
import Model.Item;
import Model.Location;
import Model.Objects;

/**
 * This class is designed to load data in from a .dat file and create the objects
 * This is done so that the main data is stored elsewhere and not within the program.
 * It also forms a basis for a possible load/save function for the game.
 */

public class LoadData {
	
	int dataPosition = 0;
	
	int LOCATION_NUMBER;
	int OBJECT_NUMBER;
	int ITEM_NUMBER;
	int locationPosition=0;
	int itemPosition=0;
	int objectPosition=0;
	
	Location[] location;
	Objects[] object;
	Item[] item;

	public LoadData() {
		
		Scanner inputStream = null;
		
		try {
			inputStream = new Scanner(new File("datafile.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("File datafile.txt not found");
		}
		
		while (inputStream.hasNextLine()) {
			
			//The data file has been divided up into sections
			//The position flag tells the loader at what point the loading is up to
			//So, the positions are as follows:
			//0) Locations
			//1) Immovable Objects
			//2) Items
			//This has been set up so that it may be possible to add further
			//parts to the program (maybe monsters, or specific items such as
			//weapons & armour)
			
			if (dataPosition == 0) {
				LOCATION_NUMBER = Integer.parseInt(inputStream.nextLine());
				OBJECT_NUMBER = Integer.parseInt(inputStream.nextLine());
				ITEM_NUMBER = Integer.parseInt(inputStream.nextLine());
				
				location = new Location[LOCATION_NUMBER]; 
				object = new Objects[OBJECT_NUMBER];
				item = new Item[ITEM_NUMBER];
				
				dataPosition ++;
			} else if (dataPosition == 1) {
				
				String locationData = inputStream.nextLine();
				
				if (locationData.equals("====")) {
					dataPosition++;
				} else if (locationData.equals("*****") && locationPosition<LOCATION_NUMBER) {
					location[locationPosition] = new Location(inputStream.nextLine());
					locationPosition++;
				} else if (locationData.equals("*****")){
				} else {
					String direction = locationData.substring(0, locationData.length()-3);
					int toRoom = Integer.parseInt(
							locationData.substring(locationData.length()-1, locationData.length()));
					location[locationPosition-1].addExit(direction, toRoom);
				}
						
			} else if (dataPosition == 2){
			
				String itemData = inputStream.nextLine();
				
				if (itemData.equals("====")) {
					dataPosition++;
				} else {
					String currentItem[] = itemData.split(",");
					item[itemPosition] = new Item(currentItem[0],Integer.parseInt(currentItem[1]));
					itemPosition++;
				}
				
			} else if (dataPosition == 3){
				
				String objectData = inputStream.nextLine();
				String currentObject[] = objectData.split(",");
				
				if (objectData.equals("====")) {
					dataPosition++;
				} else if (currentObject[0].equals("LOCKED")) {
					int keyNumber = Integer.parseInt(currentObject[2]);
					object[objectPosition] = 
							new Container(currentObject[1], Integer.parseInt(currentObject[2]),
							true,item[keyNumber], keyNumber);
					objectPosition++;
				} else if (currentObject[0].equals("UNLOCK")) {
					object[objectPosition] = new Container(currentObject[1],
							Integer.parseInt(currentObject[2]));
					objectPosition++;
				} else {
					object[objectPosition] = new Door(currentObject[0],
							Integer.parseInt(currentObject[1]));
					objectPosition++;
				}
			}
		}	
		
		inputStream.close();
	}
	
	//Once all of the data has been loaded, this method will place everything where it needs
	//to be.
	//It should be noted that location 0 is the player's backpack.
	public void populateLocations() {
		
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
