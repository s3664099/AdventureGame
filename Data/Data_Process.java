package Data;

public class Data_Process {
	
	Location start;
	String description;
	Container container;
	
	public Data_Process() {
		
		start = new Location("In an office","An office of a consulting detective. The room is dark and dingy, and little light permeates the shadows");
		Location hall = new Location("In a hall","A hallway in an 18th Century building in the heart of London. There is a door leading to your office and stairs leading down to the street");
		Item woman = new Being("Worried Woman","",new Conversation(""),false);
		Exit door = new CloseableExit("Door","Door",hall,true,"The door is a sturdy wooden door leading to the hall",woman);
		
		
		
	}
	
	public Location start() {
		return start;
	}
}