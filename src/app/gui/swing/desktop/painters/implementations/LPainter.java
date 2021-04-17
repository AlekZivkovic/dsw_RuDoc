package app.gui.swing.desktop.painters.implementations;

import app.gui.swing.desktop.painters.PainterDevice;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class LPainter extends PainterDevice{




    public LPainter(Shape shape){
        super(null);
        this.shape=shape;
    }


    public LPainter(int x, int j, int xx, int jj) {
        super(null);
        shape = new GeneralPath();

        ((GeneralPath) shape).moveTo(x, j);

        ((GeneralPath) shape).lineTo(xx, j);

        ((GeneralPath) shape).lineTo(xx,jj);

        ((GeneralPath) shape).lineTo(x,jj);

        ((GeneralPath) shape).closePath();

    }

    @Override
    public Shape getShape() {
        return super.getShape();
    }
}
