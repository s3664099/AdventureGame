package Data;

import java.util.logging.Logger;
import Model.Main;

public class Data_Process implements AutoCloseable {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
	private Location start;
	private int topScore;
	
	//Get three treasures and return them to the office.
	//When three treasures are there, game ends.
	
	public Data_Process() {
		
		this.topScore = 2;
		
		start = new Location.Builder("In an office","An office of a consulting detective. The room is dark and dingy, and little light permeates the shadows").setTreasureStore(true).build();
		CarriableItem doorKey = new CarriableItem.Builder("Small Modern Brass Key","A brass key with the word 'door' carved into the head").build();
		CarriableItem goldKey = new CarriableItem.Builder("Gold Key","A small gold key. I wonder what it opens").build();
		Container desk = new Container.Builder("Large Old Mahogany Desk","A large mahogany desk sitting across from the door. The desk is covered in papers").setClosed(true).addItem(doorKey).addItem(goldKey).build();
		start.addItem(desk);
		Location hall = new Location.Builder("In a hall","A hallway in an 18th Century building in the heart of London. There is a door leading to your office and stairs leading down to the street").build();
		Exit door = new LockableExit.Builder("Big Blue Mahogany Door", hall, false).setLocked(true).setKey(doorKey).addDescription("A flimsy door that leads to the hall").build();
		start.addExit(door);
		Location kitchen = new Location.Builder("In the kitchen","This office has a kitchen").build();
		Exit north = new OrdinaryExit.Builder("North", kitchen, true).build();
		Exit south = new OrdinaryExit.Builder("South",start,true).build();
		ImmoveableItem rug = new ImmoveableItem.Builder("Colourful Persian Rug", "This looks pretty expensive. A shame it is nailed to the floor").build();
		MoveableItem table = new MoveableItem.Builder("Dirty Kitchen Table","This table looks like it has seen better days, back in the 60s").setItem(rug).build();
		kitchen.addExit(south);
		kitchen.addItem(table);
		Bag suitcase = new Bag.Builder("Suitcase","It is tattered and has seen better days. It is made of leather")
					.setCloseable(true)
					.setClosed(true)
					.setLockable(false)
					.setLocked(false)
					.setKey(null)
					.build();
		kitchen.addItem(suitcase);
		start.addExit(north);
		CarriableItem card = new CarriableItem.Builder("Card","A Gold American Express credit card")
					.setRead("It has the name of Richard Pickles, but I'm not telling you the number because I don't trust you.")
					.setTreasure()
					.build();
		CarriableItem leaflet = new CarriableItem.Builder("Leaflet","An advertisment for curtian installation")
					.setRead("Bob's curtians, make sure you neighbour can't see you dismembering that body")
					.build();
		CarriableItem note = new CarriableItem.Builder("Note","It looks like it is from your landlord")
					.setRead("You are way, way, way behind in your rent. Pay me now or, or, or, there will be trouble")
					.build();
		hall.addItem(card);
		hall.addItem(note);
		hall.addItem(leaflet);
		door = new LockableExit.Builder("Door", start, false).setKey(doorKey).setLocked(true).addClosed(true)
				.addDescription("A flimsy door that leads into your office.").addCommand("door").build();
		door.toggleOpenClose();
		hall.addExit(door);
		hall.setScore(true);
		Location street = new Location.Builder("On the Street","").setEndRoom(true).setEndComment("You got hit by a car!").build();
		Exit stairs = new OrdinaryExit.Builder("Stairs",street,false).build();
		hall.addExit(stairs);
		Conversation hooker = new Conversation("Where's my 50 quid from last time?");
		Being being = new Being("Dirty skanky hooker","A hooker with a cigarette in her mouth, dressed rather skantily, stands in the door opposite yours",hooker,false);
		hall.addItem(being);
		Location window = new Location.Builder("At the end of the hall","The end of the hallway. There is a window that looks out onto the street below").build();
		Exit ne = new OrdinaryExit.Builder("Northeast",window,false).build();
		Exit sw = new OrdinaryExit.Builder("Southwest",hall,false).build();
		hall.addExit(ne);
		window.addExit(sw);
		
		Location library = new Location.Builder("In a library", "A cozy library with walls lined with bookshelves. A leather armchair sits in one corner.").build();
		Container bookshelf = new Container.Builder("Tall Oak Bookshelf", "An old bookshelf filled with dusty tomes and journals.")
					.setCloseable(true)
					.setClosed(true)
					.setLockable(true)
					.setLocked(true)
					.setKey(goldKey)
					.build();	
		CarriableItem diary = new CarriableItem.Builder("Leather-bound Diary", "An old diary with a lock on it. It might contain secrets.").build();
		Bag toteBag = new Bag.Builder("Tote Bag","A tote bag with a tarten pattern on it").build();
		bookshelf.addItem(diary);
		library.addItem(bookshelf);
		Exit west = new OrdinaryExit.Builder("West", library, false).build();
		Exit east = new OrdinaryExit.Builder("East", start, false).build();
		start.addExit(west);
		library.addExit(east);
		library.addItem(toteBag);

		Location storage = new Location.Builder("In the storage room", "A small, cluttered room filled with crates and forgotten items.").build();
		Container crate = new Container.Builder("Wooden Crate", "A large crate covered in cobwebs.")
					.setCloseable(true)
					.setClosed(true)
					.build();
		CarriableItem flashlight = new CarriableItem.Builder("Flashlight", "An old flashlight. It might still work.").build();
		crate.addItem(flashlight);
		storage.addItem(crate);
		east = new OrdinaryExit.Builder("East", storage, false).build();
		hall.addExit(east);
		storage.addExit(west);

		Location roof = new Location.Builder("On the roof", "A flat rooftop with a great view of the city skyline. The wind is strong up here.").build();
		ImmoveableItem antenna = new ImmoveableItem.Builder("Rusted Antenna", "An old TV antenna that no longer serves its purpose.").build();
		MoveableItem box = new MoveableItem.Builder("Small Wooden Box", "A box that seems out of place here. It rattles when moved.").setItem(antenna).build();
		roof.addItem(box);
		Exit up = new OrdinaryExit.Builder("Up", roof, false).build();
		window.addExit(up);
		roof.addExit(sw);

		Location basement = new Location.Builder("In the basement", "A damp and dark basement filled with a musty smell.").build();
		Container chest = new Container.Builder("Iron-bound Chest", "A heavy chest with a large padlock.")
					.setCloseable(true)
					.setClosed(true)
					.build();
		CarriableItem map = new CarriableItem.Builder("Treasure Map", "An old map with faded markings. It might lead to something valuable.").build();
		chest.addItem(map);
		basement.addItem(chest);
		
		Location garden = new Location.Builder("In the garden", "A peaceful garden filled with overgrown plants and flowers. A small fountain trickles in the center.").build();
		ImmoveableItem fountain = new ImmoveableItem.Builder("Stone Fountain", "A small fountain with water trickling down. It looks like it hasn't been maintained in years.").build();
		CarriableItem shovel = new CarriableItem.Builder("Rusty Shovel", "A shovel covered in rust. It might still be useful for digging.").build();
		garden.addItem(fountain);
		garden.addItem(shovel);
		Exit gardenExit = new CloseableExit.Builder("Back Door", garden, false).addClosed(true).addCommand("door")
				.addDescription("This is a wooden door with a window looking out into the back yard").build();
		Exit kitchenExit = new CloseableExit.Builder("Back Door", garden, false).addClosed(true).addCommand("door")
				.addDescription("This is a wooden door with a window looking into the kitchen").build();
		kitchen.addExit(gardenExit);
		garden.addExit(kitchenExit);

		Location study = new Location.Builder("In the study", "A quiet study with a large writing desk, a fireplace, and shelves filled with books.").build();
		Container writingDesk = new Container.Builder("Writing Desk", "A sturdy desk with drawers. It looks like it's been used frequently.")
					.setCloseable(true)
					.setClosed(true)
					.build();
		CarriableItem letter = new CarriableItem.Builder("Sealed Letter", "A letter with a wax seal. It feels important.").build();
		writingDesk.addItem(letter);
		study.addItem(writingDesk);
		Exit studyExit = new OrdinaryExit.Builder("West", study, true).build();
		Exit libraryExit = new OrdinaryExit.Builder("East", library, true).build();
		library.addExit(studyExit);
		study.addExit(libraryExit);		
		
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

	@Override
	public void close() throws Exception {
		logger.fine("Closing game data resources");
		
	}
}