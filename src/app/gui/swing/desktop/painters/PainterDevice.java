package app.gui.swing.desktop.painters;

import app.repository.slotFactory.sloth.Slot;
import java.awt.*;

public class PainterDevice extends  SlotPainter {
    protected  Shape shape;
    protected  int offTextX;
    protected  int offTextY;

    public PainterDevice(Slot slot) {
        super(slot);
    }


    @Override
    public void paint(Graphics2D g) {

        if(!isSlotSlected())
            g.setPaint(Color.RED);
        else g.setPaint(Color.GREEN);

        g.setStroke(new BasicStroke(2f));
        g.draw(getShape());

    }
    public  void lassoPaint(Graphics2D g){
        g.setPaint(Color.BLUE);
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        g.draw(getShape());
    }

    @Override
    public boolean isElementAt(Point point) {
        return getShape().contains(point);
    }

    public Shape getShape() {
        return shape;
    }

    protected int primX(int x,int y,Slot slot){
        double cord=0;

        cord=(x-slot.getPosI())*Math.cos(slot.getAngle())-(y-slot.getPosJ())*Math.sin(slot.getAngle())+slot.getPosI();


        return  (int)cord;
    }
    protected int primY(int x,int y,Slot slot){
        double cord=0;

        cord=(x-slot.getPosI())*Math.sin(slot.getAngle())+(y-slot.getPosJ())*Math.cos(slot.getAngle())+slot.getPosJ();


        return  (int)cord;
    }
}
