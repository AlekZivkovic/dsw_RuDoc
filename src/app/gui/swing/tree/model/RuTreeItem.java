package app.gui.swing.tree.model;



import app.repository.Page;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;


public class RuTreeItem extends DefaultMutableTreeNode implements Comparable<RuTreeItem>{


    private String name;
    private RuNode nodeModel;

    public RuTreeItem(RuNode nodeModel) {
        this.nodeModel = nodeModel;
        this.name = nodeModel.getName();
       // nodeModel.addSubscriber(MainFrame.getInstance().getDesktop());
    }

    public RuTreeItem(RuNode ruNode, String name) {
        this.name = name;
        this.nodeModel = ruNode;
        //nodeModel.addSubscriber(MainFrame.getInstance().getDesktop());
    }

    @Override
    public int getIndex(TreeNode node) {
        return findIndexByChild((RuTreeItem)node);
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return findChildByIndex(childIndex);
    }

    @Override
    public int getChildCount() {
        if(nodeModel instanceof RuNodeComposite)
            return ((RuNodeComposite) nodeModel).getChildren().size();
        return 0;
    }

    @Override
    public boolean getAllowsChildren() {
        if(nodeModel instanceof RuNodeComposite)
            return true;
        return false;
    }

    @Override
    public boolean isLeaf() {
        if(nodeModel instanceof  Page)return  true;
        if(nodeModel instanceof RuNodeComposite)
            return false;
        return true;
    }

    @Override
    public Enumeration children() {
        if(nodeModel instanceof RuNodeComposite)
            return (Enumeration) ((RuNodeComposite) nodeModel).getChildren();
        return null;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof RuTreeItem) {
            RuTreeItem otherObj = (RuTreeItem) obj;
            return this.nodeModel.equals(otherObj.nodeModel);
        }
        return false;
    }

    private TreeNode findChildByIndex(int childIndex){
        if(nodeModel instanceof RuNodeComposite){
            try {
                RuTreeItem toLookFor = new RuTreeItem(((RuNodeComposite) nodeModel).getChildren().get(childIndex));

                Iterator childrenIterator = children.iterator();
                TreeNode current;

                while (childrenIterator.hasNext()) {
                    current = (TreeNode) childrenIterator.next();
                    if (current.equals(toLookFor))
                        return current;
                }
            }
            catch(NullPointerException e){
                return null;
            }
        }

        return null;
    }

    private int findIndexByChild(RuTreeItem node){

        if(this.nodeModel instanceof RuNodeComposite){
            return  ((RuNodeComposite) this.nodeModel).getChildren().indexOf(node.getNodeModel());
        }
        return -1;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.nodeModel.setName(name);
    }

    public String getName() {
        return name;
    }

    public RuNode getNodeModel() {
        return nodeModel;
    }

    @Override
    public int compareTo(RuTreeItem o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public void insert(MutableTreeNode newChild, int childIndex) {

        newChild.setParent(this);
        if (children == null) {
            children = new Vector<>();
        }
        children.insertElementAt(newChild, childIndex);
    }
}
