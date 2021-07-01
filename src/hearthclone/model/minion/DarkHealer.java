package hearthclone.model.minion;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.BattleCry;
import hearthclone.model.Minion;

//Complete
public class DarkHealer extends AbstractMinion implements BattleCry {

    private static String name = "DarkHealer";
    private static String description = "BattleCry: Restore 2 HP to all friendly characters";
    private static int baseCost = 5;
    private static int baseATK = 4;
    private static int baseHP = 5;

    public DarkHealer() {
        super(DarkHealer.name, DarkHealer.description, DarkHealer.baseCost, DarkHealer.baseHP, DarkHealer.baseATK);
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        List<Minion> targets = new ArrayList<Minion>();
        for (Minion minion : this.master.getAlly())
            targets.add(minion);

        targets.remove(this);
        for (Minion minion : targets) {
            minion.setHP(minion.getHP() + 2);
        }
    }

}