package dungeon;

public class Item {
	protected Room position;
	protected String type;
	
	public Item(Room position,String type)
	{
		this.position = position;
		this.type = type;
	}
}
