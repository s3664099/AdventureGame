/* Query
 * Created: 23 March 2024
 * Updated: 4 April 2024
 * Version: 0.1
 * Options for conversation.
 */

package Data;

public class Query {

	String question;
	Conversation conversation;

	public Query(String question, Conversation conversation) {
		this.question = question;
		this.conversation = conversation;
	}
	
	public Conversation getConversation() {
		return this.conversation;
	}
	
	public String getQuery() {
		return question;
	}
	
}

/* 23 March 2024 - Created file
 * 4 April 2024 - Added functions to handle an extended conversation
*/
