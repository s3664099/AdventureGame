/* Conversation
 * Created: 23 March 2024
 * Updated: 23 March 2024
 * Version: 0.0
 * Class for conversations.
 */

package Data;

import java.util.ArrayList;

public class Conversation {

	String response;
	ArrayList<Query> queries;
	
	public Conversation(String response) {
		this.response = response;
	}
	
	public Conversation(String response, ArrayList<Query> queries) {
		this.response = response;
		this.queries = queries;
	}
	
	public void addQuery(Query query) {
		this.queries.add(query);
	}
}

/* 23 March 2024 - Created file
*/
