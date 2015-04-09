package _3_2Game;

import java.util.ArrayList;

public interface UI_Updater {
	
	public void P1_DrawMD(Card card);
	public void P2_DrawMD(Card card);
	public void P3_DrawMD(Card card);
	public void P4_DrawMD(Card card);
	
	public void P1_DrawDD(Card card);
	public void P2_DrawDD(Card card);
	public void P3_DrawDD(Card card);
	public void P4_DrawDD(Card card);
	
	public void P1_DiscardMD(Card card);
	public void P2_DiscardMD(Card card);
	public void P3_DiscardMD(Card card);
	public void P4_DiscardMD(Card card);
	
	public void P1_DiscardDD(Card card);
	public void P2_DiscardDD(Card card);
	public void P3_DiscardDD(Card card);
	public void P4_DiscardDD(Card card);
	
	public void shuffle(ArrayList<Card> P1_cards);
	
	public void display(String msg);

}
