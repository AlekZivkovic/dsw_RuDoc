package app.gui.swing.controller;

import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractRudokAction{

    public DeleteAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_D, InputEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/Delete.png"));
        putValue(NAME, "Delete Node");
        putValue(SHORT_DESCRIPTION, "Delete Node");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getTree().delete();
    }
}
