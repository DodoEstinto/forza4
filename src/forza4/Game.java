/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 *
 * @author AdSumPro
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
       GameScreen gameScreen=new GameScreen("Forza 4");
       gameScreen.setVisible(true);
       
       
       //TEST PER IL REPAINT
       /**
       JFrame frame= new JFrame("ciao");
       Tile tile=new Tile("tile");
       frame.add(tile);
       frame.setVisible(true);
       frame.setSize(300, 300);
       tile.setBackground(Color.red);
       while(true){
       Thread.sleep(200);
       tile.repaint();
       }
       **/
        
    }
    
}
