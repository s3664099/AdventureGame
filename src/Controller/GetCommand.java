package Controller;

import java.util.Scanner;

public class GetCommand {
	
	Scanner input = new Scanner(System.in);
	
	public String command(String command) {
		
		command = input.nextLine();
		command = command.toLowerCase();
		
		return command;
		
	}

}
