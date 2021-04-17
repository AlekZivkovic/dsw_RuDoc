package app.gui.swing.desktop.state;

import app.gui.swing.desktop.view.RuDeskDoc;
import app.gui.swing.desktop.view.RuDeskPage;
import app.repository.Document;
import app.repository.Page;
import app.repository.slotFactory.sloth.Slot;

import java.awt.*;

public class StateExt extends  State {
    protected RuDeskDoc ruDeskDoc;
    protected  RuDeskPage ruDeskPage;



    protected void setPage() {
        for(Component comp :ruDeskDoc.getComponents()){
            RuDeskPage ruPage=(RuDeskPage)comp;
            if(ruPage.isSelected()){
                ruDeskPage=ruPage;
            }
        }
    }
    protected boolean prov(Point point){
        for(Component comp :ruDeskDoc.getComponents()){
            RuDeskPage ruPage=(RuDeskPage)comp;
            if(ruPage.isSelected() && ruPage.getDeviceAtPosition(point)!= null){
                return  false;
            }
        }

        return  true;
    }

    protected void subscribeShared(){
        if(((Document)ruDeskDoc.getDoc()).isShared()) {
            for (Slot s : ((Page) ruDeskPage.getItem()).getSelected()) {
                ruDeskPage.subscribe(s);
            }
        }
    }

    protected void subscribeDeserialized(){
        for(Slot s:((Page) ruDeskPage.getItem()).getSelected()){
            if(s.getSubscribers()==null)
                s.initSubs();
            if(!s.getSubscribers().contains(ruDeskPage)) {
                ruDeskPage.subscribe(s);
            }
        }
    }
}
