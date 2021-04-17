package app.command;

import app.core.PublisherString;
import app.core.SubscriberString;
import app.repository.Page;

public interface CommandManagerInterface extends PublisherString {

    void addCommand(CommandInterface command);
    void doCommand();
    void undoCommand();
    void setUndoRedo();
    void deleteActions(Page page);

}
