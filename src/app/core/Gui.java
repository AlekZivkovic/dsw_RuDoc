package app.core;

import app.gui.swing.choosers.ChooserType;
import app.gui.swing.choosers.Choosers;

import java.io.File;

public interface Gui {
    void start();
    File getChooser(ChooserType chooserType);
}
