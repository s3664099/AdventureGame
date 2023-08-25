package Control;
import java.util.Scanner;

public class Input {

	Scanner input;
	
	public Input() {
		input = new Scanner(System.in);
	}
	
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
