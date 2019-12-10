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
		roomLeftSide = rooms[0][1];
		roomRightSide = rooms[4][2];
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
				   + "* - *", roomUpperLeft.toString());
		
		assertEquals("* - *\n"
				   + "| E *\n"
				   + "* * *", roomBottomRight.toString());
		
		assertEquals("* - *\n"
				   + "* E |\n"
				   + "* - *", roomLeftSide.toString());
		
		assertEquals("* - *\n"
				   + "| E *\n"
				   + "* - *", roomRightSide.toString());

		assertEquals("* - *\n"
				   + "| E |\n"
				   + "* - *", roomCenter.toString());
		
		//Test different items, all use a center room
		roomCenter.addItem(new Pit(roomCenter));
		assertEquals("* - *\n"
				   + "| P |\n"
				   + "* - *", roomCenter.toString());
		roomCenter.emptyRoom();
		
		roomCenter.addItem(new Potion(roomCenter));
		assertEquals("* - *\n"
				   + "| H |\n"
				   + "* - *", roomCenter.toString());
		roomCenter.emptyRoom();
		
		roomCenter.addItem(new Pillar(roomCenter));
		assertEquals("* - *\n"
				   + "| L |\n"
				   + "* - *", roomCenter.toString());
		roomCenter.emptyRoom();
		
		roomCenter.addItem(new Potion(roomCenter));
		roomCenter.addItem(new Pit(roomCenter));
		assertEquals("* - *\n"
				   + "| M |\n"
				   + "* - *", roomCenter.toString());
		roomCenter.emptyRoom();
		
		//Test Monsters
		roomCenter.addMonster();
		assertEquals("* - *\n"
				   + "| X |\n"
				   + "* - *", roomCenter.toString());
		roomCenter.emptyRoom();
		
		//Test Entrance and Exits
		roomCenter.setEntrance();
		assertEquals("* - *\n"
				   + "| I |\n"
				   + "* - *", roomCenter.toString());
		
		roomUpperLeft.setExit();
		assertEquals("* * *\n"
				   + "* O |\n"
				   + "* - *", roomUpperLeft.toString());
		
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

}
