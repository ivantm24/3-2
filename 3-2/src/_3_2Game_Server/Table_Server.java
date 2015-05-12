
package _3_2Game_Server;

import _3_2Game_Client.Player_Client;
import _3_2Game_Client.Table;
import java.util.ArrayList;


class Table_Server extends Table{
    private ArrayList<Player_Client> _players=new ArrayList<>();
    public Table_Server(String TableName) {
        super(TableName);
    }
    synchronized public void add(Player_Client player){
        _players.add(player);
    }
    synchronized public ArrayList<Player_Client> getPlayers(){
        return _players;
    }
    
    synchronized public boolean contains(Player_Client player){
        return _players.contains(player);
    }
    
    public synchronized void sendToAllPlayers(String msg){
        for (Player_Client pl : _players) {
            pl.send(msg);
        }
    }
    
}
