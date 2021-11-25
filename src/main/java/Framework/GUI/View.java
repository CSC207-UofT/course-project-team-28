package Framework.GUI;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;

import javax.swing.*;

public abstract class View {
    protected final View previous;
    private final JFrame frame = new JFrame();
    WriteUser writeUser = new WriteUser();
    WriteMovie writeMovie = new WriteMovie();
    WriteReview writeReview = new WriteReview();
    //InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);

    protected static String userName = "";

    public View(View previous) {
        this.previous = previous;
    }


    protected abstract void UpdateText();

    protected JFrame getFrame() {
        return frame;
    }

    protected void nextView(View view, boolean disposeCurrent) {
        JFrame nextFrame = view.getFrame();
        nextFrame.setLocationRelativeTo(null);
        nextFrame.setVisible(true);
        if (disposeCurrent) {
            frame.dispose();
        }
    }

}
