/* Conversation
 * Created: 23 March 2024
 * Updated: 27 March 2024
 * Version: 0.1
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
	
	public String getResponse() {
		return this.response;
	}
	
	//Checks to see if there are any queries
	public boolean checkQueries() {
		
		boolean queries = false;
		
		if (this.queries.size()>0) {
			queries = true;
		}
		
		return queries;
	}
}

/* 23 March 2024 - Created file
 * Added getter for response, and also check to see if there are any queries
*/
