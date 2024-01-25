/* Main Function
 * Created: 25 August 2023
 * Updated: 25 January 2024
 * Version: 0.4
 * This is the main routine for the game
 */

package Model;
import java.util.ArrayList;

import Control.Input;
import Control.Parser;
import Data.Data_Process;
import Data.Item;
import Data.Location;
import View.Display_Text;

public class Main {
	
	String query = "Tell me what to do: ";
	String command;
	ArrayList<Item> inventory = new ArrayList<Item>();
	int score = 0;

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
			display.displayResponse(processor.processCommand(commands,data,inventory));
			this.score += processor.getScore();
			data = processor.getCurrentLocation();
		}
	}
}

/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 8 September 2023 - Reconfigured code to pass responses to view and retrieve location
 *                    from processor
 * 25 January 2024 - Added score for treasures
 */