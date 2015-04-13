package _3_2Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		ArrayList<Player> players=new ArrayList<Player>();
		Player P1=new Player("ivan");
		players.add(P1);
		players.add(new Player("CPU2"));
		players.add(new Player("CPU3"));
		players.add(new Player("CPU4"));
		UI_Updater ui= new UI_cmd();
		Game gm=new Game(players,ui);
		new Thread(gm).start();
		
		int i;Deck deck;
		Scanner s = new Scanner(System.in);
		while(gm.getStatus()!=Game.Status.OVER){
			//System.out.println(gm.getStatus());
			if (P1.getTurn()){
				System.out.println("Select 1 for MD, 2 for DD:");
				i=s.nextInt();
				if (i==1){
					deck=gm.MainDeck;
				}else{
					deck=gm.DicardedDeck;
				}
				Card drewCard=P1.Draw(deck);
				if(i==1)
					ui.P1_DrawMD(drewCard);
				else
					ui.P1_DrawDD(drewCard, gm.DicardedDeck.Peek());
				System.out.println("Select card to dicard:");
				i=s.nextInt();
				Card discardedCard=P1.Discard(P1.getCards().get(i), gm.DicardedDeck);
				ui.P1_DiscardDD(discardedCard);
				P1.setTurn(false);
				//gm.notify();
			}
		}

	}

}
