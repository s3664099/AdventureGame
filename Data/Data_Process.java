package Data;

public class Data_Process {
	
	Location start;
	String description;
	
	public Data_Process() {
		description = "The Minories Pub in London beckons adventurers with its historic charm, wooden beams, and a well-stocked bar. \nInside, you'll find a cozy haven where tales of epic journeys and daring quests are shared.";
		start = new Location("in the Minories",description);
		description = "Tucked away behind The Minories, the beer garden is a hidden oasis of greenery and tranquility. Amidst colorful blooms, you can enjoy your chosen drink, basking in the serene ambiance as you plan your next adventure or savor the moment.";
		Location two = new Location("in the Minories Beer Garden",description);
		description = "The road outside The Minories near Tower Gateway DLR Station buzzes with modern city life. Skyscrapers rise around you as commuters rush to and fro, and the distant rumble of traffic fills the air. This vibrant junction serves as a bustling crossroads, offering access to London's iconic landmarks and endless urban adventures.";
		Location three = new Location("under the railway line",description);
		description = "Outside the Tower of London, at the heart of Tower Hill, history stands proudly. Cobblestone streets echo the footsteps of countless visitors who've come to witness the grandeur of this iconic castle. Tower Bridge graces the skyline, and the Thames River flows as a testament to London's enduring legacy. This place, where past and present intersect, invites you to explore tales of intrigue and valor.";
		Location four = new Location("on Tower Hill",description);
		description = "The area outside the Tower of London is a historic treasure trove where ancient cobblestone streets wind past formidable stone walls, creating an immersive medieval atmosphere. Tower Bridge graces the skyline, and the tranquil Thames River flows nearby, offering a picturesque backdrop that blends centuries of history with modern London's charm. It's a place where the past meets the present, inviting visitors to explore the tales of this iconic fortress.";
		Location five = new Location("outside the Tower of London",description);
		description = "Byward Street, just outside The Tower of London, immerses you in a world of medieval allure. Cobblestone paths wind past ancient stone walls, with the historic fortress looming overhead. The atmosphere is rich with whispers of centuries gone by, creating an enchanting backdrop for your journey through history's pages.";
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
		start.addExit(new CloseableExit("Storeroom Door","storeroom door",fifteen,true,""));
		two.addExit(new OrdinaryExit("South",start,true));
		three.addExit(new OrdinaryExit("South",four,true));
		three.addExit(new OrdinaryExit("North",eleven,true));
		three.addExit(new OrdinaryExit("the Minories","minories",start,false,""));
		four.addExit(new OrdinaryExit("North",three,true));
		four.addExit(new OrdinaryExit("South",five,true));
		four.addExit(new OrdinaryExit("West",six,true));		
		five.addExit(new OrdinaryExit("North",four,true));
		six.addExit(new OrdinaryExit("East",four,true));
		six.addExit(new OrdinaryExit("West",seven,true));
		six.addExit(new OrdinaryExit("North",nine,true));
		seven.addExit(new OrdinaryExit("West",five,true));
		seven.addExit(new OrdinaryExit("Hung, Drawn & Quartered","pub hung drawn quartered",eight,false,""));
		eight.addExit(new OrdinaryExit("North",seven,true));
		nine.addExit(new OrdinaryExit("South",six,true));
		nine.addExit(new OrdinaryExit("Tower Hill Station","tower hill station",ten,true,""));
		ten.addExit(new OrdinaryExit("South",nine,true));
		eleven.addExit(new OrdinaryExit("South",three,true));
		eleven.addExit(new OrdinaryExit("West",thirteen,true));
		eleven.addExit(new OrdinaryExit("Starbucks",twelve,false));
		twelve.addExit(new OrdinaryExit("West",eleven,true));
		twelve.addExit(new CloseableExit("Toilet Door","toilet door",sixteen,true,""));
		thirteen.addExit(new OrdinaryExit("East",eleven,true));
		thirteen.addExit(new OrdinaryExit("Cheshire Cheese","cheshire cheese pub",fourteen,true,""));
		fourteen.addExit(new OrdinaryExit("East",thirteen,true));
		fifteen.addExit(new OrdinaryExit("West", start, true));
		sixteen.addExit(new OrdinaryExit("North", twelve, true));
	}
	
	public Location start() {
		return start;
	}
}