/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4.logic;

import java.util.Arrays;

/**
 * This class manage the logic grid of connect four games. It handles the
 * creation of the grid, the insertion of the values in the grid, the victory
 * logic and check, and manage to keep the integrity of the values in the grid.
 *
 * @author Paossi Davide
 */
public class GameGrid {

    /**
     * The number of column of elements of the grid.
     */
    public static final int X_SIZE = 7;
    /**
     * The number of the rows of the grid.
     */
    public static final int Y_SIZE = 6;
    /**
     * The value that rappresents a hole.
     */
    public static final int VOID = 0;
    /**
     * The value that rappresents a hole occuped by the player 1.
     */
    public static final int PLAYER_1 = 1;
    /**
     * The value that rappresents a hole occuped by the player 2.
     */
    public static final int PLAYER_2 = 2;
    /**
     * The value that rappresents a tie.
     */
    public static final int TIE = 0;
    /**
     * The value that rappresents the victory of the player 1.
     */
    public static final int PLAYER_1_WIN = 1;
    /**
     * The value that rappresents the victory of player 2.
     */
    public static final int PLAYER_2_WIN = 2;
    /**
     * The value that rappresents that the game is still going on.
     */
    public static final int NO_WIN = 3;
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
    public GameGrid() {
        grid = new int[X_SIZE][Y_SIZE];
        leftPieces = X_SIZE * Y_SIZE;
        currentPlayer = randomPlayer();
    }

    /**
     * Reset the actual grid.
     */
    public void reset() {
        grid = new int[X_SIZE][Y_SIZE];
        leftPieces = X_SIZE * Y_SIZE;
        //Generate a random player
        currentPlayer = randomPlayer();
    }

    /**
     * Give a randomly 1 or 2.
     *
     * @return 1 or 2.
     */
    private int randomPlayer() {
        return (int) ((Math.random() * 2) + 1);
    }

    /**
     * Insert a piece in the grid.
     *
     * @param column The column where the piece is inserted.
     * @return the height of the hole where the piece has stopped. If there is
     * no room returns -1.
     */
    public int insert(int column) {
        boolean ris;
        int y = deepestPiece(column);
        if (y != -1) {
            grid[column][y] = currentPlayer;
            leftPieces -= 1;
        }
        return y;
    }

    /**
     * Finds the deepest empty hole where the piece can be inserted.
     *
     * @param column The column to check
     * @return the height of the hole where the piece would stop. If there is no
     * room returns -1.
     */
    private int deepestPiece(int column) {
        int y = 0;
        if (column < grid.length && column >= 0) {
            for (y = 0; y < Y_SIZE; y++) {
                //check for a collision.
                if (grid[column][y] != 0) {
                    break;
                }
            }
        }
        //return the last hole or the hole before the collision.
        return y - 1;
        //return found ? y-1 : y;
    }

    /**
     * Check the victory.
     *
     * @param column the column of the last inserted piece.
     * @param row the row of the last inserted piece.
     * @return the status of the game (win,tie,no win).
     */
    public int checkVictory(int column, int row) {

        //checks if the column is in the right range
        if (column < X_SIZE && column >= 0) {
            //checks if the row is in the right range
            if (row < Y_SIZE && row >= 0) {
                /*check if the given hole corrisponds to the player 
                    (counts as the first piece of four to win)*/
                if (grid[column][row] == currentPlayer) {
                    /*from there we'll search for only 3 more pieces,
                        the first has been already counted*/
                    int right = checkRight(column + 1, row);
                    if (right >= 3) {
                        return currentPlayer;
                    }

                    int left = checkLeft(column - 1, row);
                    if (right + left >= 3) {
                        return currentPlayer;
                    }

                    int up = checkUp(column, row - 1);
                    if (up >= 3) {
                        return currentPlayer;
                    }

                    int down = checkDown(column, row + 1);
                    if (up + down >= 3) {
                        return currentPlayer;
                    }

                    int upRight = checkUpRight(column + 1, row - 1);
                    if (upRight >= 3) {
                        return currentPlayer;
                    }

                    int downLeft = checkDownLeft(column - 1, row + 1);
                    if (downLeft + upRight >= 3) {
                        return currentPlayer;
                    }

                    int upLeft = checkUpLeft(column - 1, row - 1);
                    if (upLeft >= 3) {
                        return currentPlayer;
                    }
                    int downRight = checkDownRight(column + 1, row + 1);
                    if (downRight + upLeft >= 3) {
                        return currentPlayer;
                    }
                }
                
                //CHECK THE TIE
                //if no one won check for a tie
                if (leftPieces == 0) {
                    return TIE;
                }
            }
        }
        return NO_WIN;

    }

