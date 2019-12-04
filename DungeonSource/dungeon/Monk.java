package dungeon;
//Written by Robin Deskins 12/4/19

public class Monk extends Hero{
	public Monk(String name) {
		super("Monk",  1, 0, .0, 0, 0, .0,name);
		AttackBehavior[] monkAttacks = {new MockAttackBehavior()};
		this.setAttackBehaviors(monkAttacks);
	}
	
	public void attack(DungeonCharacter opponent) {
		this.attackBehavior.attack(this, name, opponent);
	}
}
