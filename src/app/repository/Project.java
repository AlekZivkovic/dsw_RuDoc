package app.repository;


import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import java.util.ArrayList;

public class Project extends RuNodeComposite {

    private String path;

    public Project(String name, RuNode parent) {

        super(name, parent);
    }

    public Project(RuNode parent){
        super(parent);
    }

//    @Override
//    protected String addName() {
//        int i = 1;
//        ArrayList<RuNode> nodes = (ArrayList<RuNode>) ((RuNodeComposite)this.getParent()).getChildren();
//        for(RuNode n:nodes){
//            String s = "Project " + i;
//            if(!n.getName().equals(s))
//                return s;
//            i++;
//        }
//        return "Project " + i;
//    }

    @Override
    protected String addName() {
        ArrayList<RuNode> nodes = (ArrayList<RuNode>) ((RuNodeComposite)this.getParent()).getChildren();
        for(int i = 1; i<=nodes.size(); i++){
            Document d = new Document("Project " + i, this.getParent());
            if(!nodes.contains(d))
                return "Project " + i;
        }
        return "Project " + (nodes.size()+1);
    }

    @Override
    public void addChild(RuNode child){
        if(child!=null && child instanceof Document){
            Document doc = (Document) child;
            if(!this.getChildren().contains(doc)){
                this.getChildren().add(doc);
                notifySubscribers();
            }
        }
    }

    public void deleteChild(RuNode child){
        if(child!=null && child instanceof Document){
            Document doc = (Document) child;
            if(this.getChildren().contains(doc)){
                this.getChildren().remove(doc);
                notifySubscribers();
            }
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
