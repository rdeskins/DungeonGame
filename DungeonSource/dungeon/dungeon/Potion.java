package dungeon;

public class Potion extends Item {
	private int healHP;
	public Potion(Room position) {
		super(position, "Potion");
		// TODO Auto-generated constructor stub
	}
	
	public int heal()
	{
		return healHP;
	}
	
	private void calcHeal()
	{
		
	}

}
