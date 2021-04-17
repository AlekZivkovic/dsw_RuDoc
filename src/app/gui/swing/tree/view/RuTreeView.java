package app.gui.swing.tree.view;



import app.gui.swing.tree.controller.RuTreeCellEditor;
import app.gui.swing.tree.controller.RuTreeMouseListener;
import app.gui.swing.tree.controller.RuTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

public class RuTreeView extends JTree {


    public RuTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        RuTreeCellRenderer ruTreeCellRenderer = new RuTreeCellRenderer();
        addTreeSelectionListener(new RuTreeSelectionListener());
        setCellEditor(new RuTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);
        addMouseListener(new RuTreeMouseListener());
    }
}
