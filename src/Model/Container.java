package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an object that is in fact a container
 * The purpose is to hold objects and can be either locked or unlocked
 * Locked containers will have a key to unlock it.
 * If the item is unlocked and the player examines it, the contents
 * of the container will be moved from the container to the room
 * The container will then be flagged as being empty.
 * It may be possible to put things into the container.
 * 
 * @author archimedes
 *
 */

public class Container extends Objects {

	List<Item> itemsInContainer = new ArrayList<Item>();
	private boolean isContainerLocked = false;
	private Item keyToUnlockContainer;
	private int keyItemNo;
	
	public Container(String name, int location) {
		super(name, location);
	}
	
	public Container(String name, int location, boolean locked, Item key, int keyItemNo) {

		super(name, location);
		this.isContainerLocked = locked;
		this.keyToUnlockContainer = key;
		this.keyItemNo = keyItemNo;
		
	}
	
	public void addItem(Item item) {
		itemsInContainer.add(item);
	}
	
	public boolean checkLocked() {
		return isContainerLocked;
	}
	
	public int getKey() {
		return keyItemNo;
	}
	
	

}
