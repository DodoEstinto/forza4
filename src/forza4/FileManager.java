/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
     * Save the status of the actual game into a file.
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
    protected static int save(int[][] grid, int player, String gameName, String player1Name, String player2Name) {
        int ris;
        //check the grid
        if (GameGrid.checkGrid(grid)) {
            //check the name
            if (gameName != null || !gameName.trim().isEmpty()) {
                //check player1Name
                if (player1Name == null || player1Name.trim().isEmpty()) {
                    player1Name = "Player1";
                }
                //check player2Name
                if (player2Name == null || player2Name.trim().isEmpty()) {
                    player1Name = "Player1";
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
                            SavedGame sg = new SavedGame();
                            sg.setGrid(grid);
                            sg.setCurrentPlayer(player);
                            sg.setPlayer1Name(player1Name);
                            sg.setPlayer2Name(player2Name);

                            try (ObjectOutputStream objOut = new ObjectOutputStream(out)) {
                                //saves the object in the file.
                                objOut.writeObject(sg);
                            }
                            ris = 1;
                        } catch (IOException ex) {
                            ris = 2;
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
     * Load a SavedGame object from a file name.
     *
     * @param name the name of the file to load.
     * @return the SavedGame Object saved in the file. Returns null if something
     * goes wrong.
     */
    protected static SavedGame load(String name) {
        SavedGame ris = null;
        if(name!=null && !name.trim().isEmpty()){
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
                            }
                        }

                    } catch (IOException | NullPointerException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Corrupted save!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        return ris;
    }

    /**
     * Second implementation of save function. It was quite good, but needed too
     * much controls and effords on the load.
     *
     *
     * 0=Nome gia usato. 1=Salvataggio con successo. 2=Salvataggio fallito.
     * 3=Griglia non valida.
     *
     * @deprecated
     * @param grid
     * @param name
     * @param player
     * @return
     */
    /*
    protected static int saveGridObject(int[][] grid,String name,int player){
        int ris;
        File f=new File(PATH+name+".f4");
        if(!f.exists()){
            try {
                f.createNewFile();
                FileOutputStream out = new FileOutputStream(f);
                OldSavedGame sg=new OldSavedGame(grid,player);
                ObjectOutputStream objOut=new ObjectOutputStream(out);
                objOut.writeObject(sg);
                out.close();
                ris=1;
            } catch (IOException ex) {
                ris=2;
            } catch (NullPointerException ex){
                ris=3;
            }
        }else{
            ris=0;
        }
        return ris;
    }
     */
    /**
     *
     * First implementation of save function. It was primitive and problematics.
     * Use save instead.
     *
     * @deprecated
     * @param grid
     * @param name
     * @param player
     * @return
     */
    /*
    private static int oldSaveTxt(int[][] grid,String name,int player){
        int ris;
        File f=new File(PATH+name+".f4");
        if(!f.exists()){
            try {
                f.createNewFile();
                PrintWriter out = new PrintWriter(f);
                out.print(""+player+";");
                for(int column=0;column<grid.length;column++){                    
                    for(int row=0;row<grid[column].length;row++){
                     out.print(""+grid[column][row]+";");
                    }                                             
                }
                ris=1;
                out.close();
                ris=1;
            } catch (IOException ex) {
                ris=2;
            } catch (NullPointerException ex){
                ris=3;
            }
        }else{
            ris=0;
        }
        return ris;
    }
     */
}
