package app.gui.swing.view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class ProjectBar extends JToolBar {
    private  JLabel prjekat;

    public ProjectBar() {
        super(HORIZONTAL);
        setFloatable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        prjekat=new JLabel();
        prjekat.setText(" ");
        add(prjekat);

    }

    public JLabel getPrjekat() {
        return prjekat;
    }

    public void setPrjekat(String prjekat) {
        this.prjekat.setText(prjekat);
    }
}
