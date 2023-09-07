/* Name: Input Class
 * Created: 25 August 2023
 * Updated: 7 September 2023
 * Version: 0.3
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
			command = cardinalDirections(command);
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
	
	//Reconfigures movement command for cardinal directions
	private String cardinalDirections(String command) {
		
		command = command.toLowerCase();
		
		if ((command.equals("n")) || (command.equals("north"))) {
			command = "go north";
		} else if ((command.equals("s")) || (command.equals("south"))) {
			command = "go south";
		} else if ((command.equals("e")) || (command.equals("east"))) {
			command = "go east";
		} else if ((command.equals("w")) || (command.equals("west"))) {
			command = "go west";
		} else if ((command.equals("u")) || (command.equals("up"))) {
			command = "go up";
		} else if ((command.equals("d")) || (command.equals("down"))) {
			command = "go down";
		}
		
		return command;
	}
}

/*
 * 25 August 2023 - Created file
 * 27 August 2023 - Added Comments
 * 7 September 2023 - Added method to allow single word/letter movement for directions
*/