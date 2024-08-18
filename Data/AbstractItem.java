/* Abstract Item Class
 * Created: 25 August 2023
 * Updated: 8 June 2024
 * Version: 1.0
 * The main class for objects. Since we can't call it an object (reserved word)
 * we have to call it a thing.
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public class AbstractItem implements Serializable {
	
	private String name;
	private String description;
	private String[] nouns;
	private boolean treasure;
	
	public AbstractItem() {}
	
	public AbstractItem(String name, String description) {
		
		this.name = name;
		this.description = description;
		name = name.toLowerCase();
		this.nouns = name.split(" ");
		this.treasure = false;
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
	
	public void updateItem(String name, String description) {
		this.name = name;
		this.description = description;
		this.nouns = name.split(" ");
	}
	
	public void setTreasure() {
		this.treasure = !this.treasure;
	}
	
	public boolean getTreasure() {
		return this.treasure;
	}
	
	//Flags that this item isn't a cover
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
	
	//Basic response when user attempts to speak to an object
	public Conversation talk() {

		String response = "";
		response = response.format("The %s doesn't respond.", this.getName());
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
	
	public void setLeave(String leave) {
		// TODO Auto-generated method stub
		
	}

	public void setLeaveConvo(String leaveConvo, Conversation endConversation) {
		// TODO Auto-generated method stub
		
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
*/