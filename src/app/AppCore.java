package app;


import app.ErrorHandler.ErrorHandlerImpl;
import app.ErrorHandler.ErrorHandlerInterface;
import app.command.CommandManager;
import app.command.CommandManagerInterface;
import app.core.ApplicationFramework;
import app.core.Gui;
import app.core.Repository;
import app.gui.swing.SwingGui;
import app.repository.RepositoryImpl;
import app.repository.SlotHandler.SlotHandlerImpl;
import app.repository.SlotHandler.SlotHandlerInterface;
import app.serilizacija.RuSerImpl;
import app.serilizacija.RuSerilization;

public class AppCore extends ApplicationFramework {

    private static AppCore instance;
    private static Repository repository;
    private ErrorHandlerInterface errorHandler;
    private SlotHandlerInterface slotHandler;
    private RuSerilization ruSerilization;

    private AppCore(){
        errorHandler = new ErrorHandlerImpl();
        slotHandler = new SlotHandlerImpl();
        ruSerilization=new RuSerImpl();
    }

    public static AppCore getInstance(){
        if(instance==null){
            instance = new AppCore();
        }
        return instance;
    }


    public void run(){

        this.gui.start();
    }

    public static void main(String[] args) {
        repository = new RepositoryImpl();
        Gui gui = new SwingGui(repository);
        ApplicationFramework appCore = AppCore.getInstance();
        appCore.initialise(gui, repository);
        appCore.run();

    }

    public ErrorHandlerInterface getErrorHandler() {
        return errorHandler;
    }

    public SlotHandlerInterface getSlotHandler(){
        return slotHandler;
    }

    public RuSerilization getRuSerilization() { return ruSerilization; }

    public Repository getRepository() {
        return repository;
    }

}
