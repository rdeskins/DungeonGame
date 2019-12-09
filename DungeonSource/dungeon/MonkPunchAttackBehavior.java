package dungeon;

import java.io.Serializable;

public class MonkPunchAttackBehavior implements AttackBehavior, Serializable{
	@Override
    public void attack(DungeonCharacter hero, String displayName, DungeonCharacter opponent) {
        if (Math.random() <= .6)
        {
            int blowPoints1 = (int)(Math.random() * 10) + 25;
            int blowPoints2 = (int)(Math.random() * 10) + 25;
            System.out.println(displayName + " punches once for " + blowPoints1
                    + " damage and punches again for " + blowPoints2 + " damage!");
            opponent.subtractHitPoints(blowPoints1+blowPoints2);
        }//punch succeeded
        else
        {
            System.out.println(displayName + " failed to land double punch attack");
            System.out.println();
        }//punch failed
    }

    @Override
    public String toString()
    {
        return "Double Punch Attack on Opponent";
    }
}
