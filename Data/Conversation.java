/* Conversation
 * Created: 23 March 2024
 * Updated: 4 March 2024
 * Version: 0.2
 * Class for conversations.
 */

package Data;

import java.util.ArrayList;

public class Conversation {

	String response;
	ArrayList<Query> queries;
	Conversation upto;
	String endConvo;
	
	public Conversation(String response) {
		this.response = response;
	}
	
	public Conversation(String response, ArrayList<Query> queries, String endConvo) {
		this.response = response;
		this.queries = queries;
		this.endConvo = endConvo;
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
	
	public void displayConversation() {
		
		int noQueries = 1;
		System.out.print(this.getResponse());
		
		for( Query query:queries) {
			System.out.printf("%d%s%n",noQueries,query.getQuery());
			noQueries += 1;
		}
		System.out.printf("%d%s%n",noQueries,this.endConvo);
		
		//Request input that checks if it is a valid input
		//If valid gets the convo from the query or ends.
		//If end, then saves where the convo is upto
		//If finish can either restart the convo, or have a blunt response and resets extended
		
	}
}

/* 23 March 2024 - Created file
 * Added getter for response, and also check to see if there are any queries
 * 4 April 2024 - Added functions for extended conversation
*/
