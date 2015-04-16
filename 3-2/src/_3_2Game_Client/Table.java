/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_Client;

import _3_2Game.Player;
import java.util.ArrayList;

/**
 *
 * @author ivantactukmercado
 */
public class Table {
    public ArrayList<Player> players=new ArrayList<>();
    final String Name;
    
    public String getName(){
        return Name;
    }
    
    public Table(String TableName) {
        this.Name = TableName;
    }
    
    @Override
    public String toString() {
            return Name+":"+players;
    }
    
}
