package hearthclone.model.minion;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.BattleCry;
import hearthclone.model.Minion;
import hearthclone.model.Targeting;

//Complete
public class UldmanKeeper extends AbstractMinion implements BattleCry, Targeting {

    private static String name = "UldmanKeeper";
    private static String description = "BattleCry: Set a minion's ATK and HP to 3";
    private static int baseCost = 4;
    private static int baseATK = 3;
    private static int baseHP = 4;

    public UldmanKeeper() {
        super(UldmanKeeper.name, UldmanKeeper.description, UldmanKeeper.baseCost, UldmanKeeper.baseHP,
                UldmanKeeper.baseATK);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        for (Minion minion : enemy) {
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        if(target == null) return;
        System.out.printf("%s BattleCry!\n", name);
        target.setATK(3);
        target.setBuffHP(3);
        target.reWriteHP(3);
    }

}