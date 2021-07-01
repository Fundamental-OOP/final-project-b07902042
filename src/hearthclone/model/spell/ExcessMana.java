package hearthclone.model.spell;

import hearthclone.model.Minion;
import hearthclone.model.Player;

//Complete
public class ExcessMana extends AbstractSpell {

    private static String name = "ExcessMana";
    private static String description = "Draw a card. (You can only have 10 Full Mana)";
    private static int baseCost = 0;

    public ExcessMana() {
        super(ExcessMana.name, ExcessMana.description, ExcessMana.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        user.drawCards();
    }

}