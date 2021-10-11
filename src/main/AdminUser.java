public class AdminUser extends User{
    public AdminUser(String username, String password){
        super(username, password);
    }

    @Override
    public AdminUser create_account(String username, String password){
        // override class User's method create_account
    }

}
