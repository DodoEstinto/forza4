/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4.testers;

import forza4.GameGrid;
import java.util.ArrayList;

/**
 *Tests the GameGrid class.
 * @author Davide Paossi
 */
public class GameGridTester {
    public static void test(){
        GameGrid gg=new GameGrid();
        /* All the grid are rappresented with a 90° left rotation (so like if 
        * they are with their left side on the ground) for comodity reasons.
        */
        System.out.println("Create the grid");
        System.out.println( "Expected:\n"+
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]");
        System.out.println("Actual:");
        printArray(gg.getGrid());
        
        System.out.println("\n\nInsert a value in a valid column.");
        ArrayList<Integer> cp=new ArrayList<>();//an array list that contains
        //the values.
        cp.add(gg.getCurrentPlayer()); //dd the current player
        gg.insert(0); //insert to the first column
        System.out.println( "Expected:\n"+
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,"+cp.get(0)+"]");
        System.out.println("Actual:");
        printArray(gg.getGrid());
        
        System.out.println("\n\nInsert a value in an invalid column.");
        gg.insert(-1);
        System.out.println( "Expected:\n"+
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,"+cp.get(0)+"]");
        System.out.println("Actual:");
        printArray(gg.getGrid());
        
        cp.add(gg.getCurrentPlayer());
        System.out.println("\n\nInsert a value after a wrong value.");
        gg.insert(0);
        System.out.println( "Expected:\n"+
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,"+cp.get(1)+","+cp.get(0)+"]");
        System.out.println("Actual:");
        printArray(gg.getGrid());
        
        System.out.println("\n\nChange the current player");
        gg.changeCurrentPlayer();
        int temp=cp.get(0)==1?2:1; //invert
        System.out.println("Expected:\n"+temp);
        System.out.println("Actual:");
        System.out.println(gg.getCurrentPlayer());
        System.out.println("\n\nReset the grid");
        gg.reset();
        System.out.println( "Expected:\n"+
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]");
        System.out.println("Actual:");
        printArray(gg.getGrid());
        
        System.out.println("\n\nCheck the set method");
        int[][] tempGrid={{0,0,0,0,0,1},{0,0,0,0,0,1},{0,0,0,0,0,1},
            {0,0,0,0,0,1},{0,0,0,0,0,2},{0,0,0,0,0,2},{0,0,0,0,0,2}};
        System.out.println( "Expected:\n"+
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,1]\n" +
                            "[0,0,0,0,0,1]\n" +
                            "[0,0,0,0,0,1]\n" +
                            "[0,0,0,0,0,1]");
        System.out.println("Actual:");
        gg.setGrid(tempGrid);
        printArray(gg.getGrid());
        
        System.out.println("\n\nCheck the victory algorithm");
        System.out.println( "Actual grid:\n"+
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,1]\n" +
                            "[0,0,0,0,0,1]\n" +
                            "[0,0,0,0,0,1]\n" +
                            "[0,0,0,0,0,1]");
        System.out.println("Given cords: (0,5)");
        if(gg.getCurrentPlayer()!=1){
            gg.changeCurrentPlayer();
        }
        System.out.println("Actual player:"+gg.getCurrentPlayer());
        System.out.println("Expected:\n 1");
        gg.setGrid(tempGrid);
        System.out.println("Actual:");
        System.out.println(gg.checkVictory(0,5));
        
        System.out.println("\n\n");
        System.out.println( "Actual grid:\n"+
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,1]\n" +
                            "[0,0,0,0,0,1]\n" +
                            "[0,0,0,0,0,1]\n" +
                            "[0,0,0,0,0,1]");
        System.out.println("Given cords: (0,3)");
        System.out.println("Expected:\n 3");
        System.out.println("Actual:");
        System.out.println(gg.checkVictory(0,3));
        
        System.out.println("\n\n");
        tempGrid=new int[][]{{1,2,1,2,1,2},{1,2,1,2,1,1},{2,1,1,1,2,1},
            {1,2,2,2,1,2},{2,1,2,2,1,2},{2,1,2,1,1,1},{1,2,1,2,2,2}};
        gg.setGrid(tempGrid);
        System.out.println( "Actual grid:\n"+
                            "[1,2,1,2,2,2]\n" +
                            "[2,1,2,1,1,1]\n" +
                            "[2,1,2,2,1,2]\n" +
                            "[1,2,2,2,1,2]\n" +
                            "[2,1,1,1,2,1]\n" +
                            "[1,2,1,2,1,1]\n" +
                            "[1,2,1,2,1,2]");
        System.out.println("Given cords: (0,0)");
        System.out.println("Expected:\n 0");
        System.out.println("Actual:");
        System.out.println(gg.checkVictory(0,0));
        
        System.out.println("\n\n");
        System.out.println( "Actual grid:\n"+
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,1,1,1,1]\n" +
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,0,0,0,2]");
        tempGrid=new int[][]{{0,0,0,0,0,2},{0,0,0,0,0,2},{0,0,1,1,1,1},
            {0,0,0,0,0,2},{0,0,0,0,0,2},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        gg.setGrid(tempGrid);
        System.out.println("Given cords: (2,5)");
        if(gg.getCurrentPlayer()!=1){
            gg.changeCurrentPlayer();
        }
        System.out.println("Actual player:"+gg.getCurrentPlayer());
        System.out.println("Expected: 1");
        System.out.println("Actual:");
        System.out.println(gg.checkVictory(2,5));
        
        System.out.println("\n\n");
        System.out.println( "Actual grid:\n"+
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,0]\n" +
                            "[0,0,0,0,0,2]\n" +
                            "[0,0,2,1,1,1]\n" +
                            "[0,0,0,2,1,1]\n" +
                            "[0,0,0,0,2,1]\n" +
                            "[0,0,0,0,2,2]");
        tempGrid=new int[][]{{0,0,0,0,2,2},{0,0,0,0,2,1},{0,0,0,2,1,1},
            {0,0,2,1,1,1},{0,0,0,0,0,2},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        gg.setGrid(tempGrid);
        System.out.println("Given cords: (3,2)");
        if(gg.getCurrentPlayer()!=2){
            gg.changeCurrentPlayer();
        }
        System.out.println("Actual player:"+gg.getCurrentPlayer());
        System.out.println("Expected: 2");
        System.out.println("Actual:");
        System.out.println(gg.checkVictory(3,2));
        
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
