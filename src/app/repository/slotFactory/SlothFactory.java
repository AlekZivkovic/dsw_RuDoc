package app.repository.slotFactory;

import app.repository.node.RuNode;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotType;

public abstract class SlothFactory {


    public Slot makeSlot(int posI, int posJ, int dimH, int dimW, SlotType slotType, RuNode parent){
     Slot slot= createSlot(slotType,parent);
     slot.setAngle(0);
     slot.setPosI(posI);
     slot.setPosJ(posJ);
     slot.setDimH(dimH);
     slot.setDimW(dimW);


     return  slot;
    }


    protected    abstract  Slot createSlot(SlotType slotType, RuNode parent);
}
