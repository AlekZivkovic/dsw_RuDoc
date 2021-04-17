package app.command;

import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.util.ArrayList;
import java.util.List;

public class RotateCommand implements CommandInterface{

    private List<Slot> slots = new ArrayList<>();
    private Page page;
    private ArrayList<Integer> previous = new ArrayList<>();
    private ArrayList<Integer> current = new ArrayList<>();

    public RotateCommand(List<Slot> slots, Page page, ArrayList<Integer> previous, ArrayList<Integer> current) {
        this.slots = slots;
        this.page = page;
        this.previous = previous;
        this.current = current;
    }

    @Override
    public void doCommand() {
        for(int i=0; i<slots.size(); i++){
            slots.get(i).setAngle(current.get(i));
        }
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
