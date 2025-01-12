/* Carriable Item Class
 * Created: 11 January 2025
 * Updated: 12 January 2025
 * Version: 1.1
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

	@Override
	public String getName() {
		String response = super.getName();
		response = getContents(response);
		return response;
	}
	
	public String getBasicName() {
		return super.getName();
	}
	
	public String getDescription() {
		String response = super.getDescription();
		response = getContents(response);			
		return response;
	}
	
	private String getContents(String response) {
		
		if ((!closed) && (!locked)) {
			response = response.format("%s. The %s contains",super.getDescription(),super.getName());
			int length = 0;
		
			for (Item content:contents) {
				String article = "a";
				
				char firstChar = content.getName().charAt(0);
				
				if ((firstChar == 'A') || (firstChar == 'E') || (firstChar == 'I')
					|| (firstChar == 'O') || (firstChar == 'U')) {
					article = "an";
				}
				
				if (length == 0) {
					response = response.format("%s %s %s",response, article, content.getName());
				} else {
					response = response.format("%s, %s %s",response,article, content.getName());
				}
				length ++;
			}
			
			if (length == 0) {
				response = response.format("%s nothing.",response);
			} else {
				this.haveViewed = true;
			}
		
		} else {
			response = response.format("%s. The %s is closed",response, super.getName());
		}
		return response;
	}
		
	public boolean getViewed() {
		return haveViewed;
	}
	
	//checks if the container is closeable
	public boolean getCloseable() {
		return closeable;
	}
	
	//Checks if it is closed
	public boolean getClosed() {
		return closed;
	}
	
	//Checks if the container is lockable
	public boolean getLockable() {
		return lockable;
	}
	
	//Checks if the container is locked
	public boolean getLocked() {
		return locked;
	}
	
	//Opens/closes the container
	public void setClosed() {
		this.closed = !closed;	
	}
	
	//Locks/Unlocks the container
	public void setLocked() {
		this.locked = !locked;
	}
	
	//Checks that the key is the correct key
	public boolean checkKey(Item key) {
		
		boolean hasKey = false;
		
		if (key.equals(this.key)) {
			hasKey = true;
		}	
		return hasKey;
	}	
}

/* 11 January 2025 - Created File
 * 12 January 2025 - Added methods to open and close the bag
 */