package dungeon;
//Written by Robin Deskins 12/4/19

public class Fobby extends Monster{
	public Fobby() {
		super("Bob the Fobby", 60, 3, .5,.2, 20,40,10,30);
		this.healBehavior = new MonsterBasicHealBehavior();
		
		AttackFactory attacks = AttackFactory.getAttackFactory();
		this.attackBehavior = attacks.getAttack("Base Attack");
	}
	
	public void attack (DungeonCharacter opponent) {
		System.out.println(name + " bashes " +
				opponent.getName() + ":");
		this.attackBehavior.attack(this, this.getName(), opponent);
	}

}
