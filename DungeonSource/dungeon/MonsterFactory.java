package dungeon;

public class MonsterFactory {
	public static Monster createMonster()
	{
		int random = (int)(Math.random() * 5) + 1;

		switch(random)
		{
			case 1: return new Ogre();

			case 2: return new Gremlin();

			case 3: return new Skeleton();
			
			case 4: return new Fobby();
			
			case 5: return new Butterfly();

			default: System.out.println("invalid choice, returning Skeleton");
				     return new Skeleton();
		}
	}
}

