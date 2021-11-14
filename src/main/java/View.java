import javax.swing.*;

public abstract class View {
    protected static final InstanceMain IM = new InstanceMain();
    protected static String userName = "";

    private final JFrame frame = new JFrame();

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
