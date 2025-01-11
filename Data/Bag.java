/* Carriable Item Class
 * Created: 11 January 2025
 * Updated: 11 January 2025
 * Version: 1.0
 * The class for a carriable container
 */

package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Bag extends CarriableItem implements Item, Serializable {

	private ArrayList<Item> contents = new ArrayList<Item>();
	private boolean lockable = false;
	private boolean closeable = false;
	private boolean locked = false;
	private boolean closed = false;
	private boolean haveViewed = false;
	private Item key;
	
	public Bag(String name, String description) {
		super(name, description);	
	}
	
	public Bag(String name,String description,boolean closeable,boolean closed,boolean lockable, boolean locked) {
		super(name,description);
		this.closeable = closeable;
		this.closed = closed;
		
		//Overides a false closeable
		if (closed) {
			this.closeable = true;
		}
		
		this.lockable = lockable;
		this.locked = locked;
		
		//Overrides a false lockable
		if (locked) {
			this.lockable = true;
		}
	}
	
	public void addItem(CarriableItem item) {
		contents.add(item);
	}
	
	public void removeItem(int index) {
		contents.remove(index);
	}
	
	public ArrayList<Item> getContents() {
		return contents;
	}
}

/* 11 January 2025 - Created File
 *
 */