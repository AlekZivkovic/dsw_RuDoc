package app.serilizacija;

import app.AppCore;
import app.gui.swing.choosers.ChooserType;
import app.gui.swing.desktop.slotView.CharType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class SlotSaver {
   private File file;

    public SlotSaver() {
        file=null;
    }

    public File saveInc(List<String> args, List<CharType> charType, String[] name){
        File theDir = new File(System.getProperty("user.dir") + "/Slots/"+name[0] + "-" + name[1] + "-" + name[2]);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        file=new File(theDir.getPath()+"/"+name[3]+".txt");
        if(!file.exists()){
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        writeInFile(args,charType);



        return  file;
    }

    private void writeInFile(List<String> args, List<CharType> charType) {
        try {
            FileWriter myWriter = new FileWriter(file);
            for (int i=0 ; i<args.size();i++) {
                CharType ct=charType.get(i);
                String str=args.get(i);
                myWriter.append(vratiodgChar(ct));
               // System.out.println("slot: "+ str + " lenght "+ str.length() );
                if(str.length()==1)
                    myWriter.append(str);
                else myWriter.write(str);
                myWriter.append(vratiodgChar(ct));
            }


            myWriter.close();
            System.out.println("Uspesno upisano iz file.");
        } catch (IOException e) {
            System.out.println("Desio se error pri upisivanju.");
            e.printStackTrace();
        }
    }


    private  Character vratiodgChar(CharType charType){
        char c=(char) 0;
        if(charType==CharType.BOLD)
            c=(char) 219;
        else  if(charType==CharType.ITALIC)
            c=(char)220;
        else if(charType==CharType.UNDERLINE)
            c=(char)221;
        else c=(char)222;

        return c;
    }


    public File slotImageChooser(String[] args) {
        File f= AppCore.getInstance().getGui().getChooser(ChooserType.SLOT);
        if(f==null)return  null;
        Path copied=null;

        try { File theDir = new File(System.getProperty("user.dir") + "/Slots/"+args[0] + "-" + args[1] + "-" + args[2]);
            if (!theDir.exists()){
                theDir.mkdirs();
            }


            Path orgin=f.toPath();
            copied = Paths.get(theDir.getPath()+"/"+args[3]+".jpg");
            Files.copy(orgin,copied, StandardCopyOption.REPLACE_EXISTING);


        }catch (Exception e){
            e.printStackTrace();
        }
        return new File(String.valueOf(copied));
    }
}
