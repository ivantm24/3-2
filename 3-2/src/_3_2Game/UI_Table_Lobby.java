
package _3_2Game;

import _3_2Game_Client.Table;
import java.util.ArrayList;

/**
 * Updates table status
 */
public interface UI_Table_Lobby {

    /**
     * Displays the tables status.
     * @param tables
     */
    public void refreshTables(ArrayList<Table> tables);
    public void display(String msg);
}
