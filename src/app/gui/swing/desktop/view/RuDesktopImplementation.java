package app.gui.swing.desktop.view;

import app.core.Publisher;
import app.core.Subscriber;
import app.gui.swing.desktop.RuDesktop;
import app.gui.swing.desktop.state.StateManager;
import app.gui.swing.view.MainFrame;
import app.repository.*;
import app.repository.slotFactory.sloth.Slot;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;
import javax.swing.*;
import java.awt.*;

public class RuDesktopImplementation implements RuDesktop, Subscriber {
    private  RuNode projectModel;
    private  RuDeskView ruDeskView;



    @Override
    public void addProject(RuNode ruNode) {
        projectModel=ruNode;
        this.subscribe(projectModel);
        updateDesktop();
        MainFrame.getInstance().getProjectBar().setPrjekat(projectModel.getName());
        MainFrame.getInstance().getTree().setSelectedProject(projectModel);

    }

    @Override
    public JTabbedPane generateDesktop(RuNode project) {
        ruDeskView=new RuDeskView();
        return ruDeskView;
    }


    @Override
    public void updateDesktop() {
        ((RuDeskView)MainFrame.getInstance().getWorkspaceDesktop()).addProject((RuNodeComposite)projectModel);
        if(projectModel!=null) {
            setFocus();
        }


    }

    @Override
    public void onUpdate() {
        ruDeskView.updateView(proveri());
            if(projectModel!=null){
                //kvari korisnicki uzitak
                //setFocus();
            }

       if(projectModel!=null)
        MainFrame.getInstance().getProjectBar().setPrjekat(projectModel.getName());

    }

    @Override
    public void subscribe(Publisher publisher) {
        publisher.addSubscriber(this);
    }

    @Override
    public void unsubscribe(Publisher publisher) {
        publisher.removeSubscriber(this);
    }

    //postavlja focus na odg tab
    private void setFocus(){
        RuNode i=MainFrame.getInstance().getTree().getSelectedItem();

        //provera dal se uopste odg node nalazi u datom projektu
        if(provSetFocus(i))return;

        for(int j=0;j<ruDeskView.getComponents().length;j++){
            RuDeskDoc r=((RuDeskDocExt)ruDeskView.getComponent(j)).getRuDeskDoc();

            if(i instanceof Document){
                    if(i.equals(r.getDoc())){
                        ruDeskView.setSelectedComponent(ruDeskView.getComponent(j));break;
                    }
            }else{
                if(i instanceof Page){
                    if(r.getDoc().equals(i.getParent()) ){
                        ruDeskView.setSelectedComponent(ruDeskView.getComponent(j)); break;
                    }

                }else{
                    if(i instanceof Slot){
                        if(r.getDoc().equals((i.getParent()).getParent())){
                            ruDeskView.setSelectedComponent(ruDeskView.getComponent(j));break;
                        }
                    }
                }


            }




        }
    }

    public RuNode getProject() {
        return projectModel;
    }

    @Override
    public RuNodeComposite getSelectedDoc() {
        if(ruDeskView==null)return null;
        if(ruDeskView.getSelectedComponent()==null)return null;
        return ((RuNodeComposite)((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().getDoc());
    }

    @Override
    public RuNodeComposite getSelectedPage() {
        if(getSelectedDoc()==null)return null;
        if(ruDeskView.getSelectedComponent()==null)return null;

        for(Component p : (((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().getComponents())){
            RuDeskPage page=(RuDeskPage)p;
            if(page.isSelected()){
                return  (RuNodeComposite) page.getItem();
            }
        }

        return  null;
    }

    //proveravamo dal nam je poslednji slektovan item Workspace jer ako jeste zatvarmo pri brisanju deskview se zatvara
    public  boolean proveri(){
        //OVA linija ne nepotrebna
       // if (MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent()==null) return false;

       //fixed issue sa dodavanjem
        if(projectModel==null)return  false;
       if(!((RuNodeComposite)projectModel.getParent()).getChildren().contains(projectModel)){
           projectModel=null;
           return  true;
       }

        return  false;
    }

    private  boolean provSetFocus(RuNode r){
        if(r instanceof  Project){
            if(!r.equals(projectModel))return  true;
        }
        if(r instanceof  Document){
            if(!projectModel.equals(r.getParent()))
                return true;
        }

        if (r instanceof  Page){
            if(!projectModel.equals(r.getParent().getParent()))
                return  true;
        }

        return  false;
    }

    @Override
    public void removeCommands(Page page) {
        ruDeskView.removeCommandsForTabs(page);
    }

    @Override
    public void startCircleState() {
        if(ruDeskView==null)return;
        if(ruDeskView.getSelectedComponent()==null)return;
        ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().startCircleState();
    }
    @Override
    public void startSelectState() {
        if(ruDeskView==null)return;
        if(ruDeskView.getSelectedComponent()==null)return;
        ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().startSelectState();
    }
    @Override
    public  void startTriangleState(){
        if(ruDeskView==null)return;
        if(ruDeskView.getSelectedComponent()==null)return;
        ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().startTriangleState();}
    @Override
    public void startRectangleState(){
        if(ruDeskView==null)return;
        if(ruDeskView.getSelectedComponent()==null)return;
        ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().startRectangleState(); }

    @Override
    public void startDeleteState() {
        if(ruDeskView==null)return;
        if(ruDeskView.getSelectedComponent()==null)return;
        ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().startDeleteState();
    }

    @Override
    public void startMoveState() {
        if(ruDeskView==null)return;
        if(ruDeskView.getSelectedComponent()==null)return;
        ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().startMoveState();
    }

    @Override
    public void startRotateState() {
        if(ruDeskView==null)return;
        if(ruDeskView.getSelectedComponent()==null)return;
        ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().startRotateState();
    }

    @Override
    public void startOpenState() {
        if(ruDeskView==null)return;
        if(ruDeskView.getSelectedComponent()==null)return;
        ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().startOpenState();
    }

    @Override
    public void startResizeState() {
        if(ruDeskView==null)return;
        if(ruDeskView.getSelectedComponent()==null)return;
        ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().startResizeState();
    }

    @Override
    public void startUndoRotateState() {
        if(ruDeskView==null)return;
        if(ruDeskView.getSelectedComponent()==null)return;
        ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc().startUndoRotateState();
    }

    @Override
    public RuDeskDoc getRuDeskDoc() {
        return ((RuDeskDocExt)ruDeskView.getSelectedComponent()).getRuDeskDoc();
    }
}
