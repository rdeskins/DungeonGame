package dungeon;

import java.util.List;
import java.util.ArrayList;

public class AttackFactory {
	private List<AttackBehavior> pool;
	private static AttackFactory instance;
	
	private AttackFactory() {
		this.pool = new ArrayList<AttackBehavior>();
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
		
		instance = this;
	}
	
	public static AttackFactory getAttackFactory() {
		if (instance != null) {
			return instance;
		}
		
		return new AttackFactory();
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
		case "Sorceress Increase HP":
			return pool.get(2);
		case "Thief Surprise Attack":
			return pool.get(3);
		case "Base Attack":
			return pool.get(0);
		default:
			return pool.get(10); 
		}
		
	}
}
