/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4.GUI;

import forza4.IO.FileManager;
import forza4.logic.GameGrid;
import forza4.interfaces.Refreshable;
import forza4.IO.SavedGame;
import forza4.interfaces.SettableScreenColors;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 * The GameScreen class rappresent a connect four game. It handle the creation
 * of the GUI and the interaction of the players with the game.
 *
 * @author Paossi Davide
 */
public class GameScreen extends JFrame implements SettableScreenColors, Refreshable {

    /**
     * The number of columns of the GUI grid.
     */
    public static final int X_SIZE = 7;
    /**
     * The number of rows of the GUI grid.
     */
    public static final int Y_SIZE = 7;
    /**
     * The prefix that identifies the buttons.
     */
    private static final String BUTTON_PREFIX = "Button";
    /**
     * The prefix that identifies the tiles.
     */
    private static final String TILE_PREFIX = "Tile";
    /**
     * The separator used in the identificators of buttons and tiles.
     */
    private static final String SEPARATOR = "_";
    /**
     * The color of the background.
     */
    private Color backgroundColor;
    /**
     * The color of the grid.
     */
    private Color gridColor;
    /**
     * The color of the borders.
     */
    private Color borderColor;
    /**
     * The color of the player 1.
     */
    private Color player1Color;
    /**
     * The color of the player 2.
     */
    private Color player2Color;
    /**
     * The color of the buttons.
     */
    private Color buttonColor;
    /**
     * The name of the player1.
     */
    private String player1Name;
    /**
     * The name of the player2.
     */
    private String player2Name;
    /**
     * The title of the game.
     */
    private String title;
    /**
     * The logic grid.
     */
    private GameGrid grid;
    /**
     * The panel that contains all the game element.
     */
    private JPanel gamePanel;
    /**
     * The actual GameScreen.
     */
    private GameScreen thisGS;

    /**
     * Create a GameScreen object with a title and a grid.
     *
     * @param title the title of the game.
     * @param player1Name the name of the player 1.
     * @param player2Name the name of the player 2.
     */
    public GameScreen(String title, String player1Name, String player2Name) {
        super();
        //set the grid
        this.grid = new GameGrid();
        //check the players name.
        if (player1Name != null && !player1Name.isEmpty()) {
            this.player1Name = player1Name;
        } else {
            this.player1Name = "Player 1";
        }

        if (player2Name != null && !player2Name.isEmpty()) {
            this.player2Name = player2Name;
        } else {
            this.player2Name = "Player 2";
        }

        String playerName = grid.getCurrentPlayer() == 1 ? this.player1Name : this.player2Name;
        //set the title
        if (title != null && !title.isEmpty()) {
            this.title = title;
        } else {
            this.title = "Connect 4 Game";
            title = this.title;
        }

        this.setTitle(title + " - " + playerName + "'s turn");
        thisGS = this;
        this.setSize(1000, 1000);
        this.setPreferredSize(new Dimension(1000, 1000));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //init the components
        init();
    }

    /**
     * Init all the components of the GameScreen
     */
    private void init() {

        createMenu();
        gamePanel = new JPanel(new GridLayout(Y_SIZE, X_SIZE));//create the panel that will host the gamegrid.
        setColors();
        addButtons(gamePanel);
        addTiles(gamePanel);
        this.add(gamePanel);
        this.pack();
        repaint();
    }

