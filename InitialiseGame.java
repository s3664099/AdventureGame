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

public class InitialiseGame {

	public Main initialiseMain(Data_Process gameData) {
		return new Main(gameData,new Display_Text(),new Input(),new Parser(),
				new Command(Objects.requireNonNull(gameData.getScore(),"Score missing")));
	}
	
}

/* 2 Aug 2025 - Create File
 */
