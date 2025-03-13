/* Title: Launcher
 * Initial: 25 August 2023
 * Updated: 13 March 2025
 * Version: 1.2
 * This is the launcher. It should not need to be updated.
 */

//TODO: Implement multi word parser
//		Handle the with section here - and if there is a with, will do both of them.
//			Add a with to carriable items that unless with specific item cannot pick it up
//			Ditto with open and moveable items
//			Can unlock a carriable Item (the book)
//      Complex Commands - use x in x
//TODO: Use command (Can also be applied to lock/unlock - also shovels and stuff).
//TODO: Commands embedded in objects/exits
//			Consider a special command for under
//TODO: Option to select Games to Play
//TODO: Begin Building Other Game
//TODO; Consider add scores when certain actions are performed
//      Possible to have the treasure store moved (or even have a character to give them to)
//TODO: v2 - A graphical interface.
//TODO: For exits, add a response variable so that a unique response can be discplayed.
//TODO: Consider dropping moveable item and simply using cover. In this sense we can add
//		A verb that need to be used to move the item.

import java.util.logging.Logger;

import Control.Input;
import Control.Parser;
import Data.Data_Process;
import Model.Command;
import Model.Main;
import View.Display_Text;

public class Start {

	private static final Logger logger = Logger.getLogger(Start.class.getName());
	
	public static void main(String[] args) {
		
		logger.info("Starting the adventure game...");
		
        try {
        	// Initialize dependencies
            Data_Process gameData = new Data_Process();
            Display_Text display = new Display_Text();
            Input input = new Input();
            Parser parser = new Parser();
            Command processor = new Command(gameData.getScore());
        	
            // Create and run the game
            Main main = new Main(gameData, display, input, parser, processor);
            main.run();
        } catch (Exception e) {
        	logger.severe("An error occurred while running the game: " + e.getMessage());
        	e.printStackTrace();
        }
        
	}
}

/*
 * 25 August 2023 - Created File 
 * 27 August 2023 - Added comments
 * 13 March 2025 - Added error handling and logging. Moved dependencies here
*/