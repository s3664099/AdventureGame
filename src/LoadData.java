import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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
		
		String locationFile = "locations.dat";
		String objectFile = "objects.dat";
		String itemFile = "items.dat";
		Boolean cont = true;

		loadLocations(locationFile, cont);
		loadObjects(objectFile, cont);
		loadItems(itemFile, cont);
										
	}
	
	//each of these methods are similar in that they load specific types of objects
	//initially they are added to an array list, but they are then passed into a 
	//primitive array (namely because primitive arrays are faster, however the length
	//needs to be predefined)
	
	public void loadLocations (String file, Boolean cont) {
		
		ArrayList<Location> locationList = new ArrayList<Location>();
		
		try {
			ObjectInputStream objinp = new ObjectInputStream( new FileInputStream(file));
			
			while (cont) {
				try {
					Location location = (Location) objinp.readObject();
					
					if (location != null) {
						locationList.add(location);
					} else {
						cont = false;
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		
		//when the end of file is reached, an exception is thrown.
		//this catches the exception and, well, does nothing because
		//nothing needs to be done.
		} catch (EOFException e) {
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		LOCATION_NUMBER = locationList.size();
		location = new Location[LOCATION_NUMBER]; 

		
		for (Location locations:locationList) {
			location[locationPosition] = locations;
			locationPosition++;
		}
	}
	
	public void loadObjects(String file, Boolean cont) {
		
		ArrayList<Objects> objectList = new ArrayList<Objects>();
		
		try {
			ObjectInputStream objinp = new ObjectInputStream( new FileInputStream(file));
			
			while (cont) {
				try {
					Objects objects = (Objects) objinp.readObject();
					
					if (objects != null) {
						objectList.add(objects);
					} else {
						cont = false;
					}
					
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				}
			}
			
		} catch (EOFException e) {
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		OBJECT_NUMBER = objectList.size();
		object = new Objects[OBJECT_NUMBER]; 

		
		for (Objects objectNumber:objectList) {
			object[objectPosition] = objectNumber;
			objectPosition++;
		}
	}
	
	public void loadItems(String file, Boolean cont) {
		
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		try {
			ObjectInputStream objinp = new ObjectInputStream( new FileInputStream(file));
			
			while (cont) {
				try {
					Item item = (Item) objinp.readObject();
					
					if (item != null) {
						itemList.add(item);
					} else {
						cont = false;
					}
					
				} catch (EOFException e) {
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			System.err.println("File "+file+" not found");
		}
		
		ITEM_NUMBER = itemList.size();
		item = new Item[ITEM_NUMBER]; 

		
		for (Item items:itemList) {
			item[itemPosition] = items;
			itemPosition++;
			
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
