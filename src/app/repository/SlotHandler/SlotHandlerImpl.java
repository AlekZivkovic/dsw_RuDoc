package app.repository.SlotHandler;

import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.shapes.Circle;
import app.repository.slotFactory.sloth.shapes.Rectangle;
import app.repository.slotFactory.sloth.shapes.Triangle;

import java.util.List;
import java.util.Vector;

public class SlotHandlerImpl implements SlotHandlerInterface{

    private int xStarting = 0;
    private int yStarting = 0;


    @Override
    public void moveSlot(List<Slot> slots, int x, int y) {
        for(Slot slot: slots) {
            int offsetX = x - xStarting;
            int offsetY = y - yStarting;

            slot.setPosI(slot.getPosI() + offsetX);
            slot.setPosJ(slot.getPosJ() + offsetY);

    }
        xStarting = x;
        yStarting = y;


    }

    @Override
    public void resizeSlot(List<Slot> slots, int x, int y) {
        for(Slot slot : slots) {
            int offsetX = x - xStarting;
            int offsetY = y - yStarting;

            if ((!(slot.getDimH() + offsetY < 25) && !(slot.getDimW() + offsetX < 45) && slot instanceof Rectangle) ||
                    (!(slot.getDimH() + offsetY < 45) && !(slot.getDimW() + offsetX < 35) && slot instanceof Triangle) ||
                    (!(slot.getDimH() + offsetY < 40) && !(slot.getDimW() + offsetX < 40) && slot instanceof Circle)) {
                if (slot instanceof Circle) {
                    if (offsetX != 0) {
                        slot.setDimH(slot.getDimH() + offsetX);
                        slot.setDimW(slot.getDimW() + offsetX);
                    }
                    if (offsetY != 0) {
                        slot.setDimH(slot.getDimH() + offsetY);
                        slot.setDimW(slot.getDimW() + offsetY);
                    }
                } else {
                    slot.setDimH(slot.getDimH() + offsetY);
                    slot.setDimW(slot.getDimW() + offsetX);
                }

            }
        }
        xStarting = x;
        yStarting = y;


    }

    @Override
    public void rotateSlot(List<Slot> slots, int x, int y) {

        for(Slot slot : slots) {
            Vector<Integer> vector1 = new Vector<>();
            vector1.add(xStarting - slot.getPosI());
            vector1.add(yStarting - slot.getPosJ());

            Vector<Integer> vector2 = new Vector<>();
            vector2.add(x - slot.getPosI());
            vector2.add(y - slot.getPosJ());

            int dotProduct = dotProductOfTwoVectors(vector1.elementAt(0), vector2.elementAt(0),
                    vector1.elementAt(1), vector2.elementAt(1));

            double intensityOfVector1 = intensityOfVector(vector1.elementAt(0), vector1.elementAt(1));
            double intensityOfVector2 = intensityOfVector(vector2.elementAt(0), vector2.elementAt(1));

            double angle = Math.acos(dotProduct / (intensityOfVector1 * intensityOfVector2));

            slot.setAngle((int) Math.toDegrees(angle));
        }

    }

    @Override
    public void changeStartingPositions(int x, int y) {
        xStarting = x;
        yStarting = y;
    }

    private int dotProductOfTwoVectors(int x1, int x2, int y1, int y2){

        int sum = 0;
        sum = x1*x2 + y1*y2;
        return sum;

    }

    private double intensityOfVector(int x, int y){

        double sum;
        sum = Math.sqrt(Math.abs(x*x + y*y));
        return sum;

    }

}
