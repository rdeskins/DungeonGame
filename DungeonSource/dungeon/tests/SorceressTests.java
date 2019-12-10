package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dungeon.Sorceress;
import dungeon.DungeonCharacter;
import dungeon.Hero;
import dungeon.Ogre;

class SorceressTests {

	@Test
	void sorceressAttackDamagesOpponent() {
		Hero dinoTest = new Sorceress("testy boi");
	
		DungeonCharacter testOgre = new Ogre();
		
		int hitPoints = testOgre.getHitPoints();
		
		dinoTest.attack(testOgre);
		
		assertNotEquals(hitPoints, testOgre.getHitPoints());
	}
	
	@Test
	void sorceressConstructorReturnsSorceress() {
		assertTrue(new Sorceress("Testy boi") instanceof Sorceress);
	}
	
	@Test
	void sorceressConstructorNullNameThrowsException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {Hero dinoTest = new Sorceress(null);});
	}

}
