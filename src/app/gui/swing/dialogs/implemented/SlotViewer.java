package app.gui.swing.dialogs.implemented;

import app.gui.swing.desktop.slotView.SlotPicView;
import app.gui.swing.desktop.slotView.SlotTextView;
import app.repository.node.RuNode;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotFileType;
import java.awt.*;


public class SlotViewer extends SlotDialog {
    private  SlotTextView textView;
    private  SlotPicView picView;
    private Slot slot;


    public  SlotViewer(RuNode ruNode){
        super(ruNode);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 3, screenHeight / 2);
        setLocationRelativeTo(null);
        setModal(false);
        setResizable(false);
        setTitle("Slot Editor");
        slot=(Slot)ruNode;

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        if(((Slot)ruNode).getFileType()== SlotFileType.TEXT)
            add(textView=new SlotTextView((Slot)ruNode));
        else add(picView=new SlotPicView((Slot)ruNode));

    }


    public SlotTextView getTextView() {
        return textView;
    }
}
