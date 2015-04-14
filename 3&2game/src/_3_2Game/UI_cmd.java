package _3_2Game;

import java.util.ArrayList;

public class UI_cmd implements UI_Updater{

	@Override
	public void P1_DrawMD(Card card) {
		System.out.println("P1 Drew card "+card+" from Main Deck");
	}

	@Override
	public void P2_DrawMD() {
		System.out.println("P2 Drew a card from Main Deck");
	}

	@Override
	public void P3_DrawMD() {
		System.out.println("P3 Drew a card from Main Deck");
		
	}

	@Override
	public void P4_DrawMD() {
		System.out.println("P4 Drew a card from Main Deck");
	}

	@Override
	public void P1_DrawDD(Card DrawnCard, Card visibleCard) {
		System.out.println("P1 Drew card "+DrawnCard+" from Discarded Deck");
		System.out.println("New Card on Discarded Deck: "+ visibleCard);
	}

	@Override
	public void P2_DrawDD(Card visibleCard) {
		System.out.println("P2 Drew card from Discarded Deck");
		System.out.println("New Card on Discarded Deck: "+ visibleCard);
	}

	@Override
	public void P3_DrawDD(Card visibleCard) {
		System.out.println("P3 Drew card from Discarded Deck");
		System.out.println("New Card on Discarded Deck: "+ visibleCard);
	}

	@Override
	public void P4_DrawDD(Card visibleCard) {
		System.out.println("P4 Drew card from Discarded Deck");
		System.out.println("New Card on Discarded Deck: "+ visibleCard);
	}

	@Override
	public void P1_DiscardDD(Card card) {
		System.out.println("New Card on Discarded Deck: "+ card);
		
	}

	@Override
	public void P2_DiscardDD(Card card) {
		System.out.println("New Card on Discarded Deck: "+ card);
		
	}

	@Override
	public void P3_DiscardDD(Card card) {
		System.out.println("New Card on Discarded Deck: "+ card);
		
	}

	@Override
	public void P4_DiscardDD(Card card) {
		System.out.println("New Card on Discarded Deck: "+ card);
		
	}

	@Override
	public void shuffle(ArrayList<Card> P1_cards, Card cardOnDD) {
		System.out.print("P1 cards: ");
		for(Card card:P1_cards){
			System.out.print(card+" ");
		}
		System.out.println();
		System.out.println("Card on Dicarded deck: "+cardOnDD);
	}

	@Override
	public void display(String msg) {
		System.out.println("MESSAGE: "+msg);
		
	}

}
