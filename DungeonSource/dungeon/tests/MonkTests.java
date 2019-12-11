package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import dungeon.Monk;

class MonkTests {
	
	@Test
	void monkConstructorReturnsMonk () {
		assertTrue(new Monk("Testy boi") instanceof Monk);
	}
	
	@Test
	void monkConstructorNullNameThrowsException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {new Monk(null);});
	}

}
