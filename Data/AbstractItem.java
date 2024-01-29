/* Abstract Item Class
 * Created: 25 August 2023
 * Updated: 28 January 2024
 * Version: 0.6
 * The main class for objects. Since we can't call it an object (reserved word)
 * we have to call it a thing.
 */

package Data;

import java.util.ArrayList;
import java.io.Serializable;

public class AbstractItem implements Serializable {
	
	private String name;
	private String description;
	private String[] nouns;
	private boolean treasure;
	
	public AbstractItem() {}
	
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
	
	//Flags that this item isn't a cover
	public boolean checkIsCover() {
		return false;
	}
	
	public boolean checkHidden(boolean whatItems) {
		return false;
	}
}

/* 25 August 2023 - Created File
* 27 August 2023 - Added Comments
* 14 September 2023 - Began to add methods to build items
* 25 January 2024 - Added methods to handle treasures
* 27 January 2024 - Made class serializable
* 28 January 2024 - Added function for cover
*/