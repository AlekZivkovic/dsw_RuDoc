package app.gui.swing.controller;


import app.gui.swing.tree.model.RuTreeItem;
import app.gui.swing.view.MainFrame;
import app.repository.Project;
import app.repository.node.RuNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class NewAction extends AbstractRudokAction {

    public NewAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/plus.png"));
        putValue(NAME, "New Node");
        putValue(SHORT_DESCRIPTION, "New Node");
    }

    public void actionPerformed(ActionEvent arg0) {
        MainFrame.getInstance().getTree().add();
    }
}
