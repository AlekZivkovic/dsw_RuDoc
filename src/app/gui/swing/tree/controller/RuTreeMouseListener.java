package app.gui.swing.tree.controller;

import app.gui.swing.tree.model.RuTreeItem;
import app.gui.swing.view.MainFrame;
import app.repository.Project;
import app.repository.node.RuNode;

import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RuTreeMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            RuTreeItem selectedItem =null;
            //System.out.println(MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent());
            selectedItem=lookForProject(MainFrame.getInstance().getWorkspaceTree().getSelectionPath());
            if(selectedItem!=null)
                MainFrame.getInstance().getDesktop().addProject(selectedItem.getNodeModel());

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //Trazi projekat koji ce staviti kao workspace
    private RuTreeItem lookForProject(TreePath treePath) {
        try {
            int i = treePath.getPath().length - 1;
            RuTreeItem project = null;
            for (; i >= 0; i--) {
                RuNode r = ((RuTreeItem) (treePath.getPath()[i])).getNodeModel();
                // System.out.println("Usao za " + r);
                // if(r instanceof Page) System.out.println(r.getParent());

                if (r instanceof Project) {
                    project = ((RuTreeItem) (treePath.getPath()[i]));
                    //  System.out.println("If");
                    break;
                }
            }
            return project;
        }catch(NullPointerException e){
            return null;
        }
    }
}
