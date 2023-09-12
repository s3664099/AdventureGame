/* Abstract Exit Class
 * Created: 5 September 2023
 * Updated: 12 September 2023
 * Version 0.2
 * Class to handle everything to do with an exit.
 */

package Data;

import java.util.ArrayList;

public abstract class AbstractExit {
	
	private String name;
	private String description = "There is nothing special";
	private Location destination;
	private boolean direction;
	private ArrayList<String> commands = new ArrayList<String>();
	
	public AbstractExit(String description, Location destination, boolean direction) {
		 
		this.name = description;
		this.destination = destination;
		this.direction = direction;
		this.commands.add(description.toLowerCase());
	}
	
	public AbstractExit(String name, Location destination, boolean direction, 
						String command, String description) {
		 
		this.name = name;
		
		for (String x:command.split(" ")) {
			this.commands.add(x);
		}
		this.destination = destination;
		this.direction = direction;
		
		if (description.length()>0) {
			this.description = description;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return description;
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
 * 12 September 2023 - Added functionality to handle exit description
 */ 
