/* Title: Launcherl
 * Initial: 25 August 2023
 * Updated: 27 August 2023
 * Version: 0.2
 * This is the launcher. It should not need to be updated.
 */

//TODO: Create a game that uses everything we have (openable containers/Lockable containers/moveable items/Look items exits)
//TODO: Create a talk to module sort of like the one in NWN
//TODO: Add Basic Ending - then increase the version.
//TODO: Implement multi word parser
//      And and then to create multiple commands
//      Full Object Command
//      Complex Commands - use x in x, unlock x with x
//TODO: Use command (Can also be applied to lock/unlock - also shovels and stuff).
//TODO: Commands embedded in objects/exits
//TODO: Option to select Games to Play
//TODO: Begin Building Other Game
//TODO; Maybe have the moveable item hide multiple things, and can be moved multiple times
//      Consider add scores when certain actions are performed
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