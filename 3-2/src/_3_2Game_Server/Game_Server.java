package _3_2Game_Server;

import _3_2Game.Card;
import _3_2Game.Deck;
import _3_2Game.Game;
import _3_2Game.Player;
import _3_2Game.UI_Updater;
import _3_2Game_Client.Player_Client;
import java.io.IOException;
import java.util.ArrayList;

public class Game_Server extends Game implements Runnable{

    String tableName;
    
    public Game_Server(ArrayList<Player> players, UI_Updater ui_adapter, String tableName) {
        super(players, ui_adapter);
        Player_Client pl=(Player_Client) players.get(0);
        ui.display("Game object created for table "+tableName);
        this.tableName=tableName;
    }

    @Override
    protected void SelectPlayerToPlay() {
        super.SelectPlayerToPlay(); 
        notifyCurrentPlayerToPlay();
    }

    @Override
    protected void NextPlayer() {
        super.NextPlayer(); 
        notifyCurrentPlayerToPlay();
    }
    
    private void notifyCurrentPlayerToPlay(){
        ui.display(currentPlayer.toString()+" turn in "+this.tableName);
        ((Player_Client)currentPlayer).send("TURN");
    }
    
    

    @Override
    protected void Deal() {
        super.Deal(); 
        
        for(Player pl: players){
                Player_Client plCli=(Player_Client)pl;
                plCli.send(this.DicardedDeck.Peek());
                for(Card card:plCli.getCards())
                    plCli.send(card);
                plCli.send("NMC");//No More Cards
            }
    }

    @Override
    protected void refillMainDeck() {
        super.refillMainDeck();
        players.stream().map((pl) -> (Player_Client)pl).forEach((plCli) -> {
            plCli.send("REFILL");
        });
    }

    @Override
    protected void FinalizeGame() {
        Player winner=null;
        for(Player pl: players){
            if (pl.hasWon()) winner=pl;
            Player_Client plCli=(Player_Client)pl;
            plCli.send("EOG");//End of Game
        }
        if (winner==null) return;
        for(Player pl: players){  
            Player_Client plCli=(Player_Client)pl;
            plCli.send(winner.getUserName());
        }
        
    }
    
    
    

    @Override
    protected void Play() {
        try {
            //Deck Selection
            String input= ((Player_Client)currentPlayer).in.readLine();
            Deck deck;
            switch(input){
                case "DISCARDED":
                    deck=this.DicardedDeck;
                    break;
                default:
                    deck=this.MainDeck;
                    break;
            }
            //Draw card
            Card drewcard=currentPlayer.Draw(deck);
            //Send card to player
            if (this.DicardedDeck==deck)
                ((Player_Client)currentPlayer).send("MYDREW_CARD",drewcard,DicardedDeck.Peek());
            else
                ((Player_Client)currentPlayer).send("MYDREW_CARD",drewcard);
            String tmp="";
            //Notify players of drew card
            for(Player pl: players){
                if (pl==currentPlayer) {
                    continue;
                }
                Player_Client plCli=(Player_Client)pl;
                if (this.DicardedDeck==deck)
                    plCli.send("DREW_CARD",this.DicardedDeck.Peek());
                else
                    plCli.send("DREW_CARD");
            }
            //Received discarded card
            input= ((Player_Client)currentPlayer).in.readLine();
            Card discardedCard=Player_Client.parseCard(input);
            currentPlayer.Discard(discardedCard, this.DicardedDeck);
            //Notify players of dicarded card
            for(Player pl: players){
                if (pl==currentPlayer) continue;
                Player_Client plCli=(Player_Client)pl;
                plCli.send("DISCARDED",discardedCard);
            }
            
        } catch (IOException ex) {
            ui.display("Error communication with player "+currentPlayer.toString());
            for(Player pl: players){
                if (pl==currentPlayer) continue;
                try {
                    ((Player_Client)pl).in.close();
                } catch (IOException ex1) {
                    ui.display("Couldn't close connection of player: "+pl);
                }
            }
        }
    }
    
    

    
    
    
    
}
