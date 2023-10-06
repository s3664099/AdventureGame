/* Container Class
 * Created: 5 October 2023
 * Updated: 5 October 2023
 * Version: 0.0
 * Class for items that can't be picked up and carried.
 */

package Data;

import java.util.ArrayList;

public class Container extends ImmoveableItem implements Item {
	
	private ArrayList<CarriableItem> contents;
	
	public Container(String name, String description) {
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
	
	public String getItemList() {
		String response = "";
		response = response.format("The %s contains ",super.getDescription());
		int length = 0;
		
		for (Item content:contents) {
			
			if (length == 0) {
				response = response.format("%s %s",response,content.getDescription());
			} else {
				response = response.format("%s, %s",response,content.getDescription());
			}
			length ++;
		}
		
		if (length == 0) {
			response = response.format("%s nothing",response);
		}
		
		return response;
	}

}

/* 5 October 2023 Created File
*/
