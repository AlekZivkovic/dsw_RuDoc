package app.gui.swing.view;


import app.AppCore;
import app.core.Repository;
import app.gui.swing.controller.ActionManager;
import app.gui.swing.desktop.RuDesktop;
import app.gui.swing.desktop.view.RuDesktopImplementation;
import app.gui.swing.dialogs.RuJDialog;
import app.gui.swing.dialogs.RuJDialogImplementation;
import app.gui.swing.tree.RuTree;
import app.gui.swing.tree.view.RuTreeImplementation;
import app.gui.swing.view.Frames.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {


    private static MainFrame instance;
    private ActionManager actionManager;
    private Repository documentRepository;
    private RuTree tree;
    private JMenuBar menu;
    private JToolBar toolBar;
    private JTree workspaceTree;
    private RuDesktop desktop;
    private JTabbedPane workspaceDesktop;
    private RuJDialog dialogs;
    private ProjectBar projectBar;
    private FrameInterface exceptionFrame;
    private FrameInterface shareFrame;
    private FrameInterface openFrame;
    private FrameInterface closeRuDokFrame;
    private FrameInterface openRuDokFrame;
    private FrameInterface saveAsFrame;

    private MainFrame(){

    }

    private void initialise() {
        actionManager = new ActionManager();
        exceptionFrame = new ExceptionFrame();
        dialogs=new RuJDialogImplementation();
        shareFrame = new ShareFrame();
        openFrame = new OpenFrame();
        openRuDokFrame = new OpenRuDokFrame();
        closeRuDokFrame = new CloseRuDokFrame();
        saveAsFrame = new SaveAsFrame();

        exceptionFrame.subscribe(AppCore.getInstance().getErrorHandler());
    }

    public void initialiseWorkspaceTree() {
        tree = new RuTreeImplementation();
        workspaceTree = tree.generateTree(documentRepository.getWorkspace());
        initialiseGUI();
    }
    public  void initialiseDesktop(){
        desktop=new RuDesktopImplementation();
        workspaceDesktop=desktop.generateDesktop(null);

    }

    private void initialiseGUI() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
       // int screenHeight = screenSize.height;
       // int screenWidth = screenSize.width;
        Long l=new Long((long) (screenSize.width / 1.2));
        Long l1=new Long((long) (screenSize.height / 1.2));
        setSize( l.intValue(), l1.intValue());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("RuDok app");

        menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new Toolbar();
        projectBar=new ProjectBar();
        add(toolBar, BorderLayout.NORTH);
        //dodajSubs();
        add(projectBar,BorderLayout.SOUTH);


        JScrollPane scroll=new JScrollPane(workspaceTree);
        scroll.setMinimumSize(new Dimension(200,150));



        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,workspaceDesktop);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                openRuDokFrame.showFrame();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                closeRuDokFrame.showFrame();
            }
        });

    }

//    private void dodajSubs() {
//        tree.addSubscriber(desktop);
//    }

    public static MainFrame getInstance(){
        if(instance==null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }
    public void setDocumentRepository(Repository documentRepository) {
        this.documentRepository = documentRepository;
    }
    public JTree getWorkspaceTree() {
        return workspaceTree;
    }
    public void setWorkspaceTree(JTree workspaceTree) {
        this.workspaceTree = workspaceTree;
    }
    public RuTree getTree() {
        return tree;
    }
    public RuDesktop getDesktop() { return desktop; }

    public JTabbedPane getWorkspaceDesktop() {
        return workspaceDesktop;
    }

    public RuJDialog getDialogs() {
        return dialogs;
    }

    public ProjectBar getProjectBar() { return projectBar; }

    public FrameInterface getShareFrame() {
        return shareFrame;
    }

    public void setShareFrame(ShareFrame shareFrame) {
        this.shareFrame = shareFrame;
    }

    public FrameInterface getOpenFrame() {
        return openFrame;
    }

    public FrameInterface getCloseRuDokFrame() {
        return closeRuDokFrame;
    }

    public FrameInterface getOpenRuDokFrame() {
        return openRuDokFrame;
    }

    public FrameInterface getSaveAsFrame() {
        return saveAsFrame;
    }

    //    public void showException(String string){
//        exceptionFrame.show(errorHandler.throwException(string));
//    }
}
