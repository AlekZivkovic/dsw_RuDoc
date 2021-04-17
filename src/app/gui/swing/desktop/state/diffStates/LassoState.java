package app.gui.swing.desktop.state.diffStates;

import app.gui.swing.desktop.painters.PainterDevice;
import app.gui.swing.desktop.state.StateExt;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.gui.swing.view.MainFrame;
import app.repository.Page;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;
import app.repository.slotFactory.RFactory;
import app.repository.slotFactory.SlothFactory;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.SlotType;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class LassoState extends StateExt {
    private Point frist;
    private boolean isFrist;


    public LassoState(RuDeskDoc ruDeskDoc){this.ruDeskDoc=ruDeskDoc;}

    //samo kad realse da vrati state na select


    @Override
    public void mousePressed(MouseEvent e) {
        frist=e.getPoint();
        isFrist=true;
        setPage();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!isFrist){
            mousePressed(e);
        }
        ruDeskPage.setLassoRec((int)frist.getX(),(int)frist.getY(),e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        List<Slot> slots=new ArrayList<Slot>();
        for (RuNode child: ((RuNodeComposite)ruDeskPage.getItem()).getChildren()){
            Slot slot=(Slot)child;
            PainterDevice pr=ruDeskPage.pronadjiOdgPainter(slot);
            if(pr.getShape().intersects(ruDeskPage.getLassoRec())){
                slots.add(slot);

            }
        }

        ((Page)ruDeskPage.getItem()).setSelected(slots);



        isFrist=false;
        ruDeskPage.setLassoRec(null);
        ruDeskDoc.startSelectState();

    }
}
