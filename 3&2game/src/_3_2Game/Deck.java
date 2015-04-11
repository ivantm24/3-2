package _3_2Game;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards=new ArrayList<Card>();
	
	public Deck(boolean empty){
		if (!empty){
			generateFullDeck();
		}
	}
	
	public void Insert(Card card){
		cards.add(card);
	}
	
	public Card Draw(){
		int size=cards.size();
		if (size==0) return null;
		Card card=cards.get(size-1);
		cards.remove(size-1);
		return card;
		
	}
	
	public boolean isEmpty(){
		return cards.isEmpty();
	}
	
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
	
	public boolean isWinningHand(){
		Card.ranks rank1=null;
		Card.ranks rank2=null;
		int rank1Count=0, rank2Count=0;
		
		for(Card card:cards){
			if (rank1==null){
				rank1=card.getRank();
				rank1Count++;
			}else{
				if (rank2==null){
					rank2=card.getRank();
					rank2Count++;
				}else{
					if (rank1==card.getRank()) rank1Count++;
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
	
	private void generateFullDeck() {
		for (int _suit = 0; _suit < 4; _suit++) {
			for (int _rank = 0; _rank < 13; _rank++) {
				cards.add(new Card(Card.suits.values()[_suit], 
						Card.ranks.values()[_rank]));
			}
		}
		
	}
}
