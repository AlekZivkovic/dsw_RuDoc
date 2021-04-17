package app.gui.swing.dialogs;

import app.gui.swing.dialogs.implemented.SlotPicker;
import app.gui.swing.dialogs.implemented.SlotViewer;
import app.gui.swing.dialogs.implemented.AboutDialog;
import app.gui.swing.dialogs.implemented.SlotDialog;
import app.gui.swing.view.MainFrame;
import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotFileType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RuJDialogImplementation implements RuJDialog {
    private AboutDialog aboutFrame;
    private SlotDialog slotView;
    private List<Slot> slots;

    public  RuJDialogImplementation(){
        addDialogs();
        slots=new ArrayList<>();
    }

    @Override
    public void addDialogs() {
        this.aboutFrame=new AboutDialog();
    }

    @Override
    public void getDialog(Dialog dialog) {

        switch (dialog){
            case ABOUT: aboutFrame.getStarted();break;
            case SLOT_VIEWER: prov();break;

        }

    }

    private void prov() {
        for(Slot s : ((Page)MainFrame.getInstance().getDesktop().getSelectedPage()).getSelected()) {
            if(slots.contains(s))continue;

            if (s.getFileType() == SlotFileType.NULL) {(slotView = new SlotPicker(s)).getStarted();}

            if (s.getFileType() != SlotFileType.NULL) { (slotView = new SlotViewer(s)).getStarted();}

            this.addSlotDialog(s);
        }
    }

    @Override
    public void addSlotDialog(Slot slot) {
        slots.add(slot);
    }

    @Override
    public void removeSlotDialog(Slot slot) {
        if(slots.contains(slot)){
            slots.remove(slots.indexOf(slot));
        }
    }
}
