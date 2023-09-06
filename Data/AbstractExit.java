/* Abstract Exit Class
 * Created: 5 September 2023
 * Updated: 6 September 2023
 * Version 0.1
 * Class to handle everything to do with an exit.
 */

package Data;

import java.util.ArrayList;

public abstract class AbstractExit {
	
	private String description;
	private Location destination;
	private boolean direction;
	private ArrayList<String> commands = new ArrayList<String>();
	
	public AbstractExit(String description, Location destination, boolean direction) {
		 
		this.description = description;
		this.destination = destination;
		this.direction = direction;
		this.commands.add(description.toLowerCase());
	}
	
	public AbstractExit(String description, Location destination, boolean direction, String command) {
		 
		this.description = description;
		
		for (String x:command.split(" ")) {
			this.commands.add(x);
		}
		this.destination = destination;
		this.direction = direction;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public ArrayList<String> getCommands() {
		return this.commands;
	}
	
	public Location getDestination() {
		return this.destination;
	}
	
	public boolean getDirection() {
		return direction;
	}

}
/* 5 September 2023 - Created File
 * 6 September 2023 - Completed class with getters and setters
 */ 
