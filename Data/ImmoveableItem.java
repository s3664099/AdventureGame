/* Immoveable Item Class
 * Created: 25 August 2023
 * Updated: 26 January 2024
 * Version: 0.5
 * Class for items that can't be picked up and carried.
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public class ImmoveableItem extends AbstractItem implements Item,Serializable {
	
	public ImmoveableItem(String name, String description) {
		super(name,description);
	}

	@Override
	public String[] getNouns() {
		
		return super.getNouns();
	}

	@Override
	public String getName() {
		return super.getName();
	}

	//Methods for items that are closeable and lockable
	@Override
	public boolean getCloseable() {

		return false;
	}

	@Override
	public boolean getClosed() {
		
		return false;
	}

	@Override
	public boolean getLockable() {
		
		return false;
	}

	@Override
	public boolean getLocked() {
		
		return false;
	}

	@Override
	public void setClosed() {		
	}

	@Override
	public void setLocked() {		
	}

	@Override
	public boolean checkKey(Item key) {
		return false;
	}

	@Override
	public boolean getMoveable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Exit getHiddenExit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getHiddenItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getMoved() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMoved() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkHiddenExits() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkHiddenItems() {
		// TODO Auto-generated method stub
		return false;
	}

}

/* 25 August 2023 - Created file
 * 27 August 2023 - Added Comments
 * 14 September 2023 - Began adding item methods.
 * 23 January 2024 - Added methods to handle the moveable items
 * 25 January 2024 - Made class serialisable.
*/
