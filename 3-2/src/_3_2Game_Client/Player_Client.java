
package _3_2Game_Client;

import _3_2Game.Card;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player_Client extends Player implements Runnable{
    public BufferedReader in;
    public PrintWriter out;
    private UI_Updater ui;
    private boolean tableReady=false;
    Chat_Client ch=null;
    
    public synchronized Chat_Client getChatClient(){
        if (ch==null) try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Player_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ch;
    }
    
    public synchronized void CreateChatClient(UI_Updater ui, String userName) throws IOException{
        ch=new Chat_Client(ui,userName);
        this.notifyAll();
    }
        
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
                        i=4;
                    }
                    break;
                case 3:
                    System.out.println("New Table Name:");
                    tableName = s.nextLine();
                    tableName = s.nextLine();
                    boolean success=client.createTable(tableName);
                    System.out.println("Success:"+success);
                    if (success){
                        i=4;//exit
                    }
                    break;
                default:
                    break;
            }
        }while(i!=4);
        
        new Thread(client).start();
        while(true){
            while(!client.getTurn());
            System.out.println("Select 1 for MD");
            i=s.nextInt();
            if (i==1){
                client.DrawFromMD();
            }else{
                client.DrawFromDD();   
            }
            System.out.println("Select card to discard");
            i=s.nextInt();
            client.Discard(client.getCards().get(i));
        }
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
    
    public void changeUi(UI_Updater ui){
        this.ui=ui;
    }
    
    public synchronized boolean isTableReady(){
     return tableReady;   
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



    public ArrayList<Table> getTablesFromServer() throws IOException {
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

    public int join(String tableName) throws IOException {
        out.println("JOIN_TABLE:"+tableName+":"+userName);
        String input=in.readLine();
        System.out.println(input);
        return Integer.parseInt(input);
    }

    public boolean createTable(String tableName) throws IOException {
        out.println("CREATE_TABLE:"+tableName+":"+userName);
        String input=in.readLine();
        return "true".equals(input);
    }

    private void waitForTableReady() throws IOException {
        String input="1";
        ui.display("Waiting for other players to join table");
        this.CreateChatClient(ui, userName);
        new Thread(ch).start();
        while(!"4".equals(input=in.readLine())){
            if (input==null) throw new IOException("Input is null in waitForTableReady");
            ui.display("Number of players in table:"+input);
        }
        out.println("TABLE_READY");
        synchronized(this){
            tableReady=true;
        }
    }
    
    public void send(String msg){
        out.println(msg);
    }
    
    public void send(Card card){
        send(card.getSuit()+"-"+card.getRank());
    }
    
    public void send(String msg, Card card){
        if (card==null){
            send(msg+":"+"null-null");
            return;
        }
        send(msg+":"+card.getSuit()+"-"+card.getRank());
    }
    public void send(String msg, Card card,Card card2){
        if (card2==null){
            send(msg+":"+card.getSuit()+"-"+card.getRank()+":"
        +"null-null");
            return;
        }
        send(msg+":"+card.getSuit()+"-"+card.getRank()+":"
        +card2.getSuit()+"-"+card2.getRank());
    }
    
    public static Card parseCard(String str){
        String[] elements=str.split("-");
        Card.suits suit;
        Card.ranks rank;
        try{
            suit=Card.suits.valueOf(elements[0]);
            rank=Card.ranks.valueOf(elements[1]);
        }catch(IllegalArgumentException e){
            return null;
        }
        return new Card(suit, rank);
    }

    @Override
    public void run() {
        try {
            waitForTableReady();
            listenDealer();
        } catch (IOException ex) {
            ui.display(ex.getMessage());
        }
    }
    
    public void DrawFromMD(){
        send("MAIN");
    }
    
    public void DrawFromDD(){
        send("DISCARDED");
    }
    
    public void Discard(Card card){
        send(card);
        ui.P1_DiscardDD(card);
        setTurn(false);
        
    }

    private void listenDealer() throws IOException {
        String input;
        Card card, cardOnDD;
        int i=0;
        ui.display("Waiting for Dealer");
        input=in.readLine();
        cardOnDD=parseCard(input);
        while(!"NMC".equals(input=in.readLine())){//No More Cards
            if (input==null) throw new IOException("Input is null in listenDealer");
            card=parseCard(input);
            this.hand.Insert(card);
        }
        ui.shuffle(this.hand.getCards(), cardOnDD);
        while(!"EOG".equals(input=in.readLine())){//End Of Game
            if (input==null) throw new IOException("Input is null in listenDealer");
            String[] elements=input.split(":");
            switch(elements[0]){
                case "TURN":
                    setTurn(true);
                    ui.display("It's your turn");
                    break;
                case "REFILL":
                    ui.display("Main Deck have been refilled with Discarded Deck cards");
                    break;
                case "MYDREW_CARD":
                    if (elements.length>2){
                        ui.P1_DrawDD(parseCard(elements[1]), parseCard(elements[2]));
                    }else if (elements.length>1){
                        ui.P1_DrawMD(parseCard(elements[1]));
                    }
                    break;
                case "DREW_CARD":
                    if (i==0){
                        if (elements.length>1)
                            ui.P2_DrawDD(parseCard(elements[1]));
                        else
                            ui.P2_DrawMD();
                        i++;
                    }else if (i==1){
                        if (elements.length>1)
                            ui.P3_DrawDD(parseCard(elements[1]));
                        else
                            ui.P3_DrawMD();
                        i++;
                    }else if (i==2){
                        if (elements.length>1)
                            ui.P4_DrawDD(parseCard(elements[1]));
                        else
                            ui.P4_DrawMD();
                        i=0;
                    }
                    break;
                case "DISCARDED":
                    if (i==0){
                         ui.P2_DiscardDD(parseCard(elements[1]));
                    }else if (i==1){
                         ui.P3_DiscardDD(parseCard(elements[1]));
                    }else if (i==2){
                         ui.P4_DiscardDD(parseCard(elements[1]));
                    }
                    break;
                    
                default:
                    break;
            }
            //ui.display(input);
        }
        input=in.readLine();
        ui.win(input);
        
    }
    
}
