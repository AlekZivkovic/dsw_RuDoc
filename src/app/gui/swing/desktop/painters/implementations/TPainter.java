package app.gui.swing.desktop.painters.implementations;

import app.gui.swing.desktop.painters.PainterDevice;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.shapes.Triangle;

import java.awt.geom.GeneralPath;

public class TPainter extends PainterDevice {

    public TPainter(Slot slot) {
        super(slot);
        Triangle tri=(Triangle)slot;
        offTextX=-14;
        offTextY=40;
        shape=new GeneralPath();
        if(slot.getAngle()==0) {

            ((GeneralPath) shape).moveTo(tri.getPosI(), tri.getPosJ() - 3);

            ((GeneralPath) shape).lineTo(tri.getPosI() - tri.getDimW(), tri.getPosJ() + tri.getDimH());

            ((GeneralPath) shape).lineTo(tri.getPosI() + tri.getDimW(), tri.getPosJ() + tri.getDimH());

            ((GeneralPath) shape).closePath();
        }else {
            pozovme(slot);
        }
    }
    private  void  pozovme(Slot slot){
        Triangle tri=(Triangle)slot;

        int ddX=tri.getPosI() + tri.getDimW();
        int ddY=tri.getPosJ() + tri.getDimH();

        int dlX=tri.getPosI()-tri.getDimW();
        int dlY=tri.getPosJ() + tri.getDimH();

        ((GeneralPath) shape).moveTo(tri.getPosI(), tri.getPosJ());
        ((GeneralPath) shape).lineTo(primX(ddX,ddY,slot),primY(ddX,ddY,slot));
        ((GeneralPath) shape).lineTo(primX(dlX,dlY,slot),primY(dlX,dlY,slot));
        ((GeneralPath) shape).closePath();

    }
}
