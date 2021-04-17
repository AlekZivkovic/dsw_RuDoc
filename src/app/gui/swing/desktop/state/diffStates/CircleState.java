package app.gui.swing.desktop.state.diffStates;

import app.command.AddCommand;
import app.gui.swing.desktop.state.StateExt;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.repository.Page;
import app.repository.slotFactory.CFactory;
import app.repository.slotFactory.SlothFactory;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotType;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CircleState extends StateExt {
    private int dimW=80;
    private int dimH=80;

    public CircleState(RuDeskDoc ruDeskDoc) {
        this.ruDeskDoc= ruDeskDoc;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        if (e.getButton() == MouseEvent.BUTTON1) {

            setPage();
            if (prov(point)) {
                SlothFactory sFactor = new CFactory();
                Slot slot = sFactor.makeSlot((int) point.getX(), (int) point.getY(), dimH, dimW, SlotType.KRUG, ruDeskPage.getItem());
                ruDeskPage.subscribe(slot);

                ruDeskDoc.getCommandManager().addCommand(new AddCommand(slot,(Page) ruDeskPage.getItem()));
            }
        }


    }

}
