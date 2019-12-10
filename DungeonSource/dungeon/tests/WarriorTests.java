package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dungeon.AttackBehavior;
import dungeon.AttackFactory;
import dungeon.DungeonCharacter;
import dungeon.Hero;
import dungeon.Ogre;
import dungeon.Warrior;

class WarriorTests {

	@Test
	void warriorAttackDamagesOpponent() {
		Hero warTest = new Warrior("testy boi");
	
		DungeonCharacter testOgre = new Ogre();
		
		int hitPoints = testOgre.getHitPoints();
		
		warTest.attack(testOgre);
		
		assertNotEquals(hitPoints, testOgre.getHitPoints());
	}
	
	@Test
	void warriorConstructorReturnsWarrior() {
		assertTrue(new Warrior("Testy boi") instanceof Warrior);
	}
	
	@Test
	void warriorConstructorNullNameThrowsException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {Hero warTest = new Warrior(null);});
	}

}
