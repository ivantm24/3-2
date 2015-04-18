/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_Server;

import _3_2Game.Game;
import _3_2Game.Player;
import _3_2Game.UI_Updater;
import _3_2Game.UI_cmd;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ivantactukmercado
 */
public class Chat_Server_Test {
    public static void main(String[] args) throws IOException{
        UI_Updater ui=new UI_cmd();
        ArrayList<Player> players=new ArrayList<>();
        players.add(new Player("P1"));
        players.add(new Player("P2"));
        players.add(new Player("P3"));
        Game gm=new Game(players, ui);
        Chat_Server server=new Chat_Server(gm, ui);
        server.startServer(ui);
    }
}
