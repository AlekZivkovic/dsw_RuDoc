package app.gui.swing.tree;



import app.core.Publisher;
import app.gui.swing.tree.model.RuTreeItem;
import app.repository.Project;
import app.repository.Workspace;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public interface RuTree {

    JTree generateTree(Workspace workspace);
    void add();
    void delete();
    void share(String projectName);
    RuNode getSelectedItem();
    RuTreeItem getSelectedTreeItem();
    RuTreeItem getRoot();
    void setRoot(Workspace root);
    RuNodeComposite getRootNodeModel();
    void openProject(Project project);
    void setProjectName(String name);
    void setSelectedProject(RuNode project);

}
