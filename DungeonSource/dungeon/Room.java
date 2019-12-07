package dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Room {
	private int x;
	private int y;
	private boolean isEntrance;
	private boolean isExit;
	private Monster monster = null;
	private ArrayList<Item> items;
	private int[] doors;
	public Room(int x, int y)
	{
		this.x = x; 
		this.y = y; 
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
		}
		items.add(I);
		return true;
	}
	public String toString()
	{
		return null;
		
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
		if(items.size() == 0 && monster.equals(null) && !isEntrance && !isExit)
		{
			return true;
		}
			
		return false;
	}
	public boolean equals(Object o)
	{
		return true;
	}
	public boolean hasNDoor()
	{
		return true;
	}
	
	public boolean hasSDoor() 
	{
		return true;
	}
	public boolean hasEDoor()
	{
		return true;
	}
	public boolean hasWDoor()
	{
		return true;
	}
	public void setExit() {
		this.isExit = true;
	}
	public void setEntrance() {
		this.isEntrance = true;
	}
	
}
