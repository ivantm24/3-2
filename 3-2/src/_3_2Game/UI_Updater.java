package _3_2Game;

import java.util.ArrayList;

public interface UI_Updater {
	
	ArrayList<Card> P1Cards=new ArrayList<>();
	
	public void P1_DrawMD(Card card);
	public void P2_DrawMD();
	public void P3_DrawMD();
	public void P4_DrawMD();
	
	public void P1_DrawDD(Card DrawnCard, Card visibleCard);
	public void P2_DrawDD(Card visibleCard);
	public void P3_DrawDD(Card visibleCard);
	public void P4_DrawDD(Card visibleCard);
	
	public void P1_DiscardDD(Card card);
	public void P2_DiscardDD(Card card);
	public void P3_DiscardDD(Card card);
	public void P4_DiscardDD(Card card);
	
	public void shuffle(ArrayList<Card> P1_cards,Card cardOnDD);
	
	public void display(String msg);

}
