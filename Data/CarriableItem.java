/* Item Class
 * Created: 25 August 2023
 * Updated: 27 August 2023
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

}

/* 25 August 2023 - Created file.
 * 27 August 2023 - Added comments
 */