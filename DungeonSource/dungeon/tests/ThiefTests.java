package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dungeon.Thief;

class ThiefTests {
	
	@Test
	void thiefConstructorReturnsThief () {
		assertTrue(new Thief("Testy boi") instanceof Thief);
	}
	
	@Test
	void thiefConstructorNullNameThrowsException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {new Thief(null);});
	}

}
