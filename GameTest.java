import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Data.OrdinaryExit;
import Data.Location;

import java.util.ArrayList;

class GameTest {

	@Test
    public void testAddExit() {
        Location location = new Location("test location","");
        OrdinaryExit exit = new OrdinaryExit("test exit", new Location("destination",""), false);
        location.addExit(exit);
        ArrayList<String> expectedNouns = new ArrayList<String>();
        expectedNouns.add("test exit");
        Assertions.assertEquals(expectedNouns, location.getNouns());
        ArrayList<OrdinaryExit> expectedExits = new ArrayList<OrdinaryExit>();
        expectedExits.add(exit);
        Assertions.assertEquals(expectedExits, location.getExits());
    }
	
	@Test
	void testName() {
		Location location = new Location("Front Door","");
		assertEquals("Front Door",location.getName(true));
	}
	
}
