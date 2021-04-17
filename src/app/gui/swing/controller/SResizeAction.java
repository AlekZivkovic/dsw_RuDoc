package app.gui.swing.controller;

import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SResizeAction extends AbstractRudokAction{

    public SResizeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_D, InputEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/resize.png"));
        putValue(NAME, "Resize Slot");
        putValue(SHORT_DESCRIPTION, "Resize Slot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startResizeState();
    }
}
