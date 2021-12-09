package Framework.GUI;

import javax.swing.*;

/**
View is the fundamental super class of all GUI-related classes.
 */
public abstract class View {

    protected final View previous;
    private final JFrame frame = new JFrame();

    protected static String userName = "";

    public View(View previous) {
        this.previous = previous;
    }

    /**
     * to update text.
     */
    protected abstract void UpdateText();

    /**
     * @return the frame
     */
    protected JFrame getFrame() {
        return frame;
    }

    /**
    The method next View aims to switch the different windows of our program and decide whether the previous window
     needs to be disposed or not.
     */
    protected void nextView(View view, boolean disposeCurrent) {
        JFrame nextFrame = view.getFrame();
        nextFrame.setLocationRelativeTo(null);
        nextFrame.setVisible(true);
        if (disposeCurrent) {
            frame.dispose();
        }
    }

}
