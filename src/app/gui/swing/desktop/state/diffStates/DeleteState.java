package app.gui.swing.desktop.state.diffStates;

import app.command.DeleteCommand;
import app.gui.swing.desktop.state.StateExt;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.gui.swing.view.MainFrame;
import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DeleteState extends StateExt {


    public DeleteState(RuDeskDoc ruDeskDoc) {
        this.ruDeskDoc = ruDeskDoc;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(e.getButton()==MouseEvent.BUTTON1){
            setPage();
            Point point=e.getPoint();
            Slot slot= ruDeskPage.getDeviceAtPosition(point);
            if(slot!=null &&  ((Page)ruDeskPage.getItem()).getSelected()!=null &&((Page)ruDeskPage.getItem()).getSelected().contains(slot)){
                // System.out.println("Nema nikog");

                ruDeskDoc.getCommandManager().addCommand(new DeleteCommand(((Page) ruDeskPage.getItem()).getSelected(),(Page) ruDeskPage.getItem()));
            }
        }

    }


}
