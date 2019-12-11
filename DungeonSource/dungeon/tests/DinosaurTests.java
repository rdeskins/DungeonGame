package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dungeon.Dinosaur;

class DinosaurTests {
	
	@Test
	void dinosaurConstructorReturnsDinosaur() {
		assertTrue(new Dinosaur("Testy boi") instanceof Dinosaur);
	}
	
	@Test
	void dinosaurConstructorNullNameThrowsException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {new Dinosaur(null);});
	}

}
