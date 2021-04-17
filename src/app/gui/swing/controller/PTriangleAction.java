package app.gui.swing.controller;

import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PTriangleAction extends AbstractRudokAction{

    public PTriangleAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/triangle.png"));
        putValue(SHORT_DESCRIPTION, "Triangle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Postavili smo  trougao ");
        MainFrame.getInstance().getDesktop().startTriangleState();
    }
}
