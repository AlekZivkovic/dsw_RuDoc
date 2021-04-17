package app.gui.swing.controller;

import app.AppCore;
import app.gui.swing.view.MainFrame;
import app.repository.Document;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ShareAction extends AbstractRudokAction{

    public ShareAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/SharedDocument.png"));
        putValue(NAME, "Share Document Between Projects");
        putValue(SHORT_DESCRIPTION, "Share Document Between Projects");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getTree().getSelectedItem() instanceof Document){
            MainFrame.getInstance().getShareFrame().showFrame();
        }

        else{
            AppCore.getInstance().getErrorHandler().throwException("Morate odabrati Dokument koji ce se deliti");
        }


    }
}
