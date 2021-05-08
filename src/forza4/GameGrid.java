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
    public static final int TIE=0;
    public static final int PLAYER_1_WIN=1;
    public static final int PLAYER_2_WIN=2;
    public static final int NO_WIN=3;
    private int grid[][];
    private int left;
    
    public GameGrid(){
        grid=new int[X_SIZE][Y_SIZE];
        left=X_SIZE*Y_SIZE;
    }
    
    public int insert(int column,int player){
        boolean ris;
        int y=deepestPiece(column);
        if(y!=-1){
            grid[column][y]=player;
            left-=1;
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
    
    public int checkVictory(int column,int row,int player){
        if(left!=0){
            if(grid[column][row]==player){
                int right=checkRight(column,row,player);
                if(right>=3){
                    return player;
                }

                int left=checkLeft(column,row,player);
                if(right+left>=3){
                    return player;
                }

                int up=checkUp(column,row,player);
                if(up>=3){
                    return player;       
                }

                int down=checkDown(column,row,player);
                if(up+down>=3){
                    return player;
                }

                int upRight=checkUpRight(column,row,player);
                if(upRight>=3){
                    return player;
                }

                int downLeft=checkDownLeft(column,row,player);
                if(downLeft+upRight>=3){
                    return player;
                }

                int upLeft=checkUpLeft(column,row,player);
                if(upLeft>=3){
                    return player;
                }
                int downRight=checkDownRight(column,row,player);
                if(downRight+upLeft>=3){
                    return player;
                }
            }
            return NO_WIN;
        }else{
            return TIE;
        }
    }
    
    private int checkRight(int column,int row,int player){
    
    
    }

    private int checkLeft(int column,int row,int player){
    }
    
    private int checkUp(int column,int row,int player){
    }
    
    private int checkDown(int column,int row,int player){
    }
    
    private int checkUpLeft(int column,int row,int player){
    }
    
    private int checkUpRight(int column,int row,int player){
    }
    
    private int checkDownLeft(int column,int row,int player){
    }
    
    private int checkDownRight(int column,int row,int player){
    }
}
