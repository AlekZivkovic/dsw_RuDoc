package app.gui.swing.tree.view;



import app.AppCore;
import app.gui.swing.tree.RuTree;
import app.gui.swing.tree.model.RuTreeItem;
import app.gui.swing.view.MainFrame;
import app.repository.*;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;
import app.repository.slotFactory.sloth.Slot;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class RuTreeImplementation implements RuTree {

    private RuTreeView treeView;
    private DefaultTreeModel treeModel;


    @Override
    public JTree generateTree(Workspace workspace) {
        RuTreeItem root = new RuTreeItem(workspace);
        treeModel = new DefaultTreeModel(root);
        treeView = new RuTreeView(treeModel);
        return treeView;
    }

    @Override
    public void add() {

        RuTreeItem selectedItem = this.getSelectedTreeItem();
        if(selectedItem==null){
            selectedItem = this.getRoot();
        }
        RuNode nodeModel = selectedItem.getNodeModel();
        treeView.expandPath(treeView.getSelectionPath());


        RuNode newNode = null;


        if(nodeModel instanceof Workspace){
            newNode = new Project(nodeModel);
        }
        else if(nodeModel instanceof Project){
            newNode = new Document(nodeModel);
        }
        else if(nodeModel instanceof Document){
            newNode = new Page(nodeModel);
        }
        else if(nodeModel instanceof Page){
            //newNode = new Slot(nodeModel);
        }
        else if(newNode == null){
            return;
        }

        RuTreeItem ruTreeItem = new RuTreeItem(newNode);
        //iz nekog razloga ne radi sortiranje;
        (selectedItem).add(ruTreeItem);
        //ruTreeItem.setParent(selectedItem);
        MainFrame.getInstance().getDesktop().subscribe(newNode);
        ((RuNodeComposite)nodeModel).addChild(newNode);


        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public void delete() {

        RuTreeItem selectedItem = this.getSelectedTreeItem();
        if(selectedItem == null || this.getSelectedItem() instanceof Workspace){
            return;
        }

        if(this.getSelectedItem() instanceof Document && ((Document)this.getSelectedItem()).isShared()){

            RuTreeItem wsRuTreeItem = this.getRoot();

            for(int i = wsRuTreeItem.getChildCount()-1; i>=0; i--){
                RuTreeItem prjRuTreeItem = (RuTreeItem) wsRuTreeItem.getChildAt(i);
                for(int j = prjRuTreeItem.getChildCount()-1; j>=0; j--){
                    if(((RuTreeItem)prjRuTreeItem.getChildAt(j)).equals(selectedItem)) {
                        selectedItem.setParent(prjRuTreeItem);
                        prjRuTreeItem.remove(selectedItem);
                        ((RuNodeComposite)prjRuTreeItem.getNodeModel()).deleteChild(selectedItem.getNodeModel());
                        break;
                    }
                }
            }

            MainFrame.getInstance().getWorkspaceTree().setSelectionPath(MainFrame.getInstance().getWorkspaceTree().getLeadSelectionPath().getParentPath());

//            ((RuNodeComposite)selectedItem.getNodeModel().getParent()).deleteChild(selectedItem.getNodeModel());
            SwingUtilities.updateComponentTreeUI(treeView);
            return;

        }

        RuNode nodeModel = selectedItem.getNodeModel();
        Object[] path = MainFrame.getInstance().getWorkspaceTree().getSelectionPath().getPath();

        if(nodeModel instanceof Page) {
            if(MainFrame.getInstance().getDesktop().getProject().equals(nodeModel.getParent().getParent()))
                MainFrame.getInstance().getDesktop().removeCommands((Page) nodeModel);
        }

        if(nodeModel instanceof Project){
            if((((Workspace) MainFrame.getInstance().getTree().getRootNodeModel()).getOpened()!=null)){
                if(((Workspace)MainFrame.getInstance().getTree().getRootNodeModel()).getOpened().equals(nodeModel)) {
                    ((Workspace) MainFrame.getInstance().getTree().getRootNodeModel()).setOpened(null);
                }
            }
        }

        RuTreeItem parent = (RuTreeItem) path[path.length - 2];


        //Iz nekog razloga mi se nije deselktovao cvor kada ga obrisem i pucao je program kada probam opet da obrisem
        //pa sam ga rucno deselektovao
       // MainFrame.getInstance().getWorkspaceTree().clearSelection();

        //Ova linija vraca pokazivac na parenta obrisanog cvora sto ce nam omoguciti da dalje sprvodimo kod AKA Focus u Tabovima
        MainFrame.getInstance().getWorkspaceTree().setSelectionPath(MainFrame.getInstance().getWorkspaceTree().getLeadSelectionPath().getParentPath());

        parent.remove(selectedItem);
        ((RuNodeComposite)parent.getNodeModel()).deleteChild(nodeModel);

        SwingUtilities.updateComponentTreeUI(treeView);

    }

    public void share(String projectName){
        RuTreeItem root = MainFrame.getInstance().getTree().getRoot();

        for(int i = 0; i<root.getChildCount(); i++){
            RuTreeItem prj = (RuTreeItem) root.getChildAt(i);

            if(prj.getName().equals(projectName)){
                RuTreeItem selectedDoc = MainFrame.getInstance().getTree().getSelectedTreeItem();

                if(((RuNodeComposite)prj.getNodeModel()).getChildren().contains(selectedDoc.getNodeModel())){
                    AppCore.getInstance().getErrorHandler().throwException("Ime vec postoji u odabranom projektu");
                    return;
                }

                prj.add(selectedDoc);
                ((RuNodeComposite)prj.getNodeModel()).addChild(selectedDoc.getNodeModel());
                ((Document)selectedDoc.getNodeModel()).setShared(Boolean.TRUE);

                TreePath treePath = new TreePath(prj.getPath());
                treeView.expandPath(treePath);

                SwingUtilities.updateComponentTreeUI(treeView);

                return;
            }

        }

    }

    @Override
    public RuNode getSelectedItem() {
        if((RuTreeItem)treeView.getLastSelectedPathComponent()==null)
            return null;
        return ((RuTreeItem)treeView.getLastSelectedPathComponent()).getNodeModel();
    }

    @Override
    public RuTreeItem getSelectedTreeItem() {
        return (RuTreeItem) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
    }

    @Override
    public RuTreeItem getRoot() {
        return (RuTreeItem) MainFrame.getInstance().getWorkspaceTree().getModel().getRoot();
    }

    @Override
    public RuNodeComposite getRootNodeModel() {
        return (RuNodeComposite) this.getRoot().getNodeModel();
    }

    @Override
    public void setRoot(Workspace root) {

        this.getRoot().removeAllChildren();
        ((RuNodeComposite)this.getRoot().getNodeModel()).deleteAllChildren();
        int index = 0;

        for(RuNode project :root.getChildren()){
            project.setParent(this.getRootNodeModel());

            RuTreeItem prjTreeItem = new RuTreeItem(project);
            this.getRoot().insert(prjTreeItem, index);
            ((RuNodeComposite)this.getRoot().getNodeModel()).addChild(project);

            MainFrame.getInstance().getDesktop().subscribe(project);
            importNode(prjTreeItem);

            index++;
        }

        SwingUtilities.updateComponentTreeUI(treeView);

    }

//    public void importProject(RuTreeItem workspace){
//
//    }


    @Override
    public void openProject(Project project) {

        if(this.getRootNodeModel().getChildren().contains(project)){
            AppCore.getInstance().getErrorHandler().throwException("Ovaj projekat vec postoji");
            return;
        }

        project.setParent(this.getRootNodeModel());

        RuTreeItem prjTreeItem = new RuTreeItem(project);
        this.getRoot().add(prjTreeItem);
        this.getRootNodeModel().addChild(project);

        MainFrame.getInstance().getDesktop().subscribe(project);

        importNode(prjTreeItem);

        SwingUtilities.updateComponentTreeUI(treeView);

    }

    private void importNode(RuTreeItem parent){

        RuNodeComposite parentNodeModel =(RuNodeComposite) parent.getNodeModel();
        int index = 0;

        for(RuNode node:parentNodeModel.getChildren()){

            MainFrame.getInstance().getDesktop().subscribe(node);
            RuTreeItem newItem = new RuTreeItem(node);

            if(!(node instanceof Slot)){
                parent.insert(newItem, index);
            }else{
                return;
            }

            importNode(newItem);
            index++;

        }

    }

    @Override
    public void setProjectName(String name) {
        this.getSelectedTreeItem().setName(name);
    }

    @Override
    public void setSelectedProject(RuNode project) {
        ((Workspace)this.getRootNodeModel()).setOpened((Project)project);
    }
}
