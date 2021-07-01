package hearthclone.model.minion;

import hearthclone.model.DeathRattle;
import hearthclone.model.spell.Coin;

//Complete
public class TombPillager extends AbstractMinion implements DeathRattle {

    private static String name = "TombPillager";
    private static String description = "DeathRattle: Add 1 Coin to your hand.";
    private static int baseCost = 4;
    private static int baseATK = 5;
    private static int baseHP = 4;

    public TombPillager() {
        super(TombPillager.name, TombPillager.description, TombPillager.baseCost, TombPillager.baseHP,
                TombPillager.baseATK);
    }

    @Override
    public void doDeathRattleEffect() {
        this.master.addHandCards(new Coin());
    }
}