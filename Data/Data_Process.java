package Data;

import java.util.logging.Logger;

import Data.Location.Builder;
import Model.Main;

public class Data_Process implements AutoCloseable {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
	private Location start;
	private int topScore;
	
	//Get three treasures and return them to the office.
	//When three treasures are there, game ends.
	
	public Data_Process() {
		
		this.topScore = 2;
		
		start = new Location(new Builder("In an office","An office of a consulting detective. The room is dark and dingy, and little light permeates the shadows").setTreasureStore(true));
		Container desk = new Container("Large Old Mahogany Desk","A large mahogany desk sitting across from the door. The desk is covered in papers",true);
		CarriableItem doorKey = new CarriableItem("Small Modern Brass Key","A brass key with the word 'door' carved into the head");
		desk.addItem(doorKey);
		start.addItem(desk);
		Location hall = new Location(new Builder("In a hall","A hallway in an 18th Century building in the heart of London. There is a door leading to your office and stairs leading down to the street"));
		LockableExit door = new LockableExit("Big Blue Mahogany Door","door",hall,true,"A flimsy door that leads to the hall",doorKey);
		start.addExit(door);
		Location kitchen = new Location(new Builder("In the kitchen","This office has a kitchen"));
		OrdinaryExit north = new OrdinaryExit("North",kitchen,false);
		OrdinaryExit south = new OrdinaryExit("South",start,false);
		ImmoveableItem rug = new ImmoveableItem("Colourful Persian Rug","This looks pretty expensive. A shame it is nailed to the floor");
		MoveableItem table = new MoveableItem("Dirty Kitchen Table","This table looks like it has seen better days, back in the 60s",rug);
		kitchen.addExit(south);
		kitchen.addItem(table);
		Bag suitcase = new Bag("Suitcase","It is tattered and has seen better days. It is made of leather",true,true,false,false,null);
		kitchen.addItem(suitcase);
		start.addExit(north);
		CarriableItem card = new CarriableItem("Card","A Gold American Express credit card");
		CarriableItem leaflet = new CarriableItem("Leaflet","An advertisment for curtian installation");
		CarriableItem note = new CarriableItem("Note","It looks like it is from your landlord");
		CarriableItem goldKey = new CarriableItem("Gold Key","A small gold key. I wonder what it opens");
		desk.addItem(goldKey);
		card.setRead("It has the name of Richard Pickles, but I'm not telling you the number because I don't trust you.");
		leaflet.setRead("Bob's curtians, make sure you neighbour can't see you dismembering that body");
		note.setRead("You are way, way, way behind in your rent. Pay me now or, or, or, there will be trouble");
		card.setTreasure();
		hall.addItem(card);
		hall.addItem(note);
		hall.addItem(leaflet);
		door = new LockableExit("Door","door",start,false,"A flimsy door that leads into your office.",doorKey);
		door.openClose();
		hall.addExit(door);
		hall.setScore(true);
		Location street = new Location(new Builder("On the Street","").setEndRoom(true).setEndComment("You got hit by a car!"));
		OrdinaryExit stairs = new OrdinaryExit("Stairs",street,false);
		hall.addExit(stairs);
		Conversation hooker = new Conversation("Where's my 50 quid from last time?");
		Being being = new Being("Dirty skanky hooker","A hooker with a cigarette in her mouth, dressed rather skantily, stands in the door opposite yours",hooker,false);
		hall.addItem(being);
		Location window = new Location(new Builder("At the end of the hall","The end of the hallway. There is a window that looks out onto the street below"));
		OrdinaryExit ne = new OrdinaryExit("Northeast",window,false);
		OrdinaryExit sw = new OrdinaryExit("Southwest",hall,false);
		hall.addExit(ne);
		window.addExit(sw);
		
		Location library = new Location(new Builder("In a library", "A cozy library with walls lined with bookshelves. A leather armchair sits in one corner."));
		Container bookshelf = new Container("Tall Oak Bookshelf", "An old bookshelf filled with dusty tomes and journals.", true,true,true,true,goldKey);		
		CarriableItem diary = new CarriableItem("Leather-bound Diary", "An old diary with a lock on it. It might contain secrets.");
		Bag toteBag = new Bag("Tote Bag","A tote bag with a tarten pattern on it");
		bookshelf.addItem(diary);
		library.addItem(bookshelf);
		OrdinaryExit west = new OrdinaryExit("West", library, false);
		OrdinaryExit east = new OrdinaryExit("East", start, false);
		start.addExit(west);
		library.addExit(east);
		library.addItem(toteBag);

		Location storage = new Location(new Builder("In the storage room", "A small, cluttered room filled with crates and forgotten items."));
		Container crate = new Container("Wooden Crate", "A large crate covered in cobwebs.", true);
		CarriableItem flashlight = new CarriableItem("Flashlight", "An old flashlight. It might still work.");
		crate.addItem(flashlight);
		storage.addItem(crate);
		east = new OrdinaryExit("East", storage, false);
		hall.addExit(east);
		storage.addExit(west);

		Location roof = new Location(new Builder("On the roof", "A flat rooftop with a great view of the city skyline. The wind is strong up here."));
		ImmoveableItem antenna = new ImmoveableItem("Rusted Antenna", "An old TV antenna that no longer serves its purpose.");
		MoveableItem box = new MoveableItem("Small Wooden Box", "A box that seems out of place here. It rattles when moved.", antenna);
		roof.addItem(box);
		OrdinaryExit up = new OrdinaryExit("Up", roof, false);
		window.addExit(up);
		roof.addExit(sw);

		Location basement = new Location(new Builder("In the basement", "A damp and dark basement filled with a musty smell."));
		Container chest = new Container("Iron-bound Chest", "A heavy chest with a large padlock.", true);
		CarriableItem map = new CarriableItem("Treasure Map", "An old map with faded markings. It might lead to something valuable.");
		chest.addItem(map);
		basement.addItem(chest);
		
		Location garden = new Location(new Builder("In the garden", "A peaceful garden filled with overgrown plants and flowers. A small fountain trickles in the center."));
		ImmoveableItem fountain = new ImmoveableItem("Stone Fountain", "A small fountain with water trickling down. It looks like it hasn't been maintained in years.");
		CarriableItem shovel = new CarriableItem("Rusty Shovel", "A shovel covered in rust. It might still be useful for digging.");
		garden.addItem(fountain);
		garden.addItem(shovel);
		CloseableExit gardenExit = new CloseableExit("Back Door","door", garden, true, "This is a wooden door with a window looking out into the back yard");
		CloseableExit kitchenExit = new CloseableExit("Back Door","door", kitchen, false, "This is a wooden door with a window looking into the kitchen");
		kitchen.addExit(gardenExit);
		garden.addExit(kitchenExit);

		Location study = new Location(new Builder("In the study", "A quiet study with a large writing desk, a fireplace, and shelves filled with books."));
		Container writingDesk = new Container("Writing Desk", "A sturdy desk with drawers. It looks like it's been used frequently.", true);
		CarriableItem letter = new CarriableItem("Sealed Letter", "A letter with a wax seal. It feels important.");
		writingDesk.addItem(letter);
		study.addItem(writingDesk);
		OrdinaryExit studyExit = new OrdinaryExit("West", study, false);
		OrdinaryExit libraryExit = new OrdinaryExit("East", library, false);
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