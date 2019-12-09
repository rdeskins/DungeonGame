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
	private ArrayList<Item> items = new ArrayList<Item>();
	
	
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
				
			}
			else if(this.y == 0)
			{
				room = "* * * \n" + this.stringMid() +"\n* -   ";
			}
			else
			{
				room = "* -   \n"  + this.stringMid() +   "\n* -   ";
			}
			
		}
		
		else if(this.y == 0)
		{
			if(this.x == 4)
			{
				room = "* * * \n" + this.stringMid() + "\n  - * "; 
			}
			else
			{
				room = "* * * \n" + this.stringMid() + "\n  -   ";
			}
		}
		else if(this.y == 4)
		{
			if(this.x == 4)
			{
				 room = "  - * \n"  + this.stringMid() + "*\n* * * ";
			}
			else
			{
				 room = "  -   \n"  + this.stringMid() + "\n* * * ";
			}
		}
		else if(this.x == 4)
		{
			room = "  - * \n" + this.stringMid() + "*\n  - * ";
		}
		else
		{
			room = "  -   \n" + this.stringMid() + "\n  -   ";
		}
		
		
		return room;
	}
	
	public String StringTop()
	{
		String room = "" ;
		
		if(x ==  0)
		{
		    if(y == 0)
			{
				room = "* * *";
			}
			else
			{
				room = "* -";
			}
			
		}
		
		else if(y == 0)
		{
			room = " * *";
		}
		else if(x == 4)
		{
			room = "   - *";
		}
		else
		{
			room = "   -";
		}
		
		
		return room;
		
	}
	
	public String stringMid()
	{
        String room = "";
		
		if(x ==  0)
		{
			room = "*   |";
			
		}
		
	
		else if(x == 4)
		{
			room = "   *";
		}
		else
		{
			room = "   |";
		}
		
		if(this.isEntrance)
		{
			if(x == 0)
			{
				room = room.substring(0, 1) + " I" + room.substring(3,room.length());
			}
			else
			{
				room = room.substring(0, 1) + "I " + room.substring(3,room.length());
			}
		}
		
		else if(this.isExit)
		{
			if(x == 0)
			{
				room = room.substring(0, 1) + " O" + room.substring(3,room.length());
			}
			else
			{
				room = room.substring(0, 1) + "O " + room.substring(3,room.length());
			}
		}
		
		else if(this.monster != null)
		{
			if(x == 0)
			{
				room = room.substring(0, 1) + " X" + room.substring(3,room.length());
			}
			else
			{
				room = room.substring(0, 1) + "X " + room.substring(3,room.length());
			}
		}
		
		else if(this.isEmpty())
		{
			if(x == 0)
			{
				room = room.substring(0, 1) + " E" + room.substring(3,room.length());
			}
			else
			{
				room = room.substring(0, 1) + "E " + room.substring(3,room.length());
			}
		}
		
		else if(this.getnumItems() != 0 && this.getItem().type.equals("potion"))
		{
				if(x == 0)
				{
					room = room.substring(0, 1) + " P" + room.substring(3,room.length());
				}
				else
				{
					room = room.substring(0, 1) + "P " + room.substring(3,room.length());
				}
		}
		
		else if(this.getnumItems() != 0 && this.getItem().type.equals("pit"))
		{
				if(x == 0)
				{
					room = room.substring(0, 1) + " P" + room.substring(3,room.length());
				}
				else
				{
					room = room.substring(0, 1) + "P " + room.substring(3,room.length());
				}
		}
		
		else if(this.getnumItems() != 0 && this.getItem().type.equals("pillar"))
		{
				if(x == 0)
				{
					room = room.substring(0, 1) + " l" + room.substring(3,room.length());
				}
				else
				{
					room = room.substring(0, 1) + "l " + room.substring(3,room.length());
				}
		}
		
		
		return room;
	}
	
	public String stringBottom()
	{
		String room = "";
		if(y == 4)
		{
			if(x == 0)
			{
				room = "* * *";
			}
			else
			{
				room = " * *";
			}
		}
		
		else if(x ==  0)
		{
				room = "* -  ";
			
		}
		
	
		else if(x == 4)
		{
			room = " - *";
		}
		else
		{
			room = " -  ";
		}
		
		
		return room;
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
			if(itemIterator.next().type.equals(I.type) ||itemIterator.next().type.equals("pillar"))
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
	public int getnumItems()
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
	
}
