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
