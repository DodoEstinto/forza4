/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import forza4.GUI.GameScreen;
import forza4.testers.FileManagerTester;
import forza4.testers.GameGridTester;
import forza4.testers.SavedGameColumnTester;
import forza4.testers.SavedGameTester;
import forza4.testers.TileTester;
import javax.swing.JOptionPane;

/**
 *
 * @author Paossi Davide
 */
public class Starter {
 
    
    public static void main(String[] args){
      Starter.startGame();
      //Options o=new Options(new GameScreen("aa","bb","cc"));
      //o.setVisible(true);
      //GameGridTester.test();
      //TileTester.test();
      //SavedGameColumnTester.test();
      //SavedGameTester.test();
      //FileManagerTester.test();
    }
    
    public static void startGame(){

        String Player1Name=JOptionPane.showInputDialog(null,"Come si chiamerà il giocatore 1?", "Title", JOptionPane.INFORMATION_MESSAGE);
        String Player2Name=JOptionPane.showInputDialog(null,"Come si chiamerà il giocatore 2?", "Title", JOptionPane.INFORMATION_MESSAGE);
        GameScreen gs=new GameScreen("Forza 4",Player1Name,Player2Name);
        gs.setVisible(true);
       
    }
}
