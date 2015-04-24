/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_DesktopGUI;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

/**
 *
 * @author ivantactukmercado
 */
public class HandGUI extends JPanel{
    
    private final boolean vertical;
    private GridBagConstraints c;
    private final Insets vInset;
    private final Insets hInset;
    
    public HandGUI(boolean vertical){
        super();
        this.vertical=vertical;
        setLayout(new GridBagLayout());
        c=new GridBagConstraints();
        vInset=new Insets(0,-60,0,0);//spacing
        hInset=new Insets(-60,0,0,0);
        c.insets=vInset;
        c.gridx=0;
        c.gridy=0;
        setOpaque(false);
    }

    @Override
    public Component add(Component comp) {
        //Component com= super.add(comp); 
        if (vertical){
            c.insets=vInset;
            c.gridx++;
        }else{
            c.insets=hInset;
            c.gridy++;
        }
        add(comp,c);
        return comp;
    }
    
    
    
}
