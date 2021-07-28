/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4.testers;

import forza4.Tile;

/**
 * Tets the tile.
 * @author Davide Paossi
 */
public class TileTester {
    public static void test(){
        Tile t=new Tile("test");
        System.out.println("Expected:\tName: test\tStatus:0");
        System.out.println("Actual:\tName:"+t.getName()+"\tStatus:"+t.getStatus());
        System.out.println("\n\n");
        t.setStatus(1);
        System.out.println("Expected:1");
        System.out.println("Actual:"+t.getStatus());
        t.setStatus(2);
        System.out.println("\n\n");
        System.out.println("Expected:2");
        System.out.println("Actual:"+t.getStatus());
        t.setStatus(3);
        System.out.println("\n\n");
        System.out.println("Expected:2");
        System.out.println("Actual:"+t.getStatus());
        t.reset();
        System.out.println("\n\n");
        System.out.println("Expected:0");
        System.out.println("Actual:"+t.getStatus());
    
    }
}












