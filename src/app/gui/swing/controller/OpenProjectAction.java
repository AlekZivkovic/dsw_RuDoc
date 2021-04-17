package app.gui.swing.controller;

import app.AppCore;
import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class OpenProjectAction extends AbstractRudokAction {

    public OpenProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/openProject.png"));
        putValue(NAME, "Open Project");
        putValue(SHORT_DESCRIPTION, "Open an existing project from your pc");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getOpenFrame().show("Project");
    }
}
