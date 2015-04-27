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
public class SinglePlayerWindow extends JFrame{

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
        GUIUpdater GUIUpdater = new GUIUpdater(msgViewer, gmGUI);
        game = new Game(players, GUIUpdater);
        GUIUpdater.set_game(game);
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

    
}
