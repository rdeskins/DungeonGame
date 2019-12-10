package dungeon;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */




public class Warrior extends Hero
{

    public Warrior(String name)
	{
    	
		super("Warrior", 125, 4, .8, 35, 60, .2,name );
		
    	if(name == null)
    		throw new IllegalArgumentException("Warrior Constructor name string null");
		
		//This is an array containing all possible attacks for a warrior
		AttackFactory attacks = AttackFactory.getAttackFactory();
		
		AttackBehavior[] warriorAttacks = {attacks.getAttack("Warrior Attack"), 
				attacks.getAttack("Warrior Crushing Blow")};
		
		//This sets the attackBehaviors array in the parent DungeonCharacter class
		this.setAttackBehaviors(warriorAttacks);
		
		this.attackBehavior = this.attackBehaviors[0];
    }//end constructor

	public void attack(DungeonCharacter opponent)
	{
		
		//This attack is set at runtime when user chooses which attack to use
		this.attackBehavior.attack(this, name, opponent);
	}//end override of attack method

}//end Warrior class