package dungeon;

import java.io.Serializable;

public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Room position;
	protected String type;
	
	public Item(Room position,String type)
	{
		this.position = position;
		this.type = type;
	}
}
