//TODO: Finish off parts of adventure so that it is fully functional
//			- Method in Container so that it will move items into
//			  The room that it is in.
//			- Method in container that will unlock the container if the correct
//			  key is used
//			- The Door class, including a description
//			- Item - when certain objects are used on them, then they will change
//			  to something else.
//TODO: Divide into controller to take instructions and view that displays
//TODO: Turn .txt file into a binary file
//TODO: Add gui to the program
//TODO: Create a character object and give statistics
//TODO: Add Monsters to fight the character

import java.io.IOException;

public class Start {

	public static void main(String[] args) throws IOException {

		LoadData data = new LoadData();
		//data.populateLocations();
		
		System.out.println("Data loaded, ready to begin");
		
		//SetUp start = new SetUp();
		//start.writeToFile();
		Main begin = new Main(data.getLocation(), data.getObject(), data.getItem());
	}

}
