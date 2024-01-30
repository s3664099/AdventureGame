/* Title: Launcher
 * Initial: 25 August 2023
 * Updated: 27 August 2023
 * Version: 0.2
 * This is the launcher. It should not need to be updated.
 */

//TODO: Create a game that uses everything we have (openable containers/Lockable containers/moveable items/Look items exits)
//TODO: Add Basic Ending - then increase the version.
//TODO: Implement multi word parser
//TODO: Use command (Can also be applied to lock/unlock - also shovels and stuff).
//TODO: Commands embedded in objects/exits
//TODO: Begin Building Other Game
//TODO; Maybe have the moveable item hide multiple things, and can be moved multiple times
//      Consider add scores when certain actions are performed
//      Possible to have the treasure store moved (or even have a character to give them to)

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