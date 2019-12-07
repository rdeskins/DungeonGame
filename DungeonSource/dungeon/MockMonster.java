package dungeon;

public class MockMonster extends Monster{
	
	public MockMonster()
	{
		super("Mockie the Mock", 100, 5, 1, 1, 10, 10, 1, 1);
		this.healBehavior = new MonsterBasicHealBehavior();
		
		AttackFactory attacks = AttackFactory.getAttackFactory();
		this.attackBehavior = attacks.getAttack("Mock Attack");

    }//end constructor
}
