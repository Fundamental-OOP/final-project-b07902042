package hearthclone.model.minion;

import hearthclone.model.DivineShield;
import hearthclone.model.Taunt;

//Complete
public class AnnoyRobot extends AbstractMinion implements DivineShield, Taunt {

    private static String name = "AnnoyRobot";
    private static String description = "DivineShield & Taunt";
    private static int baseCost = 2;
    private static int baseATK = 1;
    private static int baseHP = 2;
    private boolean divineShield = true;

    public AnnoyRobot() {
        super(AnnoyRobot.name, AnnoyRobot.description, AnnoyRobot.baseCost, AnnoyRobot.baseHP, AnnoyRobot.baseATK);
    }

    @Override
    public void setHP(int HP) {
        // Heal
        if (this.HP < HP) {
            System.out.printf("%s +%d HP.\n", name, HP - this.HP);
            this.HP = Math.min(HP, this.buffHP);
        }
        // damage
        else if (this.HP > HP) {
            if (this.divineShield) {
                System.out.printf("%s deny damage by DivineShield.\n", name);
                this.divineShield = false;
            } else {
                System.out.printf("%s -%d HP.\n", name, this.HP - HP);
                this.HP = HP;
            }
        }
        this.minionChange();
    }

    @Override
    public boolean hasDivineShield() {
        return this.divineShield;
    }
}