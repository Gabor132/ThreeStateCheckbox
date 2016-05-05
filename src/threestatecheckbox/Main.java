/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threestatecheckbox;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author Dragos-Alexandru
 */
public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("TSCB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 300);
        
        JTristateCheckBox tscb = new JTristateCheckBox("Test");
        frame.add(tscb);
        
        frame.setVisible(true);
    }
}