    /**
     * Add the buttons to a given JPanel.
     *
     * @param gamePanel the JPanel.
     */
    private void addButtons(JPanel gamePanel) {
        if (gamePanel != null) {
            ActionListener buttonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    //take the action command, that contains the column of the button.
                    String actionCommand = ae.getActionCommand();
                    int column;
                    try {
                        //try to get the value
                        column = Integer.valueOf(actionCommand);
                        //try to insert
                        int row = grid.insert(column);
                        //if it was successful
                        if (row != -1) {
                            //takes all the components
                            Component[] components = gamePanel.getComponents();
                            //set the status  of the right component
                            insert(components, row, column);

                            //check the victory
                            int ris = grid.checkVictory(column, row);
                            //if something happens that isn't victory
                            if (ris != GameGrid.NO_WIN) {
                                //The game has endend, starting to prepare the end game message.
                                String msg;
                                if (ris == GameGrid.TIE) {
                                    msg = "TIE! No one won!";
                                } else {
                                    //select the winner
                                    String winner = ris == GameGrid.PLAYER_1_WIN ? player1Name : player2Name;
                                    msg = winner + " won!";
                                }
                                //saving the choise of the user
                                int choise = JOptionPane.showConfirmDialog(thisGS, msg + "\n" + "Want to start a new game?",
                                        "Game ended", JOptionPane.YES_NO_OPTION);
                                //YES:0   NO:1
                                if (choise == 0) {
                                    //reset all the components and the logic.
                                    reset(components, true);
                                } else {
                                    thisGS.dispose();
                                }
                            }
                            //change the player
                            grid.changeCurrentPlayer();
                            //get the actual player name
                            String playerName = grid.getCurrentPlayer() == GameGrid.PLAYER_1 ? player1Name : player2Name;
                            //set the title
                            thisGS.setTitle(title + " - " + playerName + "'s turn");
                        } else {
                            //impossibile to insert
                            JOptionPane.showMessageDialog(thisGS, "Invalid Column", "An error occured", JOptionPane.ERROR_MESSAGE);
                        }
                        //repaint to show the changings
                        repaint();
                    } catch (NumberFormatException nfe) {
                        //it'll land here because for some reason the value of the button isn't a number.
                        JOptionPane.showMessageDialog(thisGS, "Invalid buttom value", "An error occured", JOptionPane.ERROR_MESSAGE);
                        thisGS.dispose();
                    }

                }
            };
            for (int currentColumn = 0; currentColumn < X_SIZE; currentColumn++) {
                //create the button.
                JButton button = createButton(currentColumn, buttonListener);
                //add the button to the gamePanel.
                if (button != null) {
                    gamePanel.add(button);
                } else {
                    JOptionPane.showMessageDialog(this, "An error occured",
                            "Can't create the buttons", JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "An error occured",
                    "Button Inizializzation Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    /**
     * Create a button.
     *
     * @param currentColumn the column.
     * @param bL the listener
     * @return the button. Returns null if the columun is invalid.
     */
    private JButton createButton(int currentColumn, ActionListener bL) {
        JButton button = null;
        if (currentColumn >= 0 && currentColumn < X_SIZE) {
            String name = BUTTON_PREFIX + SEPARATOR + currentColumn;
            //create a button that has an arrow instead of text.
            button = new BasicArrowButton(BasicArrowButton.SOUTH);
            button.setName(name);
            button.setBackground(buttonColor);
            button.setBorder(new LineBorder(borderColor));
            //set as action command his column number
            button.setActionCommand(Integer.toString(currentColumn));
            //set his action listener
            button.addActionListener(bL);
        }
        return button;
    }

    /**
     * Add the tiles to a given JPanel.
     *
     * @param gamePanel the JPanel
     */
    private void addTiles(JPanel gamePanel) {
        if (gamePanel != null) {
            //The first row is occupied from the buttons
            for (int currentRow = 1; currentRow < Y_SIZE; currentRow++) {
                for (int currentColumn = 0; currentColumn < X_SIZE; currentColumn++) {
                    String name = TILE_PREFIX + SEPARATOR + (currentRow - 1) + SEPARATOR + currentColumn;
                    Tile tile = new Tile(name, this);
                    tile.setBackground(gridColor);
                    tile.setBorder(new LineBorder(borderColor));
                    gamePanel.add(tile);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "An error occured", "Tiles Inizializzation Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    /**
     * Set the default colors.
     */
    private void setColors() {

        backgroundColor = Color.WHITE;
        gridColor = Color.DARK_GRAY;
        borderColor = Color.BLACK;
        player1Color = Color.RED;
        player2Color = Color.BLUE;
        buttonColor = Color.DARK_GRAY;

    }

    /**
     * Returns the background color.
     *
     * @return the background color.
     */
    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Set the background color.
     *
     * @param backgroundColor the background color.
     */
    @Override
    public void setBackgroundColor(Color backgroundColor) {
        if (backgroundColor != null) {
            this.backgroundColor = backgroundColor;
        }
    }

    /**
     * Returns the border color.
     *
     * @return the border color.
     */
    @Override
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Set the border color.
     *
     * @param borderColor the border color.
     */
    @Override
    public void setBorderColor(Color borderColor) {
        if (borderColor != null) {
            this.borderColor = borderColor;
        }
    }

    /**
     * Get the player 1 color.
     *
     * @return the player 1 color.
     */
    @Override
    public Color getPlayer1Color() {
        return player1Color;
    }

    /**
     * Set the player 1 color.
     *
     * @param player1Color the player 1 color.
     */
    @Override
    public void setPlayer1Color(Color player1Color) {
        if (player1Color != null) {
            this.player1Color = player1Color;
        }
    }

    /**
     * Get the player 2 color.
     *
     * @return the player 2 color.
     */
    @Override
    public Color getPlayer2Color() {
        return player2Color;
    }

    /**
     * Set the player 2 color.
     *
     * @param player2Color the player 2 color.
     */
    @Override
    public void setPlayer2Color(Color player2Color) {
        if (player2Color != null) {
            this.player2Color = player2Color;
        }
    }

    /**
     * Get the grid color.
     *
     * @return the grid color.
     */
    @Override
    public Color getGridColor() {
        return gridColor;
    }

    /**
     * Set the grid color.
     *
     * @param gridColor the grid color.
     */
    @Override
    public void setGridColor(Color gridColor) {
        if (gridColor != null) {
            this.gridColor = gridColor;
        }
    }

    /**
     * Reset the status of all components. If gridReset is true resets the logic
     * grid too.
     *
     * @param components the components to reset.
     * @param gridReset the reset selector.
     */
    private void reset(Component[] components, boolean gridReset) {
        if (components != null) {
            //reset the logic grid
            gridReset(gridReset);
            //search all the tiles and reset them (GUI RESET)
            tilesReset(components);
        } else {
            JOptionPane.showMessageDialog(this, "Reset Error",
                    "An error occured", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    /**
     * Reset the logic grid if the parameter is true.
     *
     * @param gridReset the reset selector.
     */
    private void gridReset(boolean gridReset) {
        if (gridReset) {
            grid.reset();
        }
    }

    /**
     * Resets all the tiles.
     *
     * @param components the components of the frame that contains the tiles.
     */
    private void tilesReset(Component[] components) {
        if (components != null) {
            for (Component c : components) {
                String cName = c.getName();

                if (cName != null && cName.startsWith(TILE_PREFIX)) {
                    //if c is a tile
                    if (c instanceof Tile) {
                        Tile tile = (Tile) c;
                        tile.reset();
                    }
                }
            }
        }
    }

    /**
     * Create the menu of the GUI.
     */
    private void createMenu() {
        //create the JMenuBar object
        JMenuBar mb = new JMenuBar();
        //create a JMenu
        JMenu gameMenu = new JMenu("Game");
        //set the action listener for the menu items of gameMenu
        ActionListener menuListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //get the action command
                String actionCommand = ae.getActionCommand();
                //if it exists
                if (actionCommand != null) {
                    String gameName;
                    //do an action based on the action command received.
                    switch (actionCommand) {
                        //if "new game" is pressed
                        case "newGame":
                            //reset the gui and the logic
                            reset(gamePanel.getComponents(), true);
                            //then make the changings visibible
                            repaint();
                            //get the actual player name
                            String playerName = grid.getCurrentPlayer() == GameGrid.PLAYER_1 ? player1Name : player2Name;
                            //set the title
                            thisGS.setTitle(title + " - " + playerName + "'s turn");
                            break;
                        //if "save game" is pressed
                        case "saveGame":
                            //save the user decision
                            gameName = JOptionPane.showInputDialog(thisGS,
                                    "Choose the name of the game:", "Save Game"
                                    ,JOptionPane.QUESTION_MESSAGE);
                            int save;
                            //try to save
                            save = FileManager.save(grid.getGrid(),
                                    grid.getCurrentPlayer(), gameName,
                                    player1Name, player2Name);
                            //if something goes wrong
                            if (save != 1) {
                                String errMsg;
                                switch (save) {
                                    case 0:
                                        errMsg = "Name not valid or already taken";
                                        break;
                                    case 2:
                                        errMsg = "I/O Exception";
                                        break;
                                    //unknown error.
                                    default:
                                        errMsg = "An error occured";
                                        break;
                                }
                                JOptionPane.showMessageDialog(thisGS, errMsg,
                                        "An error occured",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        //if load game is pressed.
                        case "loadGame":
                            //save the choise
                            gameName = JOptionPane.showInputDialog(thisGS,
                                    "Choose the name of the game:", "Load game",
                                    JOptionPane.QUESTION_MESSAGE);
                            //try to load the game
                            SavedGame sg = FileManager.load(gameName);
                            //if the game has been loaded
                            loadGame(sg);
                            break;
                        //if options is pressed
                        case "options":
                            Options o = new Options(thisGS, thisGS);
                            o.setVisible(true);
                            break;
                        //if exit is pressed
                        case "exit":
                            //close the window.
                            thisGS.dispose();
                            break;

                    }
                }
            }
        };
        //create a new item for the menu 
        JMenuItem newGameItem = new JMenuItem("New game");
        //add the menuListener
        newGameItem.addActionListener(menuListener);
        //set his action command
        newGameItem.setActionCommand("newGame");
        //add to the menu
        gameMenu.add(newGameItem);
        //repeat the same pattern for all the menu items.
        JMenuItem saveItem = new JMenuItem("Save Game");
        saveItem.addActionListener(menuListener);
        saveItem.setActionCommand("saveGame");
        gameMenu.add(saveItem);
        JMenuItem loadItem = new JMenuItem("Load Game");
        loadItem.addActionListener(menuListener);
        loadItem.setActionCommand("loadGame");
        gameMenu.add(loadItem);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(menuListener);
        exitItem.setActionCommand("exit");
        gameMenu.add(exitItem);
        //add the menu to the menu bar
        mb.add(gameMenu);
        //create a menu for the options
        JMenu optionMenu = new JMenu("Options");
        JMenuItem optionItem = new JMenuItem("Options");
        optionItem.addActionListener(menuListener);
        optionItem.setActionCommand("options");
        optionMenu.add(optionItem);
        //optionMenu.add("Credits");
        mb.add(optionMenu);
        //add the menu bar to the GameScreen.
        this.setJMenuBar(mb);
    }

    /**
     * Load the game saved in a given SavedGame
     *
     * @param sg the saved game
     */
    private void loadGame(SavedGame sg) {
        if (sg != null) {
            //try to get the grid
            int[][] saveGrid = sg.getGrid();
            //check the integrity
            if (GameGrid.checkGrid(saveGrid)) {
                //all ok, save it
                grid.setGrid(saveGrid);
                //resets all the gui
                reset(gamePanel.getComponents(), false);
                //check the status of the gui
                checkStatus();
                //try to get the names or set default ones.
                String player1Name = sg.getPlayer1Name();
                String player2Name = sg.getPlayer2Name();
                String gameTitle = sg.getGameTitle();
                if (gameTitle != null && !gameTitle.isEmpty()) {
                    thisGS.title = gameTitle;
                } else {
                    thisGS.title = "Connect 4 Game";
                }
                if (player1Name != null && !player1Name.isEmpty()) {
                    thisGS.player1Name = player1Name;
                } else {
                    thisGS.player1Name = "Player 1";
                }
                if (player2Name != null && !player2Name.isEmpty()) {
                    thisGS.player2Name = player2Name;
                } else {
                    thisGS.player2Name = "Player 2";
                }
                //check if they are the same or change it
                if (grid.getCurrentPlayer() != sg.getCurrentPlayer()) {
                    grid.changeCurrentPlayer();
                }
                //get the current player name
                String currentPlayer = sg.getCurrentPlayer() == 1
                        ? thisGS.player1Name : thisGS.player2Name;
                //set the title
                thisGS.setTitle(title + " - " + currentPlayer + "'s turn");
                repaint();
            } else {
                JOptionPane.showMessageDialog(thisGS, "Corrupted game",
                        "Impossible to load the game", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(thisGS, "Game corrupted or not found",
                    "Impossible to load the game", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Check the status of the GUI grid, and set all the status of the tiles to
     * corrisponds the logic grid.
     */
    private void checkStatus() {
        if (gamePanel != null) {
            Component[] components = gamePanel.getComponents();
            if (components != null) {
                //search all the tiles
                for (Component c : components) {
                    String cName = c.getName();
                    if (cName != null && cName.startsWith(TILE_PREFIX)) {
                        if (c instanceof Tile) {
                            Tile tile = (Tile) c;
                            //split the name
                            String[] splitted = cName.split(SEPARATOR);
                            try {
                                //check the length
                                if (splitted.length == 3) {
                                    //find the row and column
                                    int row = Integer.parseInt(splitted[1]);
                                    int column = Integer.parseInt(splitted[2]);
                                    //set the status
                                    tile.setStatus(grid.getGrid()[column][row]);
                                }
                            } catch (NumberFormatException nfe) {
                                JOptionPane.showMessageDialog(this,
                                        "Tile name format error",
                                        "An error occured",
                                        JOptionPane.ERROR_MESSAGE);
                                this.dispose();
                            }
                        }
                    }

                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "An error occured",
                    "Status Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    /**
     * Change the status of a specific tile in the GameScreen.
     *
     * @param components the components that contains the tiles.
     * @param row the row of the tile.
     * @param column the column of the tile.
     */
    private void insert(Component[] components, int row, int column) {
        if (components != null) {
            if (row >= 0 && column >= 0 && row < GameGrid.Y_SIZE
                    && column < GameGrid.X_SIZE) {
                for (Component c : components) {
                    String cName = c.getName();
                    if (cName != null) {
                        //search a tile by his name
                        if (cName.startsWith(TILE_PREFIX)) {
                            String[] splitted = cName.split(SEPARATOR);
                            if (splitted.length == 3) {
                                //the second element of the array contains the row.
                                String rowString = splitted[1];
                                try {
                                    //try to get the row
                                    int rowInt = Integer.valueOf(rowString);
                                    //if the row corrisponds
                                    if (row == rowInt) {
                                        //the third element of the array contains the column
                                        String columnString = splitted[2];
                                        int columnInt = Integer.valueOf(columnString);
                                        //if the column corrisponds too
                                        if (column == columnInt) {
                                            //check if c is a tile
                                            if (c instanceof Tile) {
                                                Tile tile = (Tile) c;
                                                //set the tile status
                                                tile.setStatus(grid.getCurrentPlayer());
                                                //refresh the gui
                                                repaint();
                                                break;
                                            }
                                        }
                                    }
                                } catch (NumberFormatException nfe) {
                                    JOptionPane.showMessageDialog(this, "Tile Number Format Error", "An error occured", JOptionPane.ERROR_MESSAGE);
                                    this.dispose();
                                }
                            }
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Gui Insert Error", "An error occured", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }

    }
}
