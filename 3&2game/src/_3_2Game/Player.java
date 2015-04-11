package _3_2Game;

import java.util.ArrayList;

public class Player {
	private String userName="";
	private Deck hand;
	private boolean isPlaying=false;
	
	public ArrayList<Card> getCards(){
		return hand.getCards();
	}
	
	public Card Draw(Deck deck){
		return this.hand.Insert(deck.Draw());
	}
	public void Discard(Card card, Deck deck){
		if (this.hand.getCards().contains(card)){
		deck.Insert(this.hand.Draw(card));
		}
	}
	
	public void startPlaying(){
		isPlaying=true;
		while(isPlaying);
	}
	public void played(){
		isPlaying=false;
	}
	
	public boolean hasWon(){
		return hand.isWinningHand();
	}
}
