package app.gui.swing.desktop.state.diffStates;

import app.AppCore;
import app.command.CommandInterface;
import app.command.MoveCommand;
import app.command.Pair;
import app.gui.swing.desktop.state.StateExt;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MoveState extends StateExt {

    private ArrayList<Pair> previous = new ArrayList<>();

    public MoveState(RuDeskDoc ruDeskDoc) {
        this.ruDeskDoc = ruDeskDoc;
    }

    /*
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            setPage();
            Point p = e.getPoint();
            Slot s = ruDeskPage.getDeviceAtPosition(p);
            if(s!=null && s.equals(((Page)ruDeskPage.getItem()).getSelected())) {
                AppCore.getInstance().getSlotHandler().changeStartingPositions((int) p.getX(), (int) p.getY());
            }
        }
    }
*/

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            setPage();
            Point p = e.getPoint();
            if(((Page)ruDeskPage.getItem()).getSelected()!=null) {
                subscribeShared();
                subscribeDeserialized();

                ArrayList<Pair> pairs = new ArrayList<Pair>();
                for(Slot s:((Page) ruDeskPage.getItem()).getSelected()){
                    Pair pair = new Pair(s.getPosI(), s.getPosJ());
                    pairs.add(pair);
                }

                previous = pairs;

                AppCore.getInstance().getSlotHandler().changeStartingPositions((int) p.getX(), (int) p.getY());
            }
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        setPage();
        Point p = e.getPoint();
        if(((Page)ruDeskPage.getItem()).getSelected()!=null){
            AppCore.getInstance().getSlotHandler().moveSlot(((Page)ruDeskPage.getItem()).getSelected(), (int)p.getX(), (int) p.getY());
        }

    }

    /*
    @Override
    public void mouseDragged(MouseEvent e) {
            setPage();
            Point p = e.getPoint();
            for(Component comp :ruDeskDoc.getComponents()){
                RuDeskPage ruPage=(RuDeskPage)comp;
                if(ruPage.isSelected() && ruPage.equals(ruDeskPage)){
                    Slot s = ruDeskPage.getDeviceAtPosition(p);
                    if(s!=null && s.equals(((Page)ruDeskPage.getItem()).getSelected())){
                        AppCore.getInstance().getSlotHandler().moveSlot(s, (int)p.getX(), (int) p.getY());
                    }
                }
            }

    }

     */

    @Override
    public void mouseReleased(MouseEvent e) {
        setPage();

        ArrayList<Pair> pairs = new ArrayList<Pair>();
        for(Slot s:((Page) ruDeskPage.getItem()).getSelected()){
            Pair pair = new Pair(s.getPosI(), s.getPosJ());
            pairs.add(pair);
        }

        ruDeskDoc.getCommandManager().addCommand(new MoveCommand(((Page) ruDeskPage.getItem()).getSelected(),(Page) ruDeskPage.getItem(), previous, pairs));
    }
}
