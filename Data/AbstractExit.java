/* Abstract Exit Class
 * Created: 5 September 2023
 * Updated: 5 August 2025
 * Version 1.3
 * Class to handle everything to do with an exit.
 * 
 * Add logging
 * Add builder
 */

package Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;;

public abstract class AbstractExit implements Serializable {
	
	private static final long serialVersionUID = -5128312604573129248L;
	private final String name;
	private final String description;
	private final Location destination;
	private final boolean direction;
	private final List<String> commands = new ArrayList<String>();
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	    in.defaultReadObject();
	}
	
	public AbstractExit(String description, Location destination, boolean direction) {
		 
		this.name = Objects.requireNonNull(description, "Name cannot be null");;
		this.destination = Objects.requireNonNull(destination, "Destination cannot be null");;
		this.direction = Objects.requireNonNull(direction, "Direction cannot be null");;
		this.commands.add(description.toLowerCase());
		this.description = "There is nothing special";
	}
	
	public AbstractExit(String name, Location destination, boolean direction, 
						String command, String description) {
		 
		this.name = Objects.requireNonNull(name, "Name cannot be null");;
				
		for (String x:command.split(" ")) {
			this.commands.add(x);
		}
		this.destination = Objects.requireNonNull(destination, "Destination cannot be null");;
		this.direction = Objects.requireNonNull(direction, "Direction cannot be null");;
		
		if (description.length()>0) {
			this.description = description;
		} else {
			this.description = "There is nothing special";
		}
	}
		
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public List<String> getCommands() {
		return Collections.unmodifiableList(this.commands);
	}
	
	public Location getDestination() {
		return this.destination;
	}
	
	public boolean getDirection() {
		return direction;
	}
	
	public boolean checkItem() {
		return false;
	}
	
	public Item getItem() {
		return null;
	}
	
	public void setItem(boolean updateReveal) {}
	
	public boolean equals (String command) {
		
		String[] names = this.name.toLowerCase().split(" ");
		String[] commands = command.toLowerCase().split(" ");
		boolean match = false;
		
		//Checks if the length of the words in the name is greater than the command
		if (names.length>=commands.length) {
			
			//Does the last word of each match
			if (names[names.length-1].equals(commands[commands.length-1])) {
				
				//If command only a single word
				if (names.length == 1) {
					match = true;
				} else {
					
					int wordsCounted = 0;
					int commandWords = 0;
					
					while(wordsCounted<names.length) {
						if (names[wordsCounted].equals(commands[commandWords])) {
							commandWords += 1;
						}
						wordsCounted += 1;
					}
					
					if (commandWords == commands.length) {
						match = true;
					}
				}
			}
		}		
		return match;
	}
}
/* 5 September 2023 - Created File
 * 6 September 2023 - Completed class with getters and setters
 * 12 September 2023 - Added functionality to handle exit description
 * 27 January 2024 - Made class serializable
 * 21 February 2024 - Added an add description method
 * 7 April 2024 - Added functions for items (namely people) to be revealed
 * 24 August 2024 - Added outline for the equals method
 * 25 August 2024 - Added equals method
 * 5 August 2025 - Added null defense and changed ArrayList to list.
 * 				 - Made list return unmodifiable
 */ 
