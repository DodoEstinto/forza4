/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import javax.swing.JOptionPane;

/**
 *
 * @author Paossi Davide
 */
public class Starter {
 
    
    public static void main(String[] args){
        Starter.startGame();
    }
    
    public static void startGame(){

        String Player1Name=JOptionPane.showInputDialog(null,"Come si chiamerà il giocatore 1?", "Title", JOptionPane.INFORMATION_MESSAGE);
        String Player2Name=JOptionPane.showInputDialog(null,"Come si chiamerà il giocatore 2?", "Title", JOptionPane.INFORMATION_MESSAGE);
        GameScreen gs=new GameScreen("Forza 4",Player1Name,Player2Name);
        gs.setVisible(true);
       
    }
}
