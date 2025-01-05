/* Command Function
 * Created: 31 August 2023
 * Updated: 5 January 2025
 * Version: 1.2
 * Class that handles fuctions that deal with commands that are entered.
 */

package Model;

public class UserCommand {
	
	private String verb;
	private String subject;
	private String object;
	private boolean lookInside = false;
	private boolean isCardinal = false;
	private boolean lookThrough = false;
	private boolean useWith = false;
	
	public void setCommand(String verb,String subject, String object) {
		this.verb = verb;
		this.subject = subject;
		this.object = object;
	}

	public void setCommand(String verb,String subject) {
		this.verb = verb;
		this.subject = subject;
	}
	
	public void setObject(String object) {
		this.object = object;
	}
	
	public void setInside() {
		this.lookInside = !this.lookInside;
	}
		
	public void setThrough() {
		this.lookThrough = !this.lookThrough;
	}
	
	public void setCardinal() {
		this.isCardinal = !this.isCardinal;
	}
	
	public void setWith() {
		this.useWith = !this.useWith;
	}
		
	public String getVerb() {
		return this.verb;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public String getObject() {
		return this.object;
	}
	
	public boolean getInside() {
		return this.lookInside;
	}
	
	public boolean getThrough() {
		return this.lookThrough;
	}
	
	public boolean getCardinal() {
		return this.isCardinal;
	}
	
	public boolean getWith() {
		return this.useWith;
	}
}

/* 31 December 2024 - Created file
 * 2 January 2025 - Added getters from lookThrough and isCardinal
 * 5 January 2025 - Added boolean to flag is user is using a with command
 * 				  - Added separate method to set the object
 */
