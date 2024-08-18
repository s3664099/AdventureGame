/* Display Class
 * Created: 25 August 2023
 * Updated: 17 August 2024
 * Version: 1.0
 * View class to display details on the console.
 */

package View;
import java.io.IOException;

import Data.Location;

public class Display_Text {
	
	public void display(Location location) {
		
		System.out.println();
		System.out.printf("You are %s%n",location.getName(false));
		System.out.println("=======================");
		
	}
	
	//Displays response to the command
	public boolean displayResponse(String response) {
		
		boolean continueGame = true;
		
		if (response.equals("END")) {
			continueGame = false;
			System.out.println("Game terminated");
		} else {
			System.out.println(response);
		}
		
		return continueGame;
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
* 15 September 2023 - Changed description method to display items. Added check to not
* 					  display exits or items if there are none.
* 17 August 2024 - Added ability to end game
*/