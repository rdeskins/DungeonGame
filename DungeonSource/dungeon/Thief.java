package dungeon;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Thief extends Hero
{

    public Thief(String name)
	{
		super("Thief", 75, 6, .8, 20, 40, .5,name);
		
    	if(name == null)
    		throw new IllegalArgumentException("Thief Constructor name string null");

		AttackFactory attacks = AttackFactory.getAttackFactory();
		
		AttackBehavior[] thiefAttacks = {attacks.getAttack("Base Attack"), 
				attacks.getAttack("Thief Surprise Attack")};
		this.setAttackBehaviors(thiefAttacks);
		
		this.attackBehavior = this.attackBehaviors[0];
    }//end constructor

	public void attack(DungeonCharacter opponent)
	{
		
		//This attack is set at runtime when user chooses which attack to use
		this.attackBehavior.attack(this, name, opponent);
	}//end override of attack method
}