/* Moveable Item Class
 * Created: 5 October 2023
 * Updated: 5 October 2023
 * Version: 0.0
 * Class for items that can't be picked up and carried.
 */

package Data;

import java.util.ArrayList;

public class MoveableItem extends ImmoveableItem implements Item {
	
	public MoveableItem(String name, String description) {
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

}

/* 5 October 2023
*/
