package Control;

public class Parser {
	
	public String[] parseCommand(String command) {
		
		command = command.toLowerCase();
		
		String[] commands = command.split(" ");
		
		return commands;
		
	}

}
