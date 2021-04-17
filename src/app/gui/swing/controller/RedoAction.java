package app.gui.swing.controller;

import app.AppCore;
import app.core.PublisherString;
import app.core.SubscriberString;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.gui.swing.view.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractRudokAction implements SubscriberString {

    public RedoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/redo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
        this.setEnabled(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().getRuDeskDoc().getCommandManager().doCommand();
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
        if(string.equals("RedoEnable"))
            this.setEnabled(true);
        if(string.equals("RedoDisable"))
            this.setEnabled(false);
    }
}
