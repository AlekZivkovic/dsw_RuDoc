package app.command;

import app.core.PublisherString;
import app.core.SubscriberString;
import app.gui.swing.view.MainFrame;
import app.repository.Page;

import java.sql.Array;
import java.util.ArrayList;

public class CommandManager implements CommandManagerInterface{

    private ArrayList<CommandInterface> commands = new ArrayList<CommandInterface>();
    private int currentCommand = -1;
    private ArrayList<SubscriberString> subscribers = new ArrayList<SubscriberString>();

    public void addCommand(CommandInterface command){

        while(commands.size()>currentCommand+1){
            commands.remove(commands.size()-1);
        }

        commands.add(command);
        currentCommand++;
        command.doCommand();

        setUndoRedo();

    }

    public void doCommand(){

        try {
            commands.get(++currentCommand).doCommand();
        }catch (Exception e) {
            System.out.println("Promenjen je tab");
        }
        finally{
            setUndoRedo();
        }

    }

    public void undoCommand(){

        try {
            commands.get(currentCommand).undoCommand();
            currentCommand--;
        }catch (Exception e){
            System.out.println("Promenjen je tab");
            this.commands.removeAll(commands);
            this.currentCommand = -1;
        }finally {
            setUndoRedo();
        }

    }

    public void setUndoRedo(){
        if(currentCommand!=-1)
            notifySubscribers("UndoEnable");
        else
            notifySubscribers("UndoDisable");

        if(currentCommand<commands.size()-1)
            notifySubscribers("RedoEnable");
        else
            notifySubscribers("RedoDisable");
    }

    @Override
    public void deleteActions(Page page) {

        int i = this.commands.size()-1;
        while(i>=0){

            Page commandPage = this.commands.get(i).getPage();
            if(commandPage.equals(page) && commandPage.getParent().getParent().equals(page.getParent().getParent())){
                commands.remove(i);
                currentCommand--;
            }

            i--;
        }

    }

    @Override
    public void addSubscriber(SubscriberString subscriber) {
        if(subscriber!=null)
            subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(SubscriberString subscriber) {
        if(subscriber!=null)
            subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String string) {
        for(SubscriberString s:subscribers){
            s.update(string);
        }
    }
}
