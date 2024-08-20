/* Title: Parser Class
 * Created: 25 August 2023
 * Updated: 21 August 2024
 * Version: 1.1
 * 
 * This processes the command. At this stage it is only a two work
 * command - noun and verb. Ideally, it will eventually process larger commands.
 */

package Control;

public class Parser {
	
	String[] cardinals = {"north","south","east","west","up","down"};
	String parsedCommands[] = new String[2]; 
	
	//Turns the command into verb noun formation
	public String[] parseCommand(String command) {
		
		String[] commands = command.split(" ");
		
		//First and second words automatically added
		parsedCommands[0] = commands[0];
		parsedCommands[1] = commands[1];
		
		//If the word move being used instead of go?
		if (commands[0].equals("move") && checkCardinals(commands)) {
			commands[0] = "go";
		}
		
		//Checks the cardinal directions. Accepts and removes 'to the'
		if (commands[0].equals("go") || checkCardinals(commands)) {
			
			if (commands[1].equals("to") && commands[2].equals("the")) {
				parsedCommands[0] = commands[0];
				parsedCommands[1] = commands[3];				
			}
		}
		
		return parsedCommands;
	}
	
	//Checks if the last word is a cardinal direction
	private boolean checkCardinals(String[] commands) {

		boolean validCardinal = false;
		
		for (String cardinal:cardinals) {	
			if (commands[commands.length].equals(cardinal)) {
				validCardinal = true;
			}
		}
		return validCardinal;
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added comments.
 * 21 August 2024 - Started building English Language parser with go command
*/