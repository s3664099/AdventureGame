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
		start.addExit(new Exit("North", two, false,false,true));
		start.addExit(new Exit("South", three, false,false,true));
		two.addExit(new Exit("South",start,false,false,true));
		three.addExit(new Exit("South",four,false,false,true));
		three.addExit(new Exit("North",eleven,false,false,true));
		three.addExit(new Exit("the Minories","minories",start,false,false,false));
		four.addExit(new Exit("North",three,false,false,true));
		four.addExit(new Exit("South",five,false,false,true));
		four.addExit(new Exit("West",six,false,false,true));		
		five.addExit(new Exit("North",four,false,false,true));
		six.addExit(new Exit("East",four,false,false,true));
		six.addExit(new Exit("West",seven,false,false,true));
		six.addExit(new Exit("North",nine,false,false,true));
		seven.addExit(new Exit("West",five,false,false,true));
		seven.addExit(new Exit("Hung, Drawn & Quartered","pub",eight,false,false,false));
		eight.addExit(new Exit("North",seven,false,false,true));
		nine.addExit(new Exit("South",six,false,false,true));
		nine.addExit(new Exit("Tower Hill Station","station",ten,false,false,true));
		ten.addExit(new Exit("South",nine,false,false,true));
		eleven.addExit(new Exit("South",three,false,false,true));
		eleven.addExit(new Exit("West",thirteen,false,false,true));
		eleven.addExit(new Exit("Starbucks",twelve,false,false,false));
		twelve.addExit(new Exit("West",eleven,false,false,true));
		thirteen.addExit(new Exit("East",eleven,false,false,true));
		thirteen.addExit(new Exit("Cheshire Cheese","pub",fourteen,false,false,true));
		fourteen.addExit(new Exit("East",thirteen,false,false,true));
	}
	
	public Location start() {
		return start;
	}
}