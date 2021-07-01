package hearthclone.model.spell;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.Targeting;
import hearthclone.model.minion.Hero;

//Complete
public class Naturalize extends AbstractSpell implements Targeting {

    private static String name = "Naturalize";
    private static String description = "Destroy a minion. Your opponent draws 2 cards.";
    private static int baseCost = 1;

    public Naturalize() {
        super(Naturalize.name, Naturalize.description, Naturalize.baseCost);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (minion.canTargeted() && !(minion instanceof Hero))
                candidates.add(minion);
        }
        for (Minion minion : enemy) {
            if (minion.canTargeted() && !(minion instanceof Hero))
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        target.setAlive(false);
        for(int t = 0; t < 2;t++)
            user.getOpponent().drawCards();
    }

}