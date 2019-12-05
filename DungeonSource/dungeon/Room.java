package dungeon;

import java.util.ArrayList;

public class Room {
	private int x;
	private int y;
	private boolean isEntrance;
	private boolean isExit;
	private Monster monster;
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
	public void addItem(Item I)
	{
		items.add(I);
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
		
	}
	public int getnumItems()
	{
		return this.items.size();
	}
	public void emptyRoom()
	{
		
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
	
}
