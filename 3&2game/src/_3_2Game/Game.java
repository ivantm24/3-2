package _3_2Game;

import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable {
	Deck MainDeck;
	Deck DicardedDeck;
	ArrayList<Player> players;
	private UI_Updater ui;
	private Status status;
	private Player currentPlayer=null;
	
	public enum Status{
		OVER, PLAYING,NOTSTARTED, WaitingForP1		
	}
	
	public Status getStatus(){
		return status;
	}
	
	public UI_Updater getUI_updater(){
		return ui;
	}
	
	public Game(ArrayList<Player> players, UI_Updater ui_adapter){
		this.players=players;
		this.ui=ui_adapter;
		this.MainDeck=new Deck(false);
		this.MainDeck.shuffle();
		this.DicardedDeck=new Deck(true);
		this.DicardedDeck.Insert(this.DicardedDeck.Draw());
		this.status=Status.PLAYING;
	}
	
	public Game(ArrayList<Player> players, UI_Updater ui_adapter, Player P1){
		this(players, ui_adapter);
		currentPlayer=P1;
	}
	
	public void StartGame(){
		Deal();
		SelectPlayerToPlay();
		while(isNotOver()){
			Play();
			NextPlayer();
		}
		ui.display("End of Game");
	}

	private void NextPlayer() {
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

	private void Play() {
		if (currentPlayer==players.get(0)){
			currentPlayer.turn=true;
			while(currentPlayer.getTurn());
			//this.status=Status.WaitingForP1;
			//while (this.status==Status.WaitingForP1);		
		}else{
			CPUplay(currentPlayer);
		}
		
	}

	private void CPUplay(Player currentPlayer2) {
		Random rand=new Random();
		Deck deck;
		if (rand.nextInt(1)==0){
			deck=MainDeck;
		}else{
			deck=DicardedDeck;
		}
		currentPlayer2.Draw(deck);
		int Pnum=players.indexOf(currentPlayer2);
		
		ArrayList<Card> cards=currentPlayer2.getCards();
		int i=rand.nextInt(cards.size());
		Card discardedCard=currentPlayer2.Discard(cards.get(i), DicardedDeck);
		
		switch (Pnum) {
		case 1:
			if (deck==MainDeck)
				ui.P2_DrawMD();
			else
				ui.P2_DrawDD(deck.Peek());
			ui.P2_DiscardDD(discardedCard);
			break;
		case 2:
			if (deck==MainDeck)
				ui.P3_DrawMD();
			else
				ui.P3_DrawDD(deck.Peek());
			ui.P3_DiscardDD(discardedCard);
			break;
		case 3:
			if (deck==MainDeck)
				ui.P4_DrawMD();
			else
				ui.P4_DrawDD(deck.Peek());
			ui.P4_DiscardDD(discardedCard);
			break;

		default:
			break;
		}
	}

	private boolean isNotOver() {
		for(Player player:players){
			if(player.hasWon()){
				return false;
			}
		}
		return true;
	}

	private void SelectPlayerToPlay() {
		if (currentPlayer!=null) return;
		if (players.contains(currentPlayer)) return;
		Random rand=new Random();
		int i=rand.nextInt(players.size());
		currentPlayer=players.get(i);
		ui.display(currentPlayer+" turn");
	}

	private void Deal() {
		for (int i = 0; i < 5; i++) {
			for(Player player:players){
				player.Draw(MainDeck);
			}
		}
		DicardedDeck.Insert(MainDeck.Draw());
		ui.shuffle(players.get(0).getCards(),DicardedDeck.Peek());
		
	}

	@Override
	public void run() {
		StartGame();
		
	}
	

}
