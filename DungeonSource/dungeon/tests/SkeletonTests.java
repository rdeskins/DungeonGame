package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Skeleton;

class SkeletonTests {
	
	@Test
	void skeletonConstructorReturnsSkeleton() {
		assertTrue(new Skeleton() instanceof Skeleton);
	}
}
