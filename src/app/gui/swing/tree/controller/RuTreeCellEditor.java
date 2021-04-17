package app.gui.swing.tree.controller;


import app.AppCore;
import app.gui.swing.tree.model.RuTreeItem;
import app.gui.swing.view.MainFrame;
import app.repository.*;
import app.repository.slotFactory.sloth.Slot;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;

public class RuTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {


    private Object clickedOn =null;
    private JTextField edit=null;


    public RuTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }



    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
           }
        return false;
    }



    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof RuTreeItem))
            return;

        RuTreeItem clicked = (RuTreeItem) clickedOn;

        if(e.getActionCommand().isEmpty()){
            AppCore.getInstance().getErrorHandler().throwException("Morate uneti neko ime");
            stopCellEditing();
            return;
        }

        if(MainFrame.getInstance().getTree().getSelectedItem() instanceof Document &&
                ((Document) MainFrame.getInstance().getTree().getSelectedItem()).isShared()){

            RuNodeComposite wsRuNode = (RuNodeComposite) MainFrame.getInstance().getTree().getRoot().getNodeModel();

            for(RuNode ruNode:wsRuNode.getChildren()){
                RuNodeComposite prj = (RuNodeComposite) ruNode;
                for(RuNode document:prj.getChildren()){
                    if(document.getName().equals(e.getActionCommand())) {
                        AppCore.getInstance().getErrorHandler().throwException("Ime dokumenta vec postoji" +
                                " u nekom od projekata");
                        stopCellEditing();
                        return;
                    }
                }
            }

        }

        if (clicked.getNodeModel() instanceof Workspace){
            clicked.setName(e.getActionCommand());
            ((Workspace) clicked.getNodeModel()).setName(e.getActionCommand());
        }

        else{

            Object[] path = MainFrame.getInstance().getWorkspaceTree().getSelectionPath().getPath();
            RuTreeItem parent = (RuTreeItem) path[path.length-2];
            ArrayList<RuNode> children = (ArrayList<RuNode>) ((RuNodeComposite)parent.getNodeModel()).getChildren();


            RuNode r = null;

            if(clicked.getNodeModel() instanceof Project){
                r = new Project(e.getActionCommand(), parent.getNodeModel());
            }
            if(clicked.getNodeModel() instanceof Document){
                r = new Document(e.getActionCommand(), parent.getNodeModel());
            }
            if(clicked.getNodeModel() instanceof Page){
                r = new Page(e.getActionCommand(), parent.getNodeModel());
            }
            if(clicked.getNodeModel() instanceof Slot){
              //  r = new Slot(e.getActionCommand(), parent.getNodeModel());
            }


            if(children.contains(r)){
                AppCore.getInstance().getErrorHandler().throwException("Ovo ime vec postoji u ovom projektu");
                stopCellEditing();
                return;
            }

            clicked.setName(e.getActionCommand());
            ((RuNode) clicked.getNodeModel()).setName(e.getActionCommand());
            stopCellEditing();


        }

    }



}
