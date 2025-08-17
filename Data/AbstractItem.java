/* Abstract Item Class
 * Created: 25 August 2023
 * Updated: 17 August 2025
 * Version: 1.5
 * The main class for objects. Since we can't call it an object (reserved word)
 * we have to call it a thing.
 */

package Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

public class AbstractItem implements Serializable {
	
	private static final long serialVersionUID = -6794443495126875911L;
	
	private final String name;
	private final String description;
	private final String[] nouns;
	private final boolean treasure;
	private final String read;

	private void readObject(ObjectInputStream in) 
		    throws IOException, ClassNotFoundException {
		    in.defaultReadObject();
		    Objects.requireNonNull(name, "Deserialized name cannot be null");
		    Objects.requireNonNull(description, "Destination cannot be null");
			Objects.requireNonNull(nouns,"Commands cannot be null");
			Objects.requireNonNull(read,"Description cannot be null");
		    
	}
	
	public AbstractItem(Builder builder) {
		this.name = Objects.requireNonNull(builder.name, "Deserialized name cannot be null");
		this.description = Objects.requireNonNull(builder.description, "Destination cannot be null");
		this.nouns = Objects.requireNonNull(builder.nouns,"Commands cannot be null");
		this.treasure = builder.treasure;
		this.read = Objects.requireNonNull(builder.read,"Description cannot be null");
	}
	
	public static class Builder {
		
		private final String name;
		private final String description;

		private String[] nouns;
		private boolean treasure;
		private String read;
		
		public Builder(String name, String description) {
			this.name = Objects.requireNonNull(name, "Deserialized name cannot be null");
			this.description = Objects.requireNonNull(description, "Destination cannot be null");
			this.nouns = name.toLowerCase().split(" ");
			this.treasure = false;
			this.read = "There is nothing to read here";
		}
		
		public void setRead(String read) {
			this.read = read;
		}
		
		public void setTreasure() {
			this.treasure = !this.treasure;
		}
		
		public Item build() {
			return null;
		}
	}
		
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getNouns() {
		return this.nouns;
	}
	
	public String readItem() {
		return this.read;
	}
		
	public boolean getTreasure() {
		return this.treasure;
	}
	
	public boolean checkIsCover() {
		return false;
	}
	
	public boolean checkHidden(boolean whatItems) {
		return false;
	}
	
	public boolean checkRemove() {
		return false;
	}
	
	public void addExit(Exit hiddenExit) {}
	
	public void addItem(Item hiddenItem) {}
	
	public Conversation talk() {
		String response = String.format("The %s doesn't respond.", this.getName());;
		Conversation conversation = new Conversation(response);
		return conversation;
	}
	
	public boolean getExtended() {
		return false;
	}
	
	public void setExtended() {}
	
	public void getLeave(String leave) {}

	public String getLeave() {
		return "";
	}
	
	public void getLeaveConvo(String leaveConvo) {}
	
	public String getLeaveConvo() {
		return "";
	}
	
	public void setLeave(String leave) {}

	public void setLeaveConvo(String leaveConvo, Conversation endConversation) {}
	
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

/* 25 August 2023 - Created File
* 27 August 2023 - Added Comments
* 14 September 2023 - Began to add methods to build items
* 25 January 2024 - Added methods to handle treasures
* 27 January 2024 - Made class serializable
* 28 January 2024 - Added function for cover
* 29 January 2024 - Added further functions for cover
* 13 March 2024 - Added blank functions for adding items and exits
* 27 March 2024 - Added the talk function
* 4 April 2024 - Added function to determine whether conversation is extended.
* 11 May 2024 - Added functionality so to change whether a conversation is extended or not.
* 8 June 2024 - Added leave conversation methods
* 24 August 2024 - Added outline for equals method
* 26 August 2024 - Added equals method
* 9 Janaury 2025 - Added read methons (ie read the item). Added basic name so containers list contents
* 16 August 2025 - Fixed warnings
* 17 August 2025 - Added builder and null warnings
*/