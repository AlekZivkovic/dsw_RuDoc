package app.command;

import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ResizeCommand implements CommandInterface{

    private List<Slot> slots = new ArrayList<>();
    private Page page;
    private ArrayList<Pair> previous = new ArrayList<>();
    private ArrayList<Pair> current = new ArrayList<>();

    public ResizeCommand(List<Slot> slots, Page page, ArrayList<Pair> previous, ArrayList<Pair> current) {
        this.slots = slots;
        this.page = page;
        this.previous = previous;
        this.current = current;
    }

    @Override
    public void doCommand() {
        for(int i = 0; i<slots.size(); i++){
            slots.get(i).setDimW(current.get(i).getFirst());
            slots.get(i).setDimH(current.get(i).getSecond());
        }
    }

    @Override
    public void undoCommand() {
        for(int i = 0; i<slots.size(); i++){
            slots.get(i).setDimW(previous.get(i).getFirst());
            slots.get(i).setDimH(previous.get(i).getSecond());
        }
    }

    @Override
    public Page getPage() {
        return this.page;
    }
}
