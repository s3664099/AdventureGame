/* Title: Launcher
 * Initial: 25 August 2023
 * Updated: 27 August 2023
 * Version: 0.2
 * This is the launcher. It should not need to be updated.
 */

//TODO: Add Basic Ending - Also a Quit - then increase the version.
//TODO: Added a score for entering a room. Also make a room the finish room - finishes
//		game when player enters the room.
//TODO: Move the check for item, and check for exit for separate function
//TODO: Add a function that converts one now to another, but if the other noun is used it 
//		makes it invalid.
//TODO: Implement multi word parser
//      And and then to create multiple commands
//      Full Object Command
//      Complex Commands - use x in x, unlock x with x
//TODO: Use command (Can also be applied to lock/unlock - also shovels and stuff).
//TODO: Commands embedded in objects/exits
//TODO: Option to select Games to Play
//TODO: Begin Building Other Game
//TODO; Consider add scores when certain actions are performed
//      Possible to have the treasure store moved (or even have a character to give them to)
//TODO: v2 - A graphical interface.

import Model.Main;

public class Start {

	public static void main(String[] args) {
		
		Main main = new Main();
		main.run();

	}

}

/*
 * 25 August 2023 - Created File 
 * 27 August 2023 - Added comments
*/