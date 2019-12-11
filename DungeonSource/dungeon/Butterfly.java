package dungeon;
//Written by Robin Deskins 12/4/19

public class Butterfly extends Monster{
	public Butterfly() {
		super("Steven the Butterfly",10,6,.95,.1,5,10,0,2);
	}
	
	public void attack(DungeonCharacter opponent) {
		System.out.println(name + " flutters at " +
				opponent.getName() + ":");
		this.attackBehavior.attack(this, this.getName(), opponent);
	}
}
