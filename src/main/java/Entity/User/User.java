package Entity.User;

/**
 * Parent abstract class of all kind of users
 */
abstract class User {

    protected final String username;
    protected final String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

}
