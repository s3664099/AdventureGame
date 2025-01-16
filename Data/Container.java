/* Container Class
 * Created: 5 October 2023
 * Updated: 16 Janaury 2025
 * Version: 1.3
 * Class for items that can contain other items. Cannot be carried
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public class Container extends ImmoveableItem implements Item,Serializable {
	
	private ArrayList<Item> contents = new ArrayList<Item>();
	private boolean lockable = false;
	private boolean closeable = false;
	private boolean locked = false;
	private boolean closed = false;
	private boolean haveViewed = false;
	private Item key;
	
	public Container(String name, String description) {
		super(name,description);
	}
	
	public Container(String name, String description, boolean closed) {
		super(name, description);
		this.closeable = true;
		this.lockable = false;
		this.locked = false;
		this.closed = closed;
	}

	public Container(String name, String description, boolean locked, 
					 boolean closed, boolean lockable, boolean closeable, Item key) {
		super(name, description);

		this.closeable = closeable;
		this.lockable = lockable;
		this.locked = locked;
		this.closed = closed;
		
		//Overwrites to prevent conflict
		if (locked) {
			this.lockable = true;
			this.closeable = true;
			this.closed = true;
		} else if (closed) {
			this.closeable = true;
		}
		
		
		this.key = key;
	}

	@Override
	public String[] getNouns() {
		
		return super.getNouns();
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
	
	public void addItem(CarriableItem item) {
		contents.add(item);
	}
	
	public ArrayList<Item> getContents() {
		
		return contents;
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

/* 5 October 2023 Created File
 * 9 October 2023 Added functionality to Container
 * 25 January 2024 - Made Class Serializable
 * 23 June 2024 - Changed the constructors to hard code the closeable and
 *                lockable status of the container.
 * 9 January 2025 - added the contents if look at room when container open
 * 11 Janaury 2025 - Put item in container now works.
 * 16 January 2025 - Update the constructor
*/
