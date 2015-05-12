
package _3_2Game_Client;

import _3_2Game.UI_Updater;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat_Client implements Runnable{

    private final PrintWriter out;
    private final BufferedReader in;
    private UI_Updater ui;
    
    public Chat_Client(UI_Updater ui, String userName) throws IOException {
        String serverAddress = "127.0.0.1";
        Socket socket = new Socket(serverAddress, 3200);
        this.ui=ui;
        in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(userName);
    }
    
    public void send(String msg){
        out.println(msg);
    }

    @Override
    public void run() {
        String input;
        try {
            while((input=in.readLine())!=null){
                ui.display(input);
            }
        } catch (IOException ex) {
            
        }
    }
    
    
    
    
    
}
