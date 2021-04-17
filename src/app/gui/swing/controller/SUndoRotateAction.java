package app.gui.swing.controller;

import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SUndoRotateAction extends AbstractRudokAction{

    public SUndoRotateAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_U, InputEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/undoRotate.png"));
        putValue(NAME, "Undo Rotate Slot");
        putValue(SHORT_DESCRIPTION, "Undo Rotate Slot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startUndoRotateState();
    }
}
