/* Conversation
 * Created: 23 March 2024
 * Updated: 30 September 2025
 * Version: 1.3
 * Class for conversations.
 */

package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conversation implements Serializable {

	private static final long serialVersionUID = -7100430312452939043L;
	private final String response;
	private final List<Query> queries;
	private final String endConversation;
	
	//Constructor where there is only a response
	public Conversation(Builder builder) {
		this.response = builder.response;
		this.queries = builder.queries;
		this.endConversation = builder.endConversation;
	}
	
	public static class Builder {
		private String response;
		private List<Query> queries;
		private String endConversation;
		
		public Builder(String response) {
			this.response = response;
			this.queries = new ArrayList<Query>();
			this.endConversation = "";
		}
		
		public Builder setQueries(ArrayList<Query> queries) {
			this.queries = queries;
			return this;
		}
		
		public Builder addQuery(Query query) {
			this.queries.add(query);
			return this;
		}
		
		public Builder setEndConversation(String endConversation) {
			this.endConversation = endConversation;
			return this;
		}
		
        protected Builder self() {
            return this;
        }
		
		public Conversation build() {
			return new Conversation(this);
		}
		
	}
		
	public void addQuery(Query query) {
		this.queries.add(query);
	}
	
	public String getResponse() {
		return this.response;
	}
	
	public boolean checkQueries() {
		return this.queries.size()>0;
	}
	
	public List<Query> getQueries() {
		return this.queries;
	}
	
	//Gets the number of queries
	public int getNoQueries() {
		return queries.size();
	}
	
	public String getEndConversation() {
		return this.endConversation;
	}
	
	/*
	//Maybe move this to a separate function
	public List<String> displayConversation() {
		
		int noQueries = 1;
		System.out.printf("%s%n",this.getResponse());
		
		for( Query query:queries) {
			System.out.printf("%d) %s%n",noQueries,query.getQuery());
			noQueries += 1;
		}
		
		//If there a queries, adds an end conversation query
		if (noQueries>1) {
			System.out.printf("%d) End Conversation%n",noQueries);
		}
		
		int input = getInput(noQueries+1);
		
		Query selectedQuery = null;
				
		for (int i = 0;i<queries.size();i++) {
			
			if ((input - 1) == i) {
				selectedQuery = queries.get(i);
			}
		}
		
		this.endResponse.clear();
		
		//End Conversation
		if (input==queries.size()+1) {

			this.endResponse.add("We will continue some other time");
			this.endResponse.add("end");
						
		//Checks if there are no queries, and if so, sets this convo as the new conve
		//And clears the queries
		} else if (selectedQuery.getConversation().getNoQueries() == 0) {

			this.endResponse.add(selectedQuery.getConversation().getResponse());
			this.endResponse.add(selectedQuery.getEndConvo());
						
			if (selectedQuery.getEndConvo().length() != 0) {
				clearConvo(selectedQuery.getConversation().getResponse());
			}
					
		//Checks if the conversation is the end and if it is returns a end convo list
		} else if (selectedQuery.getConversation() == null) {
			this.endResponse.add(endConvo);
			this.endResponse.add(selectedQuery.getEndConvo());
		} else {
			this.endResponse = selectedQuery.getConversation().displayConversation();
						
			//Checks if the convo now has no queries, and if so, clears this convo as well
			if(this.endResponse.get(1).equals("finish")) {
				clearConvo(selectedQuery.getConversation().getResponse());
			} else if (this.endResponse.get(1).equals("end")) {
				clearConvo(selectedQuery.getConversation().getResponse());
				
				//Goes through the queries from the previous conversation, and adds the
				//to the current one.
				for (Query query:selectedQuery.getConversation().getQueries()) {
					queries.add(query);
				}
			}
		}
		
		
		return this.endResponse;
	}
	
	//Move this outside the class
	private void clearConvo(String response) {
		//this.response = response;
		//this.queries = new ArrayList<Query>();
	}
	
	//Move this outside the class
	private int getInput(int noQueries) {
		
		boolean validInput = false;
		int input = 0;
		
		System.out.println("How do you respond: ");
		Scanner query = new Scanner(System.in);
		
		while (!validInput) {
			
			//Checks if input is a numbers
			try {
				input = Integer.parseInt(query.nextLine());
				
				//Checks that it is within the value
				if ((input <1) && (input>noQueries)) {
					System.out.printf("Please enter a value between 1 and %s",noQueries);
				} else {
					validInput = true;
				}
			} catch (NumberFormatException e) {
				
                // Input is not a valid integer
                System.out.println("Input must be an integer.");
            }
		}
		return input;
	}
	*/
}

/* 23 March 2024 - Created file
 * Added getter for response, and also check to see if there are any queries
 * 4 April 2024 - Added functions for extended conversation
 * 18 April 2024 - Added function to take user input
 * 9 May 2024 - The queries now work
 * 10 May 2024 - Made variables private. Added arraylist for response
 * 11 May 2024 - Added functionality to end conversation. Started building end conversation
 * 14 May 2024 - Fixing issue with last reply
 * 8 June 2024 - Cleared the array.
 * 4 January 2025 - Made class serializable
 * 23 September 2025 - Added Builder class
 * 30 September 2025 - Completed updating class
*/
