/* Being
 * Created: 23 March 2024
 * Updated: 8 June 2024
 * Version: 0.4
 * Class for creatures.
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public class Being extends AbstractItem implements Item,Serializable {
	
	private Conversation conversation;
	private Boolean extended;
	private static Conversation conversationStart;
	private String leave = "ok";
	private String leaveConvo;
	private String leaveResponse;
	private Conversation endCoversation;
	
	public Being(String name, String description, Conversation conversation, Boolean extended) {
		super(name,description);
		this.conversation = conversation;
		this.conversationStart = conversation;
		this.extended = extended;
	}
	
	public void setLeave(String leave) {
		this.leave = leave;
	}
	
	public String getLeave() {
		return leave;
	}
	
	//Sets a conversation if the user no longer wishes to talk.
	public void setLeaveConvo(String leaveConvo, Conversation endConversation) {
		this.leaveConvo = leaveConvo;
		this.endCoversation = endConversation;
	}
	
	//Leave function for when the being no longer wishes to talk.
	public String getLeaveConvo() {
		
		this.extended = !this.extended;
		this.conversation = this.endCoversation;
		return this.leaveConvo;
	}
	
	public Conversation talk() {
		return conversation;
	}
	
	public boolean getExtended() {
		return this.extended;
	}
	
	public void setExtended() {
		this.extended = !this.extended;
	}

	@Override
	public String[] getNouns() {
		
		return super.getNouns();
	}

	@Override
	public String getName() {
		return super.getName();
	}

	//Methods for items that are closeable and lockable
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
	public void setClosed() {		
	}

	@Override
	public void setLocked() {		
	}

	@Override
	public boolean checkKey(Item key) {
		return false;
	}

	@Override
	public boolean getMoveable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Exit getHiddenExit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getHiddenItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getMoved() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMoved() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkHiddenExits() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkHiddenItems() {
		// TODO Auto-generated method stub
		return false;
	}
}

/* 23 March 2024 - Created file
 * 4 April 2024 - Added boolean to advise whether conversation extended of not. Added
 * 		static conversation to hold the start
 * 10 May 2024 - Made variables private. Added end convo functionality
 * 11 May 2024 - Added set extended function to change whether conversation is extended
 * 8 June 2024 - Added the leave conversation
*/
