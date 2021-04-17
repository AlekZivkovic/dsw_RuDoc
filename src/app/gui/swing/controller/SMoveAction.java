package app.gui.swing.controller;

import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SMoveAction extends AbstractRudokAction{

    public SMoveAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_D, InputEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/move.png"));
        putValue(NAME, "Move Slot");
        putValue(SHORT_DESCRIPTION, "Move Slot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startMoveState();
    }
}
