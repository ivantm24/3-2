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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author ivantactukmercado
 */
public class SinglePlayerWindow extends JFrame implements UI_Updater{

    ArrayList<Player> players;
    Game game;
    JTextArea msgViewer;
    GameBoardGUI gmGUI;
    
    public SinglePlayerWindow() throws HeadlessException, IOException {
        init();
    }

    private void init() throws IOException {
        setTitle("3 & 2 sinlge player game");
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(600, 600));
        getContentPane().setBackground(Color.orange);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        gmGUI=new GameBoardGUI();
        GridBagConstraints cGm = new GridBagConstraints();
        cGm.gridx=0;
        cGm.gridy=0;
        cGm.weighty=1;
        cGm.fill=GridBagConstraints.VERTICAL;
        add(gmGUI,cGm);
        
        msgViewer=new JTextArea();
        msgViewer.setColumns(10);
        msgViewer.setLineWrap(true);
        msgViewer.setWrapStyleWord(true);
        msgViewer.setEditable(false);
        JScrollPane MsgScrollPane1 = new JScrollPane(msgViewer);
        GridBagConstraints cCh = new GridBagConstraints();
        cCh.gridx=0;
        cCh.gridy=1;
        cCh.fill=GridBagConstraints.BOTH;
        cCh.weightx=1;
        cCh.weighty=1;
        add(MsgScrollPane1,cCh);
        
        players=new ArrayList<>();
        Player P1=new Player("ivan");
        players.add(P1);
        players.add(new Player("CPU2"));
        players.add(new Player("CPU3"));
        players.add(new Player("CPU4"));

        this.addWindowListener(new WindowAdapter() {
            //
            // Invoked when a window has been opened.
            //
            public void windowOpened(WindowEvent e) {
                
            }
        });
        game = new Game(players, this);
        gmGUI.setEventListener(game);
        new Thread(game).start();
        
    }
    
    
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                SinglePlayerWindow ex;
                try {
                    ex = new SinglePlayerWindow();
                    ex.setVisible(true);
                    
                } catch (HeadlessException ex1) {
                    Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (IOException ex1) {
                    Logger.getLogger(SinglePlayerWindow.class.getName()).log(Level.SEVERE, null, ex1);
                }
                
                
            }
        });
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
        msgViewer.append("P1 cards:"+game.getPlayers().get(0).getCards());
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
