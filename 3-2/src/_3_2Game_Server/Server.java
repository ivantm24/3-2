/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_Server;

import _3_2Game.UI_cmd;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author ivantactukmercado
 */
public class Server {

	public static void main(String[] args) throws IOException {
            Chat_Server ch=new Chat_Server(new UI_cmd());
            new Thread(ch).start();
	System.out.println("3&2 server is running");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(3232);
        try {
            while (true) {
                new TableAssigner(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }

	}

}
