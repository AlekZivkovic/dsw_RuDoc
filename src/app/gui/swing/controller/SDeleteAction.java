package app.gui.swing.controller;

import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SDeleteAction extends AbstractRudokAction {

    public SDeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_5, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/sDelete.png"));
        putValue(SHORT_DESCRIPTION, "Delete an object");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startDeleteState();

    }
}
