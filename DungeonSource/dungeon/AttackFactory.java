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
		pool.add(new MonkAttackBehavior());
		pool.add(new MonkPunchAttackBehavior());
		pool.add(new DinosaurAttackBehavior());
		pool.add(new DinosaurStompAttackBehavior());
		pool.add(new MockAttackBehavior());
	}
	
	public AttackBehavior getAttack(String name) {
		switch(name) {
		case "Dinosaur Stomp Attack":
			return pool.get(9);
		case "Dinosaur Attack":
			return pool.get(8);
		case "Monk Punch Attack":
			return pool.get(7);
		case "Monk Attack":
			return pool.get(6);
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
			return pool.get(10); 
		}
		
	}
}
