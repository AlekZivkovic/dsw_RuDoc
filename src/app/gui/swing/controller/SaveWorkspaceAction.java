package app.gui.swing.controller;

import app.AppCore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveWorkspaceAction extends AbstractRudokAction{

    public SaveWorkspaceAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/SaveWorkspace.png"));
        putValue(NAME, "Save Workspace");
        putValue(SHORT_DESCRIPTION, "Save Current Workspace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AppCore.getInstance().getRuSerilization().saveWorkspace();
    }
}
