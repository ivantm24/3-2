package _3_2Game;

public class Card {
	
	private final suits _suit;
	private final ranks _rank;
	
	public enum suits{
		diamods, hearts, clubs, spades
	}
	public enum ranks{
		Ace,_2,_3,_4,_5,_6,_7,_8,_9,_10,Jack,Queen,King
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
	
	@Override
	public String toString() {
		return _rank.toString()+"."+_suit.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

}
