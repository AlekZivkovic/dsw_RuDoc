package app.gui.swing.view.Frames;

import app.AppCore;
import app.core.PublisherString;
import app.gui.swing.view.MainFrame;
import app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SaveAsFrame extends JFileChooser implements FrameInterface {

    int val;

    public SaveAsFrame(){

        this.setCurrentDirectory(new File(System.getProperty("user.dir")));

        this.addChoosableFileFilter(new FileNameExtensionFilter("RuNodes", "ser"));
        this.setAcceptAllFileFilterUsed(false);

    }

    @Override
    public void show(String text) {

    }

    @Override
    public void showFrame() {

        val = this.showSaveDialog(this);
        if(val == JFileChooser.APPROVE_OPTION){
            File choose = this.getSelectedFile();
            String newName = choose.getAbsolutePath();
            String projectName = choose.getName();

            if(newName.contains(".")){
                newName = choose.getAbsolutePath().substring(0, choose.getAbsolutePath().indexOf("."));
                projectName = projectName.substring(0, projectName.indexOf("."));
            }

            if(projectName.equals("")){
                AppCore.getInstance().getErrorHandler().throwException("Niste uneli ime projekta");
                return;
            }

            for(RuNode p:MainFrame.getInstance().getTree().getRootNodeModel().getChildren()){
                if(p.getName().equals(projectName)){
                    AppCore.getInstance().getErrorHandler().throwException("Ovaj projekat vec postoji");
                    return;
                }
            }

            File file = new File(newName + ".ser");
            AppCore.getInstance().getRuSerilization().saveAsProject(file);

        }

    }

    @Override
    public void subscribe(PublisherString publisher) {

    }

    @Override
    public void unsubscribe(PublisherString publisher) {

    }

    @Override
    public void update(String string) {

    }
}
