package dungeon;

public class Dinosaur extends Hero{
	public Dinosaur(String name) {
		super("Dinosaur",  200, 2, .6, 50, 85, .6,name);
		
    	if(name == null)
    		throw new IllegalArgumentException("Dinosaur Constructor name string null");
		
		AttackFactory attacks = AttackFactory.getAttackFactory();
		
		AttackBehavior[] dinosaurAttacks = {attacks.getAttack("Dinosaur Attack"), 
				attacks.getAttack("Dinosaur Stomp Attack")};
		this.setAttackBehaviors(dinosaurAttacks);
		
		this.attackBehavior = this.attackBehaviors[0];
	}
	
	public void attack(DungeonCharacter opponent) {
		this.attackBehavior.attack(this, name, opponent);
	}
}
