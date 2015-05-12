
package _3_2Game_Client;

import _3_2Game.Player;
import java.util.ArrayList;

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
