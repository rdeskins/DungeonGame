package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeon.Dungeon;
import dungeon.Memento;
import dungeon.Room;
import dungeon.Warrior;

class DungeonTests {

	@Test
	void testCreateDungeonSetsFields() {
		Dungeon dungeon = new Dungeon();
		

	}

	@Test
	void testSetUpDungeon() {
		Dungeon dungeon = new Dungeon();
		
		dungeon.setUpDungeon(new Warrior("Testy boi"));
		
		System.out.println(dungeon);
	}

	@Test
	void testToString() {
		Dungeon dungeon = new Dungeon();
		
		assertNotNull(dungeon.toString());
	}

	@Test
	void testSaveDungeon() {
		Dungeon expectDungeon = new Dungeon();
		
		expectDungeon.setUpDungeon(new Warrior("Testy boi"));
		
		Memento memento = expectDungeon.saveDungeon();
		
		Dungeon dungeon = new Dungeon();
		
		dungeon.loadDungeon(memento);
		
		assertTrue(expectDungeon.equals(dungeon));
	}
	
	@Test
	void testLoadDungeon() {
		Dungeon dungeon = new Dungeon();
		
		dungeon.setUpDungeon(new Warrior("Testy boi"));
		
		Memento memento = dungeon.saveDungeon();
		
		Dungeon expectedDungeon = new Dungeon();
		
		expectedDungeon.loadDungeon(memento);
		
		assertTrue(expectedDungeon.equals(dungeon));
	}

}
