import java.io.IOException;

abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
//    abstract void create_account(String username, String password) throws IOException;

    // create a new account and write into file, it should be only called when UserManger considers the username
    // and password is valid. Thus, Input are all valid.
}
