package _3_2Game;

import java.util.ArrayList;

public class Game {
	Deck MainDeck;
	Deck DicardedDeck;
	ArrayList<Player> players;
	private UI_Updater ui;
	private Status status;
	Player currentPlayer;
	
	public enum Status{
		OVER, PLAYING		
	}
	
	public Game(ArrayList<Player> players, UI_Updater ui_adapter){
		this.players=players;
		this.ui=ui_adapter;
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
		// TODO Auto-generated method stub
		
	}

	private void Play() {
		// TODO Auto-generated method stub
		
	}

	private boolean isNotOver() {
		// TODO Auto-generated method stub
		return false;
	}

	private void SelectPlayerToPlay() {
		// TODO Auto-generated method stub
		
	}

	private void Deal() {
		// TODO Auto-generated method stub
		
	}
	

}
