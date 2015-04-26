/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_DesktopGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author ivantactukmercado
 */
public class TableGUI extends JPanel{

    public TableGUI() {
        Init();
    }
    
    private void Init(){
        setBackground(Color.DARK_GRAY);
        
        ArrayList<String> data=new ArrayList<>();
        for (int i = 0; i < 70; i++) {
            data.add("klk"+i);
        
        }
        
        JList list = new JList(data.toArray()); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        
        JButton joinBtn=new JButton("Join");
        JButton refreshBtn=new JButton("Refresh");
        
        this.add(listScroller);
        this.add(joinBtn);
        this.add(refreshBtn);
        
    }
}
