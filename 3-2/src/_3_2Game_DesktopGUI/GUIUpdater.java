
package _3_2Game_DesktopGUI;

import _3_2Game.Card;
import _3_2Game.Game;
import _3_2Game.UI_Updater;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * 
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
        display("P1_DrawMD:"+card);
        gmGUI.P1.insert(card);
    }

    @Override
    public void P2_DrawMD() {
        display("P2_DrawMD");
        gmGUI.P2.makeAllVisible();
    }

    @Override
    public void P3_DrawMD() {
        display("P3_DrawMD");
        gmGUI.P3.makeAllVisible();
    }

    @Override
    public void P4_DrawMD() {
        display("P4_DrawMD");
        gmGUI.P4.makeAllVisible();
    }

    @Override
    public void P1_DrawDD(Card DrawnCard, Card visibleCard) {
        display("P1_DrawDD:"+DrawnCard+","+visibleCard);
        try {
            gmGUI.DicardedDeck.setCard(visibleCard);
        } catch (IOException ex) {
            Logger.getLogger(GameBoardGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        gmGUI.P1.insert(DrawnCard);
    }

    @Override
    public void P2_DrawDD(Card visibleCard) {
        display("P2_DrawDD:"+visibleCard);
        gmGUI.P2.makeAllVisible();
        try {
            gmGUI.DicardedDeck.setCard(visibleCard);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void P3_DrawDD(Card visibleCard) {
        display("P3_DrawDD:"+visibleCard);
        gmGUI.P3.makeAllVisible();
        try {
            gmGUI.DicardedDeck.setCard(visibleCard);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void P4_DrawDD(Card visibleCard) {
        display("P4_DrawDD:"+visibleCard);
        gmGUI.P4.makeAllVisible();
        try {
            gmGUI.DicardedDeck.setCard(visibleCard);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void P1_DiscardDD(Card card) {
        display("P1_DiscardDD:"+card);
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
        display("P2_DiscardDD:"+card);
        gmGUI.P2.makeFirstInvisible();
        try {
            gmGUI.DicardedDeck.setCard(card);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void P3_DiscardDD(Card card) {
        display("P3_DiscardDD:"+card.toString());
        gmGUI.P3.makeFirstInvisible();
        try {
            gmGUI.DicardedDeck.setCard(card);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void P4_DiscardDD(Card card) {
        display("P4_DiscardDD:"+card.toString());
        gmGUI.P4.makeFirstInvisible();
        try {
            gmGUI.DicardedDeck.setCard(card);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void shuffle(ArrayList<Card> P1_cards, Card cardOnDD) {
        display("Shuffled:"+P1_cards.toString());
        try {
            gmGUI.P1.shuffled(P1_cards);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        gmGUI.P2.shuffled();
        gmGUI.P3.shuffled();
        gmGUI.P4.shuffled();
        try {
            gmGUI.DicardedDeck.setCard(cardOnDD);
        } catch (IOException ex) {
            Logger.getLogger(GUIUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void display(String msg) {
        msgViewer.append(msg+"\n");
        msgViewer.setCaretPosition(msgViewer.getDocument().getLength());
    }
    
    @Override
    public void win(String username){
        display(username +" has won");
        
    }
    
}
