package app.gui.swing.view.Frames;

import app.AppCore;
import app.core.PublisherString;
import app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class OpenFrame extends JFileChooser implements FrameInterface{

    int val;

    public OpenFrame(){

        this.setCurrentDirectory(new File(System.getProperty("user.dir")));

        this.addChoosableFileFilter(new FileNameExtensionFilter("RuNodes", "ser"));
        this.setAcceptAllFileFilterUsed(false);

    }

    @Override
    public void show(String text) {
        val = this.showOpenDialog(this);
        if(val == JFileChooser.APPROVE_OPTION){
            File f = new File(this.getSelectedFile().toString());
            AppCore.getInstance().getRuSerilization().open(f, text);
        }

    }

    public void showFrame(){

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
