package dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dungeon.*;

class RoomTests {
	Room[][] rooms = new Room[5][5];
	Room roomUpperLeft;
	Room roomBottomRight;
	Room roomLeftSide;
	Room roomRightSide;
	Room roomCenter;
	
	@BeforeEach
	void setUp() {
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				rooms[i][j] = new Room(i,j);
			}
		}
		
		roomUpperLeft = rooms[0][0];
		roomBottomRight = rooms[4][4];
		roomLeftSide = rooms[1][0];
		roomRightSide = rooms[1][4];
		roomCenter = rooms[2][2];
	}
	
	@Test
	void testToString() {
		/**Test basic representation of doors with empty rooms
		 * Example of expected output for roomCenter would be
		 * * - *
		 * | E |
		 * * - * 
		*/
		assertEquals("* * *\n"
				   + "* E |\n"
				   + "* - *", roomUpperLeft);
		
		assertEquals("* - *\n"
				   + "| E *\n"
				   + "* * *", roomBottomRight);
		
		assertEquals("* - *\n"
				   + "* E |\n"
				   + "* - *", roomLeftSide);
		
		assertEquals("* - *\n"
				   + "| E *\n"
				   + "* - *", roomRightSide);

		assertEquals("* - *\n"
				   + "| E |\n"
				   + "* - *", roomCenter);
		
		//Test different items, all use a center room
		roomCenter.addItem(new Pit(roomCenter));
		assertEquals("* - *\n"
				   + "| P |\n"
				   + "* - *", roomCenter);
		roomCenter.emptyRoom();
		
		roomCenter.addItem(new Potion(roomCenter));
		assertEquals("* - *\n"
				   + "| H |\n"
				   + "* - *", roomCenter);
		roomCenter.emptyRoom();
		
		roomCenter.addItem(new Pillar(roomCenter));
		assertEquals("* - *\n"
				   + "| L |\n"
				   + "* - *", roomCenter);
		roomCenter.emptyRoom();
		
		roomCenter.addItem(new Potion(roomCenter));
		roomCenter.addItem(new Pit(roomCenter));
		assertEquals("* - *\n"
				   + "| M |\n"
				   + "* - *", roomCenter);
		roomCenter.emptyRoom();
		
		//Test Monsters
		roomCenter.addMonster();
		assertEquals("* - *\n"
				   + "| X |\n"
				   + "* - *", roomCenter);
		roomCenter.emptyRoom();
		
		//Test Entrance and Exits
		roomCenter.setEntrance();
		assertEquals("* - *\n"
				   + "| I |\n"
				   + "* - *", roomCenter);
		
		roomUpperLeft.setExit();
		assertEquals("* * *\n"
				   + "* O |\n"
				   + "* - *", roomUpperLeft);
		
	}
	
	@Test
	void testStringTop() {
				assertEquals("* * *", roomUpperLeft.stringTop());
				
				assertEquals("* - *", roomBottomRight.stringTop());
				
				assertEquals("* - *", roomLeftSide.stringTop());
				
				assertEquals("* - *", roomRightSide.stringTop());

				assertEquals("* - *", roomCenter.stringTop());
	}
	
	@Test
	void testStringMid() {
		//Test basic representation of doors with empty rooms
				assertEquals("* E |", roomUpperLeft.stringMid());
				
				assertEquals("| E *", roomBottomRight.stringMid());
				
				assertEquals("* E |", roomLeftSide.stringMid());
				
				assertEquals("| E *", roomRightSide.stringMid());

				assertEquals("| E |", roomCenter.stringMid());
				
				//Test different items, all use a center room
				roomCenter.addItem(new Pit(roomCenter));
				assertEquals("| P |", roomCenter.stringMid());
				roomCenter.emptyRoom();
				
				roomCenter.addItem(new Potion(roomCenter));
				assertEquals("| H |", roomCenter.stringMid());
				roomCenter.emptyRoom();
				
				roomCenter.addItem(new Pillar(roomCenter));
				assertEquals("| L |", roomCenter.stringMid());
				roomCenter.emptyRoom();
				
				roomCenter.addItem(new Potion(roomCenter));
				roomCenter.addItem(new Pit(roomCenter));
				assertEquals("| M |", roomCenter.stringMid());
				roomCenter.emptyRoom();
				
				//Test Monsters
				roomCenter.addMonster();
				assertEquals("| X |", roomCenter.stringMid());
				roomCenter.emptyRoom();
				
				//Test Entrance and Exits
				roomCenter.setEntrance();
				assertEquals("| I |", roomCenter.stringMid());
				
				roomUpperLeft.setExit();
				assertEquals("* O |", roomUpperLeft.stringMid());
	}
	
	@Test
	void testStringBottom() {
		assertEquals("* - *", roomUpperLeft.stringBottom());
		
		assertEquals("* * *", roomBottomRight.stringBottom());
		
		assertEquals("* - *", roomLeftSide.stringBottom());
		
		assertEquals("* - *", roomRightSide.stringBottom());

		assertEquals("* - *", roomCenter.stringBottom());
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(roomCenter.isEmpty());
		
		roomCenter.addItem(new Potion(roomCenter));
		assertFalse(roomCenter.isEmpty());
		roomCenter.emptyRoom();
		
		roomCenter.addMonster();
		assertFalse(roomCenter.isEmpty());		
	}
	
	@Test
	void testEmptyRoom() {
		roomCenter.addItem(new Potion(roomCenter));
		roomCenter.addMonster();
		roomCenter.emptyRoom();
		
		assertTrue(roomCenter.getNumItems() == 0);
		assertTrue(roomCenter.getMonster() == null);
	}
	
	@Test
	void testEquals() {
		Room[][] rooms2 = new Room[5][5];
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				rooms[i][j] = new Room(i,j);
			}
		}
		Room roomCenter2 = rooms2[2][2];
		
		assertTrue(roomCenter.equals(roomCenter2));
		
		roomCenter2.addItem(new Potion(roomCenter2));
		roomCenter.addItem(new Potion(roomCenter));
		assertTrue(roomCenter.equals(roomCenter2));
		
		roomCenter2.addMonster();
		assertFalse(roomCenter.equals(roomCenter2));
		
		assertFalse(roomCenter.equals(roomUpperLeft));
	}
}
