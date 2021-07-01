package hearthclone.model.minion;

import java.util.List;

import hearthclone.constant.Const;
import hearthclone.model.BattleCry;
import hearthclone.model.Card;
import hearthclone.model.Minion;

//Complete
public class VarianWrynn extends AbstractMinion implements BattleCry {

    private static String name = "VarianWrynn";
    private static String description = "BattleCry: Draw 3 cards, summon any minion you drew";
    private static int baseCost = 10;
    private static int baseATK = 7;
    private static int baseHP = 7;

    public VarianWrynn() {
        super(VarianWrynn.name, VarianWrynn.description, VarianWrynn.baseCost, VarianWrynn.baseHP, VarianWrynn.baseATK);
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        for (int i = 0; i < 3; i++) {
            List<Card> deck = this.master.getDeck();
            if (deck.size() > 0) {
                Card newCard = deck.get(0);
                if (newCard instanceof Minion) {
                    if (this.master.getAlly().size() < Const.BOARD_SPACE + 1) {
                        this.master.summonAlly((Minion) deck.get(0), this.master.getAlly().size());
                        deck.remove(0);
                        this.master.getGame().cardDrew(this.master.getPlayerId(), false, true, newCard);
                        continue;
                    }
                }
            }
            this.master.drawCards();
        }

    }

}