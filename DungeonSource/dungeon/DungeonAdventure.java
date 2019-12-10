package dungeon;
import java.util.Scanner;

/**
 * Title: Dungeon.java
 *
 * Description: Driver file for Heroes and Monsters project
 *
 * Copyright:    Copyright (c) 2001
 * Company: Code Dogs Inc.
 * I.M. Knurdy
 *
 * History:
 *  11/4/2001: Wrote program
 *    --created DungeonCharacter class
 *    --created Hero class
 *    --created Monster class
 *    --had Hero battle Monster
 *    --fixed attack quirks (dead monster can no longer attack)
 *    --made Hero and Monster abstract
 *    --created Warrior
 *    --created Ogre
 *    --made Warrior and Ogre battle
 *    --added battleChoices to Hero
 *    --added special skill to Warrior
 *    --made Warrior and Ogre battle
 *    --created Sorceress
 *    --created Thief
 *    --created Skeleton
 *    --created Gremlin
 *    --added game play features to Dungeon.java (this file)
 *  11/27/2001: Finished documenting program
 * version 1.0
 */



/*
  This class is the driver file for the Heroes and Monsters project.  It will
  do the following:

  1.  Allow the user to choose a hero
  2.  Randomly select a monster
  3.  Allow the hero to battle the monster

  Once a battle concludes, the user has the option of repeating the above

*/
public class DungeonAdventure
{
	static Scanner kb = new Scanner(System.in);

    public static void main(String[] args)
	{

		System.out.print("Welcome to the pillars of OO a dungeon adventure game!");
		do
		{
		    play();

		} while (playAgain());

    }//end main method

/*-------------------------------------------------------------------
chooseHero allows the user to select a hero, creates that hero, and
returns it.  It utilizes a polymorphic reference (Hero) to accomplish
this task
---------------------------------------------------------------------*/
    
