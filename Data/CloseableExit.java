/* CloseableExit Class
 * Created: 4 September 2023
 * Updated: 6 September 2023
 * Version 0.4
 * Class to handle and exit that can be closed
 */

package Data;

import java.util.ArrayList;

public class CloseableExit extends AbstractExit implements Exit {
	
	private boolean closed;
		
	//Exit with multiple commands
	public CloseableExit(String description, String command, Location destination, boolean closed) {
	
		super(description, destination, false, command);
		this.closed = closed;

	}
	
	public String moveDescription(String command) {
		
		String response = "";
		
		if (closed) {
			response = response.format("The %s is closed%n", super.getDescription());
		} else {
			response = response.format("You enter the %s%n",super.getDescription());
		}
		
		return response;
	}
	
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
	public String getDescription() {
		
		return super.getDescription();
	}

	@Override
	public ArrayList<String> getCommands() {
		
		return super.getCommands();
	}

	@Override
	public Location getDestination() {
		
		return super.getDestination();
	}

	@Override
	public void lockUnlock() {}

	//Flags that the exit can be opened/closed
	@Override
	public boolean isOpenable() {
		return true;
	}
}

/* 4 September 2023 - Created File
 * 6 September 2023 - Fixed file to call abstractExit and match interface
 */