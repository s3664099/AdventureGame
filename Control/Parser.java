/* Title: Parser Class
 * Created: 25 August 2023
 * Updated: 27 August 2023
 * Version: 1.0
 * 
 * This processes the command. At this stage it is only a two work
 * command - noun and verb. Ideally, it will eventually process larger commands.
 */

package Control;

public class Parser {
	
	//Takes a string. Splits it by space, and makes the command lower case.
	//Returns an array containing the commands.
	public String[] parseCommand(String command) {
		
		command = command.toLowerCase();
		
		String[] commands = command.split(" ");
		
		return commands;
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added comments.
*/