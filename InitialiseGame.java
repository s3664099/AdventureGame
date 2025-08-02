/* Initialise Game Function
 * Created: 2 August 2025
 * Updated: 2 August 2025
 * Version: 1.0
 * This initialises the game
 */

import java.util.Objects;

import Control.Input;
import Control.Parser;
import Data.Data_Process;
import Model.Command;
import Model.Main;
import View.Display_Text;
import java.util.logging.Logger;

public class InitialiseGame {

	private static final Logger logger = Logger.getLogger(InitialiseGame.class.getName());
	
    /**
     * Initializes and returns a fully configured Main game instance.
     * @param gameData Pre-loaded game data (must not be null).
     * @return Configured Main game instance.
     * @throws NullPointerException if gameData or its score is null.
     */
	public Main initialiseMain(Data_Process gameData) {
		
        Objects.requireNonNull(gameData, "GameData cannot be null");
        logger.fine("Initializing game dependencies...");
		
		Display_Text display = new Display_Text();
		Input input = new Input();
		Parser parser = new Parser();
		
        Command command = new Command(
                Objects.requireNonNull(gameData.getScore(), "Game score cannot be null")
            );
		
        logger.fine("Game dependencies initialized successfully");
        return new Main(gameData, display, input, parser, command);
	}
	
}

/* 2 Aug 2025 - Create File
 */
