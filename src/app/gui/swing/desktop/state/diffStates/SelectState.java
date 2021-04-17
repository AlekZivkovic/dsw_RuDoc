package app.gui.swing.desktop.state.diffStates;

import app.gui.swing.desktop.state.StateExt;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.gui.swing.view.MainFrame;
import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class SelectState extends StateExt {
    private  Point point;
    public SelectState(RuDeskDoc ruDeskDoc) {
        this.ruDeskDoc = ruDeskDoc;
    }

    @Override
    public void mousePressed(MouseEvent e) {
       point=e.getPoint();
        if(e.getButton()==MouseEvent.BUTTON1){
            setPage();
            Slot slot= ruDeskPage.getDeviceAtPosition(point);
            if(slot==null){
                // System.out.println("Nema nikog");
                ((Page) ruDeskPage.getItem()).setSelected(null);
            }else{
                // System.out.println("ima nekog");
                ((Page) ruDeskPage.getItem()).setSelected(Arrays.asList(slot));
            }

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ruDeskDoc.startLassoState();
        ruDeskDoc.getStateManager().getCurrState().mouseDragged(e);
    }

}
