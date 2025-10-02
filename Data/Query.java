/* Query
 * Created: 23 March 2024
 * Updated: 4 January 2025
 * Version: 1.5
 * Options for conversation.
 */

package Data;

import java.io.Serializable;

public class Query implements Serializable {

	private static final long serialVersionUID = -3179181971555916081L;
	private final String question;
	private final Conversation conversation;
	private final String end = "";

	private Query(Builder builder) {
		this.question = builder.question;
		this.conversation = builder.conversation;
		this.end = builder.end;
	}
	
	public static class Builder(){
		pr
	}
	
	//Continue conversation query
	public Query(String question, Conversation conversation) {
		this.question = question;
		this.conversation = conversation;
	}
	
	//End conversation query
	public Query(String question) {
		this.question = question;
		this.conversation = null;
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
*/
