package app.repository.node;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public abstract class RuNodeComposite extends RuNode implements Serializable {
    private List<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public RuNodeComposite(String name, RuNode parent, List<RuNode> children) {
        super(name, parent);
        this.children = children;
    }

    public RuNodeComposite(RuNode parent){
        super(parent);
        this.children = new ArrayList<>();
    }

    public abstract void addChild(RuNode child);
    public abstract void deleteChild(RuNode child);

    public void deleteAllChildren(){
        children.removeAll(children);
    }

    public RuNode getChildByName(String name) {
        for (RuNode child: this.getChildren()) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }

    @Override
    public int compareTo(RuNode node) {
        return this.getName().compareTo(node.getName());
    }

    public List<RuNode> getChildren() {
        return children;
    }

    public void setChildren(List<RuNode> children) {
        this.children = children;
    }
}
