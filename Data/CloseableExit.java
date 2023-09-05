/* CloseableExit Class
 * Created: 4 September 2023
 * Updated: 5 September 2023
 * Version 0.3
 * Class to handle and exit that can be closed
 */

package Data;

import java.util.ArrayList;

public class CloseableExit implements Exit {
	
	private String description;
	private Location destination;
	private boolean direction;
	private ArrayList<String> commands = new ArrayList<String>();
	private boolean closed;
	
	//Standard Exit
	public CloseableExit(String description, Location destination, boolean closed) {
		
		this.description = description;
		this.destination = destination;
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

	@Override
	public String moveDescription(String Command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lockUnlock() {
		// TODO Auto-generated method stub
		
	}
}

/* 4 September 2023 - Created File
*/