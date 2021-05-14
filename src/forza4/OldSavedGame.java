/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author AdSumPro
 */
public class OldSavedGame implements Serializable{
    private int[][] grid;
    int actualPlayer;
    
    public OldSavedGame(int[][] grid,int player){
        this.grid=grid;
        this.actualPlayer=player;
    }
    /**
     * Return a COPY of the grid.
     * @return the COPY of the grid.
     */
    public int[][] getGrid(){
        int[][] copyGrid=new int[grid.length][];
        for(int i=0;i<grid.length;i++){
            copyGrid[i]=Arrays.copyOf(grid[i],grid[i].length);
        }
        return copyGrid;
    }
}
