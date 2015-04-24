/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game;

import _3_2Game_Client.Table;
import java.util.ArrayList;

/**
 * Updates table status
 * @author ivantactukmercado
 */
public interface UI_Table_Lobby {

    /**
     * Displays the tables status.
     * @param tables
     */
    public void refreshTables(ArrayList<Table> tables);
    public void display(String msg);
}
