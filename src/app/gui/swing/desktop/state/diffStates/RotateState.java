package app.gui.swing.desktop.state.diffStates;

import app.AppCore;
import app.command.RotateCommand;
import app.gui.swing.desktop.state.StateExt;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.gui.swing.desktop.view.RuDeskPage;
import app.repository.Document;
import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;
import javafx.scene.transform.Rotate;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RotateState extends StateExt {

    private ArrayList<Integer> previous = new ArrayList<>();

    public RotateState(RuDeskDoc ruDeskDoc) {
        this.ruDeskDoc = ruDeskDoc;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            setPage();
            Point p = e.getPoint();
            Slot s = ruDeskPage.getDeviceAtPosition(p);
            if(e.getClickCount()==2){
                if(s!=null) {
                    s.setAngle(0);
                }
                return;
            }
            if(s!=null && ((Page)ruDeskPage.getItem()).getSelected().contains(s)) {
                subscribeShared();
                subscribeDeserialized();
                AppCore.getInstance().getSlotHandler().changeStartingPositions((int) p.getX(), (int) p.getY());

                for(Slot slot:((Page) ruDeskPage.getItem()).getSelected()){
                    previous.add(slot.getAngle());
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setPage();
        Point p = e.getPoint();
        if(((Page)ruDeskPage.getItem()).getSelected()==null)return;
        AppCore.getInstance().getSlotHandler().rotateSlot(((Page)ruDeskPage.getItem()).getSelected(), (int) p.getX(), (int) p.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setPage();

        ArrayList<Integer> current = new ArrayList<>();
        for(Slot s:((Page)ruDeskPage.getItem()).getSelected()){
            current.add(s.getAngle());
        }

        ruDeskDoc.getCommandManager().addCommand(new RotateCommand(((Page) ruDeskPage.getItem()).getSelected(),(Page) ruDeskPage.getItem(), previous, current));
    }
}
