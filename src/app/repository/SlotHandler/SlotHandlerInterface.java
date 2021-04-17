package app.repository.SlotHandler;

import app.repository.slotFactory.sloth.Slot;

import java.util.List;

public interface SlotHandlerInterface {

    public void moveSlot(List<Slot> slot, int x, int y);
    public void resizeSlot(List<Slot> slot, int x, int y);
    public void rotateSlot(List<Slot> slot, int x, int y);
    public void changeStartingPositions(int x, int y);

}
