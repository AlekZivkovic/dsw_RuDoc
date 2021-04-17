package app.gui.swing.dialogs.implemented;

import app.gui.swing.view.MainFrame;
import app.repository.node.RuNode;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotFileType;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public  abstract  class SlotDialog extends JDialog {
    protected RuNode ruNode;

    public SlotDialog(RuNode ruNode){
        this.ruNode=ruNode;
        addWindowListener(new RuWinLisn(this));
    }



    public  void getStarted(){

        setVisible(true);
    }

    class RuWinLisn extends WindowAdapter {
        private SlotDialog sl;
        public RuWinLisn(SlotDialog slotDialog) {
            sl=slotDialog;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            if(((Slot)ruNode).getFileType()== SlotFileType.TEXT)uradiZaSlotView((SlotViewer)sl);

            else{
                sl.setVisible(false);
                MainFrame.getInstance().getDialogs().removeSlotDialog((Slot)ruNode);
            }
        }
    }

    private void uradiZaSlotView(SlotViewer sl) {

        if (sl.getTextView() != null && sl.getTextView().isChanged()) {
            int a = JOptionPane.showConfirmDialog(MainFrame.getInstance().getFocusOwner(), "Trenutni sadrzaj nije sacuvan\n hocete da sacuvate?");

            if (a == JOptionPane.YES_OPTION) {
               // System.out.println("wow");
                sl.getTextView().saveChanges();

                sl.setVisible(false);
            }
            if (a == JOptionPane.NO_OPTION) {
                sl.dispose();
            }
        }else{
            sl.setVisible(false);
        }
        MainFrame.getInstance().getDialogs().removeSlotDialog((Slot)ruNode);
    }

}
