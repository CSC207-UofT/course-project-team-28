abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    abstract Object[] getObject();
    abstract String getUsername();
    abstract String getUserPassword();
}
