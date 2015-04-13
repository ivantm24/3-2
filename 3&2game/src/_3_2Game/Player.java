package _3_2Game;

import java.util.ArrayList;

public class Player {
	private String userName="";
	private Deck hand;
	
	public ArrayList<Card> getCards(){
		return hand.getCards();
	}
	
	public Card Draw(Deck deck){
		return this.hand.Insert(deck.Draw());
	}
	public Card Discard(Card card, Deck deck){
		if (this.hand.getCards().contains(card)){
		deck.Insert(this.hand.Draw(card));
		return card;
		}
		return null;
	}
	
	
	public boolean hasWon(){
		return hand.isWinningHand();
	}
}
