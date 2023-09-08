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
		start.addExit(new OrdinaryExit("North", two, true));
		start.addExit(new OrdinaryExit("South", three, true));
		start.addExit(new CloseableExit("Storeroom Door","storeroom door",fifteen,true));
		two.addExit(new OrdinaryExit("South",start,true));
		three.addExit(new OrdinaryExit("South",four,true));
		three.addExit(new OrdinaryExit("North",eleven,true));
		three.addExit(new OrdinaryExit("the Minories","minories",start,false));
		four.addExit(new OrdinaryExit("North",three,true));
		four.addExit(new OrdinaryExit("South",five,true));
		four.addExit(new OrdinaryExit("West",six,true));		
		five.addExit(new OrdinaryExit("North",four,true));
		six.addExit(new OrdinaryExit("East",four,true));
		six.addExit(new OrdinaryExit("West",seven,true));
		six.addExit(new OrdinaryExit("North",nine,true));
		seven.addExit(new OrdinaryExit("West",five,true));
		seven.addExit(new OrdinaryExit("Hung, Drawn & Quartered","pub hung drawn quartered",eight,false));
		eight.addExit(new OrdinaryExit("North",seven,true));
		nine.addExit(new OrdinaryExit("South",six,true));
		nine.addExit(new OrdinaryExit("Tower Hill Station","tower hill station",ten,true));
		ten.addExit(new OrdinaryExit("South",nine,true));
		eleven.addExit(new OrdinaryExit("South",three,true));
		eleven.addExit(new OrdinaryExit("West",thirteen,true));
		eleven.addExit(new OrdinaryExit("Starbucks",twelve,false));
		twelve.addExit(new OrdinaryExit("West",eleven,true));
		twelve.addExit(new CloseableExit("Toilet Door","toilet door",sixteen,true));
		thirteen.addExit(new OrdinaryExit("East",eleven,true));
		thirteen.addExit(new OrdinaryExit("Cheshire Cheese","cheshire cheese pub",fourteen,true));
		fourteen.addExit(new OrdinaryExit("East",thirteen,true));
		fifteen.addExit(new OrdinaryExit("West", start, true));
		sixteen.addExit(new OrdinaryExit("North", twelve, true));
	}
	
	public Location start() {
		return start;
	}
}