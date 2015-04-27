/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_DesktopGUI;

import _3_2Game.Card;
import _3_2Game.Game;
import _3_2Game.Player;
import _3_2Game.UI_Updater;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Leo
 */
public class GUIUpdater implements UI_Updater {
    Game game;
    JTextArea msgViewer;
    GameBoardGUI gmGUI;

    public GUIUpdater(JTextArea msgViewer, GameBoardGUI gmGUI) {
        this.msgViewer = msgViewer;
        this.gmGUI = gmGUI;
    }
    
    public void set_game(Game game){
        this.game = game;
    }
    
    @Override
    public void P1_DrawMD(Card card) {
        msgViewer.append("P1_DrawMD:"+card+"\n");
        gmGUI.P1.insert(card);
    }

    @Override
    public void P2_DrawMD() {
        msgViewer.append("P2_DrawMD:"+"\n");
        gmGUI.P2.makeAllVisible();
    }

    @Override
    public void P3_DrawMD() {
        msgViewer.append("P3_DrawMD:"+"\n");
        gmGUI.P3.makeAllVisible();
    }

    @Override
    public void P4_DrawMD() {
        msgViewer.append("P4_DrawMD:"+"\n");
        gmGUI.P4.makeAllVisible();
    }

    @Override
    public void P1_DrawDD(Card DrawnCard, Card visibleCard) {
        msgViewer.append("P1_DrawDD:"+DrawnCard+","+visibleCard+"\n");
        try {
            gmGUI.DicardedDeck.setCard(visibleCard);
        } catch (IOException ex) {
            Logger.getLogger(GameBoardGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        gmGUI.P1.insert(DrawnCard);
    }

    @Override
    public void P2_DrawDD(Card visibleCard) {
        msgViewer.append("P2_DrawDD:"+visibleCard+"\n");
        gmGUI.P2.makeAllVisible();
        try {
            gmGUI.DicardedDeck.setCard(visibleCard);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void P3_DrawDD(Card visibleCard) {
        msgViewer.append("P3_DrawDD:"+visibleCard+"\n");
        gmGUI.P3.makeAllVisible();
        try {
            gmGUI.DicardedDeck.setCard(visibleCard);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void P4_DrawDD(Card visibleCard) {
        msgViewer.append("P4_DrawDD:"+visibleCard+"\n");
        gmGUI.P4.makeAllVisible();
        try {
            gmGUI.DicardedDeck.setCard(visibleCard);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void P1_DiscardDD(Card card) {
        msgViewer.append("P1_DiscardDD:"+card+"\n");
        //msgViewer.append("P1 cards:"+game.getPlayers().get(0).getCards());
        this.gmGUI.P1.remove(card);
                    try {
                        this.gmGUI.DicardedDeck.setCard(card);
                    } catch (IOException ex) {
                        Logger.getLogger(GameBoardGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @Override
    public void P2_DiscardDD(Card card) {
        msgViewer.append("P2_DiscardDD:"+card+"\n");
        gmGUI.P2.makeFirstInvisible();
        try {
            gmGUI.DicardedDeck.setCard(card);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void P3_DiscardDD(Card card) {
        msgViewer.append("P3_DiscardDD:"+card.toString()+"\n");
        gmGUI.P3.makeFirstInvisible();
        try {
            gmGUI.DicardedDeck.setCard(card);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void P4_DiscardDD(Card card) {
        msgViewer.append("P4_DiscardDD:"+card.toString()+"\n");
        gmGUI.P4.makeFirstInvisible();
        try {
            gmGUI.DicardedDeck.setCard(card);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void shuffle(ArrayList<Card> P1_cards, Card cardOnDD) {
        msgViewer.append("Shuffled:"+P1_cards.toString()+"\n");
        try {
            gmGUI.P1.shuffled(P1_cards);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        gmGUI.P2.shuffled();
        gmGUI.P3.shuffled();
        gmGUI.P4.shuffled();
    }

    @Override
    public void display(String msg) {
        msgViewer.append(msg+"\n");
    }
    
}
