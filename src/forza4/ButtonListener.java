/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author AdSumPro
 */
public class ButtonListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        String actionCommand = ae.getActionCommand();
        System.out.println(actionCommand);
    }
    
}
