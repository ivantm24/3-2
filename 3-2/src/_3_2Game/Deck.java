package _3_2Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Deck or Hand of Cards. 
 * It contains an array of Cards.
 * It can shuffle itself
 * It can generate a full deck
 * It determines if it contains winning cards.
 */
public class Deck {
	private ArrayList<Card> cards=new ArrayList<Card>();
	
        /**
         * Creates a new Deck or Hand of cards
         * @param empty it is true if the new deck is going to be empty
         */
	public Deck(boolean empty){
		if (!empty){
			generateFullDeck();
		}
	}
	
        /**
         * Retrieve list of cards in deck
         * @return list of cards
         */
	public ArrayList<Card> getCards(){
		return cards;
	}
	
        /**
         * It inserts a card in the deck
         * @param card card to insert
         * @return card inserted
         */
	public Card Insert(Card card){
		if (card==null) return null;
		cards.add(card);
		return card;
	}
	
        /**
         * Draw the card on top of the deck
         * @return drew card
         */
	public Card Draw(){
		Card card=Peek();
		cards.remove(card);
		return card;
	}
        
        /**
         * Returns the card on top of the Deck
         * @return card on top of the Deck
         */
	public Card Peek(){
		int size=cards.size();
		if (size==0) return null;
		Card card=cards.get(size-1);
		return card;
	}
	
        /**
         * Draw a specific card from the Deck
         * @param card card to draw
         * @return  drew card
         */
	public Card Draw(Card card){
		int i;
		
		for (i = 0; i < cards.size(); i++) {
			if (card.equals(cards.get(i)))
				break;
		}
		if (cards.size()==i) return null;
		cards.remove(i);
		return card;
	}
	
        /**
         * Verifies if the Deck is empty
         * @return true if the Deck is empty
         */
	public boolean isEmpty(){
		return cards.isEmpty();
	}
	
        /**
         * Shuffles the Deck
         */
	public void shuffle(){
		ArrayList<Card> newCardsShuffled=new ArrayList<Card>();
		Random rand = new Random();
		int size=cards.size(), ranIndex;
		while(size>0){
			size--;
			if (size!=0)
				ranIndex= rand.nextInt(size);
			else
				ranIndex=0;
			newCardsShuffled.add(cards.get(ranIndex));
			cards.remove(ranIndex);
		}
		cards=newCardsShuffled;
	}
	
        /**
         * Verifies if the Deck is a wining Deck
         * @return true if Deck have 3&2
         */
	public boolean isWinningHand(){
		Card.ranks rank1=null;
		Card.ranks rank2=null;
		int rank1Count=0, rank2Count=0;
		
		for(Card card:cards){
			if (rank1==null){
				rank1=card.getRank();
				rank1Count++;
			}else{
                                if (rank1==card.getRank()) rank1Count++;
                                else if (rank2==null){
                                            rank2=card.getRank();
                                            rank2Count++;
				}else{
					
					if (rank2==card.getRank()) rank2Count++;
				}
			}
			if (rank1Count>3||rank2Count>3)
				break;
		}
		if (rank1Count==3 &&rank2Count==2) return true;
		if (rank1Count==2 &&rank2Count==3) return true;
		return false;
	}
	
        /**
         * Fills up Deck with 52 cards
         */
	private void generateFullDeck() {
		for (int _suit = 0; _suit < 4; _suit++) {
			for (int _rank = 0; _rank < 13; _rank++) {
				cards.add(new Card(Card.suits.values()[_suit], 
						Card.ranks.values()[_rank]));
			}
		}
		
	}
}
