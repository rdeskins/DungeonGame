package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dungeon.Warrior;

class WarriorTests {
	
	@Test
	void warriorConstructorReturnsWarrior() {
		assertTrue(new Warrior("Testy boi") instanceof Warrior);
	}
	
	@Test
	void warriorConstructorNullNameThrowsException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {new Warrior(null);});
	}

}
