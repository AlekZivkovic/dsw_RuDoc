package app.serilizacija;


import app.AppCore;
import app.gui.swing.desktop.slotView.CharType;
import app.gui.swing.view.MainFrame;
import app.repository.Project;
import app.repository.Workspace;
import app.repository.node.RuNode;
import app.repository.node.RuNodeComposite;
import javafx.util.Pair;
import java.awt.*;
import java.io.*;
import java.util.List;



public class RuSerImpl implements  RuSerilization {
    private  SlotSaver slotSaver;
    private SlotReader slotReader;
    private String currentWorkspaceDir;

    public RuSerImpl() {
        this.slotSaver=new SlotSaver();
        this.slotReader =new SlotReader();
        currentWorkspaceDir = this.currWorkspace();
    }

    @Override
    public File chooseSlotImage(String[]args) { return slotSaver.slotImageChooser(args); }

    @Override
    public File saveSlot(List<String> args, List<CharType> charType, String[] name) { return slotSaver.saveInc(args,charType,name); }

    @Override
    public List<Pair<String,CharType>> readSlotFile(File file) {
        return slotReader.readFromFile(file);
    }
    @Override
    public Image readSlotImage(File file) { return slotReader.readImage(file); }


    @Override
    public void saveWorkspace() {
        String ruPath = System.getProperty("user.dir");
        RuNodeComposite wsNodeModel = MainFrame.getInstance().getTree().getRootNodeModel();

        String wsPath = ruPath + "/Projects/" + wsNodeModel.getName();
        File wsDir = new File(wsPath);

        File currWorkspace = new File(ruPath + "/CurrentWorkspace.txt");
        if(!currWorkspace.exists()) {
            try {
                currWorkspace.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(wsDir.mkdirs())
            System.out.println("Uspesno kreiranje direktorijuma");
        else
            System.out.println("Direktorijum vec postoji");

        File wsSer = new File(wsPath + "/" + wsNodeModel.getName() + ".ser");
        currentWorkspaceDir = wsPath + "/" + wsNodeModel.getName() + ".ser";

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(currWorkspace));
            bufferedWriter.write(currentWorkspaceDir);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File wsProjects = new File(wsPath + "/" + wsNodeModel.getName() + "Projects.txt");
        if(!wsProjects.exists()) {
            try {
                wsProjects.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{

            BufferedWriter bw = new BufferedWriter(new FileWriter(wsProjects));

            for(RuNode p: wsNodeModel.getChildren()){

                ObjectOutputStream outPrj = null;

                if(((Project)p).getPath()!=null) {

                    String prjPath = ((Project) p).getPath();
                    File projectFile = new File(prjPath);

                    if(!(projectFile.exists())){
                        AppCore.getInstance().getErrorHandler().throwException("Neki od projekata ne postoji");
                        return;
                    }

                    outPrj = new ObjectOutputStream(new FileOutputStream(projectFile));
                    outPrj.writeObject(p);

                    bw.write(prjPath);
                    bw.newLine();

                }
                else {

                    String prjDirStr = wsPath + "/" + p.getName();

                    File prjDir = new File(prjDirStr);
                    prjDir.mkdirs();

                    File prj = new File(prjDirStr + "/" + p.getName() + ".ser");
                    outPrj = new ObjectOutputStream(new FileOutputStream(prj));
                    outPrj.writeObject(p);

                    bw.write(prjDirStr + "/" + p.getName() + ".ser");
                    bw.newLine();
                }

            }

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(wsSer));
//          RuNodeComposite wsNodeModel = (RuNodeComposite) ws.getNodeModel();
            out.writeObject(wsNodeModel);

            bw.close();
            out.close();
        }catch (IOException e){
            System.out.println("Exception");
        }

    }


    @Override
    public void saveProject() {

        RuNode project = MainFrame.getInstance().getTree().getSelectedItem();
        if(!(project instanceof Project)){
            AppCore.getInstance().getErrorHandler().throwException("Niste odabrali projekat");
            return;
        }

        try {

            ObjectOutputStream out = null;
            String path;

            if (((Project) project).getPath() != null) {

                path = ((Project) project).getPath();
                File prjFile = new File(path);

                if(!(prjFile.exists())){
                    AppCore.getInstance().getErrorHandler().throwException("Projekat je obrisan sa kompjutera");
                    return;
                }

                out = new ObjectOutputStream(new FileOutputStream(prjFile));
                out.writeObject(project);

            } else {

                path = System.getProperty("user.dir") + "/Projects/Workspace/" + project.getName() + "/" + project.getName() + ".ser";
                String dir = System.getProperty("user.dir") + "/Projects/Workspace/" + project.getName();

                File prjFile = new File(path);
                File prjDir = new File(dir);

                prjDir.mkdirs();

                if(!(prjFile.exists())){
                    prjFile.createNewFile();
                }

                out = new ObjectOutputStream(new FileOutputStream(prjFile));
                out.writeObject(project);

            }

            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    public void saveAsProject(File file) {

        RuNodeComposite projectNode = (RuNodeComposite) MainFrame.getInstance().getTree().getSelectedItem();
        MainFrame.getInstance().getTree().setProjectName(file.getName().substring(0, file.getName().indexOf(".")));
        ((Project)projectNode).setPath(file.getAbsolutePath());

        try {

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(projectNode);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void open(File file, String string){

        try {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            RuNodeComposite node = (RuNodeComposite) in.readObject();

            if(node instanceof Workspace && string.equals("Workspace")){
                MainFrame.getInstance().getTree().setRoot((Workspace)node);
                if(((Workspace)node).getOpened()!=null){
                    MainFrame.getInstance().getDesktop().addProject(((Workspace)node).getOpened());
                }
            }

            else if(node instanceof Project && string.equals("Project")){
                MainFrame.getInstance().getTree().openProject((Project)node);
                ((Project)node).setPath(file.getAbsolutePath().toString());
            }

            else{
                AppCore.getInstance().getErrorHandler().throwException("Niste odabrali dobar fajl");
            }

            in.close();

        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

    }


    @Override
    public String getCurrentWorkspaceDir() {
        return currentWorkspaceDir;
    }


    private String currWorkspace(){
        String ruPath = System.getProperty("user.dir");
        File file = new File(ruPath + "/CurrentWorkspace.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            br.close();
            return str;

        } catch (Exception e) {
            System.out.println("Fajl nije pronadjen");
            return null;
        }

    }
}
