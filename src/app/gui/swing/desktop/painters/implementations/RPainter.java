package app.gui.swing.desktop.painters.implementations;

import app.gui.swing.desktop.painters.PainterDevice;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.shapes.Rectangle;

import java.awt.geom.GeneralPath;

public class RPainter extends PainterDevice {


    public RPainter(Slot slot) {
        super(slot);
       Rectangle rectangle =(Rectangle) slot;

        offTextX=7;
        offTextY=14;
        shape=new GeneralPath();
        if(slot.getAngle()==0) {
            ((GeneralPath) shape).moveTo(rectangle.getPosI(), rectangle.getPosJ());

            ((GeneralPath) shape).lineTo(rectangle.getPosI() + rectangle.getDimW(), rectangle.getPosJ());

            ((GeneralPath) shape).lineTo(rectangle.getPosI() + rectangle.getDimW(), rectangle.getPosJ() + rectangle.getDimH());

            ((GeneralPath) shape).lineTo(rectangle.getPosI(), rectangle.getPosJ() + rectangle.getDimH());

            ((GeneralPath) shape).closePath();
        }else{
            pozovme(slot);
        }

    }


    private  void  pozovme(Slot slot){
        Rectangle rectangle =(Rectangle) slot;
        int gdX=rectangle.getPosI()+rectangle.getDimW();
        int gdY= rectangle.getPosJ();

        int ddX=rectangle.getPosI() + rectangle.getDimW();
        int ddY=rectangle.getPosJ() + rectangle.getDimH();

        int dlX=rectangle.getPosI();
        int dlY=rectangle.getPosJ() + rectangle.getDimH();

        ((GeneralPath) shape).moveTo(rectangle.getPosI(), rectangle.getPosJ());
        ((GeneralPath) shape).lineTo(primX(gdX,gdY,slot),primY(gdX,gdY,slot));
        ((GeneralPath) shape).lineTo(primX(ddX,ddY,slot),primY(ddX,ddY,slot));
        ((GeneralPath) shape).lineTo(primX(dlX,dlY,slot),primY(dlX,dlY,slot));
        ((GeneralPath) shape).closePath();
    }



}
