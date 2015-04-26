package _3_2Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It contains all the game logic
 * @author ivantactukmercado
 */
public class Game implements Runnable {
	protected Deck MainDeck;
	protected Deck DicardedDeck;
	protected ArrayList<Player> players;
	protected UI_Updater ui;
	protected Status status;
	protected Player currentPlayer=null;
	private Random rand=new Random();
	
	public enum Status{
		OVER, PLAYING,NOTSTARTED, WaitingForP1		
	}
        
        public Card peekDD(){
            return DicardedDeck.Peek();
        }
	
        /**
         * Get Status of game
         * @return game status
         */
	public Status getStatus(){
		synchronized (this) {
			return status;
		}
	}
	
        /**
         * Sets game status
         * @param status 
         */
	private void setStatus(Status status){
		synchronized (this) {
			this.status=status;
		}
	}
        
        /**
         * Retrieve players
         * @return players in game
         */
        public ArrayList<Player> getPlayers(){
            return players;
        }
	
        /**
         * Returns uiUpdater use to update the user interface
         * @return ui_updater
         */
	public UI_Updater getUI_updater(){
		return ui;
	}
	
        /**
         * Creates new game
         * @param players list of player 
         * @param ui_adapter ui updater
         */
	public Game(ArrayList<Player> players, UI_Updater ui_adapter){
		this.players=players;
		this.ui=ui_adapter;
		this.MainDeck=new Deck(false);
		this.MainDeck.shuffle();
		this.DicardedDeck=new Deck(true);
		this.DicardedDeck.Insert(this.DicardedDeck.Draw());
		this.status=Status.PLAYING;
	}
	
        /**
         * Creates new game
         * @param players list of players 
         * @param ui_adapter ui updater
         * @param P1 user information
         */
	public Game(ArrayList<Player> players, UI_Updater ui_adapter, Player P1){
		this(players, ui_adapter);
		currentPlayer=P1;
	}
	
        /**
         * Executes the game logic
         */
	public void StartGame(){
		Deal();
		SelectPlayerToPlay();
		while(isNotOver()){
                    Play();
                    NextPlayer();
                    if (MainDeck.isEmpty()){
                        refillMainDeck();
                    }
		}
		setStatus(Status.OVER);
		ui.display("End of Game");
	}

        /**
         * Decides who is going to be the next player
         * and assign next player to current player
         */
	protected void NextPlayer() {
		int i=players.indexOf(currentPlayer)+1;
		if (i>=players.size()){
			i=0;
		}
		currentPlayer=players.get(i);
		ui.display(currentPlayer+" turn");
	}
	
//	public void NotifyP1move(){
//		this.status=Status.PLAYING;
//	}

        /**
         * Waits for each player to perform its move
         */
	protected void Play() {
		if (currentPlayer==players.get(0)){
			currentPlayer.turn=true;
			while(currentPlayer.getTurn());
			//this.status=Status.WaitingForP1;
			//while (this.status==Status.WaitingForP1);		
		}else{
			CPUplay(currentPlayer);
		}
		
	}

        /**
         * Logic to make CPU make a move
         * @param currentPlayer2 current player
         */
	private void CPUplay(Player currentPlayer2) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
		Deck deck;
		if (rand.nextInt(2)==0){
			deck=MainDeck;
		}else{
			deck=DicardedDeck;
		}
		currentPlayer2.Draw(deck);
		Card cardOnDDafterDraw=deck.Peek();
		int Pnum=players.indexOf(currentPlayer2);
		
		ArrayList<Card> cards=currentPlayer2.getCards();
		int i=rand.nextInt(cards.size());
		Card discardedCard=currentPlayer2.Discard(cards.get(i), DicardedDeck);
		
		switch (Pnum) {
		case 1:
			if (deck==MainDeck)
				ui.P2_DrawMD();
			else
				ui.P2_DrawDD(cardOnDDafterDraw);
			ui.P2_DiscardDD(discardedCard);
			break;
		case 2:
			if (deck==MainDeck)
				ui.P3_DrawMD();
			else
				ui.P3_DrawDD(cardOnDDafterDraw);
			ui.P3_DiscardDD(discardedCard);
			break;
		case 3:
			if (deck==MainDeck)
				ui.P4_DrawMD();
			else
				ui.P4_DrawDD(cardOnDDafterDraw);
			ui.P4_DiscardDD(discardedCard);
			break;

		default:
			break;
		}
	}

        /**
         * Check if someone has a winning hand
         * @return if game is over returns false
         */
	private boolean isNotOver() {
		for(Player player:players){
			if(player.hasWon()){
				return false;
			}
		}
		return true;
	}

        /**
         * Choose the first player to play
         */
	protected void SelectPlayerToPlay() {
		if (currentPlayer!=null) return;
		if (players.contains(currentPlayer)) return;
		int i=rand.nextInt(players.size());
		currentPlayer=players.get(i);
		ui.display(currentPlayer+" turn");
	}

        /**
         * Deals the cards to every player
         */
	protected void Deal() {
		for (int i = 0; i < 5; i++) {
			for(Player player:players){
				player.Draw(MainDeck);
			}
		}
		DicardedDeck.Insert(MainDeck.Draw());
		ui.shuffle(players.get(0).getCards(),DicardedDeck.Peek());
		
	}
        /**
         * Takes all cards from Discarded Deck, except the 1st one,
         * and inserts all those card back to Main Deck and Shuffles Main Deck
         */
        protected void refillMainDeck(){
            Card cardOnDD=DicardedDeck.Draw();
            while(!DicardedDeck.isEmpty()){
                MainDeck.Insert(DicardedDeck.Draw());
            }
            MainDeck.shuffle();
            DicardedDeck.Insert(cardOnDD);
            ui.display("Main Deck have been refilled with Discarded Deck cards");
        }
        
        /**
         * User draw a card from main deck
         * @return drew card
         */
        public Card P1DrawCardMD(){
            Player P1;
            P1=players.get(0);
            Card c= P1.Draw(MainDeck);
            ui.P1_DrawMD(c);
            return c;
        }
        
        /**
         * User draw a card from discarded deck
         * @return  drew card
         */
        public Card P1DrawCardDD(){
            Player P1;
            P1=players.get(0);
            Card c= P1.Draw(DicardedDeck);
            ui.P1_DrawDD(c, DicardedDeck.Peek());
            return c;
        }
        
        public Card P1Discard(Card card){
            Player P1;
            P1=players.get(0);
            Card c= P1.Discard(card, DicardedDeck);
            ui.P1_DiscardDD(c);
            return c;
        }

	@Override
	public void run() {
		StartGame();
		
	}
	

}
