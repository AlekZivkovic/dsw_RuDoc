package app.repository;



import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import java.util.List;

public class Workspace extends RuNodeComposite {

    private Project opened = null;

    public Workspace(String name) {

        super(name, null);
    }

    @Override
    public void addChild(RuNode child) {
        if (child != null &&  child instanceof Project){
            Project project = (Project) child;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);
                notifySubscribers();
            }
        }
    }

    public void deleteChild(RuNode child){
        if(child!=null && child instanceof Project){
            Project prj = (Project) child;
            if(this.getChildren().contains(prj)){
                this.getChildren().remove(prj);
                prj.notifySubscribers();
                notifySubscribers();
            }
        }
    }

    @Override
    protected String addName() {
        return null;
    }

    public Project getOpened() {
        return opened;
    }

    public void setOpened(Project opened) {
        this.opened = opened;
    }
}
