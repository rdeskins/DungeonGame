package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;
import dungeon.*;

import org.junit.jupiter.api.Test;

class PitTests {

	@Test
	void testDamage() {
		final int high = 20;
		final int low = 1;
		Pit testPit = new Pit(new Room(0,0));
		
		assertTrue(testPit.damage() <= high);
		assertTrue(testPit.damage() >= low);
	}

}
