package dungeon;
//Written by Robin Deskins 12/4/19

public class Dinosaur extends Hero{
	public Dinosaur(String name) {
		super("Dinosaur",  1, 0, .0, 0, 0, .0,name);
		AttackBehavior[] dinosaurAttacks = {new MockAttackBehavior()};
		this.setAttackBehaviors(dinosaurAttacks);
	}
	
	public void attack(DungeonCharacter opponent) {
		this.attackBehavior.attack(this, name, opponent);
	}
}
