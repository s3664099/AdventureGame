/* Carriable Item Class
 * Created: 25 August 2023
 * Updated: 23 January 2024
 * The class for things that can be picked up and carried
 */

package Data;

public class CarriableItem extends AbstractItem implements Item {

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

}

/* 25 August 2023 - Created file.
 * 27 August 2023 - Added comments
 * 23 Janaury 2024 - Added methods for moveable item
 */