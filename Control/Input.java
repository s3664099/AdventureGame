package Control;
import java.util.Scanner;

public class Input {

	Scanner input;
	
	public Input() {
		input = new Scanner(System.in);
	}
	
	public String getCommand(String query) {
		
		String command;
		
		System.out.print(query);
		command = input.nextLine();
		
		return command;
	}

}
