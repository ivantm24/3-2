
package _3_2Game_Client;

import _3_2Game.UI_Updater;
import _3_2Game.UI_cmd;
import java.io.IOException;
import java.util.Scanner;

public class Chat_Client_Test {
    public static void main(String[] args) throws IOException{
        UI_Updater ui=new UI_cmd();
        Chat_Client cl=new Chat_Client(ui,"ivantm24");
        new Thread(cl).start();
        Scanner s = new Scanner(System.in);
        while(true){
            cl.send(s.nextLine());
        }
    }
}
