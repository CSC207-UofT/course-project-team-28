package Entity.User;

/**
 * Parent abstract class of all kind of users
 */
abstract class User {

    protected String username;
    protected String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * getter
     */
    abstract Object[] getObject();
    abstract String getUsername();
    abstract String getUserPassword();
}
