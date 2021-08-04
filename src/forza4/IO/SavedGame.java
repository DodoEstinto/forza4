/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4.IO;

import forza4.logic.GameGrid;
import java.io.Serializable;

/**
 * Class needed to save the Connect 4 game. It permits to serialize all the
 * information to be easily saved on a file
 *
 * @author Paossi Davide
 */
public class SavedGame implements Serializable {

    /**
     * The columns of the game.
     */
    private SavedGameColumn[] columns;
    /**
     * The current player of the game.
     */
    private int currentPlayer;
    /**
     * The name of the player 1
     */
    private String player1Name;
    /**
     * The name of the player 2
     */
    private String player2Name;
    /**
     * The title of the game
     */
    private String gameTitle;

    /**
     * Create a SavedGame object.
     */
    public SavedGame() {
    }

    
    //TO CHECK
    /**
     * Returns the grid, if the grid has problems returns null.
     *
     * @return the grid, if the grid has problems returns null.
     */
    public int[][] getGrid() {
        int[][] grid = null;
        if (columns != null) {
            grid = new int[columns.length][];
            for (int i = 0; i < columns.length; i++) {
                if (SavedGameColumn.checkColumn(columns[i])) {
                    grid[i] = columns[i].getColumn();
                } else {
                    grid = null;//grid is not valid
                    break;
                }
            }
        }
        return grid;
    }

    /**
     * Set the grid. If the grid is not valid, it will not be set.
     *
     * @param grid the grid to be set.
     */
    public void setGrid(int[][] grid) {
        if (GameGrid.checkGrid(grid)) { //check the grid
            columns = new SavedGameColumn[grid.length];
            for (int i = 0; i < columns.length; i++) {
                columns[i] = new SavedGameColumn();
                columns[i].setColumn(grid[i]);
            }
        }
    }

    /**
     * Get the current player of the game.
     *
     * @return the current player.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Set the current player of the game. If the value is not valid it will not
     * be set.
     *
     * @param currentPlayer the current player.
     */
    public void setCurrentPlayer(int currentPlayer) {
        if (currentPlayer == GameGrid.PLAYER_1
                || currentPlayer == GameGrid.PLAYER_2) {
            this.currentPlayer = currentPlayer;
        }
    }

    /**
     * Return the name of the player 1.
     *
     * @return the name of the player 1.
     */
    public String getPlayer1Name() {
        return player1Name;
    }

    /**
     * Set the name of the player 1. If the name is not valid it will not be set
     *
     * @param player1Name the name of the player 1.
     */
    public void setPlayer1Name(String player1Name) {
        if (player1Name != null && !player1Name.trim().isEmpty()) {
            this.player1Name = player1Name;
        }
    }

    /**
     * Return the name of the player 2.
     *
     * @return the name of the player 2.
     */
    public String getPlayer2Name() {
        return player2Name;
    }

    /**
     * Set the name of the player 2. If the name is not valid it will not be set
     *
     * @param player2Name the name of the player 2.
     */
    public void setPlayer2Name(String player2Name) {
        if (player2Name != null && !player2Name.trim().isEmpty()) {
            this.player2Name = player2Name;
        }
    }

    /**
     * Get the title of the game.
     *
     * @return the title of the game.
     */
    public String getGameTitle() {
        return gameTitle;
    }

    /**
     * Set the name of the game. If the name is not valid it will not be set
     *
     * @param gameTitle the name of the game.
     */
    public void setGameTitle(String gameTitle) {
        if (gameTitle != null && !gameTitle.trim().isEmpty()) {
            this.gameTitle = gameTitle;
        }
    }

    /**
     * Check the integrity of the SavedGame Object.
     *
     * @return false if the SavedGame object is invalid, true otherwise.
     */
    public boolean checkIntegrity() {
        if (this.columns != null) {
            for (SavedGameColumn c : columns) {
                if (!SavedGameColumn.checkColumn(c)) {
                        return false;            
                }
            }
            if (currentPlayer == GameGrid.PLAYER_1
                    || currentPlayer == GameGrid.PLAYER_2) {
                if (gameTitle != null && !gameTitle.trim().isEmpty()) {
                    if (player1Name != null && !player1Name.trim().isEmpty()) {
                        if (player2Name != null && !player2Name.trim().isEmpty()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
