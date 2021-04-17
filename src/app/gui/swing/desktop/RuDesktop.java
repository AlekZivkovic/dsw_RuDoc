package app.gui.swing.desktop;

import app.core.Subscriber;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.repository.Page;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import javax.swing.*;

public interface RuDesktop  extends Subscriber {
    void updateDesktop();
    void addProject(RuNode ruNode);
    JTabbedPane generateDesktop(RuNode project);
    RuNode getProject();
    void startCircleState();
    void startSelectState();
    void startTriangleState();
    void startRectangleState();
    void startDeleteState();
    void startMoveState();
    void startResizeState();
    void startRotateState();
    void startUndoRotateState();
    void startOpenState();
    //potrebno testiranje
    RuNodeComposite getSelectedDoc();
    RuNodeComposite getSelectedPage();
    RuDeskDoc getRuDeskDoc();
    void removeCommands(Page page);


}
