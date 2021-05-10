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
    /**
     * Number of column of elements of the grid.
     */
    public static final int X_SIZE=7;
    /**
     * The number of the rows of the grid.
     */
    public static final int Y_SIZE=7;
    /**
     * The value that rappresents a hole.
     */
    public static final int VOID=0;
    /**
     * The value that rappresents a hole occuped by the player 1.
     */
    public static final int PLAYER_1=1;
    /**
     * The value that rappresents a hole occuped by the player 2.
     */
    public static final int PLAYER_2=2;
    /**
     * The value that rappresents a tie.
     */
    public static final int TIE=0;
    /**
     * The value that rappresents the victory of the player 1.
     */
    public static final int PLAYER_1_WIN=1;
    /**
     * The value that rappresents the victory of player 2.
     */
    public static final int PLAYER_2_WIN=2;
    /**
     * The value that rappresents that the game is still going on.
     */
    public static final int NO_WIN=3;
    /**
     * The grid
     */
    private int grid[][];
    /**
     * How many holes are left
     */
    private int leftPieces;
    /**
     * The player that have to play.
     */
    private int currentPlayer;
    /**
     * Create a grid.
     */
    public GameGrid(){
        grid=new int[X_SIZE][Y_SIZE];
        leftPieces=X_SIZE*Y_SIZE;
        currentPlayer=1;
    }
    
    /**
     * Insert a piece in the grid.
     * @param column The column where the piece is inserted.
     * @param player The player that is inserting the piece.
     * @return the height of the hole where the piece has stopped. If there is no room returns -1.
     */
    public int insert(int column){
        boolean ris;
        int y=deepestPiece(column);
        if(y!=-1){
            grid[column][y]=currentPlayer;
            leftPieces-=1;
        }
        return y;
    }
    
    /**
     * Finds the deepest empty hole where the piece can be insterd.
     * @param column The column to check
     * @return the height of the hole where the piece would stop. If there is no room returns -1.
     */
    private int deepestPiece(int column){
        boolean found=false;
        int y;
        for(y=0;y<Y_SIZE;y++){
            if(grid[column][y]!=0){
                found=true;
                break;
            }
        }
        return y-1;
        //return found ? y-1 : y;
    }
    /**
     *  Check the victory.
     * @param column the column of the last inserted piece.
     * @param row the row of the last inserted piece.
     * @param player the player that inserted the last piece.
     * @return the status of the game (win,tie,no win).
     */
    public int checkVictory(int column,int row){
        if(leftPieces!=0){
            /*check if the given hole corrisponds to the player 
            (counts as the first piece of four to win)*/
            if(grid[column][row]==currentPlayer){
                /*from there we'll search for only 3 more pieces,
                the first has been already counted*/
                int right=checkRight(column+1,row);
                if(right>=3){
                    return currentPlayer;
                }

                int left=checkLeft(column-1,row);
                if(right+left>=3){
                    return currentPlayer;
                }

                int up=checkUp(column,row-1);
                if(up>=3){
                    return currentPlayer;       
                }

                int down=checkDown(column,row+1);
                if(up+down>=3){
                    return currentPlayer;
                }

                int upRight=checkUpRight(column+1,row-1);
                if(upRight>=3){
                    return currentPlayer;
                }

                int downLeft=checkDownLeft(column-1,row+1);
                if(downLeft+upRight>=3){
                    return currentPlayer;
                }

                int upLeft=checkUpLeft(column-1,row-1);
                if(upLeft>=3){
                    return currentPlayer;
                }
                int downRight=checkDownRight(column+1,row+1);
                if(downRight+upLeft>=3){
                    return currentPlayer;
                }
            }
            return NO_WIN;
        }else{
            return TIE;
        }
    }
    
    private int checkRight(int column,int row){
        int ris=0;
        while(column<X_SIZE){
            if(currentPlayer==grid[column][row]){
                ris+=1;
                column+=1;
            }else{
                break;
            }
        }
        return ris;
    }

    private int checkLeft(int column,int row){
        int ris=0;
        while(column>=0){
            if(currentPlayer==grid[column][row]){
                ris+=1;
                column-=1;
            }else{
                break;
            }
        }
        return ris;
    }
    
    private int checkUp(int column,int row){
        int ris=0;
        while(row>=0){
            if(currentPlayer==grid[column][row]){
                ris+=1;
                row-=1;
            }else{
                break;
            }
        }
        return ris;
    }
    
    private int checkDown(int column,int row){
        int ris=0;
        while(row<Y_SIZE){
            if(currentPlayer==grid[column][row]){
                ris+=1;
                row+=1;
            }else{
                break;
            }
        }
        return ris;
    }
    
    private int checkUpLeft(int column,int row){
        int ris=0;
        while(row>=0 && column>=0){
            if(currentPlayer==grid[column][row]){
                ris+=1;
                row-=1;
                column-=1;
            }else{
                break;
            }
        }
        return ris;
    }
    
    private int checkUpRight(int column,int row){
        int ris=0;
        while(row>=0 && column<X_SIZE){
            if(currentPlayer==grid[column][row]){
                ris+=1;
                row-=1;
                column+=1;
            }else{
                break;
            }
        }
        return ris;
    }
    
    private int checkDownLeft(int column,int row){
        int ris=0;
        while(row<Y_SIZE && column>=0){
            if(currentPlayer==grid[column][row]){
                ris+=1;
                row+=1;
                column-=1;
            }else{
                break;
            }
        }
        return ris;
    }
    
    private int checkDownRight(int column,int row){
        int ris=0;
        while(row<Y_SIZE && column<X_SIZE){
            if(currentPlayer==grid[column][row]){
                ris+=1;
                row+=1;
                column+=1;
            }else{
                break;
            }
        }
        return ris;
    }

    /**
     * Return the player that's playing.
     * @return the current player.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void changeCurrentPlayer(){
        currentPlayer= currentPlayer==2? 1:2;  
    }
    
    
}
