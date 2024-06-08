package Data;

public class Data_Process {
	
	private Location start;
	private String description;
	private Container container;
	
	public Data_Process() {
		
		start = new Location("In an office","An office of a consulting detective. The room is dark and dingy, and little light permeates the shadows");
		Location hall = new Location("In a hall","A hallway in an 18th Century building in the heart of London. There is a door leading to your office and stairs leading down to the street");
		
		Conversation convo = new Conversation("Hi");
		Query query = new Query("Hi");
		convo.addQuery(query);
		Item woman = new Being("Worried Woman","",convo,true);
		woman.setLeave("leave");
		woman.setLeaveConvo("Well, if you don't want to talk to me you can bugger off",
				new Conversation("Fuck off!"));
		convo = new Conversation("How's it going?");
		query.addConversation(convo);
		query = new Query("Pretty good, and you?");
		convo.addQuery(query);
		convo = new Conversation("Yeah, I'm doing quite well as well. What is your name?");
		query.addConversation(convo);
		query = new Query("I'm Jack and you?");
		convo.addQuery(query);
		convo = new Conversation("I'm Jance, so, what do you do for a crust?");
		query.addConversation(convo);
				
		Exit door = new CloseableExit("Door","Door",hall,true,"The door is a sturdy wooden door leading to the hall",woman);
		start.addItem(woman);
	}
	
	public Location start() {
		return start;
	}
}