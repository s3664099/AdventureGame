/* Main Function
 * Created: 25 August 2023
 * Updated: 8 September 2023
 * Version: 0.3
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
		String response = "";
		
		while (true) {
			
			if(processor.displayLocation()) {
				display.display(data);
			}
			command = input.getCommand(query);
			String[] commands = parser.parseCommand(command);
			display.displayResponse(processor.processCommand(commands,data));
			data = processor.getCurrentLocation();
		}
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 8 September 2023 - Reconfigured code to pass responses to view and retrieve location
 *                    from processor
 */