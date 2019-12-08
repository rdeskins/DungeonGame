package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dungeon.*;

class MonsterTests {
	Monster canHeal, cantHeal;
	
	@BeforeEach
	void setUp() {
		canHeal = new MockMonster();
		cantHeal = new MockMonster();
		cantHeal.setChanceToHeal(0);
	}
	
	@Test
	void testHeal() {
		//Mock Monsters can only heal for 1 hitpoint
		canHeal.heal();
		assertEquals(101,canHeal.getHitPoints());
		cantHeal.heal();
		assertEquals(100,cantHeal.getHitPoints());
	}
	
	@Test
	void testSubtractHitPoints() {
		canHeal.subtractHitPoints(10);
		assertEquals(91,canHeal.getHitPoints()); //100hp - 10hp + 1hp = 91hp
		cantHeal.subtractHitPoints(10);
		assertEquals(90,cantHeal.getHitPoints()); //100hp - 10hp = 90hp
	}

}
