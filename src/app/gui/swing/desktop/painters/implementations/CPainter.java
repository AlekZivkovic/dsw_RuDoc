package app.gui.swing.desktop.painters.implementations;

import app.gui.swing.desktop.painters.PainterDevice;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.shapes.Circle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class CPainter extends PainterDevice {
    public CPainter(Slot slot) {
        super(slot);
        Circle cir=(Circle)slot;
        offTextX=-13;
        offTextY=18;
        shape=new Ellipse2D.Double(cir.getPosI()-23,cir.getPosJ()-3,cir.getDimW(),cir.getDimH());
        if(slot.getAngle()!=0){

            pozovme(slot);
        }
    }

    private  void  pozovme(Slot slot){
        Circle cir=(Circle)slot;

        Shape shape1=new GeneralPath();
        int gdX=cir.getPosI()+cir.getDimW();
        int gdY= cir.getPosJ();

        int ddX=cir.getPosI() + cir.getDimW();
        int ddY=cir.getPosJ() + cir.getDimH();

        int dlX=cir.getPosI();
        int dlY=cir.getPosJ() + cir.getDimH();

        ((GeneralPath) shape1).moveTo(cir.getPosI(), cir.getPosJ());
        ((GeneralPath) shape1).lineTo(primX(gdX,gdY,slot),primY(gdX,gdY,slot));
        ((GeneralPath) shape1).lineTo(primX(ddX,ddY,slot),primY(ddX,ddY,slot));
        ((GeneralPath) shape1).lineTo(primX(dlX,dlY,slot),primY(dlX,dlY,slot));
        ((GeneralPath) shape1).closePath();

        Rectangle rectangle=new Rectangle(cir.getPosI(),cir.getPosJ(),cir.getDimW(),cir.getDimH());
            rectangle.setBounds(shape1.getBounds());

        ((Ellipse2D.Double)shape).setFrame(rectangle);

    }


}
