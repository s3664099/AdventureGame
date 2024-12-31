/* Command Function
 * Created: 31 August 2023
 * Updated: 31 December 2024
 * Version: 1.0
 * Class that handles fuctions that deal with commands that are entered.
 */

package Model;

public class UserCommand {
	
	private String verb;
	private String subject;
	private String object;
	private boolean lookAround = false;
	private boolean lookInside = false;
	private boolean isCardinal = false;
	private boolean lookThrough = false;
	
	public void setCommand(String verb,String subject, String object) {
		this.verb = verb;
		this.subject = subject;
		this.object = object;
	}

	public void setCommand(String verb,String subject) {
		this.verb = verb;
		this.subject = subject;
	}
	
	public void setInside() {
		this.lookInside = !this.lookInside;
	}
	
	public void setAround() {
		this.lookAround = !this.lookAround;
	}
	
	public void setThrough() {
		this.lookThrough = !this.lookThrough;
	}
	
	public void setCardinal() {
		this.isCardinal = !this.isCardinal;
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
}

/* 31 December 2024 - Created file
 * 
 */
