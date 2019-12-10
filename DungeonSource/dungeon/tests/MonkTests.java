package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dungeon.DungeonCharacter;
import dungeon.Hero;
import dungeon.Ogre;
import dungeon.Monk;

class MonkTests {

	@Test
	void monkAttackDamagesOpponent() {
		Hero monkTest = new Monk("testy boi");
	
		DungeonCharacter testOgre = new Ogre();
		
		int hitPoints = testOgre.getHitPoints();
		
		monkTest.attack(testOgre);
		
		assertNotEquals(hitPoints, testOgre.getHitPoints());
	}
	
	@Test
	void monkConstructorReturnsMonk () {
		assertTrue(new Monk("Testy boi") instanceof Monk);
	}
	
	@Test
	void monkConstructorNullNameThrowsException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {Hero monkTest = new Monk(null);});
	}

}
