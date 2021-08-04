/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4.interfaces;

import java.awt.Color;

/**
 *
 * @author AdSumPro
 */
public interface SettableScreenColors extends ScreenColors {
    
     /**
     * Set the background color.
     *
     * @param backgroundColor the background color.
     */
    public void setBackgroundColor(Color backgroundColor);

    /**
     * Set the border color.
     *
     * @param borderColor the border color.
     */
    public void setBorderColor(Color borderColor);

    /**
     * Set the player 1 color.
     *
     * @param player1Color the player 1 color.
     */
    public void setPlayer1Color(Color player1Color);
    
    /**
     * Set the player 2 color.
     *
     * @param player2Color the player 2 color.
     */
    public void setPlayer2Color(Color player2Color);

    /**
     * Set the grid color.
     *
     * @param gridColor the grid color.
     */
    public void setGridColor(Color gridColor);
}
