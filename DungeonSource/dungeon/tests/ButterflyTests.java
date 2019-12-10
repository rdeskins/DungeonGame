package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Butterfly;
import dungeon.Hero;
import dungeon.MockHero;
import dungeon.Monster;

class ButterflyTests {

	@Test
	void butterflyAttackDamagesOpponent() {
		Monster butterflyTest = new Butterfly();
		
		Hero mockHero = new MockHero("Testy boi");
		
		int hitPoints = mockHero.getHitPoints();
		
		butterflyTest.attack(mockHero);
		
		assertNotEquals(hitPoints, mockHero.getHitPoints());
	}
	
	@Test
	void butterflyConstructorReturnsButterfly() {
		assertTrue(new Butterfly() instanceof Butterfly);
	}

}
