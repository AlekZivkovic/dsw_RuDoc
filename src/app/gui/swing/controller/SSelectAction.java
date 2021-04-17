package app.gui.swing.controller;

import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SSelectAction extends AbstractRudokAction {

    public SSelectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_4, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/select.png"));
        putValue(SHORT_DESCRIPTION, "Select");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startSelectState();
    }
}
