package app.command;

import app.repository.Page;

import java.util.ArrayList;

public interface CommandInterface {

    void doCommand();
    void undoCommand();
    Page getPage();

}
