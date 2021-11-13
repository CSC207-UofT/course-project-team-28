public abstract class SharedView extends View {
    protected boolean isAdmin;

    public SharedView(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
