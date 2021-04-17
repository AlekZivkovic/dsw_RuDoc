package app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JToolBar {

    public Toolbar() {
        super(HORIZONTAL);
        setFloatable(false);

        add (MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add (MainFrame.getInstance().getActionManager().getShareAction());
        this.addSeparator(new Dimension(15, 0));
        add (MainFrame.getInstance().getActionManager().getAboutAction());
        this.addSeparator(new Dimension(67, 0));
        add(MainFrame.getInstance().getActionManager().getpRectangleAction());
        add(MainFrame.getInstance().getActionManager().getpTriangleAction());
        add(MainFrame.getInstance().getActionManager().getpCircleAction());
        add(MainFrame.getInstance().getActionManager().getsSelectAction());
        add(MainFrame.getInstance().getActionManager().getsDeleteAction());
        add(MainFrame.getInstance().getActionManager().getsMoveAction());
        add(MainFrame.getInstance().getActionManager().getsResizeAction());
        add(MainFrame.getInstance().getActionManager().getsRotateAction());
        add(MainFrame.getInstance().getActionManager().getsUndoRotateAction());
        add(MainFrame.getInstance().getActionManager().getsOpenAction());
        this.addSeparator(new Dimension(25, 0));
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
    }
}
