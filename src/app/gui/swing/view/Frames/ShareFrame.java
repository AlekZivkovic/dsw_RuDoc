package app.gui.swing.view.Frames;

import app.AppCore;
import app.core.PublisherString;
import app.gui.swing.view.MainFrame;
import app.repository.Document;
import app.repository.Project;
import app.repository.Workspace;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShareFrame extends JDialog implements FrameInterface{

    private JButton btn;
    private JLabel label = new JLabel();
    private JTextField textField = new JTextField();

    public ShareFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 3, screenHeight / 3);
        setLocationRelativeTo(null);
        setModal(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setResizable(false);

        this.setTitle("Share Document");
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

        label.setText("Upisite ime projekta");


        btn = new JButton("Share");
        //btn.setText("Close");
        btn.setPreferredSize(new Dimension(100, 40));
        //btn.setMaximumSize(new Dimension(70, 60));
        btn.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String projectName = textField.getText();
                    RuNodeComposite ws = (RuNodeComposite) MainFrame.getInstance().getTree().getRoot().getNodeModel();
                    RuNodeComposite p = new Project(ws);
                    p.setName(projectName);

                    if (ws.getChildren().contains(p)) {
                        MainFrame.getInstance().getTree().share(projectName);
                        textField.setText("");
                        hide();

                    } else {
                        AppCore.getInstance().getErrorHandler().throwException("Ovaj projekat ne postoji");
                    }


            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 40));
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(btn, BorderLayout.SOUTH);

        this.add(panel);

        this.validate();

    }

    @Override
    public void show(String text) {

    }

    public void showFrame(){
        btn.setText("Share");
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
