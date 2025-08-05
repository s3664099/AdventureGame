/* CloseableExit Class
 * Created: 4 September 2023
 * Updated: 5 August 2025
 * Version 1.1
 * Class to handle and exit that can be closed
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public class CloseableExit extends AbstractExit implements Exit, Serializable  {
	
	private static final long serialVersionUID = 8276805139765346452L;
	private boolean closed;
	private Item item;
	private boolean itemRevealed;
		
	//Exit with multiple commands
	public CloseableExit(String name, String command, Location destination, 
						boolean closed, String description) {
	
		super(name, destination, false, command, description);
		this.closed = closed;
		this.itemRevealed = false;

	}

	//Exit involving an item
	public CloseableExit(String name, String command, Location destination, 
						boolean closed, String description,Item item) {
	
		super(name, destination, false, command, description);
		this.closed = closed;
		this.itemRevealed = true;
		this.item = item;
	}
	
	//Returns the description of what happens when attempt to move
	public String moveDescription(String command) {
		
		String response = "";
		
		if (closed) {
			response = String.format("The %s is closed%n", super.getName());
		} else {
			response = String.format("You enter the %s%n",super.getName());
		}
		
		return response;
	}
	
	//Checks if it is possible to move in that direction
	public boolean haveMoved() {
		
		boolean moved = true;
		
		if (closed) {
			moved = false;
		}
		
		return moved;
	}
	
	public boolean getOpen() {
		return this.closed;
	}
	
	public void openClose() {
		this.closed = !this.closed;
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
		return false;
	}
	
	public boolean isLockable() {
		return false;
	}
	
	@Override
	public Item getKey() {
		return null;
	}

	@Override
	public String lockUnlock(CarriableItem item, String action) {
		return null;
	}
	
	public void setItem(boolean itemRevealed) {
		this.itemRevealed = itemRevealed;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public boolean checkItem() {
		return this.itemRevealed;
	}

	@Override
	public void addDescription(String string) {}
}

/* 4 September 2023 - Created File
 * 6 September 2023 - Fixed file to call abstractExit and match interface
 * 12 September 2023 - Added functionality to handle exit descriptions
 * 11 October 2023 - Added check for lockable
 * 13 October 2023 - Added unimplemented methods
 * 25 January 2024 - Made Class Serializable
 * 5 August 2025 - Fixed minor issues
 */