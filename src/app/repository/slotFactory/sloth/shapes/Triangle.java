package app.repository.slotFactory.sloth.shapes;

import app.repository.node.RuNode;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotType;

public class Triangle extends Slot {
    private SlotType slotType;

    public Triangle(String name, RuNode parent, SlotType slotType) {
        super(name, parent);
        this.slotType=slotType;
    }

    public Triangle(RuNode parent, SlotType slotType) {
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
