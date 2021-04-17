package app.gui.swing.view.Frames;

import app.core.SubscriberString;

public interface FrameInterface extends SubscriberString {

    void show(String text);
    void showFrame();

}
