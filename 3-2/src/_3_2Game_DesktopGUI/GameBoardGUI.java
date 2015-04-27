/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_DesktopGUI;

import _3_2Game.Card;
import _3_2Game.Deck;
import _3_2Game.Game;
import _3_2Game.Player;
import _3_2Game_Client.Player_Client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author ivantactukmercado
 */
public class GameBoardGUI extends JPanel{
    
    public HandGUI P1;
    public HandGUI P2;
    public HandGUI P3;
    public HandGUI P4;
    
    public JPanel decks=new JPanel();
    public CardGUI MainDeck;
    public CardGUI DicardedDeck;
    
    private boolean P1hasDrawnCard=false;
    
    public GameBoardGUI() throws IOException {

        initUI();
    }
    
    private void initUI() throws IOException {
        setLayout(new BorderLayout());
        CardGUI card;
        P1=new HandGUI(true);
        for (int i = 0; i < 6; i++) {
           card=new CardGUI(null, true);
           P1.add(card); 
        }
        
        P3=new HandGUI(true);
        for (int i = 0; i < 6; i++) {
           card=new CardGUI(null, true);
           P3.add(card); 
        }
        
        P2=new HandGUI(false); 
        for (int i = 0; i < 6; i++) {
           card=new CardGUI(null, false);
           P2.add(card); 
        }
       
        
        P4=new HandGUI(false);
        for (int i = 0; i < 6; i++) {
           card=new CardGUI(null, false);
           P4.add(card); 
        }
        
        decks=new JPanel();
        decks.setLayout(new GridBagLayout());
        MainDeck=new CardGUI(null, true);
        decks.add(MainDeck);
        DicardedDeck=new CardGUI(new Card(Card.suits.diamonds, Card.ranks._10), true);
        decks.add(DicardedDeck);
        decks.setOpaque(false);
        this.add(P1,BorderLayout.SOUTH);
        this.add(P3,BorderLayout.NORTH);
        this.add(P2,BorderLayout.WEST);
        this.add(P4,BorderLayout.EAST);
        this.add(decks,BorderLayout.CENTER);
  
        //setSize(430, 430);
        setMinimumSize(new Dimension(430, 430));
        setBackground(Color.orange);

    }
    
    public void setEventListener(Game gm){
        MainDeck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Player player1=gm.getPlayers().get(0);
                if (player1.getTurn()&&!P1hasDrawnCard){
                    Card _card=gm.P1DrawCardMD();
                    P1hasDrawnCard=true;
                }
                
            }
        });
        DicardedDeck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Player player1=gm.getPlayers().get(0);
                if (player1.getTurn()&&!P1hasDrawnCard){
                    Card _card=gm.P1DrawCardDD();
                    CardGUI c = (CardGUI)event.getSource();
                    ((JPanel)c.getParent()).revalidate();
                    P1hasDrawnCard=true;
                }
                
            }
        });
     for(CardGUI c:P1.getCards()){
         c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Player player1=gm.getPlayers().get(0);
                if (player1.getTurn()&&P1hasDrawnCard){
                    CardGUI c = (CardGUI)event.getSource();
                    gm.P1Discard(c.getCard());
                    ((JPanel)c.getParent()).revalidate(); 
                    player1.setTurn(false);
                    P1hasDrawnCard=false;
                }
                
            }
        });
     }   
    }
    
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                test ex;
                try {
                    ex = new test();
                    ex.setVisible(true);
                } catch (IOException ex1) {
                    Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex1);
                }
                
            }
        });
    }

    void setEventListener(Player_Client player) {
        MainDeck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (player.getTurn()&&!P1hasDrawnCard){
                    player.DrawFromMD();
                    P1hasDrawnCard=true;
                }
                
            }
        });
        DicardedDeck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (player.getTurn()&&!P1hasDrawnCard){
                    player.DrawFromDD();
                }
                
            }
        });
     for(CardGUI c:P1.getCards()){
         c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (player.getTurn()&&P1hasDrawnCard){
                    CardGUI c = (CardGUI)event.getSource();
                    player.Discard(c.getCard());
                    //((JPanel)c.getParent()).revalidate(); 
                    P1hasDrawnCard=false;
                }
                
            }
        });
     } 
    }
    
}
