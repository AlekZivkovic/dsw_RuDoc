package app.repository.slotFactory.sloth.shapes;

import app.repository.node.RuNode;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotType;

public class Circle extends Slot {
    private SlotType slotType;

    public Circle(String name, RuNode parent, SlotType slotType) {
        super(name, parent);
        this.slotType=slotType;
    }

    public Circle(RuNode parent, SlotType slotType) {
        super(parent);
        this.slotType =slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }


    public SlotType getSlotType() {
        return slotType;
    }
}
