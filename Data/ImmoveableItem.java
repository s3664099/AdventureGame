/* Immoveable Item Class
 * Created: 25 August 2023
 * Updated: 14 September 2023
 * Version: 0.3
 * Class for items that can't be picked up and carried.
 */

package Data;

import java.util.ArrayList;

public class ImmoveableItem extends AbstractItem implements Item {
	
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

}

/* 25 August 2023 - Created file
 * 27 August 2023 - Added Comments
 * 14 September 2023 - Began adding item methods.
*/