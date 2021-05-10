/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

/**
 *
 * @author AdSumPro
 */
public class GameManager {
    
    GameScreen gs;
    GameGrid gg;
    
    public GameManager(){
    
    }
    
    public void startGame(){
        gg=new GameGrid();
        gs=new GameScreen("Forza 4",gg);
        gs.setVisible(true);
       
    }
}
