package app.gui.swing.controller;

import app.AppCore;
import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class OpenWorkspaceAction extends AbstractRudokAction{

    public OpenWorkspaceAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/OpenWorkspace.png"));
        putValue(NAME, "Open Workspace");
        putValue(SHORT_DESCRIPTION, "Open Saved Workspace From Your PC");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getOpenFrame().show("Workspace");
    }

}
