package dungeon;
import java.util.Scanner;

/**
 * Title: Hero.java
 *
 * Description: Abstract base class for a hierarchy of heroes.  It is derived
 *  from DungeonCharacter.  A Hero has battle choices: regular attack and a
 *  special skill which is defined by the classes derived from Hero.
 *
 *  class variables (all are directly accessible from derived classes):
 *    chanceToBlock -- a hero has a chance to block an opponents attack
 *    numOfAttacks -- if a hero is faster than opponent, their is a possibility
 *                for more than one attack per round of battle
 *
 *  class methods (all are public):
 *    public Hero(String name, int hitPoints, int attackSpeed,
				     double chanceToHit, int damageMin, int damageMax,
					 double chanceToBlock)
	  public void readName()
	  public boolean defend()
	  public void subtractHitPoints(int hitPoints)
	  public void battleChoices(DungeonCharacter opponent)

 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */


public abstract class Hero extends DungeonCharacter
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double chanceToBlock;
	protected int numOfAttacks;
	protected int healPotionsFound, visionPotionsFound, pillarsFound;



//-----------------------------------------------------------------
//calls base constructor and gets name of hero from user
  public Hero(String name, int hitPoints, int attackSpeed,
				     double chanceToHit, int damageMin, int damageMax,
					 double chanceToBlock, String displayName)
  {
	super(displayName, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax);
	this.chanceToBlock = chanceToBlock;
  }
  
  //Robin Deskins Note: This is for testing purposes to bypass the RNG of blocking/not blocking
  public void setChanceToBlock(int blockChance) {
	  this.chanceToBlock = blockChance;
  }
  
  //Doug Doner Note: Added getters and setters for numOfAttacks
  public int getnumOfAttacks() { return this.numOfAttacks; }
  public void setnumOfAttacks(final int numOfAttacks) { this.numOfAttacks = numOfAttacks; }
  
  public int getHealPotionsFound() {
	  return this.healPotionsFound;
  }
  
  public int getVisionPotionsFound() {
	  return this.visionPotionsFound;
  }
  
  public int getPillarsFound() {
	  return this.pillarsFound;
  }

/*-------------------------------------------------------
readName obtains a name for the hero from the user

Receives: nothing
Returns: nothing
This method calls: nothing
This method is called by: hero constructor
---------------------------------------------------------*/

/*-------------------------------------------------------
defend determines if hero blocks attack

Receives: nothing
Returns: true if attack is blocked, false otherwise

This method calls: Math.random()
This method is called by: subtractHitPoints()
---------------------------------------------------------*/
  public boolean defend()
  {
		return Math.random() <= chanceToBlock;

  }//end defend method

/*-------------------------------------------------------
subtractHitPoints checks to see if hero blocked attack, if so a message
is displayed, otherwise base version of this method is invoked to
perform the subtraction operation.  This method overrides the method
inherited from DungeonCharacter promoting polymorphic behavior

Receives: hit points to subtract
Returns: nothing

This method calls: defend() or base version of method
This method is called by: attack() from base class
---------------------------------------------------------*/
public void subtractHitPoints(int hitPoints)
	{
		if (defend())
		{
			System.out.println(name + " BLOCKED the attack!");
		}
		else
		{
			super.subtractHitPoints(hitPoints);
		}


	}//end method

/*-------------------------------------------------------
battleChoices will be overridden in derived classes.  It computes the
number of turns a hero will get per round based on the opponent that is
being fought.  The number of turns is reported to the user.  This stuff might
go better in another method that is invoked from this one...

Receives: opponent
Returns: nothing

This method calls: getAttackSpeed()
This method is called by: external sources
---------------------------------------------------------*/
	public void battleChoices(DungeonCharacter opponent, Scanner kb)
	{
	    numOfAttacks = attackSpeed/opponent.getAttackSpeed();
	    
		if (numOfAttacks == 0)
			numOfAttacks++;

		System.out.println("Number of turns this round is: " + numOfAttacks);
		
		do
		{
		    int choice = -1;
			
			//Iterates through available attacks for user to choose from
			for(int i = 0; i < attackBehaviors.length; i++)
				System.out.println(i + 1 + ". " + attackBehaviors[i]);

			
			//While the choice is outside of the range of the possible attacks array
			while(choice < 0 || choice > attackBehaviors.length)
			{
				try
				{
					System.out.print("Enter your choice: ");
					choice = Integer.parseInt(kb.next());
					kb.nextLine();
				}
				catch(Exception e)
				{
					System.out.println("invalid choice!");
				}
			}

			//Sets attackBehavior to array index that user chose
			this.attackBehavior = attackBehaviors[choice - 1];

			this.attack(opponent);

			this.numOfAttacks--;
			if (numOfAttacks > 0)
				System.out.println("Number of turns remaining is: " + numOfAttacks);

		} while(numOfAttacks > 0 && opponent.isAlive());


	}//end battleChoices
	
	@Override
	public String toString() {
		return "Name: " + getName()
				+ "\nHit Points: " + getHitPoints()
				+ "\nTotal Healing Potions: " + getHealPotionsFound()
				+ "\nTotal Vision Potions: " + getVisionPotionsFound()
				+ "\nTotal Pillars of OO Found: " + getPillarsFound();
	}

	public void setPosition(Room room, Dungeon dungeon) {
	
		this.position = room;
		dungeon.updateHeroLocation(room);
	}
}//end Hero class