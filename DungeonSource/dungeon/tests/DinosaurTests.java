package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dungeon.DungeonCharacter;
import dungeon.Hero;
import dungeon.Ogre;
import dungeon.Dinosaur;

class DinosaurTests {

	@Test
	void dinosaurAttackDamagesOpponent() {
		Hero dinoTest = new Dinosaur("testy boi");
	
		DungeonCharacter testOgre = new Ogre();
		
		int hitPoints = testOgre.getHitPoints();
		
		dinoTest.attack(testOgre);
		
		assertNotEquals(hitPoints, testOgre.getHitPoints());
	}
	
	@Test
	void dinosaurConstructorReturnsDinosaur() {
		assertTrue(new Dinosaur("Testy boi") instanceof Dinosaur);
	}
	
	@Test
	void dinosaurConstructorNullNameThrowsException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {Hero dinoTest = new Dinosaur(null);});
	}

}
