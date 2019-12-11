package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Gremlin;

class GremlinTests {
	
	@Test
	void gremlinConstructorReturnsGremlin() {
		assertTrue(new Gremlin() instanceof Gremlin);
	}
	
}
