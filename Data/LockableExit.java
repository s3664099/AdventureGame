/* LockableExit Class
 * Created: 11 October 2023
 * Updated: 11 October 2023
 * Version 0.0
 * Class to handle and exit that can be locked
 */

package Data;

import java.util.ArrayList;

public class LockableExit extends CloseableExit implements Exit {
	
	private boolean locked;
	private CarriableItem key;
	private boolean lockable = true;
		
	//Exit with multiple commands
	public LockableExit(String name, String command, Location destination, 
						boolean closed, String description, CarriableItem key) {
	
		super(name, command, destination, false, description);
		this.locked = closed;
		this.key = key;

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

	public void lockUnlock(CarriableItem item) {
		
		if (item == key) {
			this.locked = !this.locked;
		}
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
}

/* 11 October 2023 - Created File
*/