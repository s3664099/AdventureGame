package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Container;
import Model.Door;
import Model.Item;
import Model.Location;
import Model.Objects;

class TestCases {
	
	private static Location location = new Location("next to a House");
	private static Item item1 = new Item("Scissors", 1);
	private static Item item2 = new Item("Hairnet", 1);
	private static Objects object1 = new Door("Building", 1);
	private static Objects object2 = new Container("Letterbox",1);
	
	@BeforeAll 
	public static void setup() {
		location.addExit("North", 2);
		location.addExit("South", 3);
		location.addObjects(object1);
		location.addObjects(object2);
		location.addItems(item1);
		location.addItems(item2);
	}
	
	@BeforeEach
	public void reset() {
		item1.setLocation(1);
		
		if (location.getItem("Scissors").equals(null))
			location.addItems(item1);
	}
	
	@Test
	void test() {

		System.out.println(location.toString());
	}
	
	@Test 
	void itemNameTest() {
		
		assertEquals(item1.getName(),"Scissors");
		
	}
	
	@Test 
	void itemLocationTest() {
		
		assertEquals(item1.getLocation(),1);
		
	}
	
	@Test 
	void objectNameTest() {
		
		assertEquals(object1.getName(),"Building");
		
	}
	
	@Test 
	void objectLocationTest() {
		
		assertEquals(object1.getLocation(),1);
		
	}
	
	@Test 
	void setLocationItemTest() {
		
		item1.setLocation(2);
		
		assertEquals(item1.getLocation(),2);
		
	}
	
	@Test
	void getItemFromLocationTest() {
		
		assertEquals(location.getItem("Scissors").getName(),item1.getName());
		
	}

}
