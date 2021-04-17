package app.gui.swing.desktop.state.diffStates;

import app.gui.swing.dialogs.Dialog;

import app.gui.swing.desktop.state.StateExt;
import app.gui.swing.desktop.view.RuDeskDoc;
import app.gui.swing.view.MainFrame;
import app.repository.Page;
import java.awt.event.MouseEvent;

public class OpenState extends StateExt {

    public OpenState(RuDeskDoc ruDeskDoc) {this.ruDeskDoc=ruDeskDoc; }

    @Override
    public void mousePressed(MouseEvent e) {
        setPage();
        if(ruDeskPage.getDeviceAtPosition(e.getPoint())!=null) {
            if( ((Page)ruDeskPage.getItem()).getSelected()!=null && ((Page)ruDeskPage.getItem()).getSelected().contains(ruDeskPage.getDeviceAtPosition(e.getPoint())))
                    MainFrame.getInstance().getDialogs().getDialog(Dialog.SLOT_VIEWER);

        }
    }
}
