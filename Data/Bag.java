/* Carriable Item Class
 * Created: 11 January 2025
 * Updated: 9 September 2025
 * Version: 1.3
 * The class for a carriable container
 */

package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bag extends CarriableItem implements Item, Serializable {

	private static final long serialVersionUID = 9052718269968423337L;
	private List<Item> contents;
	private boolean lockable = false;
	private boolean closeable = false;
	private boolean locked = false;
	private boolean closed = false;
	private boolean haveViewed = false;
	private Item key;
	
	public Bag(Builder builder) {
		super(builder);
		this.contents = new ArrayList<>(builder.contents);
		this.closeable = builder.closeable;
		this.lockable = builder.lockable;
		this.locked = validateLockState(builder.locked,builder.lockable,builder.closeable);
		this.closed = validateCloseState(builder.closed,builder.closeable);
		this.haveViewed = builder.haveViewed;
		this.key = builder.key;
		
		validateState();
	}
	
	private boolean validateLockState(boolean requestedLocked,boolean isLockable,boolean isCloseable) {
		if(requestedLocked && (!isLockable || !isCloseable)) {
			throw new IllegalStateException("Cannot lock a container that is not lockable and closeable");
		}
		return requestedLocked;
	}
	
	private boolean validateCloseState(boolean requestedClosed, boolean isCloseable) {
		if(requestedClosed && !isCloseable) {
			throw new IllegalStateException("Cannot close a container that is not closeable");
		}
		return requestedClosed;
	}
	
	private void validateState() {
		if (locked && !closed) {
			throw new IllegalStateException("Cannot have a locked container that is not closed");
		}
		if (locked && !lockable) {
			throw new IllegalStateException("Cannot have a locked container that is not lockable");
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
 * 16 January 2025 - Added a key to the bag
 * 9 September 2025 - Started implementing builder class
 */