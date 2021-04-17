package app.gui.swing.desktop.view;

import app.command.CommandManager;
import app.command.CommandManagerInterface;
import app.gui.swing.desktop.state.StateManager;
import app.gui.swing.view.MainFrame;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RuDeskDoc extends JPanel {
    private RuNode doc;
    private List<RuDeskPage>pages;
    private StateManager stateManager;
    private CommandManagerInterface commandManager;

    public RuDeskDoc(RuNode doc) {
        this.doc = doc;
        this.stateManager=new StateManager(this);
        commandManager = new CommandManager();
        this.setCommandManagerSubs();
        pages=new ArrayList<>();
        GridLayout gl=new GridLayout();
        gl.setHgap(60);
        gl.setVgap(30);
        gl.setRows(20);
        gl.setColumns(1);
        setBackground(Color.GRAY);
        setLayout(gl);
    }


    //idk mozda zatreba nekad da imamo pagove
    public void addChild(RuDeskPage ruDeskPage){
        if(!pages.contains(ruDeskPage))
            pages.add(ruDeskPage);
            add(ruDeskPage);

    }

    public RuNode getDoc() {
        return doc;
    }

    public List<RuDeskPage> getPages() {
        return pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RuDeskDoc)) return false;
        RuDeskDoc ruDeskDoc = (RuDeskDoc) o;
        return Objects.equals(doc, ruDeskDoc.doc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void pokuajPoprave() {
        if(getComponents()==null)return;
        int i=0;
        boolean rename=false;
        boolean delete=false;
        for(RuNode ruNode: ((RuNodeComposite)doc).getChildren()){
         //   System.out.println("Ime page u  docu "+ ruNode.getName());
           // System.out.println("componet count"+ getComponentCount()+ " a I JE " + i);

            if(i>=this.getComponentCount()){
                //System.out.println("Usli smo u if "+ ruNode.getName());
                RuDeskPage test= new RuDeskPage(ruNode);
                addChild(test);
                repaint();
            }

            RuDeskPage ruPage=pronadjiOdgPage(ruNode);
            if(ruPage == null){
                delete=true;
                //System.out.println("Pronadjen je page koji treba da se delete");
                continue;
            }

            if(!ruNode.getName().equals(ruPage.getStrings()[2])) {
                rename = true;
               // System.out.println("Flag je postavljen za true");
            }

            i++;

        }
        if(rename==true || getComponentCount()>i){
            removeAll();
            for(RuNode ruNode: ((RuNodeComposite)doc).getChildren()){
                RuDeskPage rp=new RuDeskPage(ruNode);
                addChild(rp);
            }
        }
        for(Component component: getComponents()){
            component.repaint();
        }


    }

    private  RuDeskPage pronadjiOdgPage(RuNode ruNode){
        RuDeskPage ruPage=null;
        for(Component component : getComponents()){
            RuDeskPage tren=(RuDeskPage)component;
            if(ruNode.getVerificationNumber()==tren.getItem().getVerificationNumber()){
                ruPage=tren;
                return ruPage;
            }
        }


        return  ruPage;
    }

    private void setCommandManagerSubs(){
        MainFrame.getInstance().getActionManager().getUndoAction().subscribe(commandManager);
        MainFrame.getInstance().getActionManager().getRedoAction().subscribe(commandManager);
    }

    public CommandManagerInterface getCommandManager() {
        return commandManager;
    }

    public void startCircleState() {
        stateManager.setCircleState();
    }

    public void startSelectState() {
        stateManager.setSelectState();
    }
    public  void startTriangleState(){stateManager.setTriangleState();}

    public void startRectangleState(){
        stateManager.setRectangleState();
    }
    public  void startDeleteState(){
        stateManager.setDeleteState();
    }

    public void startMoveState(){
        stateManager.setMoveState();
    }
    public  void  startOpenState(){stateManager.setOpenState();}

    public void startRotateState(){
        stateManager.setRotateState();
    }
    public  void startLassoState(){stateManager.setLassoState();}

    public void startResizeState(){
        stateManager.setResizeState();
    }
    public StateManager getStateManager() {
        return stateManager;
    }

    public void startUndoRotateState(){
        stateManager.setUndoRotateState();
    }

}
