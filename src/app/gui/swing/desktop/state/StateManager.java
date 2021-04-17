package app.gui.swing.desktop.state;

import app.gui.swing.desktop.state.diffStates.*;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.gui.swing.desktop.view.RuDeskPage;
import javafx.scene.transform.Rotate;

public class StateManager {
    private  State currState;


    CircleState circleState;
    RectangleState rectangleState;
    TriangleState triangleState;
    SelectState selectState;
    DeleteState deleteState;
    MoveState moveState;
    ResizeState resizeState;
    RotateState rotateState;
    UndoRotateState undoRotateState;
    LassoState lassoState;
    OpenState openState;


    public StateManager(RuDeskDoc ruDeskDoc) {
        this.circleState = new CircleState(ruDeskDoc);
        this.rectangleState = new RectangleState(ruDeskDoc);
        this.triangleState = new TriangleState(ruDeskDoc);
        this.selectState = new SelectState(ruDeskDoc);
        this.deleteState=new DeleteState(ruDeskDoc);
        this.moveState = new MoveState(ruDeskDoc);
        this.resizeState = new ResizeState(ruDeskDoc);
        this.rotateState = new RotateState(ruDeskDoc);
        this.undoRotateState = new UndoRotateState(ruDeskDoc);
        this.lassoState=new LassoState(ruDeskDoc);
        this.openState=new OpenState(ruDeskDoc);
        this.currState=selectState;
    }

    public  void setTriangleState(){ currState=triangleState;}
    public void setCircleState() { currState = circleState; }
    public void setRectangleState(){ currState = rectangleState; }
    public void setSelectState(){ currState = selectState; }
    public void setDeleteState(){ currState=deleteState;}
    public void setMoveState(){
        currState = moveState;
    }
    public void setRotateState(){
        currState = rotateState;
    }
    public void setResizeState(){
        currState = resizeState;
    }
    public void setLassoState(){currState=lassoState;}
    public void setOpenState() { currState = openState; }

    public void setUndoRotateState(){
        currState = undoRotateState;
    }
    public State getCurrState() {
        return currState;
    }
}
