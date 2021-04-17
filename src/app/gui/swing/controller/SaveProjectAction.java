package app.gui.swing.controller;

import app.AppCore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveProjectAction extends AbstractRudokAction{

    public SaveProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/saveProject.png"));
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AppCore.getInstance().getRuSerilization().saveProject();
    }
}
