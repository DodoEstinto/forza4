/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4.IO;

import forza4.logic.GameGrid;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Class needed to save the columns of a Connect 4 game. It helps to serialize all the
 * information to be easily saved on a file
 *
 * @author Paossi Davide
 */
public class SavedGameColumn implements Serializable {

    /**
     * A column of the game.
     */
    private int[] column;

    /**
     * Create a SavedGameColumn Object.
     */
    public SavedGameColumn() {
    }

    /**
     * Get the column.
     *
     * @return the column.
     */
    public int[] getColumn() {
        return column;
    }

    /**
     * Set the column if valid.
     *
     * @param column the column.
     */
    public void setColumn(int[] column) {
        if (checkColumn(column)) {
            this.column = column;
        }
    }
    
    /**
     * Check if the SavedGameColumn is valid
     *
     * @param column the SavedGameColumn to check
     * @return true if the SavedGameColumn is valid, false otherwise.
     */
    public static boolean checkColumn(SavedGameColumn column) {
        boolean ris;
        if(column==null){
            ris=false;
        }else{
            ris=checkColumn(column.getColumn());
        }
        return ris;
    }

    /**
     * Check if the column is valid
     *
     * @param column the column to check
     * @return true if the column is valid, false otherwise.
     */
    public static boolean checkColumn(int[] column) {
        boolean ris = true;
        if (column == null) {
            ris = false;
        } else {
            for (int v : column) {
                if (v != GameGrid.PLAYER_1 && v != GameGrid.PLAYER_2
                        && v != GameGrid.VOID) {
                    ris = false;
                    break;
                }
            }
        }
        return ris;
    }
    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString(){
        return Arrays.toString(column);
    }

}
