package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Ogre;
import dungeon.Hero;
import dungeon.MockHero;
import dungeon.Monster;

class OgreTests {

	@Test
	void ogreAttackDamagesOpponent() {
		Monster ogreTest = new Ogre();
		
		
		Hero mockHero = new MockHero("Testy boi");
		
		int hitPoints = mockHero.getHitPoints();
		
		ogreTest.attack(mockHero);
		
		assertNotEquals(hitPoints, mockHero.getHitPoints());
	}
	
	@Test
	void ogreConstructorReturnsOgre() {
		assertTrue(new Ogre() instanceof Ogre);
	}

}
