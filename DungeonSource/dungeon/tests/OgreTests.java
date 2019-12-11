package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Ogre;

class OgreTests {
	
	@Test
	void ogreConstructorReturnsOgre() {
		assertTrue(new Ogre() instanceof Ogre);
	}

}
