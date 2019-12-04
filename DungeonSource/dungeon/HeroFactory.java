package dungeon;

public class HeroFactory {
	public static Hero createHero(String type,String name)
	{
		Hero hero = null;
		if(type.equals("Sorceress"))
		{
			hero = new Sorceress(name);
		}
		else if(type.equals("Thief"))
		{
			hero = new Thief(name);
		}
		else if(type.equals("Warrior"))
		{
			hero = new Warrior(name);
		}
		else if(type.equals("Monk"))
		{
			hero = new Monk(name);
		}
		else if (type.equals("Dinosaur"))
		{
			hero = new Dinosaur(name);
		}
		else
		{
			throw new java.lang.IllegalArgumentException();
		}
		return hero;
	}
}
