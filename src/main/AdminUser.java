import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class AdminUser extends User{
    public FileReader userreader = new FileReader("Normal.txt");;
    public BufferedReader userlogin = new BufferedReader(userreader);
    public FileWriter writeuser = new FileWriter("Normal.txt", true);


    public AdminUser(String username, String password){
        super(username, password);
    }

    @Override
    public AdminUser create_account(String username, String password){
        AdminUser user = new AdminUser(username, password);

        while(!userlogin.readLine().equals("")) {
            userlogin.readLine();
        }
        userlogin.readLine();
        userlogin.close();
        writeuser.write("U");
        writeuser.write("\r\n");
        writeuser.write("mokila");
        writeuser.write("\r\n");
        writeuser.write("123456");
        writeuser.write("\r\n");
        writeuser.close();

        return user;
    }

}
