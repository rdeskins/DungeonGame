package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import dungeon.Dungeon;
import dungeon.Memento;
import dungeon.Room;
import dungeon.Warrior;
import dungeon.Pillar;

class DungeonTests {

	@Test
	void testCreateDungeonSetsFields() {
		Dungeon dungeon = new Dungeon();
		dungeon.createDungeon();
		Room[][] rooms = dungeon.getDungeonRooms();

		assertTrue(rooms.length == 5);
		assertTrue(rooms[0].length == 5);
	}

	@Test
	void testSetUpDungeon() {
		Dungeon dungeon = new Dungeon();
		dungeon.createDungeon();
		dungeon.setUpDungeon(new Warrior("Testy boi"));
		
		Room[][] rooms = dungeon.getDungeonRooms();
		int totalPillars = 0;
		int totalEntrance = 0;
		int totalExit = 0;
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				Room currentRoom = rooms[x][y];
				if (currentRoom.getItem() instanceof Pillar)
					totalPillars++;
				else if (currentRoom.isEntrance())
					totalEntrance++;
				else if (currentRoom.isExit())
					totalExit++;
			}
		}
		
		assertTrue(totalPillars == 4);
		assertTrue(totalEntrance == 1);
		assertTrue(totalExit == 1);
	}

	@Test
	void testToString() {
		Dungeon dungeon = new Dungeon();
		dungeon.createDungeon();
		
		assertEquals(dungeon.toString(),
				 "* * ** * ** * ** * ** * *\n"
				+"* E || E || E || E || E *\n"
				+"* - ** - ** - ** - ** - *\n"
				+"* - ** - ** - ** - ** - *\n"
				+"* E || E || E || E || E *\n"
				+"* - ** - ** - ** - ** - *\n"
				+"* - ** - ** - ** - ** - *\n"
				+"* E || E || E || E || E *\n"
				+"* - ** - ** - ** - ** - *\n"
				+"* - ** - ** - ** - ** - *\n"
				+"* E || E || E || E || E *\n"
				+"* - ** - ** - ** - ** - *\n"
				+"* - ** - ** - ** - ** - *\n"
				+"* E || E || E || E || E *\n"
				+"* * ** * ** * ** * ** * *\n");
		//No further tests because of RNG of item placement.
	}

	@Test
	void testSaveDungeon() {
		Dungeon expectDungeon = new Dungeon();
		
		expectDungeon.createDungeon();
		expectDungeon.setUpDungeon(new Warrior("Testy boi"));
		
		Memento memento = expectDungeon.saveDungeon();
		
		Dungeon dungeon = new Dungeon();
		
		dungeon.loadDungeon(memento);
		
		assertTrue(expectDungeon.toString().equals(dungeon.toString()));
	}
	
	@Test
	void testLoadDungeon() {
		Dungeon dungeon = new Dungeon();
		
		dungeon.createDungeon();
		
		dungeon.setUpDungeon(new Warrior("Testy boi"));
		
		Memento memento = dungeon.saveDungeon();
		
		Dungeon expectedDungeon = new Dungeon();
		
		expectedDungeon.loadDungeon(memento);
		
		assertTrue(expectedDungeon.toString().equals(dungeon.toString()));
	}

}
