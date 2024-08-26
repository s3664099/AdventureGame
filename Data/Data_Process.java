package Data;

public class Data_Process {
	
	private Location start;
	private String description;
	private Container container;
	private int topScore;
	
	//Get three treasures and return them to the office.
	//When three treasures are there, game ends.
	
	public Data_Process() {
		
		this.topScore = 2;
		
		start = new Location("In an office","An office of a consulting detective. The room is dark and dingy, and little light permeates the shadows");
		start.setTreasureStore();
		Container desk = new Container("Large Old Mahogany Desk","A large mahogany desk sitting across from the door. The desk is covered in papers",true);
		CarriableItem doorKey = new CarriableItem("Small Modern Brass Key","A brass key with the word 'door' carved into the head");
		desk.addItem(doorKey);
		start.addItem(desk);
		Location hall = new Location("In a hall","A hallway in an 18th Century building in the heart of London. There is a door leading to your office and stairs leading down to the street");
		LockableExit door = new LockableExit("Big Blue Mahogany Door","door",hall,true,"A flimsy door that leads to the hall",doorKey);
		start.addExit(door);
		Location kitchen = new Location("In the kitchen","This office has a kitchen");
		OrdinaryExit north = new OrdinaryExit("North",kitchen,false);
		OrdinaryExit south = new OrdinaryExit("South",start,false);
		kitchen.addExit(south);
		start.addExit(north);
		CarriableItem card = new CarriableItem("Card","A Gold American Express credit card");
		card.setTreasure();
		hall.addItem(card);
		door = new LockableExit("Door","door",start,false,"A flimsy door that leads into your office.",doorKey);
		door.openClose();
		hall.addExit(door);
		hall.setScore();
		Location street = new Location("On the Street","","You got hit by a car!");
		OrdinaryExit stairs = new OrdinaryExit("Stairs",street,false);
		hall.addExit(stairs);
		
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
	
	public int getScore() {
		return this.topScore;
	}
}