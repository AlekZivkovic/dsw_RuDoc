package app.gui.swing.controller;




public class ActionManager {

    private NewAction newAction;
    private AboutAction aboutAction;
    private DeleteAction deleteAction;
    private ShareAction shareAction;
    private  PRectangleAction pRectangleAction;
    private  PTriangleAction pTriangleAction;
    private  PCircleAction pCircleAction;
    private  SSelectAction sSelectAction;
    private  SDeleteAction sDeleteAction;
    private SMoveAction sMoveAction;
    private SRotateAction sRotateAction;
    private SResizeAction sResizeAction;
    private SUndoRotateAction sUndoRotateAction;
    private SOpenAction sOpenAction;
    private SaveWorkspaceAction saveWorkspaceAction;
    private OpenWorkspaceAction openWorkspaceAction;
    private OpenProjectAction openProjectAction;
    private SaveAsProjectAction saveAsProjectAction;
    private SaveProjectAction saveProjectAction;
    private UndoAction undoAction;
    private RedoAction redoAction;

    public ActionManager() {

        initialiseActions();
    }

    private void initialiseActions() {
        newAction = new NewAction();
        aboutAction=new AboutAction();
        deleteAction = new DeleteAction();
        shareAction = new ShareAction();
        pRectangleAction=new PRectangleAction();
        pTriangleAction=new PTriangleAction();
        pCircleAction=new PCircleAction();
        sSelectAction=new SSelectAction();
        sDeleteAction=new SDeleteAction();
        sMoveAction = new SMoveAction();
        sRotateAction = new SRotateAction();
        sResizeAction = new SResizeAction();
        sUndoRotateAction = new SUndoRotateAction();
        sOpenAction= new SOpenAction();
        saveWorkspaceAction = new SaveWorkspaceAction();
        openWorkspaceAction = new OpenWorkspaceAction();
        openProjectAction = new OpenProjectAction();
        saveAsProjectAction = new SaveAsProjectAction();
        saveProjectAction = new SaveProjectAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();

    }

    public PTriangleAction getpTriangleAction() { return pTriangleAction; }

    public PCircleAction getpCircleAction() { return pCircleAction; }

    public NewAction getNewAction() {
        return newAction;
    }

    public void setNewAction(NewAction newAction) {
        this.newAction = newAction;
    }

    public AboutAction getAboutAction() {
        return aboutAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(DeleteAction deleteAction) {
        this.deleteAction = deleteAction;
    }

    public PRectangleAction getpRectangleAction() {
        return pRectangleAction;
    }

    public SSelectAction getsSelectAction() {
        return sSelectAction;
    }

    public SDeleteAction getsDeleteAction() { return sDeleteAction; }

    public ShareAction getShareAction() {
        return shareAction;
    }

    public SMoveAction getsMoveAction() {
        return sMoveAction;
    }

    public SRotateAction getsRotateAction() {
        return sRotateAction;
    }

    public SResizeAction getsResizeAction() {
        return sResizeAction;
    }

    public SUndoRotateAction getsUndoRotateAction() {
        return sUndoRotateAction;
    }

    public SOpenAction getsOpenAction() { return sOpenAction; }

    public void setsUndoRotateAction(SUndoRotateAction sUndoRotateAction) {
        this.sUndoRotateAction = sUndoRotateAction;
    }

    public SaveWorkspaceAction getSaveWorkspaceAction() {
        return saveWorkspaceAction;
    }

    public OpenWorkspaceAction getOpenWorkspaceAction() {
        return openWorkspaceAction;
    }

    public OpenProjectAction getOpenProjectAction() {
        return openProjectAction;
    }

    public SaveAsProjectAction getSaveAsProjectAction() {
        return saveAsProjectAction;
    }

    public SaveProjectAction getSaveProjectAction() {
        return saveProjectAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }
}
