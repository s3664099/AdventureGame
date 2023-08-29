/* Exit Class
 * Created: 25 August 2023
 * Updated: 29 August 2023
 * Version 0.3
 * Class to handle everything to do with an exit.
 */

package Data;

import java.util.ArrayList;

public class Exit {
	
	private String description;
	private Location destination;
	private boolean locked;
	private boolean closed;
	private boolean direction;
	private ArrayList<String> commands = new ArrayList<String>();
	
	//Standard Exit
	public Exit(String description, Location destination, boolean locked, 
				boolean closed,boolean direction) {
		this.description = description;
		this.commands.add(description.toLowerCase());
		this.destination = destination;
		this.locked = locked;
		this.closed = closed;
		this.direction = direction;
	}
	
	//Exit with mutliple commands
	public Exit(String description, String command, Location destination, boolean locked, 
			boolean closed,boolean direction) {
	this.description = description;
	
	for (String x:command.split(" ")) {
		this.commands.add(x);
	}
	this.destination = destination;
	this.locked = locked;
	this.closed = closed;
	this.direction = direction;
}
	
	public String move() {
		
		String response = "";
		
		if (locked) {
			response = response.format("The %s is locked%n", description);
		} else if (closed) {
			response = response.format("The %s is closed%n", description);
		} else if (!direction) {
			response = response.format("You enter %s%n", this.destination.getName());	
		} else {
			response = response.format("You go %s and enter %s%n",this.description,
										this.destination.getName());
		}
		return response;
	}
	
	public boolean haveMoved() {
		
		boolean moved = true;
		
		if ((locked) || (closed)) {
			moved = false;
		}
		
		return moved;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public ArrayList<String> getCommand() {
		return this.commands;
	}
	
	public Location getDestination() {
		return this.destination;
	}
	
	public boolean getOpen() {
		return this.closed;
	}
	
	public boolean getLocked() {
		return this.locked;
	}
	
	public void openClose() {
		this.closed = !this.closed;
	}
	
	public void lockUnlock() {
		this.locked = !this.locked;
	}	
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 29 August 2023 - Added open/close lock/unlock. Added multiple commands for exits
*/