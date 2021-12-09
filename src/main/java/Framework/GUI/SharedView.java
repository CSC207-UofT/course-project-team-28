package Framework.GUI;

/**
 SharedView is the child class of View.
 */
public abstract class SharedView extends View {
    protected final boolean isAdmin;

    /**
     *
     * @param previous the previous page
     * @param isAdmin the boolean to check whether it is an admin user.
     */
    public SharedView(View previous, boolean isAdmin) {
        super(previous);
        this.isAdmin = isAdmin;
    }
}
