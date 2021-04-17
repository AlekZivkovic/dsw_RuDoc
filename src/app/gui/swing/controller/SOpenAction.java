package app.gui.swing.controller;

import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SOpenAction extends AbstractRudokAction {
    public SOpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_6, InputEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/openSourse.png"));
        putValue(NAME, "Open Slot");
        putValue(SHORT_DESCRIPTION, "Open slot by clicking on it");
    }


    @Override
    public void actionPerformed(ActionEvent e) { MainFrame.getInstance().getDesktop().startOpenState(); }
}
