/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_Server;

import _3_2Game.Game;
import _3_2Game.Player;
import _3_2Game.UI_Updater;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author ivantactukmercado
 */
public class Chat_Server {

    Game gm;
    public Chat_Server(Game gm, UI_Updater ui) {
        this.gm=gm;
    }
    
    
    
    void startServer(UI_Updater ui) throws IOException{
        System.out.println("3&2 chat server is running");
        int clientNumber = 0;
        ArrayList<PrintWriter> out_everyone=new ArrayList<>();
        ArrayList<Chat_Server_Listener> chats=new ArrayList<>();
        Chat_Server_Listener chatTmp;
        ServerSocket listener = new ServerSocket(3200);
        try {
            for(Player pl:gm.getPlayers()){
                chatTmp=(new Chat_Server_Listener(listener.accept(), pl.toString(),out_everyone,ui));
                out_everyone.add(chatTmp.getOut());
                chats.add(chatTmp);
            }
            chats.stream().forEach((ch) -> {
                new Thread(ch).start();
            });
        } finally {
            listener.close();
        }
    }
    
    class Chat_Server_Listener implements Runnable{
        
        private final ArrayList<PrintWriter> out_everyone;
        private final PrintWriter out;
        private final BufferedReader in;
        private final String userName;
        private UI_Updater ui;

        private Chat_Server_Listener(Socket socket, String userName,ArrayList<PrintWriter> out_everyone, UI_Updater ui) throws IOException {
            in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            this.userName=userName;
            this.out_everyone=out_everyone;
            this.ui=ui;
        }
        
        public void BroadCast(String msg){
            for(PrintWriter o:out_everyone){
                o.println(userName+":"+msg);
            }
        }
        
        public PrintWriter getOut(){
            return out;
        }

        @Override
        public void run() {
            String input;
            try {
                while((input=in.readLine())!=null){
                    BroadCast(input);
                }
            } catch (IOException ex) {
                ui.display("Can't read from chat_server of player "+userName);
            }
            finally{
                try {
                    in.close();
                } catch (IOException ex) {
                    ui.display("Can't closed chat_server of player "+userName);
                }
            }
        }
        
    }
    
}
