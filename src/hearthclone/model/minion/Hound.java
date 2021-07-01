package hearthclone.model.minion;

import hearthclone.model.Charge;

//Complete
public class Hound extends AbstractMinion implements Charge {

    private static String name = "Hound";
    private static String description = "Charge";
    private static int baseCost = 1;
    private static int baseATK = 1;
    private static int baseHP = 1;

    public Hound() {
        super(Hound.name, Hound.description, Hound.baseCost, Hound.baseHP, Hound.baseATK);
    }

}