/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_DesktopGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ivantactukmercado
 */
public class ChatGUI extends JPanel{

    JTextArea chatViewer;
    
    public ChatGUI() {
        super();
        init();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        
        chatViewer=new JTextArea();
        chatViewer.setColumns(20);
        chatViewer.setLineWrap(true);
        //chatViewer.setRows(5);
        chatViewer.setWrapStyleWord(true);
        chatViewer.setEditable(false);
        JScrollPane chatViewerScrollPane1 = new JScrollPane(chatViewer);
        JTextField chatSender=new JTextField("");
        chatSender.setPreferredSize(new Dimension(200, 20));
        chatSender.setMinimumSize(new Dimension(80, 20));
        JButton chatBtn=new JButton("Send");
        chatBtn.setSize(20, 40);
        
        JPanel lowerPanel=new JPanel();
        lowerPanel.setOpaque(false);
        this.add(chatViewerScrollPane1);
        lowerPanel.add(chatSender);
        lowerPanel.add(chatBtn);
        this.add(lowerPanel);
        
        
        for (int i = 0; i < 1000; i++) {
            chatViewer.append("\n");
        }
        
        
    }
    
    public JTextArea getChatViewer(){
        return chatViewer;
    }
    
}
