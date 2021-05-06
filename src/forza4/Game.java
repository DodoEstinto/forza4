/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

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
    public static void main(String[] args) {
        GameScreen gameScreen=new GameScreen("Forza 4");
        gameScreen.setVisible(true);
    }
    
}
