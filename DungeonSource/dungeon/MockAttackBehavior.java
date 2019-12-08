package dungeon;

public class MockAttackBehavior implements AttackBehavior{
	private BaseAttackBehavior baseAttack = new BaseAttackBehavior();
	
	@Override
	public void attack(DungeonCharacter hero, String displayName, DungeonCharacter opponent) {
		System.out.println(displayName + " does some MOCK ATTACK at " +
				opponent.getName() + ":");
		
		//Call to BaseAttackBehavior
		this.baseAttack.attack(hero, displayName, opponent);
	}
	
	@Override
	public String toString() {
		return "Mock Attack opponent";
	}

}
