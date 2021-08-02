/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

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
 * @author AdSumPro
 */
public class Options extends JFrame {

    private JPanel mainPanel;
    private SettableScreenColors ssc;
    private final Options thisOptions;
    private Refreshable ref;

    public Options(SettableScreenColors ssc, Refreshable ref) {
        super();
        this.setSize(700, 500);
        this.setPreferredSize(new Dimension(1000, 1000));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void init() {
        mainPanel = new JPanel(new GridLayout(6, 1));
        JLabel label = new JLabel("Select the color to change:");
        mainPanel.add(label);
        createComboBox();
        createColorShower();
        createButtons();
        this.add(mainPanel);
    }

    private void createComboBox() {
        JComboBox<String> cb = new JComboBox<>(new String[]{"backgroundColor",
            "player1Color", "player2Color"});
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mainPanel != null) {
                    Component[] components = mainPanel.getComponents();
                    if (components != null) {
                        for (Component c : components) {
                            String name = c.getName();
                            if (name != null && name.equals("colorShower")) {
                                if (c instanceof JPanel) {
                                    JPanel colorShower = (JPanel) c;
                                    Color color;
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
                                }
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

    private void createButtons() {

        JButton chooseColorButton = new JButton("Press here to choose a color");
        chooseColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Color selectedColor = JColorChooser.showDialog(null, "Title", Color.red);
                if (mainPanel != null) {
                    Component[] components = mainPanel.getComponents();
                    if (components != null) {
                        for (Component c : components) {
                            String name = c.getName();
                            if (name != null && name.equals("colorShower")) {
                                if (c instanceof JPanel) {
                                    JPanel colorShower = (JPanel) c;
                                    colorShower.setBackground(selectedColor);
                                }
                            }
                        }
                    }
                }
            }
        });

        JButton confirmColorButton = new JButton("Press here to confirm the color");

        confirmColorButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                //DA FINIRE
                if (mainPanel != null) {
                    Component[] components = mainPanel.getComponents();
                    if (components != null) {
                        for (Component c : components) {
                            String name = c.getName();
                            if (name != null && name.equals("colorShower")) {
                                if (c instanceof JPanel) {
                                    JPanel colorShower = (JPanel) c;

                                    for (Component c2 : components) {
                                        name = c2.getName();
                                        if (name != null && name.equals("comboBox")) {
                                            if (c2 instanceof JComboBox) {
                                                JComboBox cb = (JComboBox) c2;
                                                Color color = colorShower.getBackground();
                                                switch (cb.getSelectedItem().toString()) {
                                                    case "backgroundColor":
                                                        ssc.setBackgroundColor(color);
                                                        ref.repaint();
                                                        break;
                                                    /*case "gridColor":
                                                        ssc.setGridColor(color);
                                                        ref.repaint();
                                                        break;
                                                    case "borderColor":
                                                        ssc.setBorderColor(color);
                                                        ref.repaint();
                                                        break;*/
                                                    case "player1Color":
                                                        ssc.setPlayer1Color(color);
                                                        ref.repaint();
                                                        break;
                                                    case "player2Color":
                                                        ssc.setPlayer2Color(color);
                                                        ref.repaint();
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                    }

                                }
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

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                thisOptions.dispose();
            }
        });

        mainPanel.add(chooseColorButton);
        mainPanel.add(confirmColorButton);
        mainPanel.add(closeButton);
    }

    private void createColorShower() {
        JPanel colorShower = new JPanel();
        colorShower.setName("colorShower");
        mainPanel.add(colorShower);
    }

}
