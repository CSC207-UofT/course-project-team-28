import javax.swing.*;

public abstract class View {
    protected abstract JFrame getFrame();
    protected abstract void nextView(View view);

}