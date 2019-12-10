package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Hero;
import dungeon.MockHero;
import dungeon.Monster;
import dungeon.Skeleton;

class SkeletonTests {

	@Test
	void skeletonAttackDamagesOpponent() {
		Monster skeletonTest = new Skeleton();
		
		Hero mockHero = new MockHero("Testy boi");
		
		int hitPoints = mockHero.getHitPoints();
		
		skeletonTest.attack(mockHero);
		
		assertNotEquals(hitPoints, mockHero.getHitPoints());
	}
	
	@Test
	void skeletonConstructorReturnsSkeleton() {
		assertTrue(new Skeleton() instanceof Skeleton);
	}
}
