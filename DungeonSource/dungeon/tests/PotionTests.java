package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;
import dungeon.*;

import org.junit.jupiter.api.Test;

class PotionTests {

	@Test
	void testHeal() {
		final int high = 15;
		final int low = 5;
		Potion testPotion = new Potion(new Room(0,0));
		
		assertTrue(testPotion.heal() <= high);
		assertTrue(testPotion.heal() >= low);
	}

}
