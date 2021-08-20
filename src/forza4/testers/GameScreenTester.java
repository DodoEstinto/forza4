
package forza4.testers;

import forza4.GUI.GameScreen;
import java.awt.Color;

/**
 * Tests the GameScreen class.
 * @author Davide Paossi
 */
public class GameScreenTester {
    
    public static void test(){
        GameScreen gs=new GameScreen("a","b","c");
        System.out.println("Setting background color");
        System.out.println("Expected: java.awt.Color[r=100,g=100,b=100]");
        gs.setBackgroundColor(new Color(100,100,100));
        System.out.println("Actual: "+gs.getBackgroundColor());
        System.out.println("\nSetting border color");
        System.out.println("Expected: java.awt.Color[r=150,g=150,b=150]");
        gs.setBorderColor(new Color(150,150,150));
        System.out.println("Actual: "+gs.getBorderColor());
        System.out.println("\nSetting grid color");
        System.out.println("Expected: java.awt.Color[r=10,g=10,b=10]");
        gs.setGridColor(new Color(10,10,10));
        System.out.println("Actual: "+gs.getGridColor());
        System.out.println("\nSetting player 1 color");
        System.out.println("Expected: java.awt.Color[r=200,g=100,b=50]");
        gs.setPlayer1Color(new Color(200,100,50));
        System.out.println("Actual: "+gs.getPlayer1Color());
        System.out.println("\nSetting player 2 color");
        System.out.println("Expected: java.awt.Color[r=60,g=40,b=30]");
        gs.setPlayer2Color(new Color(60,40,30));
        System.out.println("Actual: "+gs.getPlayer2Color());
       
    }
}
