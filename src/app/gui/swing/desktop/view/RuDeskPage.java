package app.gui.swing.desktop.view;

import app.core.Publisher;
import app.core.Subscriber;
import app.gui.swing.desktop.painters.*;
import app.gui.swing.desktop.painters.implementations.CPainter;
import app.gui.swing.desktop.painters.implementations.LPainter;
import app.gui.swing.desktop.painters.implementations.RPainter;
import app.gui.swing.desktop.painters.implementations.TPainter;
import app.gui.swing.view.MainFrame;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;
import app.repository.slotFactory.sloth.Slot;
import app.repository.slotFactory.sloth.shapes.Circle;
import app.repository.slotFactory.sloth.shapes.Rectangle;
import app.repository.slotFactory.sloth.shapes.Triangle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class RuDeskPage extends JPanel  implements Subscriber {
    private RuNode item;
    private  String[] strings;
    private  boolean selected;
    private Rectangle2D lassoRec;
    private  boolean isClicked;



    public RuDeskPage(RuNode item){
        this.selected=false;
        this.item=item;
        this.strings= new String[3];
        strings[0]=item.getParent().getParent().getName();
        strings[1]=item.getParent().getName();
        strings[2]=item.getName();
        lassoRec=null;
        item.addSubscriber(this);
        addElem();
        MouseContoller mouseContoller = new MouseContoller();
        addMouseListener(mouseContoller);
        addMouseMotionListener(mouseContoller);
        subscribe(item);

    }


    private void addElem() {
        setPreferredSize(new Dimension(200,300));
        setLayout(new FlowLayout(FlowLayout.LEFT));

        setMaximumSize(new Dimension(200,300));
    }

    public void addPageName(Object o, Graphics g){
        if(o==null)return;

        if(o instanceof  String){
            String str=((String)o);
            g.drawString(str, 10,15);
        }
    }

    private  class  MouseContoller extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
           // System.out.println(" hello " + item.getName());
            if(e.getButton()== MouseEvent.BUTTON1){
                  Point position = e.getPoint();
                  //System.out.println("cords " + position);
                  ((RuDeskDocExt) MainFrame.getInstance().getWorkspaceDesktop().getSelectedComponent()).getRuDeskDoc().getStateManager().getCurrState().mousePressed(e);
              }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            //if(e.getButton()==MouseEvent.BUTTON1){
                Point position=e.getPoint();
                //System.out.println("cords "+ position);
                ((RuDeskDocExt)MainFrame.getInstance().getWorkspaceDesktop().getSelectedComponent()).getRuDeskDoc().getStateManager().getCurrState().mouseDragged(e);
            //}
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //if(e.getButton()==MouseEvent.BUTTON1){
                Point position=e.getPoint();
                //System.out.println("cords "+ position);
                ((RuDeskDocExt)MainFrame.getInstance().getWorkspaceDesktop().getSelectedComponent()).getRuDeskDoc().getStateManager().getCurrState().mouseReleased(e);
           // }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setSelected(true);
        }

        @Override
        public void mouseExited(MouseEvent e) { setSelected(false); }
    }

    public  void addSlots(Slot slot){
        ((RuNodeComposite)getItem()).addChild(slot);
    }


    public Slot getDeviceAtPosition(Point point) {
        for(int i=((RuNodeComposite)getItem()).getChildren().size()-1;i>=0;i--){
            Slot slot=((Slot)((RuNodeComposite)getItem()).getChildren().get(i));
            //Potrebno dodati f-ju za odg painter
            PainterDevice painter =pronadjiOdgPainter(slot);
           if(painter.isElementAt(point)){
            return slot;
            }
        }
        return null;
    }

    public PainterDevice pronadjiOdgPainter(Slot slot){
        PainterDevice test=null;
        if(slot instanceof Rectangle)
            test=new RPainter(slot);
        if(slot instanceof Triangle)
            test=new TPainter(slot);
        if(slot instanceof Circle)
            test=new CPainter(slot);
        return test;
    }







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RuDeskPage)) return false;
        RuDeskPage that = (RuDeskPage) o;
        return Objects.equals(item, that.item);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graph = (Graphics2D) g;
        addPageName(strings[0]+"-"+strings[1]+"-"+strings[2],g);
        graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        for(RuNode e1 : ((RuNodeComposite)getItem()).getChildren()){
            Slot slot=(Slot)e1;
            PainterDevice painter=pronadjiOdgPainter(slot);
            painter.paint(graph);
        }
        if(lassoRec !=null){
            PainterDevice pain=new LPainter(lassoRec);
            pain.lassoPaint(graph);
        }

    }

    public RuNode getItem() {
        return item;
    }

    @Override
    public void onUpdate() {
        repaint();
    }

    @Override
    public void subscribe(Publisher publisher) {
        publisher.addSubscriber(this);
    }

    @Override
    public void unsubscribe(Publisher publisher) {
        publisher.removeSubscriber(this);
    }

    public String[] getStrings() {
        return strings;
    }
    public boolean isSelected() {
        return selected;
    }

    public Rectangle2D getLassoRec() { return lassoRec; }

    public void setLassoRec(int x, int j,int xx,int jj) {
        lassoRec=new Rectangle2D.Double();
        AffineTransform affineTransform=new AffineTransform();
        lassoRec.setFrame(affineTransform.createTransformedShape((new LPainter(x,j,xx,jj)).getShape()).getBounds2D());
        repaint();
    }

    public void setLassoRec(Rectangle2D lassoRec) {
        this.lassoRec = lassoRec;
        repaint();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
