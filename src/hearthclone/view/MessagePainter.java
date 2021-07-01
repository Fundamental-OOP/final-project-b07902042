package hearthclone.view;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;

import hearthclone.constant.Const;
import hearthclone.model.*;

public class MessagePainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        Graphics g = screenImg.getGraphics();
        g.setFont(new Font("Consolas", Font.PLAIN, Const.TURN_MESSAGE_FONT_SIZE));
        if(game.isMyTurn())
            g.drawString("Turn " + game.getTurn() + ": Your Turn", Const.MESSAGE_X, Const.MESSAGE_Y);       
        else
            g.drawString("Turn " + game.getTurn() + ": Opponent's Turn", Const.MESSAGE_X, Const.MESSAGE_Y);
        int state = game.getState();
        g.setFont(new Font("Consolas", Font.PLAIN, Const.MESSAGE_FONT_SIZE));
        if(state == Const.STATE_GAME_END){
            g.drawString("Game ends.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
            if(game.getWinner() == game.getPlayerId())
                g.drawString("YOU WIN!!!!", Const.MESSAGE_X, Const.MESSAGE_Y + 2*Const.MESSAGE_H);
            else if (game.getWinner() == game.getOpponentId())
                g.drawString("YOU LOSE...", Const.MESSAGE_X, Const.MESSAGE_Y + 2*Const.MESSAGE_H);
            else 
                g.drawString("DRAW!!!!", Const.MESSAGE_X, Const.MESSAGE_Y + 2*Const.MESSAGE_H);
        }
        if(!game.isMyTurn()) 
            return;       
        if(state == Const.STATE_PENDING){
            g.drawString("You can choose a card to play,", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
            g.drawString("OR select a minion to attack.", Const.MESSAGE_X, Const.MESSAGE_Y + 2*Const.MESSAGE_H);
            g.drawString("OR end your turn.", Const.MESSAGE_X, Const.MESSAGE_Y + 3*Const.MESSAGE_H);
        }
        else if(state == Const.STATE_VALID_CARD){
            g.drawString("This card can be played.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
            g.drawString("Click \"SELECT\" to play it.", Const.MESSAGE_X, Const.MESSAGE_Y + 2*Const.MESSAGE_H);
        }
        else if(state == Const.STATE_INVALID_CARD){
            g.drawString("This card cannot be played.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
        }
        else if(state == Const.STATE_VALID_ATTACKER){
            g.drawString("This minion can attack.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
            g.drawString("Click \"SELECT\" to choose a target.", Const.MESSAGE_X, Const.MESSAGE_Y + 2*Const.MESSAGE_H);
        }
        else if(state == Const.STATE_INVALID_ATTACKER){
            g.drawString("This minion cannot attack now.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
        }
        else if(state == Const.STATE_CARD_TARGETING){
            g.drawString("Choose a target.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
        }
        else if(state == Const.STATE_ATTACKER_TARGETING){
            g.drawString("Choose a target to be attacked.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
        }
        else if(state == Const.STATE_VALID_TARGET){
            g.drawString("This is a valid target.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
            g.drawString("Click \"SELECT\" to confirm.", Const.MESSAGE_X, Const.MESSAGE_Y + 2*Const.MESSAGE_H);
        }
        else if(state == Const.STATE_INVALID_TARGET){
            g.drawString("This is NOT a valid target.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
        }
        else if(state == Const.STATE_VALID_ATTACKED){
            g.drawString("This is a valid target.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
            g.drawString("Click \"SELECT\" to confirm.", Const.MESSAGE_X, Const.MESSAGE_Y + 2*Const.MESSAGE_H);
        }
        else if(state == Const.STATE_INVALID_ATTACKED){
            g.drawString("This target cannot be attacked.", Const.MESSAGE_X, Const.MESSAGE_Y + Const.MESSAGE_H);
            g.drawString("Choose another one.", Const.MESSAGE_X, Const.MESSAGE_Y + 2*Const.MESSAGE_H);
        }
    }
}