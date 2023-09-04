/* CloseableExit Class
 * Created: 4 September 2023
 * Updated: 4 September 2023
 * Version 0.1
 * Class to handle and exit that can be closed
 */

package Data;

import java.util.ArrayList;

public class CloseableExit extends Exit {
	
	private boolean closed;
	private boolean closeable = true;
	
	//Standard Exit
	public CloseableExit(String description, Location destination, boolean closed,boolean direction) {
		
		super(description, destination, direction);
		this.closed = closed;
	}
		
	//Exit with multiple commands
	public CloseableExit(String description, String command, Location destination,
			boolean closed, boolean direction) {
	
		super(description, command, destination, direction);
		
		this.closed = closed;

	}
	
	public String move() {
		
		String response = "";
		
		if (closed) {
			response = response.format("The %s is closed%n", super.getDescription());
		} else {
			response = super.move();
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
}

/* 4 September 2023 - Created File
*/