	private static Hero chooseHero()
	{
		int choice = -1;

		
		System.out.println("Choose a hero:\n" +
					       "1. Warrior\n" +
						   "2. Sorceress\n" +
						   "3. Thief\n" +
						   "4. Monk\n" +
						   "5. Dinosaur\n");
		
		
		//While the choice is outside of the range of the possible attacks array
		while(choice < 1 || choice > 5)
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
		
		String name;
		System.out.print("Enter character name: ");
		name = kb.nextLine();
		switch(choice)
		{
			case 1: return HeroFactory.createHero("Warrior", name);

			case 2: return HeroFactory.createHero("Sorceress", name);

			case 3: return HeroFactory.createHero("Thief", name);
			
			case 4: return HeroFactory.createHero("Monk",name);
			
			case 5: return HeroFactory.createHero("Dinosaur", name);

			default: System.out.println("invalid choice, returning Thief");
			return HeroFactory.createHero("Thief", name);
		}//end switch
	}//end chooseHero method

/*-------------------------------------------------------------------
playAgain allows gets choice from user to play another game.  It returns
true if the user chooses to continue, false otherwise.
---------------------------------------------------------------------*/
	private static boolean playAgain()
	{
		String again;

		System.out.println("Play again (y/n)?");
		again = kb.next();
		
		//Updates playAgain() method to handle proper string input reading
		return (again.equals("Y") || again.equals("y"));
	}//end playAgain method


/*-------------------------------------------------------------------
battle is the actual combat portion of the game.  It requires a Hero
and a Monster to be passed in.  Battle occurs in rounds.  The Hero
goes first, then the Monster.  At the conclusion of each round, the
user has the option of quitting.
---------------------------------------------------------------------*/
	private static void battle(Hero theHero, Monster theMonster)
	{
		String pause = "p";
		System.out.println(theHero.getName() + " battles " +
							theMonster.getName());
		System.out.println("---------------------------------------------");

		//do battle
		while (theHero.isAlive() && theMonster.isAlive() && !pause.equals("q"))
		{
		    //hero goes first
			theHero.battleChoices(theMonster,kb);

			//monster's turn (provided it's still alive!)
			if (theMonster.isAlive())
			    theMonster.attack(theHero);

			//let the player bail out if desired
			System.out.print("\n-->q to quit, anything else to continue: ");
			pause = kb.next();

		}//end battle loop

		if (!theMonster.isAlive())
		    System.out.println(theHero.getName() + " was victorious!");
		else if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated :-(");
		else//both are alive so user quit the game
			System.out.println("Quitters never win ;-)");

	}//end battle method

	public static void play()
	{
		Hero theHero;
		theHero = chooseHero();
		
		Dungeon dungeon = new Dungeon();
		
		System.out.println("New game or load?");
		System.out.println("1: New game\n2. Load game");
		
		int choice = kb.nextInt();
		
		if(choice == 2) {
			DungeonAdventure.loadGame(dungeon);
		}
		else {
			dungeon.createDungeon();
			dungeon.setUpDungeon(theHero);
		}
		
	    System.out.println("the mighty " + theHero.name + " enters the dungeon " );
	    
	    System.out.println(dungeon.toString());
	    
	    boolean win = false;
	    while(theHero.isAlive() && !win)
	    {
	    	System.out.println(theHero.getPosition());
	    	if(theHero.getPosition().isEmpty())
	    	{
	    		System.out.print("theres nothing in this room ");
	    	}
	    	else
	    	{
	    		if(theHero.getPosition().getNumItems() > 0)
	    		{
	    			if(theHero.getPosition().getNumItems() > 1)
	    			{
	    				int numItems = theHero.getPosition().getNumItems();
	    				while(numItems > 0)
	    				{
	    					Item item = theHero.getPosition().getItem(numItems);
	    					getItem(item,theHero);
	    				}
	    			
	    			}	
	    			else
	    			{
	    				Item item = theHero.getPosition().getItem();
	    				getItem(item,theHero);
	    			}
	    			theHero.getPosition().emptyRoom();
	    		}
	    		else if(theHero.getPosition().getMonster() != null)
	    		{
	    			System.out.println("a monster jumps out at " + theHero.getName());
	    			battle(theHero,theHero.getPosition().getMonster()); 
	    			theHero.getPosition().emptyRoom();
	    		}
	    		else if (theHero.getPosition().isEntrance())
	    		{
	    			System.out.println("it's the entrance ");
	    		}
	    		else if(theHero.getPosition().isExit())
	    		{
	    			if(theHero.pillarsFound == 4)
	    			{
	    				System.out.println("congrats! you won! ");
	    				win = true;
	    			}
	    			else
	    			{
	    				System.out.println("its the exit, you have " + theHero.getPillarsFound() + " pillars, come back when you have found all 4");
	    			}
	    			
	    		}
	    	
	    	}
	    	kb.nextLine(); //flush buffer
	    	movement(theHero,dungeon);
	    }
	}
	private static void getItem(Item item, Hero theHero) {
		// TODO Auto-generated method stub
		
	}

	private static void movement(Hero theHero, Dungeon dungeon) {
		int x = theHero.getPosition().getX();
		int y = theHero.getPosition().getY();
		boolean success = false;
		while(!success)
		{
			System.out.println("enter a movement N,E,S or W"); 
			String movement = kb.nextLine();
			if(movement.equals("N")|| movement.equals("n"))
			{		
				y++;
				if(y < 4)
				{
					success = true;
				}
			}
			else if(movement.equals("E")|| movement.equals("e"))
			{
				x++;
				if(x < 4)
				{
					success = true;
				}
			}
			else if(movement.equals("S")|| movement.equals("s"))
			{
				y--;
				if(y> 0)
				{
					success = true; 
				}
			}
			else if(movement.equals("W")|| movement.equals("w"))
			{
				x--;
				if(x>0)
				{
					success = true;
				}
				
			}
			else
			{
				System.out.print("try again!");
				x = theHero.getPosition().getX();
				y = theHero.getPosition().getY();
				
			}
		}
		theHero.setPosition(dungeon.getRoom(x, y),dungeon);
	}

	private static void saveGame()
	{
		//to be added by memento
	}
	private static void loadGame(Dungeon dungeon)
	{
		Memento fileMemento = new FileMemento("DungeonState.txt");
		dungeon.loadDungeon(fileMemento);
	}
	
}//end Dungeon class