package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import dungeon.*;
import org.junit.jupiter.api.Test;

class HeroTests {
	Hero testBlock, testNoBlock;
	
	@BeforeEach
	void setUp() {
		testBlock = new MockHero("name");
		testBlock.setChanceToBlock(1); //100% chance to block
		testNoBlock = new MockHero("name");
	}
	
	@Test
	void testDefend() {
		assertTrue(testBlock.defend());
		assertFalse(testNoBlock.defend());
	}
	
	@Test
	void testSubtractHitPoints() {
		testBlock.subtractHitPoints(10);
		assertEquals(1000,testBlock.getHitPoints());
		testNoBlock.subtractHitPoints(10);
		assertEquals(990,testNoBlock.getHitPoints());
	}
	
	//No Test for battlechoices as of now, unsure how to test for it

}
