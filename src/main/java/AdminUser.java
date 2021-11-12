public class AdminUser extends User{

    public AdminUser(String username, String password) {
        super(username, password);
    }

    @Override
    public Object[] getObject() {
        Object[] au = new Object[2];
        au[0] = this.username;
        au[1] = this.password;

        return au;
    }

    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public String getUserPassword(){
        return this.password;
    }

}