    /**
     * Check how many consecutive tiles of the current player are right of the
     * given point in the grid.
     *
     * @param column the column of the given point.
     * @param row the row of the given point
     * @return the number of the consecutive tiles of the player.
     */
    private int checkRight(int column, int row) {
        int ris = 0;
        while (column < X_SIZE && column >= 0) {
            if (currentPlayer == grid[column][row]) {
                ris += 1;
                column += 1;
            } else {
                break;
            }
        }
        return ris;
    }

    /**
     * Check how many consecutive tiles of the current player are on the left of
     * the given point in the grid.
     *
     * @param column the column of the given point
     * @param row the row of the given point
     * @return the number of consecutive tiles of the player.
     */
    private int checkLeft(int column, int row) {
        int ris = 0;
        while (column >= 0 && column < X_SIZE) {
            if (currentPlayer == grid[column][row]) {
                ris += 1;
                column -= 1;
            } else {
                break;
            }
        }
        return ris;
    }

    /**
     * Check how many consecutive tiles of the current player are above the
     * given point in the grid.
     *
     * @param column the column of the given point
     * @param row the row of the given point
     * @return the number of the consecutive tiles of the player
     */
    private int checkUp(int column, int row) {
        int ris = 0;
        while (row >= 0 && row < Y_SIZE) {
            if (currentPlayer == grid[column][row]) {
                ris += 1;
                row -= 1;
            } else {
                break;
            }
        }
        return ris;
    }

    /**
     * Check how many consecutive tiles of the current player are below the
     * given point in the grid.
     *
     * @param column the column of the given point
     * @param row the row of the given point.
     * @return the number of consecutive tiles of the player
     */
    private int checkDown(int column, int row) {
        int ris = 0;
        while (row < Y_SIZE && row >= 0) {
            if (currentPlayer == grid[column][row]) {
                ris += 1;
                row += 1;
            } else {
                break;
            }
        }
        return ris;
    }

    /**
     * Check how many consecutive tiles of the current player are in the up-left
     * diagonal from the given point in the grid.
     *
     * @param column the column of the given point
     * @param row the row of the given point
     * @return the number of consecutive tiles of the player
     */
    private int checkUpLeft(int column, int row) {
        int ris = 0;
        while (row >= 0 && column >= 0 && row < Y_SIZE && column < X_SIZE) {
            if (currentPlayer == grid[column][row]) {
                ris += 1;
                row -= 1;
                column -= 1;
            } else {
                break;
            }
        }
        return ris;
    }

    /**
     * Check how many consecutive tiles of the current player are in the
     * up-right diagonal from the given point in the grid.
     *
     * @param column the column of the given point
     * @param row the row of the given point
     * @return the number of consecutive tiles of the player
     */
    private int checkUpRight(int column, int row) {
        int ris = 0;
        while (row >= 0 && column < X_SIZE && row < Y_SIZE && column >= 0) {
            if (currentPlayer == grid[column][row]) {
                ris += 1;
                row -= 1;
                column += 1;
            } else {
                break;
            }
        }
        return ris;
    }

