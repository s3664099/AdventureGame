/* Container Class
 * Created: 5 October 2023
 * Updated: 6 September 2025
 * Version: 1.4
 * Class for items that can contain other items. Cannot be carried
 */

package Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import java.io.Serializable;

public class Container extends ImmoveableItem implements Item,Serializable {
	
	private static final long serialVersionUID = 2484768982194210993L;
	private final List<Item> contents;
	private final boolean lockable;
	private final boolean closeable;
	private boolean locked;
	private boolean closed;
	private boolean haveViewed;
	private final Item key;
	
	private Container(Builder builder) {
		super(builder);
		this.contents = new ArrayList<>(builder.contents);
		this.lockable = builder.lockable;
		this.closeable = builder.closeable;
		this.locked = validateLockState(builder.locked,builder.lockable,builder.closeable);
		this.closed = validateCloseState(builder.closed,builder.closeable);
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
	
	public static class Builder extends ImmoveableItem.Builder {
		
		private List<Item> contents = new ArrayList<Item>();
		private boolean lockable = false;
		private boolean closeable = false;
		private boolean locked = false;
		private boolean closed = false;
		private Item key = null;
		
		public Builder(String name, String description) {
			super(name,description);
		}
		
		public Builder setKey(Item key) {
			this.key = Objects.requireNonNull(key,"Key cannot be null");
			this.lockable = true;
			this.closeable = true;
			return this;
		}
		
		public Builder setCloseable(boolean closeable) {
			this.closeable = closeable;
			if (!closeable) {
				this.closed = false;
				this.locked = false;
			}
			return this;
		}
		
		public Builder setClosed(boolean closed) {
			if (closed && !closeable) {
				throw new IllegalStateException("Cannot set closed if container is not closeable");
			}
			this.closed = closed;
			if (!closed) {
				this.locked = false;
			}
			return this;
		}
		
		public Builder setLockable(boolean lockable) {
			this.lockable = lockable;
			if (!lockable) {
				this.locked = false;
			}
			return this;
		}
		
		public Builder setLocked(boolean locked) {
			if (locked && (!lockable || !closeable)) {
				throw new IllegalStateException("Cannot set locked if container is not lockable and closeable");
			}
			this.locked = locked;
			if(locked) {
				this.closed = true;
			}
			return this;
		}
		
		public Builder addItem(CarriableItem item) {
			contents.add(Objects.requireNonNull(item,"Item cannot be null"));
			return this;
		}
		
        @Override
        protected Builder self() {
            return this;
        }
		
		public Container build() {
			return new Container(this);
		}
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
			response = String.format("%s. The %s contains",super.getDescription(),super.getName());
			int length = 0;
		
			for (Item content:contents) {
				String article = "a";
				
				char firstChar = content.getName().charAt(0);
				
				if ((firstChar == 'A') || (firstChar == 'E') || (firstChar == 'I')
					|| (firstChar == 'O') || (firstChar == 'U')) {
					article = "an";
				}
				
				if (length == 0) {
					response = String.format("%s %s %s",response, article, content.getName());
				} else {
					response = String.format("%s, %s %s",response,article, content.getName());
				}
				length ++;
			}
			
			if (length == 0) {
				response = String.format("%s nothing.",response);
			} else {
				this.haveViewed = true;
			}
		
		} else {
			response = String.format("%s. The %s is closed",response, super.getName());
		}
		return response;
	}
	
	public void addItem(CarriableItem item) {
		contents.add(Objects.requireNonNull(item,"Item cannot be null"));
	}
	
	public List<Item> getContents() {
		return Collections.unmodifiableList(this.contents);
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
		return key.equals(this.key);
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
 * 6 September 2025 - Updated class with builder and added protections such as nonNull
 * 					  unmodifiable list
 * 					- Added validations
*/
