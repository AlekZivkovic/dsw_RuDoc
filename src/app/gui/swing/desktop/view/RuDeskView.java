package app.gui.swing.desktop.view;

import app.core.PublisherString;
import app.core.SubscriberString;
import app.gui.swing.desktop.state.StateManager;
import app.gui.swing.desktop.state.diffStates.CircleState;
import app.gui.swing.desktop.state.diffStates.RectangleState;
import app.gui.swing.desktop.state.diffStates.SelectState;
import app.gui.swing.desktop.state.diffStates.TriangleState;
import app.gui.swing.view.MainFrame;
import app.repository.Page;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RuDeskView extends JTabbedPane implements PublisherString {

    private ArrayList<SubscriberString> subscribers = new ArrayList<>();

    public RuDeskView(){
        MainFrame.getInstance().getActionManager().getUndoAction().subscribe(this);
        MainFrame.getInstance().getActionManager().getRedoAction().subscribe(this);
        initTabListener();
    }

    private void initTabListener(){

        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if(getSelectedComponent()==null) {
                    notifySubscribers("UndoDisable");
                    notifySubscribers("RedoDisable");
                    return;
                }
                (((RuDeskDocExt)getSelectedComponent()).getRuDeskDoc()).getCommandManager().setUndoRedo();
            }
        });

    }


    private void addElem(RuNodeComposite project) {

        List<Component> tabovi= Arrays.asList(getComponents());
        List<RuNode> lista=(List)project.getChildren();
        removeAll();
        for(RuNode e: lista) {
            for(Component com : tabovi){
                RuDeskDoc doc=((RuDeskDocExt)com).getRuDeskDoc();
                if(e.getVerificationNumber()==doc.getDoc().getVerificationNumber() && proveraZaComponent(doc)){
                    //System.out.println(e.getVerificationNumber() +"  je " + doc.getDoc().getVerificationNumber());
                    addDoc((RuNodeComposite)e,doc.getStateManager());
                    continue;
                }
            }
            addDoc((RuNodeComposite) e, null);
        }


    }

    private boolean proveraZaComponent(RuDeskDoc doc) {
        boolean flag=true;
        for(Component com : getComponents()){
            RuDeskDoc e=((RuDeskDocExt)com).getRuDeskDoc();
            if(doc.getDoc().getVerificationNumber()==e.getDoc().getVerificationNumber()){
                flag=false;
                return  flag;
            }
        }

        return  flag;
    }

    private void addDoc(RuNodeComposite pack, StateManager stateManager) {
        RuDeskDoc doc=new RuDeskDoc(pack);
        if(proveraZaComponent(doc)==false)return;
        if(stateManager!=null){
            if(stateManager.getCurrState() instanceof RectangleState)
                doc.getStateManager().setRectangleState();
            if(stateManager.getCurrState() instanceof TriangleState)
                doc.getStateManager().setTriangleState();
            if(stateManager.getCurrState() instanceof CircleState)
                doc.getStateManager().setCircleState();
        }
        for(RuNode e: pack.getChildren()){
            RuDeskPage ei=new RuDeskPage(e);

            doc.addChild(ei);
        }
        ImageIcon icon=new ImageIcon(getClass().getResource("images/tab.png"));
        addTab(pack.getName(),icon,new RuDeskDocExt(doc));
    }


    public void addProject(RuNodeComposite project){
        if(project instanceof RuNode ) {
            addElem(project);
        }
        revalidate();
    }

    /*
    Zbog nekog razloga ovo jedino moze ovako preko hasan-a i bol polja
    jako je bolno......

     */


    public  void updateView(boolean flag){
        //Ako smo izbrisali projekat koji je imao docs brisemo i njih

        int bol=0;
        int hasan=0;

        if(flag==true){
             removeAll();
             MainFrame.getInstance().getProjectBar().setPrjekat(" ");
        }else{

            if(MainFrame.getInstance().getDesktop().getProject() !=null){
                RuNode selected=null;
                //interna provera ne vezana za eventHandler koja ce da proveri  da li imamo uopsten neki selektovan doc
                //ako imamo onda pamtimo ga i "refresh" dati projekat i posle vracamo pokazivac na taj doc
                if(this.getSelectedComponent()!=null) {
                    selected = ((RuDeskDocExt) this.getSelectedComponent()).getRuDeskDoc().getDoc();
                    bol=((RuDeskDocExt) this.getSelectedComponent()).getVerticalScrollBar().getValue();
                }
                //ovde je bila izmena za popravku
                pokusajPopravljanja();
                //addElem((RuNodeComposite) MainFrame.getInstance().getDesktop().getProject());
                if(selected==null)return;
                   for(Component s : getComponents()){
                       if(((RuDeskDocExt)s).getRuDeskDoc().getDoc().getVerificationNumber()==selected.getVerificationNumber()){
                           setSelectedComponent(s);

                           if(bol!=0 && bol!=90) {
                               ((RuDeskDocExt) this.getSelectedComponent()).getVerticalScrollBar().setValue(bol);
                               hasan=bol;
                           }else{
                               ((RuDeskDocExt) this.getSelectedComponent()).getVerticalScrollBar().setValue(hasan);
                           }
                           break;
                      }
                   }

            }


        }
        if(this.getSelectedComponent()!=null) {
            ((RuDeskDocExt) this.getSelectedComponent()).getVerticalScrollBar().setValue(hasan);
        }
    }

    private void pokusajPopravljanja() {
        if(getComponents()==null)return;
        if(MainFrame.getInstance().getDesktop().getProject()==null)return;
        int i=0;
        boolean rename=false;

        List<RuNode> chi=((RuNodeComposite)MainFrame.getInstance().getDesktop().getProject()).getChildren();
        for(RuNode ru : chi){
            if(i>=this.getTabCount()){
                ImageIcon icon=new ImageIcon(getClass().getResource("images/tab.png"));
                addTab(ru.getName(),icon,new RuDeskDocExt(new RuDeskDoc(ru)));
            }
            if(!ru.getName().equals(this.getTitleAt(i))){
                rename=true;
                //System.out.println("Flag je postavljen za true");
            }
            i++;
        }

        if(rename==true || getTabCount()>i)
            addElem(((RuNodeComposite)MainFrame.getInstance().getDesktop().getProject()));


        for(Component component: getComponents()){
            RuDeskDocExt ruD=(RuDeskDocExt)component;
            ruD.popravi();
            component.repaint();
        }


    }

    public void removeCommandsForTabs(Page page){

        int tabCount = this.getTabCount();
        for(int i = 0; i<tabCount; i++){
            RuDeskDocExt ruDeskDocExt =(RuDeskDocExt) this.getComponentAt(i);

            if(ruDeskDocExt.getRuDeskDoc().getDoc().equals(page.getParent())){
                ruDeskDocExt.getRuDeskDoc().getCommandManager().deleteActions(page);

                if(MainFrame.getInstance().getDesktop().getSelectedDoc().equals(ruDeskDocExt.getRuDeskDoc().getDoc()))
                    ruDeskDocExt.getRuDeskDoc().getCommandManager().setUndoRedo();
            }

        }

    }


    @Override
    public void addSubscriber(SubscriberString subscriber) {
        if(subscriber!=null)
            subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(SubscriberString subscriber) {
        if(subscriber!=null)
            subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String string) {
        for(SubscriberString s:subscribers){
            s.update(string);
        }
    }
}
