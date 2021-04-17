package app.command;


import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.util.ArrayList;
import java.util.List;

public class UndoRotateCommand implements CommandInterface {

    private List<Slot> slots = new ArrayList<>();
    private Page page;
    private ArrayList<Integer> previous = new ArrayList<>();

    public UndoRotateCommand(List<Slot> slots, Page page, ArrayList<Integer> previous) {
        this.slots = slots;
        this.page = page;
        this.previous = previous;
    }

    @Override
    public void doCommand() {
        for(Slot slot:slots)
            slot.setAngle(0);
    }

    @Override
    public void undoCommand() {
        for(int i = 0; i<slots.size(); i++){
            slots.get(i).setAngle(previous.get(i));
        }
    }

    @Override
    public Page getPage() {
        return this.page;
    }
}
