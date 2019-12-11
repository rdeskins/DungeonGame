package dungeon;

import java.util.Random;

public class Potion extends Item {
	public Potion(Room position) {
		super(position, "Potion");
		// TODO Auto-generated constructor stub
	}
	
	public int heal()
	{
		Random rand = new Random();
		return ((rand.nextInt(10))+5);
	}
}
