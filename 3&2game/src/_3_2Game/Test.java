package _3_2Game;

public class Test {

	public static void main(String[] args) {
		Deck d=new Deck(false);
		d.shuffle();
		int i=1;
		while(!d.isEmpty()){
			System.out.println(i+")"+d.Draw());
			i++;
		}

	}

}
