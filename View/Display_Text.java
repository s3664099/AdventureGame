/* Display Class
 * Created: 25 August 2023
 * Updated: 8 September 2023
 * Version: 0.3
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
	
	//Displays response to the command
	public void displayResponse(String response) {
		System.out.println(response);
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
* 8 September 2023 - Added method to display responses
*/