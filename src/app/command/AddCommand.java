package app.command;

import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.util.ArrayList;

public class AddCommand implements CommandInterface{

    private Page page;
    private Slot slot;

    public AddCommand(Slot slot, Page page){

        this.slot = slot;
        this.page = page;

    }


    @Override
    public void doCommand() {
        page.addChild(slot);
    }

    @Override
    public void undoCommand() {
        page.deleteChild(slot);
    }

    @Override
    public Page getPage() {
        return this.page;
    }
}
