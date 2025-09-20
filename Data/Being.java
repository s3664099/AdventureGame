/* Being
 * Created: 23 March 2024
 * Updated: 20 September 2025
 * Version: 1.3
 * Class for creatures.
 * 
 * Look at how convo works, and maybe set the endConvo with the leave
 */

package Data;

import java.util.Objects;
import java.util.Optional;
import java.io.Serializable;

public class Being extends AbstractItem implements Item,Serializable {
	
	private static final long serialVersionUID = 9188928562890699138L;
	private final Conversation conversation;
	private final String leave;
	private final String leaveConvo;
	private final Conversation endConversation;
	private final String read;
	private boolean extended;
	private boolean leaveSet;
	
	public Being(Builder builder) {
		super(builder);
		this.conversation = builder.conversation;
		this.extended = builder.extended;
		this.leaveSet = false;
		this.read = builder.read;
		this.leave = builder.leave;
		this.leaveConvo = builder.leaveConvo;
		this.endConversation = builder.endConversation;
	}
	
	public static class Builder extends AbstractItem.Builder {
		
		private Conversation conversation;
		private boolean extended;
		private String leave;
		private String leaveConvo;
		private Conversation endConversation;
		private String read;
		
		public Builder(String name, String description,Conversation conversation, Boolean extended) {
			super(name,description);
			this.leave = "ok";
			this.read = String.format("Despite your amazing intuition, you are unable to read %s", name);
			this.conversation = Objects.requireNonNull(conversation,"Conversation cannot be null");;
			this.extended = extended;
			this.leaveConvo = null;
			this.endConversation = null;
		}
		
		public Builder setLeave(String leave) {
			this.leave = Objects.requireNonNull(leave,"leave cannot be null");
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
	
	public void setLeaveConvo(boolean leaveSet) {
		this.leaveSet = leaveSet;
	}
	
	//Leave function for when the being no longer wishes to talk.
	public String getLeaveConvo() {
		return this.leaveConvo;
	}
	
	public Conversation talk() {
		return leaveSet?endConversation:conversation;
	}
	
	public boolean getExtended() {
		return this.extended;
	}
	
	public void setExtended(boolean extended) {
		this.extended = extended;
	}
	
	public String getRead() {
		return this.read;
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
		return false;
	}

	@Override
	public Optional<Exit> getHiddenExit() {
		return Optional.empty();
	}

	@Override
	public Optional<Item> getHiddenItem() {
		return Optional.empty();
	}

	@Override
	public boolean getMoved() {
		return false;
	}

	@Override
	public void setMoved() {}

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

/* 23 March 2024 - Created file
 * 4 April 2024 - Added boolean to advise whether conversation extended of not. Added
 * 		static conversation to hold the start
 * 10 May 2024 - Made variables private. Added end convo functionality
 * 11 May 2024 - Added set extended function to change whether conversation is extended
 * 8 June 2024 - Added the leave conversation
 * 9 January 2025 - Added special read for a being
 * 11 January 2025 - Added an addItem method
 * 20 September 2025 - Added builder class and updated methods
*/
