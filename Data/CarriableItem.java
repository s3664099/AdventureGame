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

}

/* 25 August 2023 - Created file.
 * 27 August 2023 - Added comments
 */