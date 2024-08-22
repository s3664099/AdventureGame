/* Title: Parser Class
 * Created: 25 August 2023
 * Updated: 22 August 2024
 * Version: 1.2
 * 
 * This processes the command. At this stage it is only a two work
 * command - noun and verb. Ideally, it will eventually process larger commands.
 */

package Control;

public class Parser {
	
	//Go through
	//Pick up/put down
	//strip the
	//String the verb and other words from the main parser so that all that is left
	//		is the object
	//Add full object name
	
	String[] cardinals = {"north","south","east","west","up","down"};
	String parsedCommands[] = {"","",""}; 
	
	//Turns the command into verb noun formation
	public String[] parseCommand(String command) {
		
		System.out.println(command);
		String[] commands = command.split(" ");
		command = command.substring(commands[0].length(), command.length()).trim();
		System.out.println(command);
		
		//First and second words automatically added
		parsedCommands[0] = commands[0];
		
		//If the word move being used instead of go?
		if (commands[0].equals("move") && checkCardinals(commands)) {
			commands[0] = "go";
			parsedCommands[0] = "go";
		}
		
		//Checks number of words in command
		if(commands.length>0) {

			//Checks the cardinal directions. Accepts and removes 'to the'
			if (commands[0].equals("go") && checkCardinals(commands)) {			
				if (commands.length>2 && commands[1].equals("to") 
									  && commands[2].equals("the")) {
					command = command.substring(7,command.length());
					System.out.println(command);
				}
			
			//Go command and not a cardinal direction
			} else if (commands.length>1 && commands[0].equals("go") 
										 && commands[1].equals("through")) {
				command = command.substring(8,command.length());
				System.out.println(command);
			}
		}
		
		System.out.println(command);
		
		//Handles look command
		if (commands[0].equals("look") && commands.length>0) {
			if (commands[1].equals("around") && commands.length>1) {
				if (commands[2].equals("room") || commands[2].equals("location")) {
					command = command.substring(7,command.length());
				}
			} else if (command.length() >3 && commands[1].equals("at")) {
				command = command.substring(3,command.length());
			}
			System.out.println(command);
		}

		//Strips 'the' from the object
		if (command.length()>4 && command.substring(0,4).equals("the ")) {
			command = command.substring(4,command.length());
			System.out.println(command);
		}
		
		parsedCommands[1] = command;
				
		return parsedCommands;
	}
	
	//Checks if the last word is a cardinal direction
	private boolean checkCardinals(String[] commands) {

		boolean validCardinal = false;
				
		for (String cardinal:cardinals) {	
			if (commands[commands.length-1].equals(cardinal)) {
				validCardinal = true;
			}
		}
		return validCardinal;
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added comments.
 * 21 August 2024 - Started building English Language parser with go command
 * 22 August 2024 - Added more functionality to the parser. Strips 'the' and more comples
 * 					move command.
*/