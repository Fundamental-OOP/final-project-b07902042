package hearthclone.model.spell;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.Targeting;

//Complete
public class PyroBlast extends AbstractSpell implements Targeting {

    private static String name = "PyroBlast";
    private static String description = "Deal 10 damage";
    private static int baseCost = 10;

    public PyroBlast() {
        super(PyroBlast.name, PyroBlast.description, PyroBlast.baseCost);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (minion.canTargeted())
                candidates.add(minion);
        }
        for (Minion minion : enemy) {
            if (minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        target.setHP(target.getHP() - 10);
    }

}