
package forza4.interfaces;

import java.awt.Color;

/**
 * This interface imposes that the class implementing it can return different
 * colors.
 * @author Paossi Davide
 */
public interface ScreenColors {
    /**
     * Returns the background color.
     * @return the background color.
     */
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
