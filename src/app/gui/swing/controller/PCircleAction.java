package app.gui.swing.controller;


import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PCircleAction extends  AbstractRudokAction {


    public PCircleAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_3, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/circle.jpg"));
        putValue(SHORT_DESCRIPTION, "Circle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startCircleState();

    }

}
