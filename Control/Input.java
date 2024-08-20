/* Name: Input Class
 * Created: 25 August 2023
 * Updated: 20 August 2024
 * Version: 1.1
 * This takes the user input and returns the command.
 * At this stage the parser consists of only a verb and a noun.
 */

package Control;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {

	Scanner input;
	
	//Initialises the scanner
	public Input() {
		input = new Scanner(System.in);
	}
	
	//Gets the user command. Takes a string that the is question to ask the user
	public ArrayList<String> getCommand(String query) {
		
		String command = "";
		ArrayList<String> actions = new ArrayList<String>();
		
		//Checks if there are any valid commands in the list
		while (actions.size() == 0) {
			
			System.out.print(query);
			command = input.nextLine();
			
			String[] commands = splitCommands(command);
			
			for (String action:commands) {
				
				boolean validCommand = false;
				action = action.trim();
				action = cardinalDirections(action);
				validCommand = validateString(action);
					
				if (validCommand) {
					actions.add(action);
				}
			}
			
			if (actions.size()==0) {
				System.out.println("Please enter a valid action");
			}
		}
		
		return actions;
	}
	
	//Splits multiple commands based on a comma separation.
	private String[] splitCommands(String command) {
		
		String[] commands = command.split(",");
		return commands;
		
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
	
	public boolean getYesNo() {
		
		//Consider moving to the control function
		boolean correctResponse = false;
		boolean writeFile = false;
		
		while (!correctResponse) {
			String query = input.nextLine();
			
			if (query.toLowerCase().equals("y")) {
				correctResponse = true;
				writeFile = true;
			} else if (query.toLowerCase().equals("n")) {
				correctResponse = true;
			}
			
			if (!correctResponse) {
				System.out.println("Please enter Y or N");
			}
		}
				
		return writeFile;
	}
}

/*
 * 25 August 2023 - Created file
 * 27 August 2023 - Added Comments
 * 7 September 2023 - Added method to allow single word/letter movement for directions
 * 26 January 2024 - Added method to get a yes or no response
 * 27 January 2024 - Fixed the Y/N reponse so that Y sends true, not N
 * 20 August 2024 - Added ability to enter multiple commands.
*/