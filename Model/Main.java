/* Main Function
 * Created: 25 August 2023
 * Updated: 2 August 2025
 * Version: 1.4
 * This is the main routine for the game
 */

package Model;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Objects;

import Control.Input;
import Control.Parser;
import Data.Data_Process;
import Data.Item;
import Data.Location;
import View.Display_Text;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
	private static final String PROMPT = "Tell me what to do: ";
	private static final String WIN_MESSAGE = "Congratulations, you have collected all the treasures!";
	
	private ArrayList<String> playerCommand;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int score = 0;
	
    private final Data_Process gameData;
    private final Display_Text display;
    private final Input input;
    private final Parser parser;
    private final Command processor;
	
	public Main(Data_Process gameData, Display_Text display, Input input, 
				Parser parser,Command processor) {
		this.gameData = Objects.requireNonNull(gameData,"gameData cannot be null");
		this.display = display;
		this.input = input;
		this.parser = parser;
		this.processor = processor;
		logger.fine("Main game successfully initialised");
	}
	
	public void run() throws Exception {

		logger.info("Game started");
		Location data = gameData.start();
		boolean gameRunning = true;
		
		if(processor.displayLocation()) {
			display.display(data);
		}		
		
		while (gameRunning) {
						
			playerCommand = input.getCommand(PROMPT);
			logger.fine("Player command "+playerCommand);
			
			//Cycles through the list of commands and executes them.
			for (String commandString:playerCommand) {
			
				UserCommand userCommand = parser.parseCommand(commandString);
				gameRunning = display.displayResponse(processor.processCommand(userCommand,data,inventory,score));
				
				//Checks if the values have been cleared, if not, loads them.
				if (processor.getCurrentLocation() != null) {
					data = processor.getCurrentLocation();
				}
				
				if (userCommand.getVerb().equals("go")) {
					display.display(data);
					logger.fine("Moved to location: " + data.getName(false));
				}
				
				//Checks if the player has reached the top score
				if (checkEndConditions(data, processor)) {
					gameRunning = false;
					logger.info("Game end condition met");
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
 * 2 August 2025 - Started updated the main class based on recommendations.
 * 				   Added logging and error handling
 */