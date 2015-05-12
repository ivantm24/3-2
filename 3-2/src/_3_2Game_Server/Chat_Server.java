
package _3_2Game_Server;

import _3_2Game.Game;
import _3_2Game.Player;
import _3_2Game.UI_Updater;
import _3_2Game.UI_cmd;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat_Server implements Runnable{

    private HashMap<Table_Server, ArrayList<PrintWriter>> multicast=new HashMap<>();
    UI_Updater ui;
    public Chat_Server( UI_Updater ui) {
        this.ui=ui;
    }
    
    private synchronized void addClient(Table_Server tb,PrintWriter out){
        if (!multicast.containsKey(tb)){
            multicast.put(tb, new ArrayList<>());
        }
        ArrayList<PrintWriter> out_everyone=multicast.get(tb);
        out_everyone.add(out);
    }
    
    private synchronized  ArrayList<PrintWriter> getMulticastOut(Table_Server tb){
        return multicast.get(tb);
    }
    
    
    public void startServer() throws IOException{
        System.out.println("3&2 chat server is running");
        ArrayList<PrintWriter> out_everyone=new ArrayList<>();
        ArrayList<Chat_Server_Listener> chats=new ArrayList<>();
        Chat_Server_Listener chatTmp;
        ServerSocket listener = new ServerSocket(3200);
        try {
            while (true) {
                new Thread(new Chat_Server_Listener(listener.accept(), ui)).start();
            }
        } finally {
            listener.close();
        }
    }

    @Override
    public void run() {
        try {
            startServer();
        } catch (IOException ex) {
            Logger.getLogger(Chat_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class Chat_Server_Listener implements Runnable{
        
        //private final ArrayList<PrintWriter> out_everyone;
        private final PrintWriter out;
        private final BufferedReader in;
        private String userName="";
        private UI_Updater ui;
        private Table_Server tb;

        private Chat_Server_Listener(Socket socket, UI_Updater ui) throws IOException {
            in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            //assign to multicast group
            this.ui=ui;
            
        }
        
        public void BroadCast(String msg){
            for(PrintWriter o:getMulticastOut(tb)){
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
                this.userName=in.readLine();
                if ("".equals(this.userName)){
                    ui.display("Empty user name for new chat client");
                    return;
                }
                tb=TableAssigner.getTableByUserName(userName);   
                if (tb==null){
                   ui.display("No table found for client: "+userName);
                    return; 
                }
                addClient(tb, out);
                
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
