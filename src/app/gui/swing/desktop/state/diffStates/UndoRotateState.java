package app.gui.swing.desktop.state.diffStates;

import app.AppCore;
import app.command.UndoRotateCommand;
import app.gui.swing.desktop.state.StateExt;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UndoRotateState extends StateExt {

    public UndoRotateState(RuDeskDoc ruDeskDoc) {
        this.ruDeskDoc = ruDeskDoc;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            setPage();
            if(((Page)ruDeskPage.getItem()).getSelected()!=null) {
                subscribeShared();

                ArrayList<Integer> previous = new ArrayList<>();
                for(Slot slot:((Page) ruDeskPage.getItem()).getSelected())
                    previous.add(slot.getAngle());

                ruDeskDoc.getCommandManager().addCommand(new UndoRotateCommand(((Page) ruDeskPage.getItem()).getSelected(),(Page) ruDeskPage.getItem(), previous));
            }
        }
    }
}
