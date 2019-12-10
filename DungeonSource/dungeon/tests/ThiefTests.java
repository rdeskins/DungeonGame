package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dungeon.DungeonCharacter;
import dungeon.Hero;
import dungeon.Ogre;
import dungeon.Thief;

class ThiefTests {

	@Test
	void thiefAttackDamagesOpponent() {
		Hero thiefTest = new Thief("testy boi");
	
		DungeonCharacter testOgre = new Ogre();
		
		int hitPoints = testOgre.getHitPoints();
		
		thiefTest.attack(testOgre);
		
		assertNotEquals(hitPoints, testOgre.getHitPoints());
	}
	
	@Test
	void thiefConstructorReturnsThief () {
		assertTrue(new Thief("Testy boi") instanceof Thief);
	}
	
	@Test
	void thiefConstructorNullNameThrowsException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {Hero thiefTest = new Thief(null);});
	}

}
