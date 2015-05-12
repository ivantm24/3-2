package _3_2Game;

/**
 * It represents an actual card (rank and suit). 
 * To String Method can be used to retrieve the image size
 */
public class Card {
	
	private final suits _suit;
	private final ranks _rank;
	
	public enum suits{
		diamonds, hearts, clubs, spades
	}
	public enum ranks{
		ace,_2,_3,_4,_5,_6,_7,_8,_9,_10,jack,queen,king
	}
	
	public Card(suits _suit, ranks _rank){
		this._suit=_suit;
		this._rank=_rank;
	}
	
	public boolean equalRank(Card card){
		return this._rank.equals(card.getRank());
	}
	
	public ranks getRank(){
		return this._rank;
	}
	public suits getSuit(){
		return this._suit;
	}
	@Override
	public String toString() {
            String tmp=_rank.toString();
            if (tmp.contains("_"))
                tmp=tmp.substring(1, tmp.length());
            return tmp+"_of_"+_suit.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

}
