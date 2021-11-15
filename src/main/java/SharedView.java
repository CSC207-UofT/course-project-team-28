public abstract class SharedView extends View {
    protected boolean isAdmin;

    public SharedView(View previous, boolean isAdmin) {
        super(previous);
        this.isAdmin = isAdmin;
    }
}
