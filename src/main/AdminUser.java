import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class AdminUser extends User{
    static FileReader userreader = new FileReader("AdminUser.txt");;
    static BufferedReader userlogin = new BufferedReader(userreader);
    static FileWriter writeuser = new FileWriter("AdminUser.txt", true);


    public AdminUser(String username, String password){
        super(username, password);
    }


    public void create_account(String username, String password){
        AdminUser user = new AdminUser(username, password);

        writeuser = new FileWriter("AdminUser/" + username + ".txt");
        writeuser.write(username);
        writeuser.write("\r\n");
        writeuser.write(password);

        writeuser.close();

        return user;
    }

}
