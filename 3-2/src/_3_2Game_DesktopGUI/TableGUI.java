/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_DesktopGUI;

import _3_2Game.Card;
import _3_2Game.Game;
import _3_2Game.Player;
import _3_2Game_Client.Player_Client;
import _3_2Game_Client.Table;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author ivantactukmercado
 */
public class TableGUI extends JPanel{
    JList list;
    JButton joinBtn;
    JButton refreshBtn;
    JButton createBtn;
        
    public TableGUI() {
        Init();
    }
    
    private void Init(){
        setBackground(Color.DARK_GRAY);
        
        DefaultListModel listModel=new DefaultListModel();
      
        list = new JList(listModel); //data has type Object[]
//        for (int i = 0; i < 70; i++) {
//            listModel.addElement("klk"+i);
//        
//        }
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        
        
        refreshBtn  = new JButton("Refresh");
        joinBtn     = new JButton("Join");
        createBtn   = new JButton("Create");
        
        this.add(listScroller);
        this.add(refreshBtn);
        this.add(joinBtn);
        this.add(createBtn);
        
    }
    
    public void setEventListener(GUIUpdater ui,Player_Client cli, ChatGUI ch){
        joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
                String tableName=(String) list.getSelectedValue();
                int cantUsers=-1;
                if (tableName!=null){
                    try {
                        ui.display("Trying to join table...:"+tableName);
                        cantUsers=cli.join(tableName);
                    } catch (IOException ex) {
                        ui.display("Error communicating with server");
                    }
                }
                if (cantUsers>0){
                    ui.display("Joined table successfully.");
                    ui.display(cantUsers+" players in table.");
                    ui.display("Waiting for other players...");
                    JButton c = (JButton)event.getSource();
                    ((JPanel)c.getParent()).setVisible(false);
                    new Thread(cli).start();
                    ch.setEventListener(ui, cli.getChatClient());
                }
            }
        });
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
                String tableName=JOptionPane.showInputDialog("Enter Table name:");
                boolean table_was_created= false;
                if (tableName!=null){
                    try {
                        ui.display("Creating table...:"+tableName);
                        table_was_created=cli.createTable(tableName);
                    } catch (IOException ex) {
                        ui.display("Error communicating with server");
                    }
                }
                if (table_was_created){
                    ui.display("Created table successfully.");
                    ui.display(table_was_created+" players in table.");
                    ui.display("Waiting for other players...");
                    JButton c = (JButton)event.getSource();
                    ((JPanel)c.getParent()).setVisible(false);
                    new Thread(cli).start();                  
                    ch.setEventListener(ui, cli.getChatClient());
                }
            }
        });
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ui.display("Refreshing table...");
               
                DefaultListModel listModel=(DefaultListModel) list.getModel();
                listModel.removeAllElements();
                ArrayList<Table> newTables=null;
                try {
                    newTables=cli.getTablesFromServer();
                } catch (IOException ex) {
                    Logger.getLogger(TableGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(newTables==null){
                    ui.display("Error retrieving tables");
                }else{
                    for(Table t: newTables){
                        listModel.addElement(t.getName());
                    }
                }
            }
        });   
    }
}
