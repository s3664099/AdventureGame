/* Container Class
 * Created: 5 October 2023
 * Updated: 9 October 2023
 * Version: 0.1
 * Class for items that can't be picked up and carried.
 */

package Data;

import java.util.ArrayList;

public class Container extends ImmoveableItem implements Item {
	
	private ArrayList<Item> contents = new ArrayList<Item>();
	private boolean lockable = false;
	private boolean closeable = false;
	private boolean locked = false;
	private boolean closed = false;
	private boolean haveViewed = false;
	
	public Container(String name, String description) {
		super(name,description);
	}
	
	public Container(String name, String description, boolean closeable, boolean lockable,
					boolean locked, boolean closed) {
		super(name, description);
		this.closeable = closeable;
		this.lockable = lockable;
		this.locked = locked;
		this.closed = closed;
	}

	@Override
	public String[] getNouns() {
		
		return super.getNouns();
	}

	@Override
	public String getName() {
		return super.getName();
	}
	
	public String getDescription() {
		String response = super.getDescription();
		
		if (!closed) {
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
	
	//Lock/Unlock
	//Open/Close

}

/* 5 October 2023 Created File
 * 9 October 2023 Added functionality to Container
*/
