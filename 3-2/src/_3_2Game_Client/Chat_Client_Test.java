/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_Client;

import _3_2Game.UI_Updater;
import _3_2Game.UI_cmd;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ivantactukmercado
 */
public class Chat_Client_Test {
    public static void main(String[] args) throws IOException{
        UI_Updater ui=new UI_cmd();
        Chat_Client cl=new Chat_Client(ui);
        new Thread(cl).start();
        Scanner s = new Scanner(System.in);
        while(true){
            cl.send(s.nextLine());
        }
    }
}
