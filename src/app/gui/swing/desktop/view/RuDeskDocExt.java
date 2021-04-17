package app.gui.swing.desktop.view;

import javax.swing.*;
import java.awt.*;

public class RuDeskDocExt extends JScrollPane {
    private  RuDeskDoc ruDeskDoc;

    public RuDeskDocExt(Component view) {
        super(view);
        ruDeskDoc=(RuDeskDoc) view;
        initScrol();

    }

    private void initScrol() {
        setMinimumSize(new Dimension(200,150));
        getVerticalScrollBar().setUnitIncrement(16);
    }


    public RuDeskDoc getRuDeskDoc() {
        return ruDeskDoc;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    public void popravi(){
        ruDeskDoc.pokuajPoprave();

        revalidate();
        repaint();
    }

}
