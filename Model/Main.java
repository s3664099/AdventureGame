/* Main Function
 * Created: 25 August 2023
 * Updated: 27 August 2023
 * This is the main routine for the game
 */

package Model;
import Control.Input;
import Control.Parser;
import Data.Data_Process;
import Data.Location;
import View.Display_Text;

//TODO
//Add locked and closed doors to test the locked/closed functions
//Move Data for Tower of London for a new game - ChatGPT

public class Main {
	
	String query = "Tell me what to do: ";
	String command;

	public void run() {
		
		Data_Process game_data = new Data_Process();
		Display_Text display = new Display_Text();
		Location data = game_data.start();
		Input input = new Input();
		Parser parser = new Parser();
		Command processor = new Command();
		
		while (true) {
			display.display(data);
			command = input.getCommand(query);
			String[] commands = parser.parseCommand(command);
			data = processor.processCommand(commands,data);
		}
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 */