package _3_2Game;

import java.util.ArrayList;

/**
 * Constains all player information
 */
public class Player {
	protected String userName="";
	protected Deck hand=new Deck(true);
	public boolean turn=false;
	private Game gm;
	
        /**
         * Assign player to a game
         * @param gm game
         */
	public void assignGame(Game gm){
		this.gm=gm;
	}
	
        /**
         * Set players turn
         * @param value true if it's player's turn 
         */
	public void setTurn(boolean value){
		synchronized (this) {
			turn=value;			
		}
	}
	
        /**
         * Retrieve Players turn
         * @return 
         */
	public boolean getTurn(){
		synchronized (this) {
			return turn;
		}
	}
	
        /**
         * Creates new player
         * @param userName 
         */
	public Player(String userName){
		this.userName=userName;
	}
	
        /**
         * Retrieves player's cards
         * @return 
         */
	public ArrayList<Card> getCards(){
		return hand.getCards();
	}
	
        /**
         * Player draw card from a deck and insert it in his hand
         * @param deck deck to draw card from
         * @return drew card
         */
	public Card Draw(Deck deck){
		return this.hand.Insert(deck.Draw());
	}
        /**
         * Player discard a card from his hand and inserted in another deck
         * @param card card to discard from hand
         * @param deck deck to insert card
         * @return discarded card
         */
	public Card Discard(Card card, Deck deck){
		if (this.hand.getCards().contains(card)){
		deck.Insert(this.hand.Draw(card));
		return card;
		}
		return null;
	}
	
	/**
         * Verifies if player has won
         * @return 
         */
	public boolean hasWon(){
		return hand.isWinningHand();
	}
        
        public String getUserName(){
            return this.userName;
        }
	
	@Override
	public String toString() {
		return userName;
	}
}
