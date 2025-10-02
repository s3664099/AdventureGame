/* Abstract Item Class
 * Created: 25 August 2023
 * Updated: 2 October 2025
 * Version: 1.9
 * The main class for objects. Since we can't call it an object (reserved word)
 * we have to call it a thing.
 */

package Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public class AbstractItem implements Serializable,Item {
	
	private static final long serialVersionUID = -6794443495126875911L;
	
	private final String name;
	private final String description;
	private final String[] nouns;
	private final boolean treasure;
	private final String read;
	
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
		private boolean treasure = false;
		private String read = "There is nothing to read here";
		
		public Builder(String name, String description) {
			this.name = Objects.requireNonNull(name, "Deserialized name cannot be null");
			this.description = Objects.requireNonNull(description, "Destination cannot be null");
			this.nouns = name.toLowerCase().split(" ");
		}
		
		public Builder setRead(String read) {
			this.read = read;
			return this;
		}
		
		public Builder setTreasure() {
			this.treasure = !this.treasure;
			return this;
		}
		
        protected Builder self() {
            return this;
        }
		
		public AbstractItem build() {
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
	
	public Conversation talk() {
		String response = String.format("The %s doesn't respond.", this.getName());;
		Conversation conversation = new Conversation.Builder(response).build();
		return conversation;
	}
	
	public boolean getExtended() {
		return false;
	}
	
	public void getLeave(String leave) {}

	public String getLeave() {
		return "";
	}
	
	public void getLeaveConvo(String leaveConvo) {}
	
	public String getLeaveConvo() {
		return "";
	}
	
	public boolean equals (String command) {
		
		String[] commands = command.toLowerCase().split(" ");
		boolean match = false;
		
		//Checks if the length of the words in the name is greater than the command
		if (nouns.length>=commands.length) {
			
			//Does the last word of each match
			if (nouns[nouns.length-1].equals(commands[commands.length-1])) {
				
				//If command only a single word
				if (nouns.length == 1) {
					match = true;
				} else {
					
					int wordsCounted = 0;
					int commandWords = 0;
					
					while(wordsCounted<nouns.length) {
						if (nouns[wordsCounted].equals(commands[commandWords])) {
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

	@Override
	public String getBasicName() {
		return null;
	}

	@Override
	public boolean getCloseable() {
		return false;
	}

	@Override
	public boolean getClosed() {
		return false;
	}

	@Override
	public boolean getLockable() {
		return false;
	}

	@Override
	public boolean getLocked() {
		return false;
	}

	@Override
	public void setClosed() {}

	@Override
	public void setLocked() {}

	@Override
	public boolean checkKey(Item key) {
		return false;
	}

	@Override
	public boolean getMoveable() {
		return false;
	}

	@Override
	public boolean getMoved() {
		return false;
	}

	@Override
	public void setMoved() {}

	@Override
	public Optional<Exit> getHiddenExit() {
		return Optional.empty();
	}

	@Override
	public Optional<Item> getHiddenItem() {
		return Optional.empty();
	}

	@Override
	public boolean checkHiddenExits() {
		return false;
	}

	@Override
	public boolean checkHiddenItems() {
		return false;
	}

	@Override
	public void addItem(CarriableItem item) {}
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
* 21 August 2025 - Removed setters. Added interface and methods
* 22 August 2025 - Updated Builder
* 23 August 2025 - Updated getHiddenItems and getHiddenExits
* 2 October 2025 - Updated Conversation
*/