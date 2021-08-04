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
public interface ScreenColors {
    
    public Color getBackgroundColor();

    /**
     * Returns the border color.
     *
     * @return the border color.
     */
    public  Color getBorderColor();


    /**
     * Get the player 1 color.
     *
     * @return the player 1 color.
     */
    public Color getPlayer1Color();

    /**
     * Get the player 2 color.
     *
     * @return the player 2 color.
     */
    public Color getPlayer2Color();


    /**
     * Get the grid color.
     *
     * @return the grid color.
     */
    public Color getGridColor();
}
