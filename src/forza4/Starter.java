
package forza4;

import forza4.GUI.GameScreen;
import forza4.testers.FileManagerTester;
import forza4.testers.GameGridTester;
import forza4.testers.GameScreenTester;
import forza4.testers.SavedGameColumnTester;
import forza4.testers.SavedGameTester;
import forza4.testers.TileTester;
import javax.swing.JOptionPane;

/**
 * Class needed to start the game.
 * @author Paossi Davide
 */
public class Starter {
 
    
    public static void main(String[] args){
      Starter.startGame();
      //GameGridTester.test();
      //GameScreenTester.test();
      //TileTester.test();
      //SavedGameColumnTester.test();
      //SavedGameTester.test();
      //FileManagerTester.test();
    }
    /**
     * Starts the game. Creates a new Gamescreen and sets it to visible.
     */
    public static void startGame(){

        String Player1Name=JOptionPane.showInputDialog(null,"What's the name of player 1?", "Name Selection", JOptionPane.INFORMATION_MESSAGE);
        String Player2Name=JOptionPane.showInputDialog(null,"What's the name of player 2?", "Name Selection", JOptionPane.INFORMATION_MESSAGE);
        GameScreen gs=new GameScreen("Forza 4",Player1Name,Player2Name);
        gs.setVisible(true);
       
    }
   
}
