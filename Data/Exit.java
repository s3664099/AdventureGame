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
	private boolean direction;
	private ArrayList<String> commands = new ArrayList<String>();
	
	//Standard Exit
	public Exit(String description, Location destination, boolean direction) {
		this.description = description;
		this.commands.add(description.toLowerCase());
		this.destination = destination;
		this.direction = direction;
	}
		
	//Exit with multiple commands
	public Exit(String description, String command, Location destination, boolean direction) {
	this.description = description;
	
	for (String x:command.split(" ")) {
		this.commands.add(x);
	}
	this.destination = destination;
	this.direction = direction;
}
	
	public String move() {
		
		String response = "";
		
		if (!direction) {
			response = response.format("You enter %s%n", this.destination.getName());	
		} else {
			response = response.format("You go %s and enter %s%n",this.description,
										this.destination.getName());
		}
		return response;
	}
	
	public boolean haveMoved() {
		
		boolean moved = true;
		
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

}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 29 August 2023 - Added open/close lock/unlock. Added multiple commands for exits
*/