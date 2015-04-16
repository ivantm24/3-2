/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_Server;

import _3_2Game.Player;
import _3_2Game.UI_Updater;
import _3_2Game.UI_cmd;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import _3_2Game_Client.Player_Client;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivantactukmercado
 */
public class TableAssigner extends Thread {
        private final Socket socket;
        private final int clientNumber;
        BufferedReader in;
        PrintWriter out;
        private static ArrayList<Table_Server> tables;
        UI_Updater ui;

	public TableAssigner(Socket socket, int clientNumber) throws IOException {
            this.socket = socket;
            this.clientNumber = clientNumber;
            this.ui=new UI_cmd();
            if (tables==null){
                tables=new ArrayList<>();
                //Test Data
//                Table_Server tmpTable=new Table_Server("DR");
//                tmpTable.players.add(new Player_Client("itm", null));
//                tables.add(tmpTable);
//                tmpTable=new Table_Server("USA");
//                tables.add(tmpTable);
            }
            in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
              out = new PrintWriter(socket.getOutputStream(), true);
            log("New connection with client# " + clientNumber + " at " + socket);
	}
        
        synchronized static void UpdateTables(){
            Table_Server tbTmp;
            for (int i = 0; i < tables.size(); i++) {
                tbTmp=tables.get(i);
                if (tbTmp.getPlayers().size()<=0){
                    tables.remove(i);
                }
            }
        }
        
        synchronized static boolean join(String tbName,Player_Client player){
            for(Table_Server tb:tables){
                if (tb.getName().equals(tbName)&&!tb.contains(player)){
                    tb.add(player);
                    log("User "+player.toString()+" joined table "+tbName);
                    return true; 
                }
            }
            return false;
        }
        
        synchronized static boolean addTable(Table_Server _table){
            if (!tables.stream().noneMatch((tb) -> (tb.getName().equals(_table.getName())))) {
                return false;
            }
            tables.add(_table);
            log("Table "+_table.getName()+" was added");
            return true;
        }
        
        synchronized static ArrayList<Table_Server> getTables(){
            return tables;
        }
        
        synchronized static Table_Server getTable(String name){
            for(Table_Server tb:tables){
                if (tb.getName().equals(name))
                    return tb;
            }
            return null;
        }
        
	
        @Override
	 public void run() {
         try {
             out.println("#" + clientNumber);
             String [] elements;
             while (true) {
                 String input = in.readLine();
                 elements=input.split(":");
                 input=elements[0];
                 if (input == null ) {
                     break;//Connection lost
                 }
                 switch(input){
                     case "GET_TABLES":
                         sendTables();
                         break;
                     case "CREATE_TABLE":
                         boolean res=create_table(elements[1],elements[2]);
                         out.println(res);
                         break;
                     case "JOIN_TABLE":
                         Table_Server tb=getTable(elements[1]);
                         boolean joined;
                         if(tb!=null)
                            joined=join(tb.getName(), new Player_Client(elements[2], socket));
                         else
                             joined=false;
                         if (!joined) {
                             out.println("-1");
                         }else{
                             ArrayList<Player_Client> players=tb.getPlayers();
                             Integer playersCount=players.size();
                             out.println(playersCount.toString());
                             tb.sendToAllPlayers(playersCount.toString()); 
                             if (playersCount==4){
                                 ArrayList<Player> normalPlayers=new ArrayList<>(players);
                                 Game_Server gm=new Game_Server(normalPlayers, ui);
                                 new Thread(gm).join();
                             }
                         }
                         
                         break;
                 }
                 
             }
         } catch (IOException e) {
             log("Error handling client# " + clientNumber + ": " + e);
         }  catch (InterruptedException ex) {
             log("Error handling client# " + clientNumber + ": " + ex);
            } finally {
             try {
                 socket.close();
             } catch (IOException e) {
                 log("Couldn't close a socket");
             }
             log("Connection with client# " + clientNumber + " closed");
         }
     }

	private static void log(String string) {
		System.out.println(string);
		
	}

    public void sendTables() {
        StringBuilder stringBuilder=new StringBuilder();
        for(Table_Server tb:tables){
            stringBuilder.append(":").append(tb.getName());
            for(Player_Client pl:tb.getPlayers()){
                stringBuilder.append(",").append(pl.toString());
            }
        }
        if (stringBuilder.length()==0) {
            out.println("::");
        }else{
            out.println(stringBuilder.toString());
        }
        
    }

    private boolean create_table(String name, String userName) {
        Table_Server tb=new Table_Server(name);
        boolean created=addTable(tb);
        if (created){
            join(name, new Player_Client(userName,in, out));
        }
        return created;
    }

}

