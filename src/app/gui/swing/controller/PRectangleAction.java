package app.gui.swing.controller;


import app.gui.swing.view.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PRectangleAction extends  AbstractRudokAction {

    public  PRectangleAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/rectange.png"));
        putValue(SHORT_DESCRIPTION, "Rectangle");
    }



    @Override
    public void actionPerformed(ActionEvent e) {
      MainFrame.getInstance().getDesktop().startRectangleState();



    }
}
