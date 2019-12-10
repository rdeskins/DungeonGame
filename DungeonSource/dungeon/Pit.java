package dungeon;

public class Pit extends Item{
	private int subtractHP;
	public Pit(Room position) {
		super(position,"Pit");
	}
	
	public int damage()
	{
		return subtractHP;
	}
	
	private void calcDamage()
	{
		
	}

}
