package Framework.GUI;

public abstract class SharedView extends View {
    /*
    SharedView is the child class of View.
     */
    protected boolean isAdmin;

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
