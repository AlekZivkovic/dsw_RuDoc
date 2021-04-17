package app.gui.swing.dialogs.implemented;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class AboutDialog extends JDialog{


    public AboutDialog(){
        addElem();


    }

    private void addElem() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(screenWidth / 3, screenHeight / 3);
        setLocationRelativeTo(null);
        setModal(true);

        setTitle("About");
        setLayout(new FlowLayout(FlowLayout.CENTER,50,10));




        JButton closeBtn=new JButton("close");

        closeBtn.setPreferredSize(new Dimension(70,30));
        closeBtn.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        JPanel jbr=new JPanel();

        BorderLayout br=new BorderLayout(10,170);
        jbr.setLayout(br);
        jbr.add(new JLabel(" "),BorderLayout.CENTER);
        jbr.add(closeBtn,BorderLayout.SOUTH);

        add(napraviSliku(getClass().getResource("image/alekJpg.jpg")," Aleksandar Zivkovic","RN 85/2020"));
        add(jbr);
        add(napraviSliku(getClass().getResource("image/filip.jpg"),"     Filip Filipovic","RN 104/2020"));
        closeBtn.setText("Close");

        validate();




    }



    private JPanel napraviSliku(URL imageUrl, String ime,String index){
        JPanel slikaPanel=new JPanel();
        slikaPanel.setLayout(new BoxLayout(slikaPanel,BoxLayout.Y_AXIS));

        JLabel slLb=new JLabel("");
        Image i = new ImageIcon(imageUrl).getImage();
        slLb.setIcon(new ImageIcon(i));
        slLb.setBounds(0,0,130,130);



        slikaPanel.add(Box.createVerticalStrut(14));
        slikaPanel.add(new JLabel(ime));
        slikaPanel.add(new JLabel("      "+index));
        slikaPanel.add(slLb);

        return slikaPanel;
    }


    public void getStarted(){
        this.setVisible(true);
    }




}
