package dungeon;

public class MockHero extends Hero{
	public final int MIN_ADD = 100;
	public final int MAX_ADD = 100;
	
	public MockHero(String name) {
		super("Mock Hero",  1000, 10, 1, 100, 100, 0,name);
		
		AttackFactory attacks = AttackFactory.getAttackFactory();
		
		AttackBehavior[] mockAttacks = {attacks.getAttack("Dinosaur Stomp Attack"),
				attacks.getAttack("Dinosaur Attack"),
				attacks.getAttack("Monk Punch Attack"),
				attacks.getAttack("Monk Attack"),
				attacks.getAttack("Warrior Crushing Blow"),
				attacks.getAttack("Warrior Attack"),
				attacks.getAttack("Sorceress Attack"),
				attacks.getAttack("Sorcereress Increase HP"),
				attacks.getAttack("Thief Surprise Attack"),
				attacks.getAttack("Mock Attack")};
		this.setAttackBehaviors(mockAttacks);
	}
	
	public void attack(DungeonCharacter opponent) {
		this.attackBehavior.attack(this, name, opponent);
	}
}
