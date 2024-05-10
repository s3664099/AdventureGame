/* Query
 * Created: 23 March 2024
 * Updated: 10 May 2024
 * Version: 0.3
 * Options for conversation.
 */

package Data;

public class Query {

	private String question;
	private Conversation conversation;
	private String end = "";

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
*/
