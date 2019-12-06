package dungeon;

public class Monk extends Hero{
	public Monk(String name) {
		super("Monk",  85, 7, .8, 30, 45, .4,name);
		AttackBehavior[] monkAttacks = {new MonkAttackBehavior(), new MonkPunchAttackBehavior()};
		this.setAttackBehaviors(monkAttacks);
	}
	
	public void attack(DungeonCharacter opponent) {
		this.attackBehavior.attack(this, name, opponent);
	}
}
