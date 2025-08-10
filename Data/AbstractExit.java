/* Abstract Exit Class
 * Created: 5 September 2023
 * Updated: 10 August 2025
 * Version 1.5
 * Class to handle everything to do with an exit.
 * 
 * Go back and add generics once the exits are fixed.
 */

package Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;

public abstract class AbstractExit implements Serializable, Exit {
	
	private static final long serialVersionUID = -5128312604573129248L;
	private final String name;
	private final String description;
	private final Location destination;
	private final boolean direction;
	private final List<String> commands;
	
	private void readObject(ObjectInputStream in) 
		    throws IOException, ClassNotFoundException {
		    in.defaultReadObject();
		    Objects.requireNonNull(name, "Deserialized name cannot be null");
		    Objects.requireNonNull(destination, "Destination cannot be null");
			Objects.requireNonNull(commands,"Commands cannot be null");
			Objects.requireNonNull(description,"Description cannot be null");
		    
	}
	
	public AbstractExit(Builder builder) {
		 
		this.name = Objects.requireNonNull(builder.name, "Name cannot be null");
		this.destination = Objects.requireNonNull(builder.destination, "Destination cannot be null");
		this.direction = builder.direction;
		this.commands = Objects.requireNonNull(builder.commands,"Commands cannot be null");
		this.description = Objects.requireNonNull(builder.description,"Description cannot be null");
		
	}

	public static class Builder {
		private final String name;
		private final Location destination;
		private final boolean direction;

		private String description;
		private List<String> commands = new ArrayList<String>();
		
		public Builder(String name, Location destination, boolean direction) {
			this.name = Objects.requireNonNull(name, "Name cannot be null");
			this.destination = Objects.requireNonNull(destination, "Destination cannot be null");;
			this.direction = direction;
			this.commands.add(description.toLowerCase());
			this.description = "There is nothing special";			
		}
		
		public Builder addCommand(String command) {
			
			for (String x:command.split(" ")) {
				this.commands.add(x);
			}			
			return this;
		}
		
		public Builder addDescription(String description) {
			if (description.length()>0) {
				this.description = description;
			} else {
				this.description = "There is nothing special";
			}
			return this;
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
	
	public abstract Item getItem();
	
	public abstract void setItem(boolean updateReveal);
	
	public boolean equals (String userInput) {
	    String normalizedExitName = String.join(" ", this.commands);
	    String normalizedInput = userInput.toLowerCase().trim();
	    return normalizedExitName.contains(normalizedInput);
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
 * 6 August 2025 - Added builder class
 * 10 August 2025 - Updated class for minor issues
 */ 
