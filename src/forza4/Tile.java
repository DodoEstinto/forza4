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
    /**
     * The ratio between the circle radious and the square.    
     */
    private static final float CIRCLE_RATIO=0.9f;
    /**
     * 
     */
    private static final float DISTANCE_RATIO= ((1-CIRCLE_RATIO)/2);
     /**
     * The tile is void.
     */
    public static final int STATUS_VOID=0;
    /**
     * The tile is occuped by the Player1.
     */
    public static final int STATUS_PLAYER1=1;
    /**
     * The tile is occuped by the Player2.
     */
    public static final int STATUS_PLAYER2=2;
    /**
     * Current status.
     */
    private int status;
    /**
     * Create a void Tile with no name.
    */
    public Tile(){
        super();
        status=STATUS_VOID;
    }
    
    /**
     * Create a Tile with a specific name.
     * @param name the name of the tile.
     */
    public Tile(String name){
        super();
        this.setName(name);
        status=STATUS_VOID;
    }
    
    /**
     * Paint the tile with the colors associated with the actual status of the tile and the configuration.
     * @param g the graphics of the tile.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawCircle(g,getWidth(),getHeight());
    }
    
    /**
     * Draw the disc hole or the player disc depending on the current status of the tile.
     * @param g the graphics of the tile.
     * @param width the width of the tile.
     * @param height the height of the tile.
     */
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

    /**
     * Get the actual status.
     * @return The actual status.
     */
    public int getStatus() {
        return status;
    }
    /**
     * Set the actual status.
     * @param status The status to set.
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    public void reset(){
        status=STATUS_VOID;
        
    }
    
    
}

