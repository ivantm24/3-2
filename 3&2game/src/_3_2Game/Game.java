package _3_2Game;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	Deck MainDeck;
	Deck DicardedDeck;
	ArrayList<Player> players;
	private UI_Updater ui;
	private Status status;
	private Player currentPlayer=null;
	
	public enum Status{
		OVER, PLAYING,NOTSTARTED		
	}
	
	public Game(ArrayList<Player> players, UI_Updater ui_adapter){
		this.players=players;
		this.ui=ui_adapter;
		this.MainDeck=new Deck(false);
		this.MainDeck.shuffle();
		this.DicardedDeck=new Deck(true);
		this.DicardedDeck.Insert(this.DicardedDeck.Draw());
		this.status=Status.NOTSTARTED;
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
	}

	private void NextPlayer() {
		int i=players.indexOf(currentPlayer)+1;
		if (i>=players.size()){
			i=0;
		}
		currentPlayer=players.get(i);
		
	}

	private void Play() {
		//TODO
		
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
	}

	private void Deal() {
		for (int i = 0; i < 5; i++) {
			for(Player player:players){
				player.Draw(MainDeck);
			}
		}
	}
	

}
