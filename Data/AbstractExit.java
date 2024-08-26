/* Abstract Exit Class
 * Created: 5 September 2023
 * Updated: 25 August 2024
 * Version 1.2
 * Class to handle everything to do with an exit.
 */

package Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.Serializable;;

public abstract class AbstractExit implements Serializable {
	
	private String name;
	private String description = "There is nothing special";
	private Location destination;
	private boolean direction;
	private ArrayList<String> commands = new ArrayList<String>();

	public AbstractExit() {}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	    in.defaultReadObject();
	}
	
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
	
	public void addDescription(String description) {
		this.description = description;
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
 */ 
