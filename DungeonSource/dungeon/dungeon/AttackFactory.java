package dungeon;

import java.util.List;

public class AttackFactory {
	private List<AttackBehavior> pool;
	
	public AttackFactory() {
		pool.add(new BaseAttackBehavior());
		pool.add(new SorceressAttackBehavior());
		pool.add(new SorceressIncreaseHitpointsBehavior());
		pool.add(new ThiefSurpriseAttackBehavior());
		pool.add(new WarriorAttackBehavior());
		pool.add(new WarriorCrushingBlowBehavior());
	}
	
	public AttackBehavior getAttack(String name) {
		switch(name) {
		case "Warrior Crushing Blow":
			return pool.get(5);
		case "Warrior Attack":
			return pool.get(4);
		case "Sorceress Attack":
			return pool.get(1);
		case "Sorcereress Increase HP":
			return pool.get(2);
		case "Thief Surprise Attack":
			return pool.get(1);
		case "Base Attack":
			return pool.get(0);
		default:
			throw new IllegalArgumentException("Invalid string name in AttackFactory getAttack()");
		}
		
	}
}
