import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Data.Exit;
import Data.Location;

import java.util.ArrayList;

class GameTest {

	@Test
    public void testAddExit() {
        Location location = new Location("test location");
        Exit exit = new Exit("test exit", new Location("destination"), false, false, false);
        location.addExit(exit);
        ArrayList<String> expectedNouns = new ArrayList<String>();
        expectedNouns.add("test exit");
        Assertions.assertEquals(expectedNouns, location.getNouns());
        ArrayList<Exit> expectedExits = new ArrayList<Exit>();
        expectedExits.add(exit);
        Assertions.assertEquals(expectedExits, location.getExits());
    }
	
	@Test
	void testName() {
		Location location = new Location("Front Door");
		assertEquals("Front Door",location.getName());
	}
	
}
