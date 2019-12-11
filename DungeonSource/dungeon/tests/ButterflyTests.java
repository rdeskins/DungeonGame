package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Butterfly;

class ButterflyTests {
	
	@Test
	void butterflyConstructorReturnsButterfly() {
		assertTrue(new Butterfly() instanceof Butterfly);
	}

}
