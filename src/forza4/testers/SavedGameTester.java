/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4.testers;

import forza4.SavedGame;

/**
 *
 * @author Davide Paossi
 */
public class SavedGameTester {
    
    public static void test(){
        
        SavedGame sg=new SavedGame();
        int[][] tempGrid={{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},
            {0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        sg.setGrid(tempGrid);
        
        System.out.println("Trying the grid set and get: ");
        System.out.println( "Expected:\n"+
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]");
        System.out.println("Actual:");
        printArray(tempGrid);
        
        
        System.out.println("\n\nInsering an invalid grid:");
        tempGrid=new int[][]{{1,1,1,0,0,0},null,{0,0,0,0,0,0},
            {0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        sg.setGrid(tempGrid);
        System.out.println( "Expected:\n"+
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]");
        System.out.println("Actual:");
        printArray(sg.getGrid());
        
        System.out.println("\n\nSetting the current player:");
        sg.setCurrentPlayer(2);
        System.out.println( "Expected: 2");
        System.out.println("Actual: "+sg.getCurrentPlayer());
        
        System.out.println("\n\nSetting an invalid current player:");
        sg.setCurrentPlayer(4);
        System.out.println( "Expected: 2");
        System.out.println("Actual: "+sg.getCurrentPlayer());
        
        System.out.println("\n\nSetting a game title:");
        sg.setGameTitle("Title");
        System.out.println("Expected: Title");
        System.out.println("Actual: "+sg.getGameTitle());
        sg.setGameTitle(null);
        
        System.out.println("\n\nSetting an invalid game title:");
        System.out.println("Expected: Title");
        System.out.println("Actual: Title");
    
        System.out.println("\n\nSetting player 1 name:");
        sg.setPlayer1Name("player 1");
        System.out.println("Expected: player 1");
        System.out.println("Actual: "+sg.getPlayer1Name());
        
        System.out.println("\n\nSetting an invalid player 1 name:");
        sg.setPlayer1Name(null);
        System.out.println("Expected: player 1");
        System.out.println("Actual: player 1");
        
        System.out.println("\n\nSetting player 2 name:");
        sg.setPlayer2Name("player 2");
        System.out.println("Expected: player 2");
        System.out.println("Actual: "+sg.getPlayer2Name());
        
        
        System.out.println("\n\nSetting an invalid player 2 name:");
        sg.setPlayer2Name(null);
        System.out.println("Expected: player 2");
        System.out.println("Actual: player 2");        
    
    }
    
    private static void printArray(int[][] arr){
        if(arr!=null){
            for(int i=arr.length-1;i>=0;i--){
                if(arr[i]!=null){
                    System.out.print("[");
                    for(int i2=0;i2<arr[i].length;i2++){
                        System.out.print(arr[i][i2]);
                        if(i2+1<arr[i].length){
                            System.out.print(",");
                        }
                    }
                    System.out.println("]");
                }else{
                    System.out.println("Null");
                }
            }
        }else{
            
        }
    }
    
}
