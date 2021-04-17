package app.repository.slotFactory;

import app.repository.node.RuNode;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotType;
import app.repository.slotFactory.sloth.shapes.Triangle;

public class TFactory extends  SlothFactory {
    @Override
    protected Slot createSlot(SlotType slotType, RuNode parent) {
        Slot s=null;
        if(slotType==SlotType.TROUGAO)
            s= new Triangle(parent,slotType);

        return s;
    }
}
