/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author AdSumPro
 */
public class Tile extends JPanel {
    private static final float CIRCLE_RATIO=0.9f;
    private static final float DISTANCE_RATIO= ((1-CIRCLE_RATIO)/2);
    public static final int STATUS_VOID=0;
    public static final int STATUS_PLAYER1=1;
    public static final int STATUS_PLAYER2=2;
    private int status;
    public Tile(){
        super();
        status=STATUS_VOID;
    }
    
    public Tile(String name){
        super();
        this.setName(name);
        status=STATUS_VOID;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        System.out.println("TAAC");
        drawCircle(g,getWidth(),getHeight());
    }
    
    private void drawCircle(Graphics g,int width,int height){
        switch(status){
            case STATUS_PLAYER1:
                g.setColor(GameScreen.getPlayer1Color());
                break;
            case STATUS_PLAYER2:
                g.setColor(GameScreen.getPlayer2Color());
                break;
            case STATUS_VOID:
            default:
                g.setColor(GameScreen.getBackgroundColor());
        }
        g.fillOval((int)(width*DISTANCE_RATIO),(int)(height*DISTANCE_RATIO), (int)(width*CIRCLE_RATIO),(int)(height*CIRCLE_RATIO));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}

