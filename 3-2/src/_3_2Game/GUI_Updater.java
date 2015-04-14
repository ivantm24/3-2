/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game;
import java.util.ArrayList;
/**
 *
 * @author neloh
 */
public class GUI_Updater implements UI_Updater {
    
    ArrayList<Card> P1Cards = new ArrayList<>();
    
    @Override
    public void display(String msg){
        int x =0;
    }
    
    @Override
    public void shuffle(ArrayList<Card> P1_cards,Card cardOnDD){
        int x = 0;
    };
    	
    @Override
    public void P1_DrawMD(Card card){};
    @Override
    public void P2_DrawMD(){};
    @Override
    public void P3_DrawMD(){};
    @Override
    public void P4_DrawMD(){};
	
    @Override
    public void P1_DrawDD(Card DrawnCard, Card visibleCard){};
    @Override
    public void P2_DrawDD(Card visibleCard){};
    @Override
    public void P3_DrawDD(Card visibleCard){};
    @Override
    public void P4_DrawDD(Card visibleCard){};
	
    @Override
    public void P1_DiscardDD(Card card){};
    @Override
    public void P2_DiscardDD(Card card){};
    @Override
    public void P3_DiscardDD(Card card){};
    @Override
    public void P4_DiscardDD(Card card){};
    
}
