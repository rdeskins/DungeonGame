package dungeon;

public class Monk extends Hero{
	public Monk(String name) {
		super("Monk",  85, 7, .8, 30, 45, .4,name);
		
		AttackFactory attacks = AttackFactory.getAttackFactory();
		
		AttackBehavior[] monkAttacks = {attacks.getAttack("Monk Attack"), 
				attacks.getAttack("Monk Punch Attack")};
		this.setAttackBehaviors(monkAttacks);
	}
	
	public void attack(DungeonCharacter opponent) {
		this.attackBehavior.attack(this, name, opponent);
	}
}
