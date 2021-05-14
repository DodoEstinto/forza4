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
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author AdSumPro
 */
public class FileManager {
    private static String path="./";
    
    /**
     * 0=Nome gia usato.
     * 1=Salvataggio con successo.
     * 2=Salvataggio fallito.
     *
     * 
     * @param grid
     * @param player
     * @param name
     * @return 
     */
    protected static int save(int[][] grid,int player,String name){
        int ris;
        File f=new File(path+name+".f4");
        if(!f.exists()){
            try {
                f.createNewFile();
                try (FileOutputStream out = new FileOutputStream(f)) {
                    SavedGame sg=new SavedGame();                 
                    sg.setGrid(grid);
                    ObjectOutputStream objOut=new ObjectOutputStream(out);
                    objOut.writeObject(sg);
                    ris=1;
                }catch (IOException ex) {
                ris=2;
                }
                
            } catch (IOException ex) {
                ris=2;
            }
        }else{
            ris=0;
        }
        return ris;
    }
    
    protected static SavedGame load(String name) {
        SavedGame ris=null;
        File f = new File(path + name + ".f4");
        if (f.exists()) {
            if (f.canRead()) {

                try (FileInputStream in = new FileInputStream(f)) {
                    ris = new SavedGame();
                    try (ObjectInputStream objIn = new ObjectInputStream(in)) {
                        Object obj;
                        obj = objIn.readObject();
                        if(obj instanceof SavedGame){
                            ris=(SavedGame)obj;
                        }
                    }

                } catch (IOException | NullPointerException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ris;
    }
    
    /**
     * Second implementation of save function.
     * It was quite good, but needed too much controls and effords on the load.
     * 
     *   
     *  0=Nome gia usato.
     *  1=Salvataggio con successo.
     *  2=Salvataggio fallito.
     *  3=Griglia non valida.
     * @deprecated 
     * @param grid
     * @param name
     * @param player
     * @return 
     */
    /*
    protected static int saveGridObject(int[][] grid,String name,int player){
        int ris;
        File f=new File(path+name+".f4");
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
     * First implementation of save function. 
     * It was primitive and problematics. Use save instead.
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
        File f=new File(path+name+".f4");
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
