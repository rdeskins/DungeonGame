package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Fobby;

class FobbyTests {
	
	@Test
	void fobbyConstructorReturnsFobby() {
		assertTrue(new Fobby() instanceof Fobby);
	}
}
