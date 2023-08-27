/* Name: Input Class
 * Created: 25 August 2023
 * Updated: 27 August 2023
 * Version: 0.2
 * This takes the user input and returns the command.
 * At this stage the parser consists of only a verb and a noun.
 */

package Control;
import java.util.Scanner;

public class Input {

	Scanner input;
	
	//Initialises the scanner
	public Input() {
		input = new Scanner(System.in);
	}
	
	//Gets the user command. Takes a string that the is question to ask the user
	public String getCommand(String query) {
		
		String command = "";
		boolean validCommand = false;
		
		while (!validCommand) {
			System.out.print(query);
			command = input.nextLine();
			validCommand = validateString(command);
		}
		
		return command;
	}
	
	//Confirms that the command is only two works
	private boolean validateString(String command) {

		boolean validCommand = false;
		
		String[] lenCommand = command.split(" ");
		
		if ((lenCommand.length<3) && (command.length()>0)) {
			validCommand = true;
		} else {
			System.out.println("Command has to be less than three words and not empty");
		}
		
		return validCommand;
	}
}

/*
 * 25 August 2023 - Created file
 * 27 August 2023 - Added Comments
*/