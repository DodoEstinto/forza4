/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Paossi Davide
 */
public class Tile extends JPanel {

    /**
     * The ratio between the circle radious and the square side.
     */
    private static final float CIRCLE_RATIO = 0.9f;
    /**
     * The minimum distance between the circle and the square side.
     */
    private static final float DISTANCE_RATIO = ((1 - CIRCLE_RATIO) / 2);
    /**
     * The tile is void.
     */
    public static final int STATUS_VOID = 0;
    /**
     * The tile is occuped by the Player1.
     */
    public static final int STATUS_PLAYER1 = 1;
    /**
     * The tile is occuped by the Player2.
     */
    public static final int STATUS_PLAYER2 = 2;
    /**
     * Current status.
     */
    private int status;
    /**
     * The colors of the GameScreen
     */
    private ScreenColors sc;

    /**
     * Create a void Tile with a specific name and a specific screen. If the
     * name is not valid, it will not be set. If sc is not valid, default colors
     * will be used.
     *
     * @param name the name of the tile.
     * @param sc the screen connected.
     */
    public Tile(String name, ScreenColors sc) {
        super();
        if (name != null && !name.trim().isEmpty()) {
            this.setName(name);
        }
        if (sc == null) {
            setColors();
        } else {
            this.sc = sc;
        }
        status = STATUS_VOID;
    }

    /**
     * Paint the tile with the colors associated with the actual status of the
     * tile and the configuration.
     *
     * @param g the graphics of the tile.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw the circle
        drawCircle(g, getWidth(), getHeight());
    }

    /**
     * Create and set the ScreenColors with default colors.
     */
    private void setColors() {
        sc = new ScreenColors() {
            @Override
            public Color getBackgroundColor() {
                return Color.WHITE;
            }

            @Override
            public Color getBorderColor() {
                return Color.BLACK;
            }

            @Override
            public Color getPlayer1Color() {
                return Color.RED;
            }

            @Override
            public Color getPlayer2Color() {
                return Color.BLUE;
            }

            @Override
            public Color getGridColor() {
                return Color.DARK_GRAY;
            }
        };
    }

    /**
     * Draw the disc hole or the player disc depending on the current status of
     * the tile.
     *
     * @param g the graphics of the tile.
     * @param width the width of the tile.
     * @param height the height of the tile.
     */
    private void drawCircle(Graphics g, int width, int height) {
        //set the color depending on the status of the tile
        switch (status) {
            case STATUS_PLAYER1:
                g.setColor(sc.getPlayer1Color());
                break;
            case STATUS_PLAYER2:
                g.setColor(sc.getPlayer2Color());
                break;
            case STATUS_VOID:
            default:
                g.setColor(sc.getBackgroundColor());
        }
        /*
        the first two parameters indicate the top left point of the square 
        that circumscribes the circle. The last two are indicate the size
        of the circle
         */
        g.fillOval((int) (width * DISTANCE_RATIO), (int) (height * DISTANCE_RATIO),
                (int) (width * CIRCLE_RATIO), (int) (height * CIRCLE_RATIO));
    }

    /**
     * Get the actual status.
     *
     * @return The actual status.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Set the actual status.
     *
     * @param status The status to set.
     */
    public void setStatus(int status) {
        if (status == STATUS_VOID || status == STATUS_PLAYER1
                || status == STATUS_PLAYER2) {
            this.status = status;
        }
    }

    /**
     * Reset the tile. The status is set to void.
     */
    public void reset() {
        status = STATUS_VOID;

    }

}
