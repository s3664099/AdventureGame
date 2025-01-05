/* Title: Launcher
 * Initial: 25 August 2023
 * Updated: 27 August 2023
 * Version: 1.0
 * This is the launcher. It should not need to be updated.
 */


//TODO: Implement multi word parser
//			Consider a special command for under
//			Handle the with section here - and if there is a with, will do both of them.
//				Each of the commands, if with it errors unless unlock
//				Add a with to carriable items that unless with specific item cannot pick it up
//				Ditto with open and moveable items
//      Complex Commands - use x in x
//TODO: Use command (Can also be applied to lock/unlock - also shovels and stuff).
//TODO: Commands embedded in objects/exits
//TODO: Option to select Games to Play
//TODO: Begin Building Other Game
//TODO; Consider add scores when certain actions are performed
//      Possible to have the treasure store moved (or even have a character to give them to)
//TODO: v2 - A graphical interface.
//TODO: For exits, add a response variable so that a unique response can be discplayed.
//TODO: Consider dropping moveable item and simply using cover. In this sense we can add
//		A verb that need to be used to move the item.

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