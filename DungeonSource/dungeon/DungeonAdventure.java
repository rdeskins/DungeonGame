package dungeon;
import java.io.File;
import java.io.FileNotFoundException;
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
	private static Hero loadedHero;
	
	static Scanner kb = new Scanner(System.in);

    public static void main(String[] args)
	{

    	try
    	{
    		File dungeonTutorialReader = new File("dungeonGameExplanation.txt");
    		Scanner dungeonTutorialScanner = new Scanner(dungeonTutorialReader);
    		
    		while(dungeonTutorialScanner.hasNext())
    			System.out.println(dungeonTutorialScanner.nextLine());
    		
    		System.out.println();
    	}
    	catch(FileNotFoundException ex)
    	{
    		System.out.println("DungeonAdventureMain tutorial file not found");
    	}
		
		do
		{
		    play();
		} while (playAgain());

    }//end main method

	public static void play()
	{
		
		
		Hero theHero = new MockHero("Initial hero");
		Dungeon dungeon = new Dungeon();
		
		
		if(saveFilesExist()) {
			System.out.println("New game or load?");
			System.out.println("1: New game\n2. Load game");
		}
		else
		{
			System.out.println("New game?");
			System.out.println("1: New game");
		}
		int choice = Integer.parseInt(kb.nextLine());
	    
	    if(choice == 2 && saveFilesExist()) {
			DungeonAdventure.loadGame(dungeon, theHero);
			theHero = loadedHero;
		}
		else {
			theHero = chooseHero();
			dungeon.createDungeon();
			dungeon.setUpDungeon(theHero);
		}
		
	    System.out.println("the mighty " + theHero.name + " enters the dungeon " );
	    
	    boolean win = false;
	    while(theHero.isAlive() && !win)
	    {
	    	System.out.println(theHero.getPosition());
	    	if(theHero.getPosition().isEmpty())
	    	{
	    		System.out.println("theres nothing in this room ");
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
	    			System.out.println(theHero.getPosition());
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
	    	if(!win && theHero.isAlive())
	    		movement(theHero,dungeon);
	    }
	    
	System.out.println(dungeon);
	}
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
		System.out.flush();
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
		System.out.println(theHero.getName() + " battles " +
							theMonster.getName());
		System.out.println("---------------------------------------------");

		//do battle
		while (theHero.isAlive() && theMonster.isAlive())
		{
		    //hero goes first
			theHero.battleChoices(theMonster,kb);

			//monster's turn (provided it's still alive!)
			if (theMonster.isAlive())
			    theMonster.attack(theHero);

		}//end battle loop

		if (!theMonster.isAlive())
		    System.out.println(theHero.getName() + " was victorious!");
		else if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated :-(");
		
		System.out.flush();

	}//end battle method

	private static void getItem(Item item, Hero theHero) {
		if(item instanceof Pillar)
		{
			System.out.println("the hero found a pillar of OO! " + theHero.getName() + " has found " + ++theHero.pillarsFound + " pillars so far");
		}
		else if(item instanceof Pit)
		{
			Pit pitItem = (Pit)item;
			int damageDone = pitItem.damage();
			
			//This is to stop the hero from blocking the attack from the pit
			double blockChance = theHero.getChanceToBlock();
			theHero.setChanceToBlock(0);
			theHero.subtractHitPoints(damageDone);
			theHero.setChanceToBlock(blockChance);
			
			System.out.println("The hero fell into a pit! " + theHero.getName() + " takes " + damageDone + " damage.");
		}
		else if(item instanceof Potion)
		{
			Potion potionItem = (Potion)item;
			int healingDone = potionItem.heal();
			theHero.addHitPoints(healingDone);
			System.out.println("The hero found a healing potion! " + theHero.getName() + " Hitpoints increased by " + healingDone + ".");
			theHero.healPotionsFound++;
		}
	}

	private static void movement(Hero theHero, Dungeon dungeon) {
		int x = theHero.getPosition().getX();
		int y = theHero.getPosition().getY();
		boolean success = false;
		do {
			x = theHero.getPosition().getX();
			y = theHero.getPosition().getY();
			System.out.println("enter a movement N, E, S, or W"); 
			System.out.println("Or \"r\" to save the game");
			String movement = kb.nextLine();
			if(movement.equals("N")|| movement.equals("n")) {	
				if(x - 1 >= 0) {
					x--;
					success = true;
				}

			}
			else if(movement.equals("E")|| movement.equals("e")) {
				if(y + 1 < dungeon.getDungeonRooms().length) {
					y++;
					success = true;
				}
			}
			else if(movement.equals("S")|| movement.equals("s")) {
				if(x + 1 < dungeon.getDungeonRooms().length) {
					x++;
					success = true;
				}
			}
			else if(movement.equals("W")|| movement.equals("w")) {
				if(y - 1 >= 0) {
					y--;
					success = true;
				}
			}
			//Secret command for displaying whole dungeon
			else if(movement.toLowerCase().equals("x"))
			{
				System.out.println(dungeon);
			}
			//Secret command for displaying whole dungeon
			else if(movement.toLowerCase().equals("r"))
			{
				System.out.println("Saving game");
				saveGame(dungeon, theHero);
				System.out.println("Game saved!");
			}
			else
			{
				System.out.println("try again!");
			}
		}while(!success);
		
		theHero.setPosition(dungeon.getRoom(x, y));
		dungeon.updateHeroLocation(dungeon.getRoom(x, y));
	}

	private static void saveGame(Dungeon dungeon, Hero hero)
	{
		Memento dungeonFileMemento = dungeon.saveDungeon();
		Memento heroFileMemento = hero.saveHero();
	}
	private static void loadGame(Dungeon dungeon, Hero hero)
	{
		Memento dungeonFileMemento = new FileMemento("DungeonState.txt");
		Memento heroFileMemento = new FileMemento("HeroState.txt");
		
		dungeon.loadDungeon(dungeonFileMemento);
		loadedHero = hero.loadHero(heroFileMemento);
		
		dungeon.updateHeroLocation(loadedHero.getPosition());

	}
	
	private static boolean saveFilesExist() {
		File dungeonFile = new File("DungeonState.txt");
		File heroFile = new File("HeroState.txt");
		
		return (dungeonFile.exists() && heroFile.exists());
	}
	
}//end Dungeon class