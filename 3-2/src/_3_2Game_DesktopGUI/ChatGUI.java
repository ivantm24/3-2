/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_DesktopGUI;

import _3_2Game_Client.Chat_Client;
import _3_2Game_Client.Player_Client;
import _3_2Game_Client.Table;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
    JButton chatBtn;
    JTextField chatSender;
    
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
        chatSender=new JTextField("");
        chatSender.setPreferredSize(new Dimension(200, 20));
        chatSender.setMinimumSize(new Dimension(80, 20));
        chatBtn=new JButton("Send");
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
    
    public void setEventListener(GUIUpdater ui,Chat_Client cli){
        chatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String msg="";
                msg=chatSender.getText();
                if ("".equals(msg)) return;
                cli.send(msg);
                chatSender.setText("");
            }
        });          
    }
    
    public JTextArea getChatViewer(){
        return chatViewer;
    }
    
}
