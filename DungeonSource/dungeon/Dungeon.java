package dungeon;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Dungeon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Room [][] dungeonRooms;
	private int potions; 
	private int pits;
	private int monsters;
	private Room heroLocation;
	
	
	public Room[][] getDungeonRooms() {
		return this.dungeonRooms;
	}
	
	public Room getHeroLocation() {
		return this.heroLocation;
	}
	
	public void createDungeon()
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
		
		Room entrance = new Room(this.heroLocation.getX(),this.heroLocation.getY());
		entrance.setEntrance();
		hero.setPosition(this.heroLocation, this);
		Room newRoom;
		Random RAND = new Random(); 
		int x = RAND.nextInt(4);
		int y = RAND.nextInt(4);
		int i = 0;
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
				dungeon += dungeonRooms[i][j].stringTop();
				j++;
			}
			dungeon += "\n";
			j =0;
			while(j < 5)
			{
				dungeon += dungeonRooms[i][j].stringMid();
				j++;
			}
			dungeon += "\n";
			j =0;
			while(j < 5)
			{
				dungeon += dungeonRooms[i][j].stringBottom();
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
		Memento fileMemento = new FileMemento("DungeonState.txt");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);   
			  out.writeObject(this);
			  out.flush();
			  byte[] yourBytes = bos.toByteArray();
			fileMemento.setState(yourBytes);
		}
		catch(IOException ex) {
			System.out.println("IO Exception Memento.saveDungeon: " + ex.getMessage());
		}
		return fileMemento;

		
	}
	public void loadDungeon(Memento memento)
	{
		byte[] dungeonStateArray = memento.getSavedState();
		
		
		ByteArrayInputStream bis = new ByteArrayInputStream(dungeonStateArray);
		
		try {
			ObjectInputStream ois = new ObjectInputStream(bis);
			Dungeon dungeonObject = (Dungeon)ois.readObject();
			
			this.dungeonRooms = dungeonObject.dungeonRooms;
			this.heroLocation = dungeonObject.heroLocation;
			
			this.monsters = dungeonObject.monsters;
			
			this.pits = dungeonObject.pits;
			
			this.potions = dungeonObject.potions;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public Room getRoom(int x, int y) {
		return dungeonRooms[y][x];
	}

	public void updateHeroLocation(Room room) {
		this.heroLocation = room;
	}
}
