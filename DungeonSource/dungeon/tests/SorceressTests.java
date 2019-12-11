package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dungeon.Sorceress;

class SorceressTests {
	
	@Test
	void sorceressConstructorReturnsSorceress() {
		assertTrue(new Sorceress("Testy boi") instanceof Sorceress);
	}
	
	@Test
	void sorceressConstructorNullNameThrowsException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {new Sorceress(null);});
	}

}
