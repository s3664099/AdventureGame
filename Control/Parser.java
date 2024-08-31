/* Title: Parser Class
 * Created: 25 August 2023
 * Updated: 31 August 2024
 * Version: 1.5
 * 
 * This processes the command. At this stage it is only a two work
 * command - noun and verb. Ideally, it will eventually process larger commands.
 */

package Control;

public class Parser {
	
	String[] cardinals = {"north","south","east","west","up","down","northeast",
							"northwest","southeast","southwest","north-east",
							"north-west","south-east","south-west"};
	String parsedCommands[] = {"","",""}; 
	
	//Turns the command into verb noun formation
	public String[] parseCommand(String command) {
				
		String[] commands = command.split(" ");
		command = command.substring(commands[0].length(),command.length()).trim();
				
		//If the word move being used instead of go?
		if (commands[0].equals("move") && checkCardinals(commands)) {
			commands[0] = "go";
		} else if (commands[0].equals("move") && commands[1].equals("through")) {
			commands[0] = "go";
		}
				
		//Inventory
		if ((commands[0].equals("take") || commands[0].equals("get")) && (
			command.equals("inventory") || command.equals("my inventory"))) {
			
			commands = new String[]{"inventory"};
			
		} else if ((commands[0].equals("i") || commands[0].equals("inv")) && 
					(commands.length == 1)) {
			commands[0] = "inventory";
		} else if (commands[0].equals("what") || (command.equals("am i carrying") ||
				command.equals("am i carrying?"))) {
			commands = new String[]{"inventory"};
		}
		
		//Checks number of words in command
		if(commands.length>0) {
			//Checks the cardinal directions. Accepts and removes 'to the'
			if (commands[0].equals("go") && checkCardinals(commands)) {			
				if (commands.length>2 && commands[1].equals("to") 
									  && commands[2].equals("the")) {
					command = command.substring(7,command.length());
				}
			
			//Go command and not a cardinal direction
			} else if (commands.length>1 && commands[0].equals("go") 
										 && commands[1].equals("through")) {
				command = command.substring(8,command.length());
			}
			
			if (commands[0].equals("go")) {
				if (command.equals("north-east")) {
					command = "northeast";
				} else if (command.equals("north-west")) {
					command = "northwest";
				} else if (command.equals("south-east")) {
					command = "southeast";
				} else if (command.equals("south-west")) {
					command = "southwest";
				}
			}
		}
		 
		//Handles look command
		if (commands.length>0 && commands[0].equals("look")) {
			if (commands.length>1 && commands[1].equals("around")) {
				if (commands[2].equals("room") || commands[2].equals("location")) {
					command = command.substring(7,command.length());
				}
			} else if (command.length() >3 && commands[1].equals("at")) {
				command = command.substring(3,command.length());
			}
			
		//Examine command
		} else if (commands[0].equals("examine") && commands.length>0) {
			commands[0] = "look";
		}
		
		//Pick up/put down.
		if (commands[0].equals("pick") && commands.length>1) {
			if (commands[1].equals("up")) {
				commands[0] = "take";
				command = command.substring(3,command.length());
			}
		} else if (commands[0].equals("put") && commands.length>1) {
			if (commands[1].equals("down")) {
				commands[0] = "drop";
				command = command.substring(5,command.length());
			}
		}

		//Strips 'the' from the object
		if (command.length()>4 && command.substring(0,4).equals("the ")) {
			command = command.substring(4,command.length());
			System.out.println(command);
		}
		
		//Processes talk/speak command
		if ((commands[0].equals("talk") || commands[0].equals("speak")) && commands.length>1) {
			if ((commands[1].equals("to")) && commands.length>2) {
				command = command.substring(3,command.length());
			} else if ((commands[1].equals("with")) && commands.length>2) {
				command = command.substring(5,command.length());
			}
		}
		
		parsedCommands[0] = commands[0];
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
 * 26 August 2024 - Moved parsing for the inventory command here, and added to it. Fixed
 * 					error with look.
 * 29 August 2024 - Fixed problem with move command for moving objects
 * 31 August 2024 - Added ne,nw,se,sw
*/