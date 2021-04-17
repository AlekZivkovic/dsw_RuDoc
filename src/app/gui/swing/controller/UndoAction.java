package app.gui.swing.controller;

import app.AppCore;
import app.core.PublisherString;
import app.core.SubscriberString;
import app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractRudokAction implements SubscriberString {

    public UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/undo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().getRuDeskDoc().getCommandManager().undoCommand();
    }

    @Override
    public void subscribe(PublisherString publisher) {
        publisher.addSubscriber(this);
    }

    @Override
    public void unsubscribe(PublisherString publisher) {
        publisher.removeSubscriber(this);
    }

    @Override
    public void update(String string) {
        if(string.equals("UndoEnable")) {
            this.setEnabled(true);
        }
        if(string.equals("UndoDisable"))
            this.setEnabled(false);
    }
}
