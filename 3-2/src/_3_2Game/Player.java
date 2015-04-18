package _3_2Game;

import java.util.ArrayList;

public class Player {
	protected String userName="";
	protected Deck hand=new Deck(true);
	public boolean turn=false;
	private Game gm;
	
	public void assignGame(Game gm){
		this.gm=gm;
	}
	
	public void setTurn(boolean value){
		synchronized (this) {
			turn=value;			
		}
	}
	
	public boolean getTurn(){
		synchronized (this) {
			return turn;
		}
	}
	
	public Player(String userName){
		this.userName=userName;
	}
	
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
	
	@Override
	public String toString() {
		return userName;
	}
}
