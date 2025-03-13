/* Main Function
 * Created: 25 August 2023
 * Updated: 13 March 2025
 * Version: 1.3
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
	
	private static final String PROMPT = "Tell me what to do: ";
	private static final String WIN_MESSAGE = "Congratulations, you have collected all the treasures!";
	
	private ArrayList<String> command;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int score = 0;
	
    private final Data_Process gameData;
    private final Display_Text display;
    private final Input input;
    private final Parser parser;
    private final Command processor;
	
	public Main(Data_Process gameData, Display_Text display, Input input, Parser parser, Command processor) {
		this.gameData = gameData;
		this.display = display;
		this.input = input;
	    this.parser = parser;
	    this.processor = processor;
	}
	

	public void run() throws Exception {

		Location data = gameData.start();
		boolean gameRunning = true;
		
		if(processor.displayLocation()) {
			display.display(data);
		}		
		
		while (gameRunning) {
						
			command = input.getCommand(PROMPT);
			
			//Cycles through the list of commands and executes them.
			for (String action:command) {
			
				UserCommand command = parser.parseCommand(action);
				gameRunning = display.displayResponse(processor.processCommand(command,data,inventory,score));
				
				//Checks if the values have been cleared, if not, loads them.
				if (processor.getCurrentLocation() != null) {
					data = processor.getCurrentLocation();
				}
				
				if (command.getVerb().equals("go")) {
					display.display(data);
				}
				
				//Checks if the player has reached the top score
				if (checkEndConditions(data, processor)) {
	                    gameRunning = false;
	            }
			}
		}
	}
	
    // Helper method to check end conditions
    private boolean checkEndConditions(Location currentLocation, Command processor) {
    
    	if (processor.compareScore()) {
            System.out.println(WIN_MESSAGE);
            return true;
        }
        if (currentLocation.checkEnd()) {
            System.out.println(currentLocation.getEndComment());
            return true;
        }
        return false;
    }
}
	
/* 25 August 2023 - Created File
 * 27 August 2023 - Added Comments
 * 8 September 2023 - Reconfigured code to pass responses to view and retrieve location
 *                    from processor
 * 25 January 2024 - Added score for treasures
 * 26 January 2024 - sent score to command for saving
 * 17 August 2024 - Added score functionality and ability to end game
 * 18 August 2024 - Added check for an end game
 * 19 August 2024 - Added check to see if player entered room with end condition
 * 20 August 2024 - Added ability to enter multiple commands
 * 13 March 2025 - Updated code based on recommendations. Moved dependencies to start, and created end condition
 */