package app.command;

import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand implements CommandInterface {

    private List<Slot> slots = new ArrayList<Slot>();
    private Page page;

    public DeleteCommand(List<Slot> slots, Page page){
        this.slots = slots;
        this.page = page;
    }


    @Override
    public void doCommand() {
        for(Slot s:slots){
            page.deleteChild(s);
        }
    }

    @Override
    public void undoCommand() {
        for(Slot s:slots){
            page.addChild(s);
        }
    }

    @Override
    public Page getPage() {
        return this.page;
    }
}
