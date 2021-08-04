package forza4.IO;

import forza4.logic.GameGrid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class make possible to save and load connect four games.
 *
 * @author Paossi Davide
 */
public class FileManager {

    /**
     * The path where the files are saved.
     */
    public static final String PATH = "./";
    /**
     * The extention of the files.
     */
    public static final String EXTENTION = ".f4";

    /**
     * Save the status of the actual game into a file. If the player are
     * invalid, they will be set to standard values.
     *
     * @param grid the logic grid of the game.
     * @param player the current player
     * @param gameName the name of the game
     * @param player1Name the name of the player1
     * @param player2Name the name of the player 2
     * @return 1 on a successful save. 0 when the gameName is already taken or
     * it's not valid. 2 if there was an IOException. 3 if the grid is not
     * valid.
     */
    public static int save(int[][] grid, int player, String gameName,
            String player1Name, String player2Name) {
        int ris;
        //check the grid
        if (GameGrid.checkGrid(grid)) {
            //check the game name
            if (gameName != null && !gameName.trim().isEmpty()) {
                //check player1Name
                if (player1Name == null || player1Name.trim().isEmpty()) {
                    player1Name = "Player1";
                }
                //check player2Name
                if (player2Name == null || player2Name.trim().isEmpty()) {
                    player2Name = "Player2";
                }
                //create a new file object.
                File f = new File(PATH + gameName + EXTENTION);
                //Checks if it already exists.
                if (!f.exists()) {
                    try {
                        //if not, creates a new one.
                        f.createNewFile();
                        //Open a stream to write the data.
                        try (FileOutputStream out = new FileOutputStream(f)) {
                            //prepare a SavedGame object.
                            SavedGame sg = createSavedGame(grid, player, player1Name, player2Name, gameName);

                            try (ObjectOutputStream objOut = new ObjectOutputStream(out)) {
                                //saves the object in the file.
                                objOut.writeObject(sg);
                            }
                            ris = 1;
                        }
                    } catch (IOException ex) {
                        ris = 2;
                    }
                } else {
                    ris = 0;
                }
            } else {
                ris = 0;
            }
        } else {
            ris = 3;
        }
        return ris;
    }

    /**
     * Create a SavedGame Object based on the given parameters.
     *
     * @param grid the logic grid.
     * @param player the actual player.
     * @param player1Name the name of player 1.
     * @param player2Name the name of player 2.
     * @param gameTitle the name of the game.
     * @return the SavedGame object.
     */
    private static SavedGame createSavedGame(int[][] grid, int player,
            String player1Name, String player2Name, String gameTitle) {
        SavedGame sg = new SavedGame();
        sg.setGrid(grid);
        sg.setCurrentPlayer(player);
        sg.setPlayer1Name(player1Name);
        sg.setPlayer2Name(player2Name);
        sg.setGameTitle(gameTitle);
        return sg;
    }

    /**
     * Load a SavedGame object from a file name.
     *
     * @param name the name of the file to load.
     * @return the SavedGame Object saved in the file. Returns null if something
     * goes wrong.
     */
    public static SavedGame load(String name) {
        SavedGame ris = null;
        if (name != null && !name.trim().isEmpty()) {
            //create a new file object.
            File f = new File(PATH + name + EXTENTION);
            //Checks if it already exists.
            if (f.exists()) {
                //check the read permissions.
                if (f.canRead()) {
                    //Open a stream to read the data.
                    try (FileInputStream in = new FileInputStream(f)) {
                        ris = new SavedGame();
                        //Open a stream to read the object.
                        try (ObjectInputStream objIn = new ObjectInputStream(in)) {
                            Object obj;
                            //try to read the object
                            obj = objIn.readObject();
                            //check the object
                            if (obj instanceof SavedGame) {
                                //all fine, save it.
                                ris = (SavedGame) obj;
                                if (!ris.checkIntegrity()) {
                                    throw new Exception(); //Corrupted save!
                                }
                            }
                        }

                    } catch (Exception ex) {
                        ris = null; //set the save as corrupted.
                        //JOptionPane.showMessageDialog(null, "Corrupted save!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } //else {
            //JOptionPane.showMessageDialog(null,"Save not found", "ERROR",JOptionPane.ERROR_MESSAGE);
            //}
        }
        return ris;
    }

}
