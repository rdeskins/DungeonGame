package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dungeon.*;

class DungeonCharacterTests {
	DungeonCharacter test;
	
	@BeforeEach
	public void setUp() {
		test = new MockHero("name"); //Has starting HP of 1000
	}
	
	@Test
	public void testAddHitPoints() {
		assertEquals(1000,test.getHitPoints());
		test.addHitPoints(-10);
		assertEquals(1000, test.getHitPoints()); //HP should not change
		
		test.addHitPoints(10);
		assertEquals(1010, test.getHitPoints());
	}
	
	@Test
	public void testSubtractHitPoints() {
		assertEquals(1000,test.getHitPoints());
		test.subtractHitPoints(-10);
		assertEquals(1000,test.getHitPoints());
		
		test.subtractHitPoints(10);
		assertEquals(990,test.getHitPoints());
		
		test.subtractHitPoints(990);
		assertEquals(0,test.getHitPoints());
	}
	
	@Test
	public void testIsAlive() {
		assertTrue(test.isAlive());
		test.subtractHitPoints(1000);
		assertFalse(test.isAlive());
		test.subtractHitPoints(10);
		assertFalse(test.isAlive());
	}
	
	@Test
	public void testAttack() {
		DungeonCharacter mockMonster = new MockMonster();
		
		assertEquals(1000,test.getHitPoints());
		mockMonster.attack(test); //Attack should do 10 damage exactly
		assertEquals(990,test.getHitPoints());
	}
}
