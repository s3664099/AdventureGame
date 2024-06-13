package Data;

public class Data_Process {
	
	private Location start;
	private String description;
	private Container container;
	
	//Get three treasures and return them to the office.
	//When three treasures are there, game ends.
	
	public Data_Process() {
		
		start = new Location("In an office","An office of a consulting detective. The room is dark and dingy, and little light permeates the shadows");
			//Key in desk opens door
		Location hall = new Location("In a hall","A hallway in an 18th Century building in the heart of London. There is a door leading to your office and stairs leading down to the street");
			//Hooker - Can't enter room while Hooker present. Get rid of hooker to enter
		//Hooker Room - Dildo
		//A Street in Soho
		//Soho Park
		//A Street in Soho
		//Pub - Craft Beer
			//Drunk in pub. Won't let you take beer if present
			//Bell - Ring bell to get rid of drunk
		//Leicester Square
		//Casino - Chip
	}
	
	public Location start() {
		return start;
	}
}