package Framework.GUI;

public abstract class SharedView extends View {
    /*
    SharedView is the child class of View.
     */
    protected boolean isAdmin;

    public SharedView(View previous, boolean isAdmin) {
        super(previous);
        this.isAdmin = isAdmin;
    }
}
