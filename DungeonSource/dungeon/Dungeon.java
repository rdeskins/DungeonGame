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
				dungeonRooms[i][j] = new Room(i,j);
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
		Random RAND = new Random(); 
		
		//Setting entrance to hero's current location
		hero.setPosition(this.heroLocation);
		this.heroLocation.setEntrance();
		
		//Setting exit
		Room exitRoom;
		int x = RAND.nextInt(4);
		int y = RAND.nextInt(4);
		boolean success = false;
		while(!success)
		{
			x = RAND.nextInt(4);
			y = RAND.nextInt(4);
			exitRoom = new Room(x,y);
			if(!(heroLocation.getX() == x) && !((heroLocation.getY() == y)))
				{
					setExit(exitRoom);
					dungeonRooms[x][y] = exitRoom;
					success = true;
				}
			
		}
		
		//Adding 4 pillars
		int i =0;
		while(i < 4)
		{
			x = RAND.nextInt(5);
			y = RAND.nextInt(5);
			if(dungeonRooms[x][y].isEmpty())
			{
				if (addPillars(dungeonRooms[x][y]));
					i++;
			}
			
		}
		i=0;
		
		//Adding monsters
		for (x = 0; x < 5; x++) {
			for (y = 0; y < 5; y++) {
				int rng = RAND.nextInt(10);
				if (rng <= 1) { //20% chance to spawn monster
					addMonsters(dungeonRooms[x][y]);
				}
			}
		}
		
		//Adding items
		for (x = 0; x < 5; x++) {
			for (y= 0; y < 5; y++) {
				int rng = RAND.nextInt(10);
				if (rng == 0){ //10% chance of Potion
					Potion potion = new Potion(dungeonRooms[x][y]);
					dungeonRooms[x][y].addItem(potion);
				}
				
				rng = RAND.nextInt(10);
				if (rng == 0) { //10% chance of Pit independent of Potion
					Pit pit = new Pit(dungeonRooms[x][y]);
					dungeonRooms[x][y].addItem(pit);
				}
				
				rng = RAND.nextInt(10);
				if (rng == 0) { //10% chance of Pit independent of Potion
					VisionPotion vPotion = new VisionPotion(dungeonRooms[x][y]);
					dungeonRooms[x][y].addItem(vPotion);
				}
			}
		}
	
	}

	public  String toString()
	{
		String dungeon = "";
		
		int i = 0;
		int j = 0;
		
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
		return r.addItem(new Pillar(r));
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
		if(!(r.addItem(new Potion(r))))
		{
			if(!(r.addItem(new Pit(r))))
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
		return dungeonRooms[x][y];
	}

	public void updateHeroLocation(Room room) {
		this.heroLocation = room;
	}
	
	public String surroundingRoomsToString(Room r) {
		//This is to prevent from printing outside of the Room array
		int startX, endX, startY, endY;
		if (r.getX() == 0)
			startX = 0;
		else
			startX = r.getX() -1;
		if (r.getX() == 4)
			endX = 4;
		else
			endX = r.getX() +1;
		
		if (r.getY() == 0)
			startY = 0;
		else
			startY = r.getY() -1;
		if (r.getY() == 4)
			endY = 4;
		else
			endY = r.getY() +1;
		
		//System.out.println(startX +""+ endX+ "" +startY + "" +endY);
		//Now print rooms from Room[startX][startY] to Room[endX][endY]
		String dungeon = "";
		
		int i = startX;
		int j = startY;
		
		while(i < endX+1)
		{
			j =startY;
			while(j < endY+1)
			{
				dungeon += dungeonRooms[i][j].stringTop();
				j++;
			}
			dungeon += "\n";
			j =startY;
			while(j < endY+1)
			{
				dungeon += dungeonRooms[i][j].stringMid();
				j++;
			}
			dungeon += "\n";
			j =startY;
			while(j < endY+1)
			{
				dungeon += dungeonRooms[i][j].stringBottom();
				j++;
			}
			dungeon += "\n";
			i++;
			
		}
		return dungeon;
	}
}
