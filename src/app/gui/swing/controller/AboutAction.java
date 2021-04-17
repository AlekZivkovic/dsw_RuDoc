package app.gui.swing.controller;

import app.gui.swing.dialogs.Dialog;
import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutAction extends  AbstractRudokAction{


    public AboutAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/Info.png"));
        putValue(NAME, "About");
        putValue(SHORT_DESCRIPTION, "Info");

    }


    @Override
    public void actionPerformed(ActionEvent e) {MainFrame.getInstance().getDialogs().getDialog(Dialog.ABOUT);
    }
}
