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
public class GameGrid {
    public static final int X_SIZE=7;
    public static final int Y_SIZE=6;
    public static final int VOID=0;
    public static final int PLAYER_1=1;
    public static final int PLAYER_2=2;
    private int grid[][];
    
    public GameGrid(){
        grid=new int[X_SIZE][Y_SIZE];
    }
    
    public int insert(int column,int player){
        boolean ris;
        int y=deepestPiece(column);
        if(y!=-1){
            grid[column][y]=player;
        }
        return y;
    }
    
    private int deepestPiece(int column){
        boolean found=false;
        int y;
        for(y=0;y<Y_SIZE;y++){
            if(grid[column][y]!=0){
                found=true;
            }
        }
        return found ? y-1 : y;
    }
}
