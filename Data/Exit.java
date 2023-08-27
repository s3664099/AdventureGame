/* Exit Class
 * Created: 25 August 2023
 * Updated: 27 August 2023
 * Version 0.2
 * Class to handle everything to do with an exit.
 */

package Data;

public class Exit {
	
	private String description;
	private Location destination;
	private boolean locked;
	private boolean closed;
	private boolean direction;
	private String command;
	
	public Exit(String description, Location destination, boolean locked, 
				boolean closed,boolean direction) {
		this.description = description;
		this.command = description.toLowerCase();
		this.destination = destination;
		this.locked = locked;
		this.closed = closed;
		this.direction = direction;
	}
	
	public Exit(String description, String command, Location destination, boolean locked, 
			boolean closed,boolean direction) {
	this.description = description;
	this.command = command;
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
	
	public String getCommand() {
		return this.command;
	}
	
	public Location getDestination() {
		return this.destination;
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
*/