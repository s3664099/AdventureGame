package Data;

public class Data_Process {
	
	Location start;
	
	public Data_Process() {
		start = new Location("in the Minories");
		Location two = new Location("in the Minories Beer Garden");
		Location three = new Location("under the railway line");
		Location four = new Location("on Tower Hill");
		Location five = new Location("outside the Tower of London");
		Location six = new Location("on Byward Street");
		Location seven = new Location("on Great Tower Street");
		Location eight = new Location("in the Hung, Drawn, and Quartered");
		Location nine = new Location("in Trinity Square Gardens");
		Location ten = new Location("in Tower Hill Station Concourse");
		Location eleven = new Location("on Crosswall");
		Location twelve = new Location("in Starbucks");
		Location thirteen = new Location("on the Crutched Friars");
		Location fourteen = new Location("in the Chesire Cheese");
		Location fifteen = new Location("in a Store Room");
		Location sixteen = new Location("in Starbuck's Toilet");
		start.addExit(new Exit("North", two, true));
		start.addExit(new Exit("South", three, true));
		start.addExit(new CloseableExit("Storeroom Door","storeroom door",fifteen,true,false));
		two.addExit(new Exit("South",start,true));
		three.addExit(new Exit("South",four,true));
		three.addExit(new Exit("North",eleven,true));
		three.addExit(new Exit("the Minories","minories",start,false));
		four.addExit(new Exit("North",three,true));
		four.addExit(new Exit("South",five,true));
		four.addExit(new Exit("West",six,true));		
		five.addExit(new Exit("North",four,true));
		six.addExit(new Exit("East",four,true));
		six.addExit(new Exit("West",seven,true));
		six.addExit(new Exit("North",nine,true));
		seven.addExit(new Exit("West",five,true));
		seven.addExit(new Exit("Hung, Drawn & Quartered","pub hung drawn quartered",eight,false));
		eight.addExit(new Exit("North",seven,true));
		nine.addExit(new Exit("South",six,true));
		nine.addExit(new Exit("Tower Hill Station","tower hill station",ten,true));
		ten.addExit(new Exit("South",nine,true));
		eleven.addExit(new Exit("South",three,true));
		eleven.addExit(new Exit("West",thirteen,true));
		eleven.addExit(new Exit("Starbucks",twelve,false));
		twelve.addExit(new Exit("West",eleven,true));
		twelve.addExit(new CloseableExit("Toilet Door","toilet door",sixteen,false,false));
		thirteen.addExit(new Exit("East",eleven,true));
		thirteen.addExit(new Exit("Cheshire Cheese","cheshire cheese pub",fourteen,true));
		fourteen.addExit(new Exit("East",thirteen,true));
	}
	
	public Location start() {
		return start;
	}
}