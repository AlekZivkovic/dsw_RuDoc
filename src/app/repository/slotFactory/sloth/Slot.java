package app.repository.slotFactory.sloth;

import app.repository.Document;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import java.io.File;
import java.util.ArrayList;

public abstract class Slot extends RuNode  {
    private File container;
    private  SlotFileType fileType;
    private  int posI;
    private  int posJ;
    private  int dimH;
    private  int dimW;
    private  int angle;


    //pozvano bilo u rutreeCelllEditoru 88
    public Slot(String name, RuNode parent) {
        super(name, parent);
        this.fileType=SlotFileType.NULL;
    }
    //pozvano bilo u rutreeImplementaciji
    public Slot(RuNode parent){
        super(parent);
        this.fileType=SlotFileType.NULL;
    }



//    @Override
//    protected String addName() {
//        int i = 1;
//        ArrayList<RuNode> nodes = (ArrayList<RuNode>) ((RuNodeComposite)this.getParent()).getChildren();
//        for(RuNode n:nodes){
//            String s = "Slot " + i;
//            if(!n.getName().equals(s))
//                return s;
//            i++;
//        }
//        return "Slot " + i;
//    }

    @Override
    protected String addName() {
        ArrayList<RuNode> nodes = (ArrayList<RuNode>) ((RuNodeComposite)this.getParent()).getChildren();
        for(int i = 1; i<=nodes.size(); i++){
            Document d = new Document("Slot " + i, this.getParent());
            if(!nodes.contains(d))
                return "Slot " + i;
        }
        return "Slot " + (nodes.size()+1);
    }

    public int getPosI() {
        return posI;
    }

    public int getPosJ() {
        return posJ;
    }

    public int getDimH() {
        return dimH;
    }

    public File getContainer() {
        return container;
    }

    public void setContainer(File container) {
        this.container = container;
    }

    public int getDimW() {
        return dimW;
    }

    public int getAngle() {
        return angle;
    }

    public void setPosI(int posI) {
        this.posI = posI;
        notifySubscribers();
    }

    public void setPosJ(int posJ) {
        this.posJ = posJ;
        notifySubscribers();
    }

    public void setDimH(int dimH) {
        this.dimH = dimH;
        notifySubscribers();
    }

    public void setDimW(int dimW) {
        this.dimW = dimW;
        notifySubscribers();
    }

    public SlotFileType getFileType() {
        return fileType;
    }

    public void setFileType(SlotFileType fileType) {
        this.fileType = fileType;
    }

    public void setAngle(int angle) {
        this.angle = angle;
        notifySubscribers();
    }

}
