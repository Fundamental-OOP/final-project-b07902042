package hearthclone.model.spell;

import hearthclone.model.Minion;
import hearthclone.model.Player;

//Complete
public class HolyLight extends AbstractSpell {

    private static String name = "HolyLight";
    private static String description = "Restore 8 HP to your hero";
    private static int baseCost = 2;

    public HolyLight() {
        super(HolyLight.name, HolyLight.description, HolyLight.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        user.getHero().setHP(user.getHero().getHP() + 8);
    }

}