package app.gui.swing.controller;

import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SRotateAction extends AbstractRudokAction{

    public SRotateAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_R, InputEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/rotate.png"));
        putValue(NAME, "Rotate Slot");
        putValue(SHORT_DESCRIPTION, "Rotate Slot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startRotateState();
    }
}
