package app.gui.swing.view.Frames;

import app.AppCore;
import app.core.PublisherString;
import app.gui.swing.view.MainFrame;
import app.repository.Project;
import app.repository.node.RuNodeComposite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenRuDokFrame extends JDialog implements FrameInterface{

    private JLabel label = new JLabel();
    private JButton btn;
    private JButton btnNe;

    public OpenRuDokFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 3, screenHeight / 5);
        setLocationRelativeTo(null);
        setModal(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setResizable(false);

        this.setTitle("Open Workspace");
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

        label.setText("Da li zelite da otvorite prethodni workspace?");

        btn = new JButton("Da");
        btn.setText("Da");
        btn.setPreferredSize(new Dimension(40, 25));
        btn.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String currWs = AppCore.getInstance().getRuSerilization().getCurrentWorkspaceDir();

                if (currWs != null && !(currWs.replaceAll("\\s", "").equals(""))) {
                    File currWorkspace = new File(AppCore.getInstance().getRuSerilization().getCurrentWorkspaceDir());
                    if (currWorkspace.exists()) {
                        hide();
                        AppCore.getInstance().getRuSerilization().open(currWorkspace, "Workspace");
                    } else {
                        hide();
                        AppCore.getInstance().getErrorHandler().throwException("Ne postoji sacuvani workspace");
                    }


                }
                else{
                    hide();
                    AppCore.getInstance().getErrorHandler().throwException("Ne postoji sacuvani workspace");
                }
            }
        });

        btnNe = new JButton("Ne");
        btnNe.setText("Ne");
        btnNe.setPreferredSize(new Dimension(40, 25));
        btnNe.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            hide();

            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 40));
        panel.add(label, BorderLayout.NORTH);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 2, 10, 15));
        panel1.add(btn);
        panel1.add(btnNe);
        panel.add(panel1, BorderLayout.CENTER);

        this.add(panel);

        this.validate();

    }

    @Override
    public void show(String text) {

    }

    public void showFrame(){
        btn.setText("Da");
        btnNe.setText("Ne");
        this.show();
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
