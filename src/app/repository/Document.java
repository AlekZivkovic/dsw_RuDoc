package app.repository;

import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import java.util.ArrayList;

public class Document extends RuNodeComposite {

    private boolean isShared = false;

    public Document(String name, RuNode parent) {
        super(name, parent);
    }

    public Document(RuNode parent) {
        super(parent);
    }


// Ovo moze da funkcionise da je lista cvorova sortirana
//    @Override
//    protected String addName() {
//        int i = 1;
//        ArrayList<RuNode> nodes = (ArrayList<RuNode>) ((RuNodeComposite)this.getParent()).getChildren();
//        for(RuNode n:nodes){
//            String s = "Document " + i;
//            if(!n.getName().equals(s))
//                return s;
//            i++;
//        }
//        return "Document " + i;
//    }


    @Override
    protected String addName() {
        ArrayList<RuNode> nodes = (ArrayList<RuNode>) ((RuNodeComposite)this.getParent()).getChildren();
        for(int i = 1; i<=nodes.size(); i++){
            Document d = new Document("Document " + i, this.getParent());
            if(!nodes.contains(d))
                return "Document " + i;
        }
        return "Document " + (nodes.size()+1);
    }

    @Override
    public void addChild(RuNode child) {
        if(child!=null && child instanceof Page){
            Page pg = (Page) child;
            if(!this.getChildren().contains(pg)){
                this.getChildren().add(pg);
                notifySubscribers();
            }
        }
    }

    @Override
    public void deleteChild(RuNode child){
        if(child!=null && child instanceof Page){
            Page pg = (Page) child;
            if(this.getChildren().contains(pg)){
                 this.getChildren().remove(pg);
                 notifySubscribers();
            }
        }
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }
}
