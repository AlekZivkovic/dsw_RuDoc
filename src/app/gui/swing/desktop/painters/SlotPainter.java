package app.gui.swing.desktop.painters;

import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.awt.*;
import java.util.Objects;

public abstract class SlotPainter{
    private Slot slot;

    public SlotPainter(Slot slot) {
        this.slot = slot;

    }

    public  abstract  boolean isElementAt(Point point);
    public  abstract  void  paint(Graphics2D g);

    public Slot getSlot() {
        return slot;
    }

    public  boolean isSlotSlected(){
        if(slot==null)return false;
        Page p=((Page)slot.getParent());
        if(p.getSelected()==null)return  false;
        if(p.getSelected().contains(slot))
            return true;

        return  false;
    }
}
