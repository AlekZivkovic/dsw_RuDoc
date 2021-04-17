package app.serilizacija;

import app.AppCore;
import app.gui.swing.desktop.slotView.CharType;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SlotReader {

    public SlotReader() {
    }


    public Image readImage(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }


    public List<Pair<String, CharType>> readFromFile(File file) {
        List<Pair<String,CharType>>res=new ArrayList<>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                CharType tren =null;
                StringBuilder sb=new StringBuilder();
                for(int i=0;i< data.length();i++){
                    // System.out.println("Tren elem" + data.charAt(i));
                    if(tren==null){
                        tren=vratiOdgTip(data.charAt(i));
                        // System.out.println("usli smo u null tren "+ tren);
                        continue;
                    }
                    if(vratiOdgTip(data.charAt(i))==null){
                        //System.out.println("Appedujemo ");
                        sb.append(data.charAt(i));
                    }else{
                        res.add(new Pair(sb.toString(),tren));
                        //System.out.println("Postavljanmo u mapu "+sb.toString() +" "+ tren );
                        sb=new StringBuilder();
                        tren=null;
                        continue;
                    }
                }
                //res.put(sb.toString(),prosli);
                // System.out.println("Postavljanmo u mapu "+sb.toString() +" "+ prosli);
            }
            System.out.println("Uspesno procitano iz file");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error se desio pri citanju.");
           // e.printStackTrace();
            AppCore.getInstance().getErrorHandler().throwException("Greska pri ucitavanju datog slota, napravljen je novi fajl");
        }
        System.out.println(res);
        return  res;
    }
    private  CharType vratiOdgTip(char c){
        CharType charType=null;
        if((int)c==219)
            charType=CharType.BOLD;
        else if((int)c==220)
            charType=CharType.ITALIC;
        else if((int)c==221)
            charType=CharType.UNDERLINE;
        else if ((int)c==222)
            charType=CharType.PLAIN;

        return  charType;
    }
}
