/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_Client;

import _3_2Game.Player;
import _3_2Game.UI_Updater;
import _3_2Game.UI_cmd;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author ivantactukmercado
 */
public class Player_Client extends Player {
    private BufferedReader in;
    public PrintWriter out;
    private UI_Updater ui;
    
        
    public static void main(String[] args) throws Exception {
        String serverAddress = "127.0.0.1";
        Socket socket = new Socket(serverAddress, 3232);
        
        Player_Client client=new Player_Client("ivantm24",socket);
        client.connectToServer();
        int i;
        Scanner s = new Scanner(System.in);
        ArrayList<Table> tables;
        String tableName;
        do{
            System.out.println("1-Refresh Table, 2-Join Table, 3-Create Table, 4-Exit");
            i=s.nextInt();
            switch(i){
                case 1:
                    tables=client.getTablesFromServer();
                    System.out.println(tables);
                    break;
                case 2:
                    System.out.println("Table Name:");
                    tableName=s.nextLine();
                    tableName=s.nextLine();
                    int cantUser=client.join(tableName);
                    System.out.println("Success:"+cantUser);
                    if (cantUser>0){
                        client.waitForTableReady();
                    }
                    break;
                case 3:
                    System.out.println("New Table Name:");
                    tableName = s.nextLine();
                    tableName = s.nextLine();
                    boolean success=client.createTable(tableName);
                    System.out.println("Success:"+success);
                    if (success){
                        client.waitForTableReady();
                    }
                    break;
                default:
                    break;
            }
        }while(i!=4);
    }

    public Player_Client(String userName,Socket socket) throws IOException {
        super(userName);
        this.ui=new UI_cmd();
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public Player_Client(String userName,BufferedReader in, PrintWriter out) {
        super(userName);
        this.in = in;
        this.out = out;
    }

    public Player_Client(String userName,BufferedReader in, PrintWriter out, UI_Updater ui) {
        super(userName);
        this.in = in;
        this.out = out;
        this.ui = ui;
    }
    
    public Player_Client(String userName, Socket socket,UI_Updater ui) throws IOException{
        super(userName);
        this.ui=ui;
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }
    
    public void connectToServer() throws IOException, Exception {

        String input=in.readLine();
        if (input==null){
            throw new Exception("Connection to server3_2 lost");
        }
        userName+="_"+input;
        ui.display("Connection to server successfull");
//        while(true){
//            input=in.readLine();
//            if (input==null){
//                break;
//            }
//            System.out.println(input);
//        }
    }



    private ArrayList<Table> getTablesFromServer() throws IOException {
        ArrayList<Table> tables=new ArrayList<>();
        out.println("GET_TABLES:");
        String input=in.readLine();
        String[] tbArray=input.split(":");
        String[] tbElements;
        Table tbTmp;
        for(String tbInfo:tbArray){
            if (tbInfo.isEmpty()) continue;
            tbElements=tbInfo.split(",");
            tbTmp=new Table(tbElements[0]);
            for(int i=1;i<tbElements.length;i++){
                tbTmp.players.add(new Player(tbElements[i]));
            }
            tables.add(tbTmp);
        }
        return tables;
    }

    private int join(String tableName) throws IOException {
        out.println("JOIN_TABLE:"+tableName+":"+userName);
        String input=in.readLine();
        System.out.println(input);
        return Integer.parseInt(input);
    }

    private boolean createTable(String tableName) throws IOException {
        out.println("CREATE_TABLE:"+tableName+":"+userName);
        String input=in.readLine();
        return "true".equals(input);
    }

    private void waitForTableReady() throws IOException {
        String input="1";
        ui.display("Waiting for other players to join table");
        while(!"4".equals(input=in.readLine())){
            if (input==null) throw new IOException("Input is null in waitForTableReady");
            ui.display("Number of players in table:"+input);
        }
    }
    
    public void send(String msg){
        out.println(msg);
    }
    
}
