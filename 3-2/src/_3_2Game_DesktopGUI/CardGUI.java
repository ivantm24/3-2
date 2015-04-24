/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_DesktopGUI;

import _3_2Game.Card;
import static _3_2Game_DesktopGUI.test.getDefaultConfiguration;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author ivantactukmercado
 */
public class CardGUI extends JButton{
    private Card card;
    private boolean vertical;
    Image im;
    
    public CardGUI(Card card, boolean vertical) throws IOException{
        super();
        this.card=card;
        this.vertical=vertical;
        
        if (this.vertical)
            im=loadImage(card);
        else
            im=rotate(loadImage(card),Math.toRadians(90));
        setIcon(new ImageIcon(im));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        
    }
    
    public Card getCard(){
        return this.card;
    }
    
    public void setCard(Card card) throws IOException{
        this.card=card;
        Image im;
        if (this.vertical)
            im=loadImage(card);
        else
            im=rotate(loadImage(card),90);
        setIcon(new ImageIcon(im));
    }
    
    public Image getImage(){
        return im;
    }

    private BufferedImage loadImage(Card card) throws IOException {

        File fl;
        if (card!=null)
            fl=new File("cards/"+card.toString()+".png");
        else
            fl=new File("cards/b1fv.png");
        BufferedImage bimg = ImageIO.read(fl);
        //System.out.println(bimg.getHeight());
        //System.out.println(bimg.getWidth());
        //original 500x726
        bimg=resizeImage(bimg, 63, 92, BufferedImage.TYPE_INT_ARGB);
        return bimg;
    }
    
    private BufferedImage resizeImage(BufferedImage originalImage, int width,
            int height, int type) throws IOException {

        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }
    
    private  BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww-w)/2, (newh-h)/2);
        g.rotate(angle, w/2, h/2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }
    
}
