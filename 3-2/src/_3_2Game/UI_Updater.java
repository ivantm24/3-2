package _3_2Game;

import java.util.ArrayList;

/**
 * This class is responsible of updating the user interface while a game is happening
 * P1 represents the user of the window
 */
public interface UI_Updater {
	
	ArrayList<Card> P1Cards=new ArrayList<>();
	
        /**
         * User draw a card from Main Deck
         * @param card drew card
         */
	public void P1_DrawMD(Card card);
        /**
         * P2 draw card from MD
         */
	public void P2_DrawMD();
        /**
         * P3 draw card from MD
         */
	public void P3_DrawMD();
        /**
         * P4 draw card from MD
         */
	public void P4_DrawMD();
	
        /**
         * User draw a card from Discarded Deck
         * @param DrawnCard Drawn Card
         * @param visibleCard new card on top of Discarded Deck
         */
	public void P1_DrawDD(Card DrawnCard, Card visibleCard);
        /**
         * P2 draw a card from Discarded Deck
         * @param visibleCard new card on top of Discarded Deck
         */
	public void P2_DrawDD(Card visibleCard);
        /**
         * P3 draw a card from Discarded Deck
         * @param visibleCard new card on top of Discarded Deck
         */
	public void P3_DrawDD(Card visibleCard);
        /**
         * P4 draw a card from Discarded Deck
         * @param visibleCard new card on top of Discarded Deck
         */
	public void P4_DrawDD(Card visibleCard);
	
        /**
         * User discard a card
         * @param card discarded card
         */
	public void P1_DiscardDD(Card card);
        /**
         * P2 discard a card
         * @param card discarded card
         */
	public void P2_DiscardDD(Card card);
        /**
         * P3 discard a card
         * @param card discarded card
         */
	public void P3_DiscardDD(Card card);
        /**
         * P4 discard a card
         * @param card discarded card
         */
	public void P4_DiscardDD(Card card);
	
        /**
         * Cards were shuffled
         * @param P1_cards cards of user
         * @param cardOnDD card on top of Discarded Deck
         */
	public void shuffle(ArrayList<Card> P1_cards,Card cardOnDD);
	
        /**
         * Display message to other users
         * @param msg message
         */
	public void display(String msg);
        
        /**
         * Displays winning message
         * @param username winner's username
         */
        public void win(String username);
        
        

}
