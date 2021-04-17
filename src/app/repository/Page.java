package app.repository;

import app.core.Subscriber;
import app.repository.slotFactory.sloth.Slot;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import java.util.ArrayList;
import java.util.List;

public class Page extends RuNodeComposite  {
    private List<Slot> selected;

    public Page(String name, RuNode parent) {
        super(name, parent);
    }

    public Page(RuNode parent){
        super(parent);
    }

//    @Override
//    protected String addName() {
//        int i = 1;
//        ArrayList<RuNode> nodes = (ArrayList<RuNode>) ((RuNodeComposite)this.getParent()).getChildren();
//        for(RuNode n:nodes){
//            String s = "Page " + i;
//            if(!n.getName().equals(s))
//                return s;
//            i++;
//        }
//        return "Page " + i;
//    }

    @Override
    protected String addName() {
        ArrayList<RuNode> nodes = (ArrayList<RuNode>) ((RuNodeComposite)this.getParent()).getChildren();
        for(int i = 1; i<=nodes.size(); i++){
            Document d = new Document("Page " + i, this.getParent());
            if(!nodes.contains(d))
                return "Page " + i;
        }
        return "Page " + (nodes.size()+1);
    }

    @Override
    public void addChild(RuNode child) {
        if((child!=null) && (child instanceof Slot)){
            Slot slt = (Slot) child;
            if(!this.getChildren().contains(slt)){
                this.getChildren().add(slt);
                notifySubscribers();
            }
        }
    }

    public void deleteChild(RuNode child){
        if(child!=null && child instanceof Slot){
            Slot slt = (Slot) child;
            if(this.getChildren().contains(slt)){
                this.getChildren().remove(slt);
                notifySubscribers();
            }
        }
    }


    public void setSelected(List<Slot>slots) {
        this.selected = slots;
        notifySubscribers();
    }

    public List<Slot> getSelected() {
        return selected;
    }
    public  void removeSelected(){
        for(Slot slot : selected){
            this.getChildren().remove(slot);
        }

        setSelected(null);
    }

//    @Override
//    public void addSubscriber(Subscriber subscriber) {
//        if(subscriber!=null)
//
//    }
}
