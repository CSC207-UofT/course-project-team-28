public class NormalUser extends User{
    public NormalUser(String username, String password){
        super(username, password);
    }

    @Override
    public NormalUser create_account(String username, String password){
        // override class User's method create_account
    }

}
