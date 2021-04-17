package app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getShareAction());

        JMenu saveMenu = new JMenu("Save");
        saveMenu.add(MainFrame.getInstance().getActionManager().getSaveWorkspaceAction());
        saveMenu.add(MainFrame.getInstance().getActionManager().getOpenWorkspaceAction());

        saveMenu.addSeparator();
        saveMenu.add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        saveMenu.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        saveMenu.add(MainFrame.getInstance().getActionManager().getSaveAsProjectAction());

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(MainFrame.getInstance().getActionManager().getAboutAction());

        this.add(fileMenu);
        this.add(saveMenu);
        this.add(helpMenu);

    }
}
