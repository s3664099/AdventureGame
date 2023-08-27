/* Display Class
 * Created: 25 August 2023
 * Updated: 27 August 2023
 * View class to display details on the console.
 */

package View;
import java.io.IOException;

import Data.Location;

public class Display_Text {
	
	public void display(Location location) {
		
		System.out.println();
		//clear();
		System.out.printf("You are %s%n",location.getName());
		System.out.printf("Exits: %s%n",location.getExitList());
		System.out.println("=======================");
		
	}
	
	private void clear() {
		for(int i = 0; i < 1000; i++)
		{
		    System.out.println("");
		}
	}
}

/* 25 August 2023 - Created File
* 27 August 2023 - Added Comments
*/