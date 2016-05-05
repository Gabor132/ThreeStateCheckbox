/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threestatecheckbox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

/**
 * TODO add an enum that has the values accepted by this component
 * @author Dragos-Alexandru
 */
public class JTristateCheckBox extends JCheckBox implements Icon, ActionListener{
    
    //The posible states of the component
    public static final int NOT_SELECTED = 0;
    public static final int UNDECIDED = 1;
    public static final int SELECTED = 2;
    
    
    public JTristateCheckBox(){
        super();
        setup(NOT_SELECTED);
    }
    
    public JTristateCheckBox(String text){
        super(text);
        setup(NOT_SELECTED);
    }
    
    public JTristateCheckBox(String text, int selected){
        super(text, (selected>1));
        setup(selected);
    }
    
    private void setup(int value){
        switch(value){
            case SELECTED: setSelected(true);
            case UNDECIDED:{}
            case NOT_SELECTED:{
                putClientProperty("SelectionState", value);
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
        this.addActionListener(this);
        this.setIcon(this);
    }

    /**
     * If the TristateCheckbox is selected
     * @return true if the TristateCheckBox is SELECTED or UNDECIDED, and false otherwise
     */
    @Override
    public boolean isSelected() {
        if(getSelectionState() > NOT_SELECTED)
            return true;
        else
            return super.isSelected();
    }

    
    /**
     * Returns the selection state of the TristateCheckbox
     * @return An integer, 0 - NOT_SELECTED, 1 - UNDECIDED, 2 - SELECTED
     */
    public int getSelectionState(){
        if(getClientProperty("SelectionState") != null){
            return (int) getClientProperty("SelectionState");
        }else{
            if(super.isSelected()){
                return SELECTED;
            }else{
                return UNDECIDED;
            }
        }
    }
    
    /**
     * Sets the selection state of the TristateCheckBox
     * @param selection must be between 0 and 2
     */
    public void setSelectionState(int selection){
        switch(selection){
            case SELECTED: setSelected(true);
                break;
            case UNDECIDED: 
            case NOT_SELECTED: setSelected(false);
                break;
            default: throw new IllegalArgumentException();
        }
        putClientProperty("SelectionState", selection);
    }
    
    private static final Icon ICON = UIManager.getIcon("CheckBox.icon");

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        ICON.paintIcon(c, g, x, y);
        if(getSelectionState() != UNDECIDED) return;
        
        int w = getIconWidth();
        int h = getIconHeight();
        
        if(c.isEnabled()){
            g.setColor(new Color(51,51,51));
        }else{
            g.setColor(new Color(122,138,158));
        }
        
        g.fillRect(x+4, y+4, w-8, h-8);
        
        if(c.isEnabled()) {
            g.setColor(new Color(81,81,81));
            g.drawRect(x+4, y+4, w-9, h-9);
        }
        
    }

    @Override
    public int getIconWidth() {
        return ICON.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return ICON.getIconHeight();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        JTristateCheckBox tcb = (JTristateCheckBox) e.getSource();
        if(tcb.getSelectionState() == NOT_SELECTED){
            tcb.setSelected(false);
        }
        
        if(tcb.getSelectionState() == SELECTED){
            tcb.putClientProperty("SelectionState", NOT_SELECTED);
        }else{
            tcb.putClientProperty("SelectionState", tcb.getSelectionState() + 1);
        }
    }
}
