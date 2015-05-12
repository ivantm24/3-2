/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3_2Game_DesktopGUI;

import _3_2Game.Card;
import _3_2Game_Client.Player_Client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ivantactukmercado
 */
public class MultiplayerWindow extends JFrame{
    
    private Image image;
    private Player_Client Player_Client;
    String serverAddress;
    Socket socket;
    boolean connected_to_table = false;
    GUIUpdater ui;
            
    public MultiplayerWindow(String username,String ip) throws IOException, Exception {
        initUI(username,ip);
    }

    private void initUI(String username,String ip) throws IOException, Exception {
        this.serverAddress=ip;
        setTitle("3 & 2 game");
        setMinimumSize(new Dimension(740, 600));
        getContentPane().setBackground(Color.orange);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GameBoardGUI gm=new GameBoardGUI();
        GridBagConstraints cGm = new GridBagConstraints();
        cGm.gridx=0;
        cGm.gridy=0;
        cGm.fill=GridBagConstraints.NONE;
        add(gm,cGm);
        TableGUI tb=new TableGUI();
        GridBagConstraints cTb = new GridBagConstraints();
        cTb.gridx=0;
        cTb.gridy=1;
        cTb.fill=GridBagConstraints.BOTH;
        cTb.weighty=1;
        add(tb,cTb);
        ChatGUI ch=new ChatGUI();
        GridBagConstraints cCh = new GridBagConstraints();
        cCh.gridx=1;
        cCh.gridy=0;
        cCh.fill=GridBagConstraints.BOTH;
        cCh.gridheight=2;
        cCh.weightx=1;
        cCh.weighty=1;
        add(ch,cCh);
        ui=new GUIUpdater(ch.getChatViewer(), gm);
        socket = new Socket(serverAddress, 3232);
        this.Player_Client = new Player_Client(username,socket,ui);
        this.Player_Client.connectToServer();
        tb.setEventListener(ui, Player_Client,ch);
        gm.setEventListener(Player_Client);
        
        
    }
    
    private BufferedImage loadImage() throws IOException {

        File fl=new File("cards/10_of_clubs.png");
        BufferedImage bimg = ImageIO.read(fl);
        //System.out.println(bimg.getHeight());
        //System.out.println(bimg.getWidth());
///////ojo
        //original 500x726
        bimg=resizeImage(bimg, 63, 92, BufferedImage.TYPE_INT_ARGB);
        return bimg;
    }
    
    private BufferedImage loadImage(String filename) throws IOException {

        File fl=new File(filename);
        BufferedImage bimg = ImageIO.read(fl);
        return bimg;
    }
    
    /**
     * rotates image. use Math.toRadians(degree) to convert from degree to radians
     * @param image
     * @param angle angles in radians
     * @return 
     */
    public  BufferedImage rotate(BufferedImage image, double angle) {
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
    
    public static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width,
            int height, int type) throws IOException {

        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }
    
    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                MultiplayerWindow ex;
                try {
                    ex = new MultiplayerWindow("ivantm24","127.0.0.1");
                    ex.setVisible(true);
                } catch (IOException ex1) {
                    Logger.getLogger(MultiplayerWindow.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (Exception ex1) {
                    Logger.getLogger(MultiplayerWindow.class.getName()).log(Level.SEVERE, null, ex1);
                }
                
            }
        });
    }
}
