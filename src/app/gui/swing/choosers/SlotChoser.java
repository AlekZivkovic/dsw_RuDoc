package app.gui.swing.choosers;

import app.AppCore;
import app.gui.swing.dialogs.Dialog;
import app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SlotChoser  implements  Choosers{
    JFileChooser chooser;

    public SlotChoser() {
        chooser=new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Open image","jpg","png","gif"));
    }

    @Override
    public File doStuff(){
        int rVal=chooser.showOpenDialog(MainFrame.getInstance().getFocusOwner());
        if(rVal==JFileChooser.APPROVE_OPTION){
            File f=chooser.getSelectedFile();
            chooser.setVisible(false);
            return  f;
        }

        return  null;
    }


}
