/* Title: Launcher
 * Initial: 25 August 2023
 * Updated: 2 August 2025
 * Version: 1.3
 * This is the launcher. It should not need to be updated.
 */

//TODO: Add a game state
//TODO: Add Class to hold constants
//TODO: Change the add item to not throw an exception - update the functionality to take something from the container & bag
//				- It should take the string, and go through items, check each of them.
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
//TODO: Need matching exits so if one is unlocked/opened, the other is automatically as well (and vice versa).

import java.util.Objects;
import java.util.logging.Logger;

import Data.Data_Process;
import Model.Main;

public class Start {

	private static final Logger logger = Logger.getLogger(Start.class.getName());
	
	public static void main(String[] args) {

		logger.setLevel(java.util.logging.Level.FINE);
		logger.info("Starting the adventure game...");
		
        try (Data_Process gameData = new Data_Process()){
        	
        	logger.fine("Loading Game Data...");
            Objects.requireNonNull(gameData,"gameData initialisation failed");
            logger.fine("Game Data loaded successfully");
        	
            logger.fine("Creating Main Game instance ...");
            Main main = new InitialiseGame().initialiseMain(gameData);
            Objects.requireNonNull(main,"Main Game creation failed");
            logger.fine("Game instance ready");
            
            logger.fine("Starting game loop");
            main.run();
            logger.fine("Game loop executed successfully");
            
        } catch (NullPointerException e) {
            logger.severe("CRITICAL: Dependency failed to initialize - " + e.getMessage());
        } catch (IllegalStateException e) {
            logger.severe("Game data error: " + e.getMessage());
        } catch (Exception e) {
            logger.severe("UNEXPECTED ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            logger.fine("Startup sequence terminated");
        }   
	}
}

/*
 * 25 August 2023 - Created File 
 * 27 August 2023 - Added comments
 * 13 March 2025 - Added error handling and logging. Moved dependencies here
 * 2 August 2025 - Moved object creation to main, and added loggers and not null
 * 				   checks.
*/