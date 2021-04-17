package app.gui.swing.desktop.slotView;


import app.AppCore;
import app.repository.slotFactory.sloth.Slot;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SlotTextView extends JPanel {
    private  JTextPane textPane;
    private Slot slot;
    private JButton boldBtn;
    private  JButton uLBtn;
    private  JButton iBtn;
    private  JButton saveBtn;
    private  boolean changed;



    public  SlotTextView(Slot slot){
        this.slot=slot;
        this.setLayout(new BorderLayout(20,20));
        initElm();
        addElem();
    }

    private void addElem() {

        JPanel dugmad= new JPanel();
        dugmad.setLayout(new FlowLayout(FlowLayout.LEFT));
        dugmad.add(boldBtn);
        dugmad.add(uLBtn);
        dugmad.add(iBtn);
        dugmad.add(new JLabel("                         "));
        dugmad.add(saveBtn);


        add(dugmad,BorderLayout.NORTH);

        JScrollPane jScrollPane=new JScrollPane(textPane);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(jScrollPane,BorderLayout.CENTER);

        textPane.setSize(50,300);
        if(slot.getContainer()!=null){
            //System.out.println("usao");
            ucitaj();
        }


        boldBtn.addActionListener(e -> makeItBold());
        iBtn.addActionListener(e -> makeItItalic());
        uLBtn.addActionListener(e -> makeItUnder());
        saveBtn.addActionListener(e-> itsTime());
        textPane.getDocument().addDocumentListener(new DocLisner());
    }

    private void ucitaj() {
        List<Pair<String,CharType>> res=AppCore.getInstance().getRuSerilization().readSlotFile(slot.getContainer());
        if(res==null)return;

        StyledDocument doc=(StyledDocument)textPane.getDocument();
        for(Pair<String,CharType> e:res){
            String key=e.getKey();
            CharType tip=e.getValue();
            //System.out.println(key + " "+ tip);
            SimpleAttributeSet as = new SimpleAttributeSet();

            if(tip == CharType.BOLD)
                StyleConstants.setBold(as,true);
            else if(tip== CharType.ITALIC)
                StyleConstants.setItalic(as,true);
            else if(tip==CharType.UNDERLINE)
                StyleConstants.setUnderline(as,true);



            int off= textPane.getText().length();
            try {
                doc.insertString(off,key,as);
            } catch (BadLocationException badLocationException) {
                badLocationException.printStackTrace();
            }


        }

    }


    private void initElm() {
        this.textPane=new JTextPane();
        this.boldBtn=new JButton();
        this.uLBtn=new JButton();
        this.iBtn=new JButton();
        this.saveBtn= new JButton();
        this.changed=false;
        saveBtn.setEnabled(false);


        boldBtn.setIcon(new ImageIcon(getClass().getResource("images/bold.png")));
        uLBtn.setIcon(new ImageIcon(getClass().getResource("images/undeline.png")));
        iBtn.setIcon(new ImageIcon(getClass().getResource("images/iItalic.png")));
        saveBtn.setIcon(new ImageIcon(getClass().getResource("images/saved.png")));
        textPane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
    }
    private  void makeItBold(){
        StyledDocument doc = (StyledDocument) textPane.getDocument();
        SimpleAttributeSet as = new SimpleAttributeSet();

        if (textPane.getSelectedText()==null){
            StyleConstants.setBold(as, !StyleConstants.isBold(doc.getCharacterElement(textPane.getText().length()-1).getAttributes()));
            textPane.setCharacterAttributes(as,true);
            textPane.requestFocus();
            return;
        }
       StyleConstants.setBold(as, !StyleConstants.isBold(textPane.getCharacterAttributes()));
        doc.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectedText().length(), as, true);
        textPane.requestFocus();

    }
    private void makeItItalic() {
       StyledDocument doc = (StyledDocument) textPane.getDocument();
        SimpleAttributeSet as = new SimpleAttributeSet();

        if (textPane.getSelectedText()==null){
            StyleConstants.setItalic(as, !StyleConstants.isItalic(doc.getCharacterElement(textPane.getText().length()-1).getAttributes()));
            textPane.setCharacterAttributes(as,true);
            textPane.requestFocus();
            return;
        }
        StyleConstants.setItalic(as, !StyleConstants.isItalic(textPane.getCharacterAttributes()));
        doc.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectedText().length(), as, true);
        textPane.requestFocus();

    }
    private void makeItUnder() {
        StyledDocument doc = (StyledDocument) textPane.getDocument();
        SimpleAttributeSet as = new SimpleAttributeSet();

        if (textPane.getSelectedText()==null){
            StyleConstants.setUnderline(as, !StyleConstants.isUnderline(doc.getCharacterElement(textPane.getText().length()-1).getAttributes()));
            textPane.setCharacterAttributes(as,true);
            textPane.requestFocus();
            return;
        }
        StyleConstants.setUnderline(as, !StyleConstants.isUnderline(textPane.getCharacterAttributes()));
        doc.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectedText().length(), as, true);
        textPane.requestFocus();
    }

    public  void saveChanges(){
        this.itsTime();
    }
    private void itsTime() {
        setChanged(false);
        List<String> args=new ArrayList<>();
        List<CharType> tipovi=new ArrayList<>();
        CharType trenutniTip=null;
        StringBuilder sb=new StringBuilder();

        StyledDocument doc = (StyledDocument) textPane.getDocument();
        try {
            for (int i = 0; i < textPane.getText().length(); i++) {
                if (trenutniTip == null) {
                    trenutniTip = vratiOdgTip(doc.getCharacterElement(i).getAttributes());
                }
                if (vratiOdgTip(doc.getCharacterElement(i).getAttributes()) == trenutniTip) {
                    sb.append(textPane.getText(i, 1));
                   // System.out.println("Tren tip " + trenutniTip);
                    //System.out.println("ime: " + textPane.getText(i, 1));
                } else {
                    args.add(sb.toString());
                    sb=new StringBuilder();
                    sb.append(textPane.getText(i, 1));
                    tipovi.add(trenutniTip);
                    //System.out.println("Tren atribur "+ trenutniTip);
                    trenutniTip = vratiOdgTip(doc.getCharacterElement(i).getAttributes());
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        args.add(sb.toString());
        tipovi.add(trenutniTip);

        System.out.println(args+ " \n"+ tipovi );
        File f=AppCore.getInstance().getRuSerilization().saveSlot(args,tipovi,new String[]{slot.getParent().getParent().getParent().getName(), slot.getParent().getParent().getName(), slot.getParent().getName(),slot.getName()});

        slot.setContainer(f);
    }

    private  CharType vratiOdgTip(AttributeSet as){
        CharType trenutniTip=null;
        if(StyleConstants.isUnderline(as))
            trenutniTip=CharType.UNDERLINE;
        else  if(StyleConstants.isBold(as))
            trenutniTip=CharType.BOLD;
        else if(StyleConstants.isItalic(as))
            trenutniTip=CharType.ITALIC;
        else trenutniTip=CharType.PLAIN;

        return  trenutniTip;
    }




    class DocLisner implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) { setChanged(true); }

        @Override
        public void removeUpdate(DocumentEvent e) {
            setChanged(true);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            setChanged(true);
        }
    }


    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        if(changed==true) {
            saveBtn.setEnabled(true);
        }else{
            saveBtn.setEnabled(false);
        }
        this.changed = changed;
    }
}
