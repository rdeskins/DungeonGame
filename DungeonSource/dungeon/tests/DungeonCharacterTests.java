package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dungeon.*;

class DungeonCharacterTests {
	DungeonCharacter test;
	private final ByteArrayOutputStream os = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@BeforeEach
	public void setUp() {
		test = new MockHero("name"); //Has starting HP of 1000
	}
	
	@BeforeEach
	public void setUpStreams() {
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
	}
	
	@After
	public void revertStreams() {
		System.setOut(originalOut);
	}
	
	@Test //Remove later, this is proof of concept for testing printing
	public void testPrinting() {
		System.out.print("Hello");
		assertEquals("Hello", os.toString());
		os.reset();
		
		System.out.println("Hello");
		assertEquals("Hello" + System.getProperty("line.separator"), os.toString());
		os.reset();
	}	
	
	@Test
	public void testAddHitPoints() {
		assertEquals(1000,test.getHitPoints());
		test.addHitPoints(-10);
		assertEquals("Hitpoint amount must be positive." + System.getProperty("line.separator"),os.toString());
		assertEquals(1000, test.getHitPoints()); //HP should not change
		
		test.addHitPoints(10);
		assertEquals(1010, test.getHitPoints());
	}
	
	@Test
	public void testSubtractHitPoints() {
		assertEquals(1000,test.getHitPoints());
		test.subtractHitPoints(-10);
		assertEquals("Hitpoint amount must be positive." + System.getProperty("line.separator"),os.toString());
		assertEquals(1000,test.getHitPoints());
		os.reset();
		
		test.subtractHitPoints(10);
		assertEquals("name hit for <10> points damage." + System.getProperty("line.separator")
					+ "name now has 990 hit points remaining." + System.getProperty("line.separator")
					+ System.getProperty("line.separator"), os.toString());
		assertEquals(990,test.getHitPoints());
		os.reset();
		
		test.subtractHitPoints(990);
		assertEquals("name hit for <990> points damage." + System.getProperty("line.separator")
					+ "name now has 0 hit points remaining." + System.getProperty("line.separator")
					+ System.getProperty("line.separator")
					+ "name has been killed :-(" + System.getProperty("line.separator"), os.toString());
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
	
	/**@Test
	public void testAttack() {
		Monster skeleton = new Skeleton();
		test.attack(skeleton);
	} NOTE: not sure how to test this, cannot choose attack from DungeonCharacter */
}
