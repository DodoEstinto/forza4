/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;


import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
    private static Color buttonColor;
    private static Color textButtonColor;
    private  GameGrid grid;
    private JPanel gamePanel;
    
    //test
    private GameScreen thisGS;
    
    public GameScreen(String title,GameGrid grid){
        super(title);
        this.grid=grid;
        thisGS=this;
        init();           
        }

   
    
    private void init(){
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenu();
        gamePanel=new JPanel(new GridLayout(Y_SIZE,X_SIZE)); //crea il pannel che ospiterà la griglia di gioco   
        setColors();
        addButtons(gamePanel);
        addTiles(gamePanel);
        this.add(gamePanel);
        repaint();
    }
    
    
    private void addButtons(JPanel gamePanel){        
        for(int currentColumn=0;currentColumn<X_SIZE;currentColumn++){
            String name=BUTTON_PREFIX+SEPARATOR+currentColumn;
            JButton button= new JButton(name);
            button.setName(name);
            button.setBackground(buttonColor);
            button.setForeground(textButtonColor);
            button.setSize(50,50);
            button.setBorder(new LineBorder(borderColor));
            button.setActionCommand(Integer.toString(currentColumn));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    //prende l'action command, il quale contiene la colonna del bottone
                    String actionCommand = ae.getActionCommand();
                    int column = Integer.valueOf(actionCommand);
                    //tenta l'inserimento
                    int row=grid.insert(column);
                    //se l'inserimento è andato a buon fine
                    if(row!=-1){
                        
                        //prende tutti i componenti e li itera.
                        Component[] components = gamePanel.getComponents();
                        for(Component c:components){
                            String cName=c.getName();
                            //cerca le tile tramite il nome
                            if(cName.startsWith(TILE_PREFIX)){
                                String[] splitted=cName.split(SEPARATOR);
                                //il secondo elemento dell'array contiene la riga.
                                String rowString=splitted[1];
                                int rowInt=Integer.valueOf(rowString);
                                //se la riga corrisponde a quella dell'inserimento
                                if(row==rowInt){
                                    //il terzo elemento dell'array contiene la colonna
                                    String columnString=splitted[2];
                                    int columnInt=Integer.valueOf(columnString);
                                    //se corrisponde anche la colonna
                                    if(column==columnInt){
                                        Tile tile=(Tile)c;
                                        //setta lo status alla casella
                                        tile.setStatus(grid.getCurrentPlayer());
                                        //aggiorna immediatamente la GUI
                                        repaint();
                                        break;
                                    }
                                }                   
                            }
                            
                        }
                        
                        //controlla la vittoria
                        int ris=grid.checkVictory(column,row);
                        //se è successo qualcosa oltre la vittoria
                        if(ris!=3){
                            //La partita è terminata, preparazione messaggio fine partita
                            String msg;
                            if(ris==0){
                                msg="PATTA! Nessuno ha vinto questa partita!";
                            }else{
                                msg="Il giocatore "+ris+" ha vinto questa partita!";
                            }
                            //salvataggio scelta utente
                            int choise=JOptionPane.showConfirmDialog(thisGS,msg+"\n"+"Vuoi iniziarne una nuova?"
                                                                    ,"Partita finita",JOptionPane.YES_NO_OPTION);
                            //SI:0   NO:1
                            if(choise==0){
                                reset(components,true);
                            }else{
                                thisGS.dispose();
                            }
                        }
                        //cambia player
                        grid.changeCurrentPlayer();
                    }
                    //repaint per mostrare le modifiche
                    repaint();
                }
            });
            gamePanel.add(button);
            
        }
    }
      
    private void addTiles(JPanel gamePanel){
        for(int currentRow=1;currentRow<Y_SIZE;currentRow++){ //La prima è occupata dai bottoni
            for(int currentColumn=0;currentColumn<X_SIZE;currentColumn++){
                String name=TILE_PREFIX+SEPARATOR+(currentRow-1)+SEPARATOR+currentColumn;
                Tile tile= new Tile(name);
                tile.setBackground(GameScreen.gridColor);
                tile.setBorder(new LineBorder(GameScreen.borderColor));
                gamePanel.add(tile);
            }
        }
    }
    
    private void setColors(){
    
        backgroundColor=Color.WHITE;
        gridColor=Color.DARK_GRAY;
        borderColor=Color.BLACK;
        player1Color=Color.RED;
        player2Color=Color.BLUE;
        buttonColor=Color.DARK_GRAY;
        textButtonColor=Color.WHITE;
    
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
    
    private void reset(Component[] components,boolean gridReset) {
        //resetta la griglia logica
        if(gridReset){
            grid.reset();
        }
        //cerca tutte le tile e le resetta (reset GUI)
        for (Component c : components) {
            String cName = c.getName();
            if (cName.startsWith(TILE_PREFIX)) {
                Tile tile = (Tile) c;
                tile.reset();
            }

        }
    }
 
    private void createMenu(){
        JMenuBar mb=new JMenuBar();
        JMenu gameMenu=new JMenu("Game");
        ActionListener menuListener=new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String actionCommand = ae.getActionCommand();
                if(actionCommand!=null){
                    String gameName;
                    switch(actionCommand){
                    
                        case "newGame":
                            reset(gamePanel.getComponents(),true);
                            repaint();
                            break;
                        case "saveGame":
                            
                            gameName=JOptionPane.showInputDialog(thisGS,"Choose the name of the game:","NewGame1");
                            int save;
                            save=FileManager.save(grid.getGrid(),grid.getCurrentPlayer(),gameName);
                            System.out.println(save);
                            break;
                        case "loadGame":
                            gameName=JOptionPane.showInputDialog(thisGS,"Choose the name of the game:","NewGame1");
                            SavedGame sg = FileManager.load(gameName);
                            if(sg!=null){
                                //GameGrid newGrid=new GameGrid();
                                //newGrid.setGrid(sg.getGrid());
                                //grid.setGrid(newGrid);
                                //thisGS.remove(gamePanel);
                                //thisGS.add(newPanel);
                                //gamePanel=newPanel;
                                //GameScreen newGS=new GameScreen(gameName,newGrid);
                                //newGS.setVisible(true);
                                //thisGS.dispose();
                                grid.setGrid(sg.getGrid());
                                reset(gamePanel.getComponents(),false);
                                checkStatus();
                                repaint();
                            }else{
                                JOptionPane.showMessageDialog(thisGS,"Partita non trovata o corrotta","Partita non caricata",JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case "exit":
                            System.out.println("exit");
                            break;
                    
                    }
                }
            }
        };
        JMenuItem newGameItem=new JMenuItem("New game");
        newGameItem.addActionListener(menuListener);
        newGameItem.setActionCommand("newGame");
        gameMenu.add(newGameItem);
        JMenuItem saveItem=new JMenuItem("Save Game");
        saveItem.addActionListener(menuListener);
        saveItem.setActionCommand("saveGame");
        gameMenu.add(saveItem);
        JMenuItem loadItem=new JMenuItem("Load Game");
        loadItem.addActionListener(menuListener);
        loadItem.setActionCommand("loadGame");
        gameMenu.add(loadItem);
        JMenuItem exitItem=new JMenuItem("Exit");
        //exitItem.setActionCommand("Exit");
        exitItem.addActionListener(menuListener);
        exitItem.setActionCommand("exit");
        gameMenu.add(exitItem);
        mb.add(gameMenu);
        JMenu optionMenu=new JMenu("Options");
        JMenuItem optionItem=new JMenuItem("Options");
        optionItem.addActionListener(menuListener);
        optionItem.setActionCommand("options");
        optionMenu.add(optionItem);
        //optionMenu.add("Credits");
        mb.add(optionMenu);
        this.setJMenuBar(mb);
    }
    
    private void checkStatus(){
        Component[] components=gamePanel.getComponents();
        //cerca tutte le tile 
        for (Component c : components) {
            String cName = c.getName();
            if (cName.startsWith(TILE_PREFIX)) {
                Tile tile = (Tile) c;
                String[] splitted = cName.split("_");
                int row=Integer.parseInt(splitted[1]);
                int column=Integer.parseInt(splitted[2]);
                tile.setStatus(grid.getGrid()[column][row]);
            }

        }   
    
    
    }
}
