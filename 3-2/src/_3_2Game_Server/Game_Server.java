/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_Server;

import _3_2Game.Game;
import _3_2Game.Player;
import _3_2Game.UI_Updater;
import _3_2Game_Client.Player_Client;
import java.util.ArrayList;

/**
 *
 * @author ivantactukmercado
 */
public class Game_Server extends Game{

    public Game_Server(ArrayList<Player> players, UI_Updater ui_adapter) {
        super(players, ui_adapter);
    }
    
    
    
}
