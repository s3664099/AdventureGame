/* Query
 * Created: 23 March 2024
 * Updated: 2 October 2025
 * Version: 1.6
 * Options for conversation.
 */

package Data;

import java.io.Serializable;
import java.util.Objects;

import Data.Conversation.Builder;

public class Query implements Serializable {

	private static final long serialVersionUID = -3179181971555916081L;
	private final String question;
	private final Conversation conversation;
	private final String end;

	private Query(Builder builder) {
		this.question = builder.question;
		this.conversation = builder.conversation;
		this.end = builder.end;
	}
	
	public static class Builder {
		private String question;
		private Conversation conversation = null;
		private String end = "";
		
		public Builder(String question) {
			this.question = Objects.requireNonNull(question,"Question cannot be null");
		}
		
		public Builder setConversation(Conversation conversation) {
			this.conversation = Objects.requireNonNull(conversation,"Conversation cannot be null");
			return this;
		}
		
		public Builder setEnd(String end) {
			this.end = Objects.requireNonNull(end,"End cannot be null");
			return this;
		}
		
        protected Builder self() {
            return this;
        }
		
		public Query build() {
			return new Query(this);
		}
	}
		
	public Conversation getConversation() {
		return this.conversation;
	}
	
	public String getQuery() {
		return question;
	}
	
	public void addConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	public void setEndConvo(String endConvo) {
		this.end = endConvo;
	}
	
	public String getEndConvo() {
		return this.end;
	}
	
}

/* 23 March 2024 - Created file
 * 4 April 2024 - Added functions to handle an extended conversation
 * 9 May 2024 - Queries now work
 * 10 May 2024 - Added end conversation boolean with setters & getters. Made variables 
 * 				 private
 * 14 May 2024 - Added function to add a conversation to the query.
 * 4 January 2025 - Made Class serializable
 * 2 October 2025 - Updated with builder class
*/
