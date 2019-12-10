package dungeon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


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
		String room = null;
		
		if(this.x ==  0)
		{
			if(this.y == 4)
			{
				room = "* - *\n*   |\n* * *";
			}
			else if(this.y == 0)
			{
				room = "* * *\n*   |\n* - *";
			}
			else
			{
				room = "* - *\n*   |\n* - *";
			}
			
		}
		
		else if(this.y == 0)
		{
			if(this.x == 4)
			{
				room = "* * *\n|   *\n* - *"; 
			}
			else
			{
				room = "* * *\n|   |\n* - *";
			}
		}
		else if(this.y == 4)
		{
			if(this.x == 4)
			{
				 room = "* - *\n|   *\n* * *";
			}
			else
			{
				 room = "* - *\n|   |\n* * *";
			}
		}
		else if(this.x == 4)
		{
			room = "* - *\n|   *\n* - *";
		}
		
		else
		{
			room = "* - *\n|   |\n* - *";
		}
		
		
		return room;
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
		return items.get(0);
	}
	
	public Item getItem(int x)
	{
		return items.get(x);
	}
	
	public boolean addItem(Item I)
	{
		Iterator <Item> itemIterator = items.iterator();
		while(itemIterator.hasNext())
		{
			if(itemIterator.next().type.equals(I.type))
				{
					return false;
				}
		};
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
		if(items.size() == 0 && (monster == null) && !isEntrance && !isExit)
		{
			return true;
		}
			
		return false;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Room)
		{
			Room PI = (Room)o;
			if(PI.getX() == this.x && PI.getY() == this.y)
			{
				return true;
			}
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
