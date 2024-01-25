/* Abstract Item Class
 * Created: 25 August 2023
 * Updated: 25 January 2024
 * Version: 0.4
 * The main class for objects. Since we can't call it an object (reserved word)
 * we have to call it a thing.
 */

package Data;

import java.util.ArrayList;

public class AbstractItem {
	
	private String name;
	private String description;
	private String[] nouns;
	private boolean treasure;
	
	public AbstractItem(String name, String description) {
		
		this.name = name;
		this.description = description;
		name = name.toLowerCase();
		this.nouns = name.split(" ");
		this.treasure = false;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getNouns() {
		return this.nouns;
	}
	
	public void updateItem(String name, String description) {
		this.name = name;
		this.description = description;
		this.nouns = name.split(" ");
	}
	
	public void setTreasure() {
		this.treasure = !this.treasure;
	}
	
	public boolean getTreasure() {
		return this.treasure;
	}
}

/* 25 August 2023 - Created File
* 27 August 2023 - Added Comments
* 14 September 2023 - Began to add methods to build items
* 25 January 2024 - Added methods to handle treasures
*/