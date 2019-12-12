package dungeon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class Room implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private boolean isEntrance;
	private boolean isExit;
	private Monster monster = null;
	private ArrayList<Item> items;
	
	
	public Room(int x, int y)
	{
		this.x = x; 
		this.y = y; 
		this.items = new ArrayList<Item>();
	}
	
	public String toString()
	{
		String room = "";

		//If the room is at the top of the array
		if(this.x == 0)
		{
			if(this.y == 0) {
				room = "* * *\n*   |\n* - *"; 
			}
			else if(this.y == 4) {
				room = "* * *\n|   *\n* - *";
			}
			else
			{
				room = "* * *\n|   |\n* - *";
			}
		}
		
		//If the room is at the bottom of the array
		else if(this.x == 4)
		{
			if(this.y == 0)
				room = "* - *\n*   |\n* * *";
			else if(this.y == 4)
				room = "* - *\n|   *\n* * *";
			else
				room = "* - *\n|   |\n* * *";
		}
		
		//If the room is on the left side of the array
		else if(this.y == 0)
		{
				 room = "* - *\n*   |\n* - *";
		}
		
		//If the room is on the right side of the array
		else if(this.y == 4)
		{
			room = "* - *\n|   *\n* - *"; 
		}
		
		//Interior Room
		else
		{
			room = "* - *\n|   |\n* - *";
		}
		
		//Add Character in center now
		String s;
		if (this.getNumItems() > 1)
			s = "M";
		else if (this.getItem() instanceof Pillar)
			s = "L";
		else if (this.getItem() instanceof Potion)
			s = "H";
		else if (this.getItem() instanceof Pit)
			s = "P";
		else if (this.getItem() instanceof VisionPotion)
			s = "V";
		else if (this.isEntrance())
			s = "I";
		else if (this.isExit())
			s = "O";
		else if (this.getMonster() != null)
			s = "X";
		else if (this.isEmpty())
			s = "E";
		else
			s = " ";
		
		
		return (room.substring(0,8) + s + room.substring(9));
	}
	
	public String stringTop()
	{
		String room = this.toString();
		return room.substring(0,5);
		
	}
	
	public String stringMid()
	{
		String room = this.toString();
		return room.substring(6,11);
	}
	
	public String stringBottom()
	{
		String room = this.toString();
		return room.substring(12);
	}
	
	public Item getItem() {
		if (!this.items.isEmpty())
			return items.get(0);
		
		return null;
	}
	
	public Item getItem(int x)
	{
		return items.get(x);
	}
	
	public boolean addItem(Item I)
	{
		if (this.isEntrance() || this.isExit())
			return false;
		else if (this.getItem() instanceof Pillar)
			return false;
		else if (this.getNumItems() > 0 && I instanceof Pillar)
			return false;
		
		Iterator <Item> itemIterator = items.iterator();
		while(itemIterator.hasNext())
		{
			Item nextItem = itemIterator.next();
			String nextType = nextItem.type;
			if(nextType.equals(I.type))
			{
				return false;
			}
		}
		
		items.add(I);
		return true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Monster getMonster() {
		return monster;
	}
	
	public void addMonster() {
		if (!isEntrance() && !isExit())
			this.monster = MonsterFactory.createMonster();
	}
	
	public int getNumItems()
	{
		return this.items.size();
	}
	
	public void emptyRoom()
	{
		this.items.clear();
		this.monster = null;
	}
	
	public boolean isEmpty()
	{
		if(items.size() == 0 && monster == null && !isEntrance && !isExit)
		{
			return true;
		}
			
		return false;
	}
	
	public void setExit() {
		this.isExit = true;
	}
	
	public void setEntrance() {
		this.isEntrance = true;
	}

	public boolean isExit() {
		return isExit;
	}

	public boolean isEntrance() {
		// TODO Auto-generated method stub
		return isEntrance;
	}
	
}
