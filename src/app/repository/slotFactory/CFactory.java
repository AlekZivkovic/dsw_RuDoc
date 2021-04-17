package app.repository.slotFactory;

import app.repository.node.RuNode;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotType;
import app.repository.slotFactory.sloth.shapes.Circle;

public class CFactory extends  SlothFactory {
    @Override
    protected Slot createSlot(SlotType slotType, RuNode parent) {
        Slot s=null;
        if(slotType== SlotType.KRUG)
            s=new Circle(parent, slotType);

            return s;
    }
}
