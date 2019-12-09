package dungeon;

import java.util.Random;

public class Dungeon {
	private Room [][] dungeonRooms;
	private int potions; 
	private int pits;
	private int monsters;
	private Room heroLocation;
	private String state;
	
	
	public Dungeon()
	{

		this.dungeonRooms = new Room[5][5];
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				dungeonRooms[j][i] = new Room(j,i);
			}
		}
		Random RAND = new Random(); 
		int x = RAND.nextInt(4);
		int y = RAND.nextInt(4);
		this.heroLocation = new Room(x,y);
		dungeonRooms[x][y] = heroLocation;
		
	}
	public void setUpDungeon(Hero hero)
	{
		heroLocation.setEntrance();
		hero.setPosition(this.heroLocation);
		int x,y,i = 0;
		Random RAND = new Random(); 
		Room newRoom;
		boolean success = false;
		while(!success)
		{
			x = RAND.nextInt(4);
			y = RAND.nextInt(4);
			newRoom = new Room(x,y);
			if(!(heroLocation.getX() == x) && !((heroLocation.getY() == y)))
				{
					setExit(newRoom);
					dungeonRooms[x][y] = newRoom;
					success = true;
				}
			
		}
		i =0;
		//add pillars
		while(i < 4)
		{
			x = RAND.nextInt(5);
			y = RAND.nextInt(5);
			newRoom = new Room(x,y);
			addPillars(newRoom);
			if(dungeonRooms[x][y].isEmpty())
			{
				dungeonRooms[x][y] = newRoom;
				i++;
			}
			
		}
		i=0;
		//add monsters
		while(i < 3)
		{
			x = RAND.nextInt(5);
			y = RAND.nextInt(5);
			newRoom = new Room(x,y);
			if(dungeonRooms[x][y].isEmpty())
			{
				addMonsters(newRoom);
				dungeonRooms[x][y] = newRoom;
				i++;
			}
			
		}
		i=0;
		//add items
		while(i < 4)
		{
			x = RAND.nextInt(5);
			y = RAND.nextInt(5);
			newRoom = new Room(x,y);
			addItems(newRoom);
			if(dungeonRooms[x][y].isEmpty())
			{
				dungeonRooms[x][y] = newRoom;
				i++;
			}
		}
		
		
		
	}

	public  String toString()
	{
		String dungeon = "";
		int i =0;
		int j =0;
		while(i < 5)
		{
			j =0;
			while(j < 5)
			{
				dungeon += dungeonRooms[j][i].StringTop();
				j++;
			}
			dungeon += "\n";
			j =0;
			while(j < 5)
			{
				dungeon += dungeonRooms[j][i].stringMid();
				j++;
			}
			dungeon += "\n";
			j =0;
			while(j < 5)
			{
				dungeon += dungeonRooms[j][i].stringBottom();
				j++;
			}
			dungeon += "\n";
			i++;
			
		}
		return dungeon;
		
	}
	private void setExit(Room r)
	{
		r.setExit();
	}
	private boolean addPillars(Room r)
	{
		return r.addItem(new Item(r,"pillar"));
	}
	private boolean addMonsters(Room r)
	{
		if(r.getMonster() == null)
		{
			r.addMonster();
			return true;
		}
		return false;
	}
	private boolean addItems(Room r)
	{
		if(!(r.addItem(new Item(r,"potion"))))
		{
			if(!(r.addItem(new Item(r,"pit"))))
			{
				return false;
			}
		}
		return true;
		
	}
	public Memento saveDungeon()
	{
		return null;
		
	}
	public Memento loadDungeon()
	{
		return null;
		
	}
}
