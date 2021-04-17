package app.serilizacija;

import app.gui.swing.desktop.slotView.CharType;
import javafx.util.Pair;
import java.awt.*;
import java.io.File;
import java.util.List;

public interface RuSerilization {
    File chooseSlotImage(String[]args);
    File saveSlot(List<String> args, List<CharType> charType, String[] name);
    List<Pair<String,CharType>> readSlotFile(File file);
    Image readSlotImage(File file);
    void saveWorkspace();
    void open(File file, String string);
    String getCurrentWorkspaceDir();
    void saveProject();
    void saveAsProject(File file);
}
