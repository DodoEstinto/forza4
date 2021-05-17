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
public class SavedGame implements Serializable{
    private SavedGameColumn[] columns;

    public int[][] getGrid() {
        int[][] grid=new int[columns.length][];
        for(int i=0;i<columns.length;i++){
            grid[i]=columns[i].getColumn();
        }
        return grid;
    }

    public void setGrid(int[][] grid) {
        columns=new SavedGameColumn[grid.length];
        for(int i=0;i<columns.length;i++){
            columns[i]=new SavedGameColumn();
            columns[i].setColumn(grid[i]);
        }
    }
    public SavedGame() {
    }
}
