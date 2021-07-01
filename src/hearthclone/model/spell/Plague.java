package hearthclone.model.spell;

import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.minion.Scarab;

//Complete
public class Plague extends AbstractSpell {

    private static String name = "Plague";
    private static String description = "Summon a 1/5 Scarab with Taunt until you have as many minion as your opponent";
    private static int baseCost = 6;

    public Plague() {
        super(Plague.name, Plague.description, Plague.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        user.summonAlly(new Scarab(), user.getAlly().size());
        int num = user.getAlly().size() - user.getEnemy().size();
        if (num >= 0)
            return;
        for (int i = 0; i < (-num); i++)
            user.summonAlly(new Scarab(), user.getAlly().size());
    }

}