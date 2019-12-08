package dungeon;

public class DinosaurStompAttackBehavior implements AttackBehavior{
	@Override
    public void attack(DungeonCharacter hero, String displayName, DungeonCharacter opponent) {
        if (Math.random() <= .2)
        {
            int blowPoints = opponent.getHitPoints();
            System.out.println(displayName + " stomps for " + blowPoints
                    + " damage, instantly killing its enemy!");
            opponent.subtractHitPoints(blowPoints);
        }//stomp succeeded
        else
        {
            System.out.println(displayName + " failed to stomp on the enemy");
            System.out.println();
        }//stomp failed
    }

    @Override
    public String toString()
    {
        return "Stomp on Opponent";
    }
}
