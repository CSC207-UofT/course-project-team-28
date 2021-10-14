import java.io.*;

public class AdminUser extends User{

    public AdminUser(String username, String password) throws IOException {
        super(username, password);
        WriteUser wu = new WriteUser();
        wu.create_file(this);
    }

    @Override
    public Object[] getObject() {
        Object[] au = new Object[2];
        au[0] = this.username;
        au[1] = this.password;

        return au;
    }

    public String getusername(){
        return this.username;
    }
    public String getuserpassword(){
        return this.password;
    }

}
