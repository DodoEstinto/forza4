/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import java.io.Serializable;

/**
 *
 * @author AdSumPro
 */
public class SavedGameColumn implements Serializable{
    private int[] column;
    private int currentPlayer;

    public SavedGameColumn(){}
    
    public int[] getColumn() {
        return column;
    }

    public void setColumn(int[] column) {
        this.column = column;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    
}
