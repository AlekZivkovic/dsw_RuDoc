package app.gui.swing;


import app.core.Gui;
import app.core.Repository;
import app.gui.swing.choosers.ChooserType;
import app.gui.swing.choosers.Choosers;
import app.gui.swing.choosers.SlotChoser;
import app.gui.swing.view.MainFrame;

import java.io.File;

public class SwingGui implements Gui {

    private MainFrame instance;
    private Repository documentRepository;
    private Choosers chooser;

    public  SwingGui(Repository documentRepository) {

        this.documentRepository = documentRepository;
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setDocumentRepository(documentRepository);
        instance.initialiseDesktop();
        instance.initialiseWorkspaceTree();

        instance.setVisible(true);
    }

    @Override
    public File getChooser(ChooserType chooserType) {
        if(chooserType==ChooserType.SLOT){
            chooser=new SlotChoser();
           return chooser.doStuff();
        }

        return null;
    }


}
