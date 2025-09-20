/* Being
 * Created: 23 March 2024
 * Updated: 20 September 2025
 * Version: 1.3
 * Class for creatures.
 */

package Data;

import java.util.ArrayList;
import java.util.Objects;

import Data.Bag.Builder;

import java.io.Serializable;

public class Being extends AbstractItem implements Item,Serializable {
	
	private static final long serialVersionUID = 9188928562890699138L;
	private final Conversation conversation;
	private final Conversation conversationStart;
	private final String leave;
	private final String leaveConvo;
	private final String leaveResponse;
	private final Conversation endConversation;
	private final String read;
	private Boolean extended;
	private Boolean leaveSet;
	
	public Being(Builder builder) {
		super(builder);
		this.conversation = builder.conversation;
		this.conversationStart = builder.conversationStart;
		this.extended = builder.extended;
		this.leaveSet = false;
		this.read = builder.read;
		this.leave = builder.leave;
		this.leaveConvo = builder.leaveConvo;
		this.leaveResponse = builder.leaveResponse;
		this.endConversation = builder.endConversation;
	}
	
	public static class Builder extends AbstractItem.Builder {
		
		private Conversation conversation;
		private Boolean extended;
		private Conversation conversationStart;
		private String leave;
		private String leaveConvo;
		private String leaveResponse;
		private Conversation endConversation;
		private String read;
		
		public Builder(String name, String description,Conversation conversation, Boolean extended) {
			super(name,description);
			this.leave = "ok";
			this.read = String.format("Despite your amazing intuition, you are unable to read %s", name);
			this.conversation = Objects.requireNonNull(conversation,"Conversation cannot be null");;
			this.extended = extended;
			this.conversationStart = null;
			this.leaveConvo = null;
			this.leaveResponse = null;
			this.endConversation = null;
		}
		
		public Builder setConversationStart(Conversation conversationStart) {
			this.conversationStart = Objects.requireNonNull(conversationStart,"ConversationStart cannot be null");
			return this;
		}
		
		public Builder setLeave(String leave) {
			this.leave = Objects.requireNonNull(leave,"leave cannot be null");
			return this;
		}
		
		public Builder setLeaveResponse(String leaveResponse) {
			this.leaveResponse = Objects.requireNonNull(leaveResponse,"leaveResponse cannot be null");
			return this;
		}
		
		public Builder setLeaveConvo(String leaveConvo) {
			this.leaveConvo = Objects.requireNonNull(leaveConvo,"leaveConvo cannot be null");
			return this;
		}
		
		public Builder setRead(String read) {
			this.read = Objects.requireNonNull(read,"read cannot be null");
			return this;
		}
		
		public Builder setEndConversation(Conversation endConversation) {
			this.endConversation = Objects.requireNonNull(endConversation,"endConversation cannot be null");
			return this;
		}
		
        @Override
        protected Builder self() {
            return this;
        }
		
		public Being build() {
			return new Being(this);
		}
	}
		
	public String getLeave() {
		return leave;
	}
	
	public void setLeaveConvo() {
		this.leaveSet = false;
	}
	
	//Leave function for when the being no longer wishes to talk.
	public String getLeaveConvo() {
		
		this.extended = !this.extended;
		return this.leaveConvo;
	}
	
	public Conversation talk() {
		return leaveSet?conversation:endConversation;
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

	@Override
	public void addItem(CarriableItem item) {
		// TODO Auto-generated method stub
		
	}
}

/* 23 March 2024 - Created file
 * 4 April 2024 - Added boolean to advise whether conversation extended of not. Added
 * 		static conversation to hold the start
 * 10 May 2024 - Made variables private. Added end convo functionality
 * 11 May 2024 - Added set extended function to change whether conversation is extended
 * 8 June 2024 - Added the leave conversation
 * 9 January 2025 - Added special read for a being
 * 11 January 2025 - Added an addItem method
 * 20 September 2025 - Start builder class
*/
