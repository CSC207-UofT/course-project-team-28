import java.io.*;

public class AdminUser extends User{

    public AdminUser(String username, String password) throws IOException {
        super(username, password);
        WriteUser wu = new WriteUser();
        wu.create_file(this);
    }


}
