import javax.swing.*;

public abstract class View {
    protected final View previous;
    private final JFrame frame = new JFrame();
    protected static final InstanceMain IM = new InstanceMain();
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
    };

}
