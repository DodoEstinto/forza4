package forza4.testers;

import forza4.IO.FileManager;
import forza4.logic.GameGrid;
import forza4.IO.SavedGame;
import java.io.File;

/**
 * Tests the FileManager class.
 * @author Davide Paossi
 */
public class FileManagerTester {
    
    public static void test(){
    
        GameGrid grid=new GameGrid();
        String gameName="Forza 4 test";
        int ris=FileManager.save(grid.getGrid(),grid.getCurrentPlayer(),gameName, "Giocatore 1" , "Giocatore 2");
        System.out.println("Create a save file");
        System.out.println("Expected: 1 ");
        System.out.println("Actual: "+ris);
        SavedGame sg = FileManager.load(gameName);
        System.out.println("Load a save file");
        System.out.println("\n\nExpected: "+gameName+";Giocatore 1;Giocatore 2");
        System.out.println("Actual: "+sg.getGameTitle()+";"+sg.getPlayer1Name()+";"+sg.getPlayer2Name());
        File f=new File(FileManager.PATH+gameName+FileManager.EXTENTION);
        if(f.exists()){
            f.delete();
        }
        
    }
            
    
}
