/* Carriable Item Class
 * Created: 25 August 2023
 * Updated: 25 January 2024
 * Version: 1.0
 * The class for things that can be picked up and carried
 */

package Data;

import java.io.Serializable;

public class CarriableItem extends AbstractItem implements Item, Serializable {

	public CarriableItem(String name, String description) {
		super(name, description);
	}
	
	public String getDescription() {
		return super.getDescription();
	}
	
	public String[] getNouns() {
		return super.getNouns();
	}
	
	public String getName() {
		return super.getName();
	}
	
	public void updateItem(String name, String description) {
		super.updateItem(name, description);
	}

	//Methods for containers
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

	@Override
	public void addItem(CarriableItem item) {
		// TODO Auto-generated method stub
		
	}
}

/* 25 August 2023 - Created file.
 * 27 August 2023 - Added comments
 * 23 Janaury 2024 - Added methods for moveable item
 * 25 January 2024 - Made class serializable
 */