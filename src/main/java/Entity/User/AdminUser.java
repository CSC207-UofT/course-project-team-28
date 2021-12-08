package Entity.User;

/**
 * Represent Admin Core.User.User
 */
public class AdminUser extends User{

    /**
     * create the object for user
     * @param username the user name of user
     * @param password the password of user
     */
    public AdminUser(String username, String password) {
        super(username, password);
    }


    /**
     * @return return all the info of user.
     */
    @Override
    public Object[] getObject() {
        Object[] au = new Object[2];
        au[0] = this.username;
        au[1] = this.password;

        return au;
    }

    /**
     * @return getter of Core.User.AdminUser
     */
    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public String getUserPassword(){
        return this.password;
    }

}