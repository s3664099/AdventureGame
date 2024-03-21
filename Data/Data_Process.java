package Data;

public class Data_Process {
	
	Location start;
	String description;
	Container container;
	
	public Data_Process() {
		
		start = new Location("In an office","An office of a consulting detective. The room is dark and dingy, and little light permeates the shadows");
		Location two = new Location("Cellar","The cellar is incredibly dusty and covered in cobwebs");
		Location three = new Location("Alcove","A small alcove hidden behind a tapestry");
		Location four = new Location("Second Cellar","The cellar is incredibly dusty and covered in cobwebs");
		Location five = new Location("Wardrobe","The cellar is incredibly dusty and covered in cobwebs");
		Location six = new Location("Wine Cellar","The cellar is full of racks containing, no doubt, some very expensive wine");
		Exit hole = new OrdinaryExit("Down",two,true);
		hole.addDescription("hole in the floor");
		Exit hole2 = new OrdinaryExit("Hole",four,true);
		hole2.addDescription("another hole in the floor");
		Exit alcove = new OrdinaryExit("North",three,true);
		Exit hole3 = new OrdinaryExit("Trapdoor",six,true);
		hole3.addDescription("a sturdy wooden trapdoor");
		alcove.addDescription("a hidden passage");
		CarriableItem coat = new CarriableItem("Puffer Jacket","It's fairly cheap and nasty, but still warm");
		CarriableItem dress = new CarriableItem("Dress","A floral dress. It has certainly seen better days");
		Item cover = new Cover("Rug","A persian rug that has seen better days",hole,false);
		CarriableItem ring = new CarriableItem("Gold Ring","Plain Gold Ring");
		CarriableItem necklace = new CarriableItem("Silve Necklace","A silver chainlink necklace");
		cover.addItem(ring);
		cover.addItem(necklace);
		cover.addExit(hole3);
		cover.addExit(hole2);
		start.addItem(cover);
		cover = new Cover("Curtain","A dark green curtian that has been stained with age",alcove,true);
		cover.addItem(coat);
		cover.addItem(dress);
		start.addItem(cover);
		CarriableItem top = new CarriableItem("Pyjama Top","A stripped flanalette shirt normally used as sleepwear");
		CarriableItem pants = new CarriableItem("Pyjama Pants","A pair of stripped flanalette pants");
		Item quilt = new Cover("Quilt","A down filled bed spread. It looks toasty warm",top,true);
		quilt.addItem(pants);
		Item bed = new Cover("Bed","A sturdy wooden bed",quilt,false);
		start.addItem(bed);
		
		/*description = "The Minories Pub in London beckons adventurers with its historic charm, wooden beams, and a well-stocked bar. \nInside, you'll find a cozy haven where tales of epic journeys and daring quests are shared.";
		start = new Location("in the Minories",description);
		start.addItem(new ImmoveableItem("Bar","The bar is a focal point of conviviality, adorned with a rich mahogany countertop that's been \ngracefully worn by countless patrons over the years."));
		start.addItem(new CarriableItem("Beer Glass","This is a think glass with a handle used for holding beer."));
		start.addItem(new CarriableItem("Brass Key","A key made of brass that looks like it opens a lock"));
		container = new Container("Table", "The table is a sturdy wooden table, stained with years worth of spilled beer");
		container.addItem(new CarriableItem("Beer Coaster","A small square piece of cardboard advertising Camden Larger."));
		container.addItem(new CarriableItem("Spoon","Your typical plain spoon you find in a pub"));
		container.addItem(new CarriableItem("Articulator","Nobody knows what this is"));
		container.addItem(new CarriableItem("Indicator","A light that should be on a car"));
		container.addItem(new CarriableItem("Steel Key","An old grimy key for an old door."));
		start.addItem(container);
		CarriableItem key = new CarriableItem("Blue Key","It's metal, and blue. Probably for an Air BnB");
		start.addItem(key);
		description = "Tucked away behind The Minories, the beer garden is a hidden oasis of greenery and tranquility. Amidst colorful \nblooms, you can enjoy your chosen drink, basking in the serene ambiance as you plan your next adventure or savor the moment.";
		Location two = new Location("in the Minories Beer Garden",description);
		two.addItem(new CarriableItem("Plastic Cup","A cup made of plastic. It looks more like a goblet"));
		two.addItem(new CarriableItem("Paper Cup","A cup made of paper with a thin layer of plastic. There are traces of coffee in it"));
		description = "The road outside The Minories near Tower Gateway DLR Station buzzes with modern city life. Skyscrapers rise \naround you as commuters rush to and fro, and the distant rumble of traffic fills the air. This vibrant junction \nserves as a bustling crossroads, offering access to London's iconic landmarks and endless urban adventures.";
		Location three = new Location("under the railway line",description);
		description = "Outside the Tower of London, at the heart of Tower Hill, history stands proudly. Cobblestone streets echo the \nfootsteps of countless visitors who've come to witness the grandeur of this iconic castle. Tower Bridge graces \nthe skyline, and the Thames River flows as a testament to London's enduring legacy. This place, where past and present intersect, \ninvites you to explore tales of intrigue and valor.";
		Location four = new Location("on Tower Hill",description);
		description = "The area outside the Tower of London is a historic treasure trove where ancient cobblestone streets wind past \nformidable stone walls, creating an immersive medieval atmosphere. Tower Bridge graces the skyline, and the \ntranquil Thames River flows nearby, offering a picturesque backdrop that blends centuries of history with modern London's charm. It's \na place where the past meets the present, inviting visitors to explore the tales of this iconic fortress.";
		Location five = new Location("outside the Tower of London",description);
		description = "Byward Street, just outside The Tower of London, immerses you in a world of medieval allure. Cobblestone paths \nwind past ancient stone walls, with the historic fortress looming overhead. The atmosphere is rich with whispers \nof centuries gone by, creating an enchanting backdrop for your journey through history's pages.";
		Location six = new Location("on Byward Street",description);
		Location seven = new Location("on Great Tower Street","");
		Location eight = new Location("in the Hung, Drawn, and Quartered","");
		Location nine = new Location("in Trinity Square Gardens","");
		Location ten = new Location("in Tower Hill Station Concourse","");
		Location eleven = new Location("on Crosswall","");
		Location twelve = new Location("in Starbucks","");
		Location thirteen = new Location("on the Crutched Friars","");
		Location fourteen = new Location("in the Chesire Cheese","");
		Location fifteen = new Location("in a Store Room","");
		Location sixteen = new Location("in Starbuck's Toilet","");
		start.addExit(new OrdinaryExit("North", two, true));
		start.addExit(new OrdinaryExit("South", three, true));
		start.addExit(new LockableExit("Storeroom Door","storeroom door",fifteen,true,"This is a rather sturdy door to a store room",key));
		two.addExit(new OrdinaryExit("South",start,true));
		three.addExit(new OrdinaryExit("South",four,true));
		three.addExit(new OrdinaryExit("North",eleven,true));
		three.addExit(new OrdinaryExit("the Minories","minories",start,false,"The Minories is a pub located under the mainline heading out of Fenchurch Street Station"));
		four.addExit(new OrdinaryExit("North",three,true));
		four.addExit(new OrdinaryExit("South",five,true));
		four.addExit(new OrdinaryExit("West",six,true));		
		five.addExit(new OrdinaryExit("North",four,true));
		six.addExit(new OrdinaryExit("East",four,true));
		six.addExit(new OrdinaryExit("West",seven,true));
		six.addExit(new OrdinaryExit("North",nine,true));
		seven.addExit(new OrdinaryExit("West",five,true));
		seven.addExit(new OrdinaryExit("Hung, Drawn & Quartered","pub hung drawn quartered",eight,false,"An old English pub that has the charm of an old English pub"));
		eight.addExit(new OrdinaryExit("North",seven,true));
		nine.addExit(new OrdinaryExit("South",six,true));
		nine.addExit(new OrdinaryExit("Tower Hill Station","tower hill station",ten,true,"A rather drab entrance that seems to abide to the principle of form follows function"));
		ten.addExit(new OrdinaryExit("South",nine,true));
		eleven.addExit(new OrdinaryExit("South",three,true));
		eleven.addExit(new OrdinaryExit("West",thirteen,true));
		eleven.addExit(new OrdinaryExit("Starbucks",twelve,false));
		twelve.addExit(new OrdinaryExit("West",eleven,true));
		twelve.addExit(new CloseableExit("Toilet Door","toilet door",sixteen,true,"It's the door to a toilet. What more do you expect, glitter and sunshine. Oh, it's gender neutral, if that causes you to have a fit"));
		thirteen.addExit(new OrdinaryExit("East",eleven,true));
		thirteen.addExit(new OrdinaryExit("Cheshire Cheese","cheshire cheese pub",fourteen,true,"A dark and dingy pub located under a railway bridge. I heard the bartender's pretty cool"));
		fourteen.addExit(new OrdinaryExit("East",thirteen,true));
		fifteen.addExit(new OrdinaryExit("West", start, true));
		sixteen.addExit(new OrdinaryExit("North", twelve, true));
		*/
	}
	
	public Location start() {
		return start;
	}
}