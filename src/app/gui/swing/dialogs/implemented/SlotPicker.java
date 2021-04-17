package app.gui.swing.dialogs.implemented;

import app.repository.node.RuNode;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotFileType;

import javax.swing.*;
import java.awt.*;

public class SlotPicker extends SlotDialog {


    public  SlotPicker(RuNode ruNode){
        super(ruNode);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(100,200);
        setLocationRelativeTo(null);
        setModal(true);

        this.ruNode=ruNode;
        setTitle("Slot Type");
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        init();
    }

    private void init() {
        JButton mBtn= new JButton("Multimedia");
        JButton tBtn= new JButton("Textualan");

        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("Odaberite tip slota"));
        panel.add(Box.createVerticalStrut(20));
        panel.add(mBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(tBtn);


        this.add(panel);

        mBtn.addActionListener(e->{
                ((Slot)ruNode).setFileType(SlotFileType.MultiMedia);
                dispose();
        });
        tBtn.addActionListener(e ->  {
                ((Slot)ruNode).setFileType(SlotFileType.TEXT);
                dispose();
        });



    }
}
