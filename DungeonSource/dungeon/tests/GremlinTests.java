package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Gremlin;
import dungeon.Hero;
import dungeon.MockHero;
import dungeon.Monster;

class GremlinTests {

	@Test
	void gremlinAttackDamagesOpponent() {
		Monster gremlinTest = new Gremlin();
		
		Hero mockHero = new MockHero("Testy boi");
		
		int hitPoints = mockHero.getHitPoints();
		
		gremlinTest.attack(mockHero);
		
		assertNotEquals(hitPoints, mockHero.getHitPoints());
	}
	
	@Test
	void gremlinConstructorReturnsGremlin() {
		assertTrue(new Gremlin() instanceof Gremlin);
	}
	
}
