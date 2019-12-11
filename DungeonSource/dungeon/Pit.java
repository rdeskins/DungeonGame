package dungeon;

import java.util.Random;

public class Pit extends Item{
	
	public Pit(Room position) {
		super(position,"Pit");
	}
	
	public int damage()
	{
		Random rand = new Random();
		return ((rand.nextInt(20))+1);
	}

}
