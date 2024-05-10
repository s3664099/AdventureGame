package Data;

public class Data_Process {
	
	Location start;
	String description;
	Container container;
	
	public Data_Process() {
		
		start = new Location("In an office","An office of a consulting detective. The room is dark and dingy, and little light permeates the shadows");
		Location hall = new Location("In a hall","A hallway in an 18th Century building in the heart of London. There is a door leading to your office and stairs leading down to the street");
		Conversation convo = new Conversation("I need some help, will you help me","Okay, bye");
		Conversation response = new Conversation("Good, I'm tired, can I sleep here");
		
		Query query = new Query("Yes",response);
		convo.addQuery(query);
		query = new Query("No");
		query.setEndConvo("leave");
		convo.addQuery(query);
		
		Item woman = new Being("Worried Woman","",convo,true);
		Exit door = new CloseableExit("Door","Door",hall,true,"The door is a sturdy wooden door leading to the hall",woman);
		start.addItem(woman);
		
		
	}
	
	public Location start() {
		return start;
	}
}