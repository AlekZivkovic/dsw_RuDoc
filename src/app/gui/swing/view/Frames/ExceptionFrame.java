package app.gui.swing.view.Frames;

import app.core.PublisherString;
import app.core.SubscriberString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ExceptionFrame extends JDialog implements FrameInterface {

    private JLabel label = new JLabel();
    private JButton btn;

    public ExceptionFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 4, screenHeight / 4);
        setLocationRelativeTo(null);
        setModal(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setResizable(false);

        this.setTitle("Error");
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));


        btn = new JButton("close");
        //btn.setText("Close");
        btn.setPreferredSize(new Dimension(100, 40));
        //btn.setMaximumSize(new Dimension(70, 60));
        btn.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();

            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 40));
        panel.add(label, BorderLayout.NORTH);
        panel.add(btn, BorderLayout.CENTER);

        this.add(panel);

        this.validate();

    }

    public void show(String text){
        label.setText(text);
        btn.setText("Close");
        this.show();
    }

    @Override
    public void showFrame() {

    }

    @Override
    public void subscribe(PublisherString publisher) {
        publisher.addSubscriber(this);
    }

    @Override
    public void unsubscribe(PublisherString publisher) {
        publisher.removeSubscriber(this);
    }

    @Override
    public void update(String string) {
        this.show(string);
    }
}
