package Model;
import Control.Input;
import Data.Data_Process;
import Data.Location;
import View.Display_Text;

//TODO
//Add Movement Functions
//Add Comments
//Move Data for Tower of London for a new game - ChatGPT


public class Main {
	
	String parser = "Tell me what to do: ";
	String command;

	public void run() {
		
		Data_Process game_data = new Data_Process();
		Display_Text display = new Display_Text();
		Location data = game_data.start();
		Input input = new Input();
		
		while (true) {
			display.display(data);
			command = input.getCommand(parser);
			System.out.println(command);
		
		}
	}
	
}
