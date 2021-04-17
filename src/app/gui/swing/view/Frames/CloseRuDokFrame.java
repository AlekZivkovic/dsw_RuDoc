package app.gui.swing.view.Frames;

import app.AppCore;
import app.core.PublisherString;
import app.gui.swing.view.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class CloseRuDokFrame extends JDialog implements FrameInterface {

    private JLabel label = new JLabel();
    private JButton btnDa;
    private JButton btnNe;
    private JButton btnOdustani;

    public CloseRuDokFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 3, screenHeight / 5);
        setLocationRelativeTo(null);
        setModal(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setResizable(false);

        this.setTitle("Save Workspace");
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));

        label.setText("Da li zelite da sacuvate trenutni workspace?");

        btnDa = new JButton("Da");
        btnDa.setPreferredSize(new Dimension(60, 25));
        btnDa.setMinimumSize(new Dimension(60, 25));
        btnDa.setMaximumSize(new Dimension(60, 25));
        btnDa.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AppCore.getInstance().getRuSerilization().saveWorkspace();
                hide();
                MainFrame.getInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });

        btnNe = new JButton("Ne");
        btnNe.setText("Ne");
        btnNe.setPreferredSize(new Dimension(60, 25));
        btnNe.setMinimumSize(new Dimension(60, 25));
        btnNe.setMaximumSize(new Dimension(60, 25));
        btnNe.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hide();
                MainFrame.getInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });

        btnOdustani = new JButton("Odustani");
        btnOdustani.setPreferredSize(new Dimension(100, 25));
        btnOdustani.setMinimumSize(new Dimension(100, 25));
        btnOdustani.setMaximumSize(new Dimension(100, 25));
        btnOdustani.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hide();

            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 40));
        panel.add(label, BorderLayout.NORTH);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel1.add(btnDa);
        panel1.add(btnNe);
        panel1.add(btnOdustani);
        panel.add(panel1, BorderLayout.CENTER);

        this.add(panel);

        this.validate();

    }

    @Override
    public void show(String text) {

    }

    public void showFrame(){
        btnDa.setText("Da");
        btnNe.setText("Ne");
        btnOdustani.setText("Odustani");
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
