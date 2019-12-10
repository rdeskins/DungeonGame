package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Fobby;
import dungeon.Hero;
import dungeon.MockHero;
import dungeon.Monster;

class FobbyTests {

	@Test
	void fobbyAttackDamagesOpponent() {
		Monster fobbyTest = new Fobby();
		
		Hero mockHero = new MockHero("Testy boi");
		
		int hitPoints = mockHero.getHitPoints();
		
		fobbyTest.attack(mockHero);
		
		assertNotEquals(hitPoints, mockHero.getHitPoints());
	}
	
	@Test
	void fobbyConstructorReturnsFobby() {
		assertTrue(new Fobby() instanceof Fobby);
	}
}
