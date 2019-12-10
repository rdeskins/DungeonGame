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
	
	@Test
	void testToString() { //Could use more tests
		assertEquals("Name: name\n"
				+ "Hit Points: 1000\n"
				+ "Total Healing Potions: 0\n"
				+ "Total Vision Potions: 0\n"
				+ "Total Pillars of OO Found: 0",testNoBlock.toString());
	}
	
	//No Test for battlechoices as of now, unsure how to test for it

	
	@Test
	void heroSaveHeroSavesState() {
		Hero expectedHero = new Warrior("Doug");
		
		Memento fileMemento = expectedHero.saveHero();
		
		Hero loadHero = new MockHero("Test");
		
		Hero loadedHero = loadHero.loadHero(fileMemento);
		
		assertEquals(expectedHero.getName(), loadedHero.getName());
		
		assertTrue(loadedHero instanceof Warrior);
	}
}
