/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4.GUI;

import forza4.interfaces.Refreshable;
import forza4.interfaces.SettableScreenColors;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Paossi Davide
 */
public class Options extends JFrame {

    /**
     * The panel that contains all the elements.
     */
    private JPanel mainPanel;
    /**
     * The screen colors to change.
     */
    private SettableScreenColors ssc;
    /**
     * Reference to itself.
     */
    private final Options thisOptions;
    /**
     * The screen to refresh.
     */
    private Refreshable ref;

    /**
     * Create a Options object with a determined SettableScreenColors and
     * Refreshable. If one or either of the two are null it will show an error
     * and dispose itself. Ssc and ref should be the same object.
     *
     * @param ssc The screen colors to change.
     * @param ref The screen to refresh.
     */
    public Options(SettableScreenColors ssc, Refreshable ref) {
        super();
        this.setSize(700, 500);
        this.setPreferredSize(new Dimension(1000, 1000));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Options");
        init();
        if (ssc == null) {
            JOptionPane.showMessageDialog(this, "An error occured",
                    "Can't access the colors", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        } else {
            this.ssc = ssc;
        }
        if (ref == null) {
            JOptionPane.showMessageDialog(this, "An error occured",
                    "Can't access the refreshable", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        } else {
            this.ref = ref;
        }
        thisOptions = this;
    }

    /**
     * Init all the components.
     */
    private void init() {
        mainPanel = new JPanel(new GridLayout(6, 1));
        JLabel label = new JLabel("Select the color to change:");
        mainPanel.add(label);
        createComboBox();
        createColorShower();
        createButtons();
        this.add(mainPanel);
    }

    /**
     * Create a combo box that makes possible to select one of the ssc colors
     * and add it in the mainPanel.
     */
    private void createComboBox() {
        JComboBox<String> cb = new JComboBox<>(new String[]{"backgroundColor",
            "gridColor", "borderColor", "player1Color", "player2Color"});
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean stop = false;
                if (mainPanel != null) {
                    //get the components and iterate them.
                    Component[] components = mainPanel.getComponents();
                    if (components != null) {
                        for (Component c : components) {
                            String name = c.getName();
                            if (name != null && name.equals("colorShower")) {
                                if (c instanceof JPanel) {
                                    //the element is the JPanel that shows
                                    //the color selected
                                    JPanel colorShower = (JPanel) c;
                                    Color color;
                                    //initialize color with the selected color
                                    switch (cb.getSelectedItem().toString()) {
                                        case "backgroundColor":
                                            color = ssc.getBackgroundColor();
                                            break;
                                        case "gridColor":
                                            color = ssc.getGridColor();
                                            break;
                                        case "borderColor":
                                            color = ssc.getBorderColor();
                                            break;
                                        case "player1Color":
                                            color = ssc.getPlayer1Color();
                                            break;
                                        case "player2Color":
                                            color = ssc.getPlayer2Color();
                                            break;
                                        default:
                                            color = Color.WHITE;
                                            break;
                                    }
                                    colorShower.setBackground(color);
                                    //marks that we found the right component
                                    stop = true;
                                }
                            }
                            //stop the cicle
                            if (stop) {
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(thisOptions,
                                "An error occured", "Can't find the components",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(thisOptions,
                            "An error occured", "Can't find the panel",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cb.setName("comboBox");
        mainPanel.add(cb);
    }

    /**
     * Create all the buttons needed and add them to the mainPanel.
     */
    private void createButtons() {

        //create the button to choose a new color
        JButton chooseColorButton = new JButton("Press here to choose a color");
        chooseColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean stop = false;
                //save the color selection of the user.
                Color selectedColor = JColorChooser.showDialog(null, "Title",
                        Color.red);
                if (mainPanel != null) {
                    //gets the component and iterate them
                    Component[] components = mainPanel.getComponents();
                    if (components != null) {
                        for (Component c : components) {
                            String name = c.getName();
                            if (name != null && name.equals("colorShower")) {
                                if (c instanceof JPanel) {
                                    JPanel colorShower = (JPanel) c;
                                    //displays the selected color
                                    colorShower.setBackground(selectedColor);
                                    //marks that we found the right component
                                    stop = true;
                                }
                            }
                            //stop the cicle
                            if (stop) {
                                break;
                            }
                        }
                    }
                }
            }
        });

        //create the button to confirm the choosen color
        JButton confirmColorButton = new JButton("Press here to confirm the color");

        confirmColorButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                boolean stop = false;
                if (mainPanel != null) {
                    /*gets all the components and iterate them in order to find
                    *the color shower.
                     */
                    Component[] components = mainPanel.getComponents();
                    if (components != null) {
                        for (Component c : components) {
                            String name = c.getName();
                            if (name != null && name.equals("colorShower")) {
                                if (c instanceof JPanel) {
                                    JPanel colorShower = (JPanel) c;
                                    /*gets all the components and iterate them 
                                     *in order to find the comboBox
                                     */
                                    for (Component c2 : components) {
                                        name = c2.getName();
                                        if (name != null && name.equals("comboBox")) {
                                            if (c2 instanceof JComboBox) {
                                                JComboBox cb = (JComboBox) c2;
                                                Color color = colorShower.getBackground();
                                                //get the selected color and set it
                                                switch (cb.getSelectedItem().toString()) {
                                                    case "backgroundColor":
                                                        ssc.setBackgroundColor(color);
                                                        break;
                                                    case "gridColor":
                                                        ssc.setGridColor(color);
                                                        break;
                                                    case "borderColor":
                                                        ssc.setBorderColor(color);
                                                        break;
                                                    case "player1Color":
                                                        ssc.setPlayer1Color(color);
                                                        break;
                                                    case "player2Color":
                                                        ssc.setPlayer2Color(color);
                                                        break;
                                                    default:
                                                        break;
                                                }
                                                /*marks that we found the right
                                                component*/
                                                stop = true;
                                                //refresh the screen
                                                ref.repaint();
                                            }
                                        }
                                        //stops the second iteration
                                        if (stop) {
                                            break;
                                        }
                                    }

                                }
                            }
                            //stops the first iteration
                            if (stop) {
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(thisOptions,
                                "An error occured", "Can't find the components",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(thisOptions,
                            "An error occured", "Can't find the panel",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        //Create the button that closes the window.
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                thisOptions.dispose();
            }
        });
        //adds all the buttons
        mainPanel.add(chooseColorButton);
        mainPanel.add(confirmColorButton);
        mainPanel.add(closeButton);
    }

    /**
     * Create the JPanel that shows the selected color.
     */
    private void createColorShower() {
        JPanel colorShower = new JPanel();
        colorShower.setName("colorShower");
        mainPanel.add(colorShower);
    }

}
