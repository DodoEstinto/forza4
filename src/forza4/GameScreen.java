/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


/**
 *
 * @author AdSumPro
 */
public class GameScreen extends JFrame{
    
    
    public static final int X_SIZE=7;
    public static final int Y_SIZE=7;
    private static final String BUTTON_PREFIX="Button";
    private static final String TILE_PREFIX="Tile";
    private static final String SEPARATOR="_";
    private static Color backgroundColor;
    private static Color gridColor;
    private static Color borderColor;
    private static Color player1Color;
    private static Color player2Color;
    private  GameGrid grid;
    private JPanel gamePanel;
    
    public GameScreen(String title,GameGrid grid){
        super(title);
        this.grid=grid;
        init();           
        }

   
    
    private void init(){
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel=new JPanel(new GridLayout(Y_SIZE,X_SIZE)); //crea il pannel che ospiterà la griglia di gioco   
        setColors();
        addButtons(gamePanel);
        addTiles(gamePanel);
        this.add(gamePanel);
    }
    
    private void addButtons(JPanel gamePanel){        
        for(int currentColumn=0;currentColumn<X_SIZE;currentColumn++){
            String name=BUTTON_PREFIX+SEPARATOR+currentColumn;
            JButton button= new JButton(name);
            button.setName(name);
            button.setBackground(Color.yellow);
            button.setSize(50,50);
            button.setBorder(new LineBorder(GameScreen.borderColor));
            button.setActionCommand(Integer.toString(currentColumn));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    String actionCommand = ae.getActionCommand();
                    Integer column = Integer.valueOf(actionCommand);
                    int row=grid.insert(column);
                    if(row!=-1){
                        Component[] components = gamePanel.getComponents();
                        for(Component c:components){
                            String cName=c.getName();
                            if(cName.startsWith(TILE_PREFIX)){
                                String[] splitted=cName.split(SEPARATOR);
                                String rowString=splitted[1];
                                int rowInt=Integer.valueOf(rowString);
                                if(row==rowInt){
                                    String columnString=splitted[2];
                                    int columnInt=Integer.valueOf(columnString);
                                    if(column==columnInt){
                                        Tile tile=(Tile)c;
                                        tile.setStatus(grid.getCurrentPlayer());
                                    }
                                }                   
                            }
                            
                        }
                        int ris=grid.checkVictory(column,row);
                        grid.changeCurrentPlayer();
                        if(ris!=3){
                            System.out.println(ris);
                        }
                    }
                    repaint();
                }
            });
            gamePanel.add(button);
            
        }
    }
      
    private void addTiles(JPanel gamePanel){
        for(int currentRow=1;currentRow<Y_SIZE;currentRow++){ //La prima è occupata dai bottoni
            for(int currentColumn=0;currentColumn<X_SIZE;currentColumn++){
                String name=TILE_PREFIX+SEPARATOR+currentRow+SEPARATOR+currentColumn;
                Tile tile= new Tile(name);
                tile.setBackground(GameScreen.gridColor);
                tile.setBorder(new LineBorder(GameScreen.borderColor));
                gamePanel.add(tile);
            }
        }
    }
    
    private void setColors(){
    
        backgroundColor=Color.WHITE;
        gridColor=Color.GRAY;
        borderColor=Color.BLACK;
        player1Color=Color.RED;
        player2Color=Color.BLUE;
    
    }
    
    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static void setBackgroundColor(Color backgroundColor) {
        GameScreen.backgroundColor = backgroundColor;
    }

    public static Color getBorderColor() {
        return borderColor;
    }

    public static void setBorderColor(Color borderColor) {
        GameScreen.borderColor = borderColor;
    }

    public static Color getPlayer1Color() {
        return player1Color;
    }

    public static void setPlayer1Color(Color player1Color) {
        GameScreen.player1Color = player1Color;
    }

    public static Color getPlayer2Color() {
        return player2Color;
    }

    public static void setPlayer2Color(Color player2Color) {
        GameScreen.player2Color = player2Color;
    }

    public static Color getGridColor() {
        return gridColor;
    }

    public static void setGridColor(Color gridColor) {
        GameScreen.gridColor = gridColor;
    }
     
    public GameGrid getGrid() {
        return grid;
    }
 
     /*public int insert(int column){
        int ris = grid.insert(column);
        if(ris!=-1){
            Component[] components = this.getComponents();
            for(Component c:components){
                if(c.getName().equals(GAME_PANEL_NAME)){
                    JPanel ((JPanel)c)
                }
            }
        }
        return ris;
    }*/
}
