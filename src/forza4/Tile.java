/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author AdSumPro
 */
public class Tile extends JPanel {
    private static final float CIRCLE_RATIO=0.9f;
    private static final float DISTANCE_RATIO= ((1-CIRCLE_RATIO)/2);
    public Tile(){
        super();
    }
    
    public Tile(String name){
        super();
        this.setName(name);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        System.out.println("TAAC");
        //drawCircle(g,getWidth()/2,getHeight()/2,Math.min(getWidth(),getHeight()));
        drawCircle(g,getWidth(),getHeight());
    }
    
    /*private void drawCircle(Graphics g,int center_x,int center_y,int radius){
        g.draw
    
    }*/
    
    private void drawCircle(Graphics g,int width,int height){
        g.setColor(GameScreen.getBackgroundColor());
        g.fillOval((int)(width*DISTANCE_RATIO),(int)(height*DISTANCE_RATIO), (int)(width*CIRCLE_RATIO),(int)(height*CIRCLE_RATIO));
    }
}
