package app.gui.swing.dialogs;

import app.repository.slotFactory.sloth.Slot;



public interface RuJDialog {
    void addDialogs();
    void getDialog(Dialog dialog);
    void addSlotDialog(Slot slot);
    void removeSlotDialog(Slot slot);
}
