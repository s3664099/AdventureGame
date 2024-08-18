/* LockableExit Class
 * Created: 11 October 2023
 * Updated: 7 April 2024
 * Version 1.0
 * Class to handle and exit that can be locked
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public class LockableExit extends CloseableExit implements Exit,Serializable {
	
	private boolean locked;
	private CarriableItem key;
	private boolean lockable = true;
	private Item item;
	private boolean itemRevealed;	
		
	//Exit with multiple commands
	public LockableExit(String name, String command, Location destination, 
						boolean closed, String description, CarriableItem key) {
	
		super(name, command, destination, true, description);
		this.locked = closed;
		this.key = key;
		this.itemRevealed = false;

	}
	
	//Returns the description of what happens when attempt to move
	public String moveDescription(String command) {
		
		String response = "";
		
		if (locked) {
			response = response.format("The %s is closed%n", super.getName());
		} else {
			response = super.moveDescription(command);
		}
		
		return response;
	}
	
	//Checks if it is possible to move in that direction
	public boolean haveMoved() {
		
		boolean moved = true;
		
		if (locked) {
			moved = false;
		} else {
			moved = super.haveMoved();
		}
		
		return moved;
	}
	
	public boolean getOpen() {
		return super.getOpen();
	}
	
	public void openClose() {
		super.openClose();
	}

	@Override
	public String getName() {
		
		return super.getName();
	}

	@Override
	public ArrayList<String> getCommands() {
		
		return super.getCommands();
	}

	@Override
	public Location getDestination() {
		
		return super.getDestination();
	}

	public String lockUnlock(CarriableItem item, String action) {
		this.locked = !this.locked;
		return String.format("You %s the %s with the %s", action, this.getName(), this.key.getName());
	}

	//Flags that the exit can be opened/closed
	@Override
	public boolean isOpenable() {
		return true;
	}

	@Override
	public String getDescription() {
		return super.getDescription();
	}
	
	public boolean getLocked() {
		return locked;
	}
	
	public Item getKey() {
		return key;
	}
	
	public boolean checkItem() {
		return super.checkItem();
	}
	
	public void setItem(boolean updateReveal) {
		super.setItem(updateReveal);
	}
	
	public Item getItem() {
		return super.getItem();
	}
}

/* 11 October 2023 - Created File
 * 13 October 2023 - Added Lockable specific methods
 * 26 January 2024 - Made class serializable
 * 7 April 2024 - Added functions for items to be revealed
*/