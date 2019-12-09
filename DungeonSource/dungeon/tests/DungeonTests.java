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
		
		dungeon.createDungeon();
		
		Room[][] dungeonRooms = dungeon.getDungeonRooms();
		
		for(int i = 0; i < dungeonRooms.length; i++) {
			for(int j = 0; j < dungeonRooms[i].length; j++)
			{
				assertNotNull(dungeonRooms[i][j]);
			}
		}
	}

	@Test
	void testSetUpDungeon() {
		Dungeon dungeon = new Dungeon();
		
		dungeon.createDungeon();
		
		dungeon.setUpDungeon(new Warrior("Testy boi"));
		
		System.out.println(dungeon);
	}

	@Test
	void testToString() {
		Dungeon dungeon = new Dungeon();
		
		dungeon.createDungeon();
		assertNotNull(dungeon.toString());
	}

	@Test
	void testSaveDungeon() {
		Dungeon expectDungeon = new Dungeon();
		
		expectDungeon.createDungeon();
		expectDungeon.setUpDungeon(new Warrior("Testy boi"));
		
		Memento memento = expectDungeon.saveDungeon();
		
		Dungeon dungeon = new Dungeon();
		
		dungeon.loadDungeon(memento);
		
		Room[][] expectedRooms = expectDungeon.getDungeonRooms();
		Room[][] dungeonRooms = dungeon.getDungeonRooms();
		
		for(int i = 0; i < expectedRooms.length; i++)
		{
			for(int j = 0; j < expectedRooms[i].length; j++)
			{
				assertTrue(expectedRooms[i][j].equals(dungeonRooms[i][j]));
			}
		}
		
		assertTrue(expectDungeon.getHeroLocation().equals(dungeon.getHeroLocation()));
	}
	
	@Test
	void testLoadDungeon() {
		Dungeon dungeon = new Dungeon();
		
		dungeon.createDungeon();
		
		dungeon.setUpDungeon(new Warrior("Testy boi"));
		
		Memento memento = dungeon.saveDungeon();
		
		Dungeon expectedDungeon = new Dungeon();
		
		expectedDungeon.loadDungeon(memento);
		
		assertTrue(expectedDungeon.getDungeonRooms()[0][4].equals(dungeon.getDungeonRooms()[0][4]));
	}

}
