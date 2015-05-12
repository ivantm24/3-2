
package _3_2Game_DesktopGUI;

import _3_2Game.Card;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


public class HandGUI extends JPanel{
    
    private final boolean vertical;
    private GridBagConstraints c;
    private final Insets vInset;
    private final Insets hInset;
    private final ArrayList<CardGUI> cards;
    private int hiddenIndex=-1;
    public HandGUI(boolean vertical){
        super();
        cards=new ArrayList<>();
        this.vertical=vertical;
        setLayout(new GridBagLayout());
        c=new GridBagConstraints();
        vInset=new Insets(0,-60,0,0);//spacing
        hInset=new Insets(-60,0,0,0);
        c.insets=vInset;
        c.gridx=10;
        c.gridy=0;
        setOpaque(false);
    }
    
    public ArrayList<CardGUI> getCards(){
        return this.cards;
    }

    @Override
    public Component add(Component comp) {
        cards.add((CardGUI) comp);
        if (vertical){
            c.insets=vInset;
            c.gridx--;
        }else{
            c.insets=hInset;
            c.gridy++;
        }
        add(comp,c);
        return comp;
    }
    
    public void makeAllVisible(){
        for(CardGUI cGUI: cards){
            cGUI.setVisible(true);
        }
    }
    
    public void makeFirstInvisible(){
        CardGUI cGUI=cards.get(0);
        cGUI.setVisible(false);
    }
    
    public void insert(Card c){
        if (hiddenIndex==-1) return;
        CardGUI cGui=cards.get(hiddenIndex);
        try {
            cGui.setCard(c);
        } catch (IOException ex) {
            Logger.getLogger(HandGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        cGui.setVisible(true);
        hiddenIndex=-1;
    }
    
    public void remove(Card c){
        for(CardGUI cGUI:cards){
            if (c.equals(cGUI.getCard())){
                hiddenIndex=cards.indexOf(cGUI);
                cGUI.setVisible(false);
                break;
            }
        }
    }
    
    public void hide(Card c){
        
    }
    
    public void shuffled(){
        int i=cards.size()-1;
        cards.get(i).setVisible(false);
        hiddenIndex=i;
    }
    
    public void shuffled(ArrayList<Card> cards) throws IOException{
        for (int i = 0; i < cards.size(); i++) {
            if (this.cards.size()<=i) break;
            this.cards.get(i).setCard(cards.get(i));
        }
        this.shuffled();
    }
    
    public void update(ArrayList<Card> cards) throws IOException{
        for(int i=0;i<this.cards.size();i++){
            if (cards.size()<=i){
                this.cards.get(i).setVisible(false);
            }else{
                this.cards.get(i).setCard(cards.get(i));
            }
        }
    }
    
}
