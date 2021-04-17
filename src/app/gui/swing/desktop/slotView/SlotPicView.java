package app.gui.swing.desktop.slotView;

import app.AppCore;
import app.gui.swing.view.MainFrame;
import app.repository.slotFactory.sloth.Slot;
import javax.swing.*;
import java.awt.*;

public class SlotPicView extends JPanel {
    private  JButton getImBtn;
    private  ImagePanel images;
    private Slot slot;



    public  SlotPicView(Slot slot){
        this.slot=slot;
        initElem();
        addElem();
    }

    private void addElem() {
        this.setLayout(new BorderLayout(10,10));
        add(getImBtn,BorderLayout.NORTH);
        add(images,BorderLayout.CENTER);

        getImBtn.addActionListener(e->prov());
    }

    private void prov() {
        slot.setContainer(AppCore.getInstance().getRuSerilization().chooseSlotImage(new String[]{slot.getParent().getParent().getParent().getName(), slot.getParent().getParent().getName(), slot.getParent().getName(),slot.getName()}));
        if(slot.getContainer()!=null){
            System.out.println(slot.getContainer().getPath());
        images.setImage(AppCore.getInstance().getRuSerilization().readSlotImage(slot.getContainer()));
        repaint();
        }
    }

    private void initElem() {
        this.getImBtn=new JButton("Choose image");
        this.images=new ImagePanel();
        if(slot.getContainer()!=null)
            images.setImage(AppCore.getInstance().getRuSerilization().readSlotImage(slot.getContainer()));
    }


    class ImagePanel extends JPanel {

        private Image img;
        public  ImagePanel(){

        }

        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) {
            this.img = img;
        }
/*
        public void paintComponent(Graphics g) {
            if(img==null){super.paintComponent(g);return;}
            g.drawImage(img, (int)(this.getSize().getWidth()-img.getWidth(null))/2,
                    (int)(this.getSize().getHeight()-img.getHeight(null))/2, null);
        }

        public  void setImage(String img){
            this.img=new ImageIcon(img).getImage();

            //System.out.println(img==null);
            this.repaint();
        }
 */

@Override
protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    if(img==null)return;
    double scaleFactor = Math.min(1d, getScaleFactorToFit(new Dimension(img.getWidth(this), img.getHeight(this)), getSize()));

    int scaleWidth = (int) Math.round(img.getWidth(null) * scaleFactor);
    int scaleHeight = (int) Math.round(img.getHeight(null) * scaleFactor);

    Image scaled = img.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);

    int width = getWidth() - 1;
    int height = getHeight() - 1;

    int x = (width - scaled.getWidth(this)) / 2;
    int y = (height - scaled.getHeight(this)) / 2;

    g.drawImage(scaled, x, y, this);

}
        public  void setImage(Image img){
            this.img=img;

            //System.out.println(img==null);
            this.repaint();
        }

    }

    private double getScaleFactor(int iMasterSize, int iTargetSize) {

        double dScale = 1;
        if (iMasterSize > iTargetSize) {

            dScale = (double) iTargetSize / (double) iMasterSize;

        } else {

            dScale = (double) iTargetSize / (double) iMasterSize;

        }

        return dScale;

    }

    private double getScaleFactorToFit(Dimension original, Dimension toFit) {

        double ds = 1d;

        if (original != null && toFit != null) {

            double dSW = getScaleFactor(original.width, toFit.width);
            double dSH = getScaleFactor(original.height, toFit.height);

            ds = Math.min(dSH, dSW);

        }

        return ds;

    }

}
