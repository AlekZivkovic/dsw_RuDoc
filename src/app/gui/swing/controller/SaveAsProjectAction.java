package app.gui.swing.controller;

import app.AppCore;
import app.gui.swing.view.MainFrame;
import app.repository.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveAsProjectAction extends AbstractRudokAction{

    public SaveAsProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/saveAsProject.png"));
        putValue(NAME, "Save As Project");
        putValue(SHORT_DESCRIPTION, "Save Project As");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getTree().getSelectedItem() instanceof Project)
            MainFrame.getInstance().getSaveAsFrame().showFrame();
        else
            AppCore.getInstance().getErrorHandler().throwException("Niste odabrali Projekat");
    }
}