    /**
     * Check how many consecutive tiles of the current player are in the
     * down-left diagonal from the given point in the grid.
     *
     * @param column the column of the given point
     * @param row the row of the given point
     * @return the number of consecutive tiles of the player
     */
    private int checkDownLeft(int column, int row) {
        int ris = 0;
        while (row < Y_SIZE && column >= 0 && row >= 0 && column < X_SIZE) {
            if (currentPlayer == grid[column][row]) {
                ris += 1;
                row += 1;
                column -= 1;
            } else {
                break;
            }
        }
        return ris;
    }

    /**
     * Check how many consecutive tiles of the current player are in the
     * down-right diagonal from the given point in the grid.
     *
     * @param column the column of the given point
     * @param row the row of the given point
     * @return the number of consecutive tiles of the player
     */
    private int checkDownRight(int column, int row) {
        int ris = 0;
        while (row < Y_SIZE && column < X_SIZE && row >= 0 && column >= 0) {
            if (currentPlayer == grid[column][row]) {
                ris += 1;
                row += 1;
                column += 1;
            } else {
                break;
            }
        }
        return ris;
    }

    /**
     * Return the player that's playing.
     *
     * @return the current player.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Change the current player.
     */
    public void changeCurrentPlayer() {
        currentPlayer = currentPlayer == 2 ? 1 : 2;
    }

    /**
     * Return a COPY of the grid.
     *
     * @return the COPY of the grid.
     */
    public int[][] getGrid() {        
        return copyGrid();
    }
    
    /**
     * Generate a copy of the grid.
     * @return the copy of the grid.
     */
    private int[][] copyGrid(){
        int[][] copyGrid = new int[X_SIZE][];
        for (int i = 0; i < X_SIZE; i++) {
            copyGrid[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        return copyGrid;
    }

    /**
     * Set a new grid. If the grid is not valid (invalid values) the grid
     * will not be set.
     *
     * @param grid the grid to be set.
     * @return true if the grid has been set. False otherwise.
     */
    public boolean setGrid(int[][] grid) {
        boolean ris = true;
        int tilesTemp = X_SIZE * Y_SIZE;
        //check the integrity of the first dimension of the array
        if (grid != null && grid.length == X_SIZE) {
            for (int[] column : grid) {
                //check the integrity of the second dimension of the array
                if (column == null || column.length != Y_SIZE) {
                    //if the grid has problems, we'll not set the grid.
                    ris = false;
                    break;
                } else {
                    //check how many tiles have been already taken.
                    for (int row : column) {
                        
                        //TO CHECK
                        //if the tile is not empty and has a valid value
                        if (row != PLAYER_1 && row != PLAYER_2 && row != VOID) {
                            ris = false;
                            break;
                        }else if (row != VOID) {
                            tilesTemp--;
                        }
                    }

                }

            }
        } else {
            //grid invalid!
            ris = false;
        }

        //if the grid is valid change the grid.
        if (ris) {
            this.grid = grid;
            leftPieces = tilesTemp;
        }
        return ris;
    }

    /**
     * Check if the grid is valid. Return true if it's valid, false otherwise.
     *
     * @param grid the grid to check.
     * @return Return true if it's valid, false otherwise.
     */
    public static boolean checkGrid(int[][] grid) {
        boolean ris = true;
        //check the integrity of the first dimension of the array
        if (grid != null && grid.length == X_SIZE) {
            for (int[] column : grid) {
                //check the integrity of the second dimension of the array
                if (column == null || column.length != Y_SIZE) {
                    //if the grid has problems
                    ris = false;
                    break;
                } else {
                    //check the rows of the column
                    for (int row : column) {
                        //if the tile has invalid values
                        if (row != VOID && row != PLAYER_1 && row != PLAYER_2) {
                            ris = false;
                            break;
                        }
                    }

                }

            }
        } else {
            //the grid is invalid!
            ris = false;
        }
        return ris;
    }
}
