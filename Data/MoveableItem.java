/* Moveable Item Class
 * Created: 5 October 2023
 * Updated: 23 January 2024
 * Version: 0.1
 * Class for items that hide exits and items.
 */

package Data;

import java.util.ArrayList;

public class MoveableItem extends ImmoveableItem implements Item {
	
	Exit exit;
	Item item;
	boolean isMoveable = true;
	boolean hasMoved = false;
	
	public MoveableItem(String name, String description, Exit exit) {
		super(name,description);
		this.exit = exit;
	}

	public MoveableItem(String name, String description, Item item) {
		super(name,description);
		
		this.item = item;
	}

	@Override
	public String[] getNouns() {
		
		return super.getNouns();
	}

	@Override
	public String getName() {
		return super.getName();
	}
	
	public boolean getMoveable() {
		return isMoveable;
	}
	
	//Checks to see whether the item has already been moved
	public boolean getMoved() {
		return hasMoved;
	}
	
	//Sets the flag to inform item has been moved
	public void setMoved() {
		hasMoved = true;
	}
	
	public Exit getExit() {
		return exit;
	}
	
	public Item getItem() {
		return item;
	}

}

/* 5 October 2023 - created class
 * 23 January 2024 - Added functionality
*/
