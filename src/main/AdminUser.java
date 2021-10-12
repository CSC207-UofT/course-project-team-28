import java.io.*;

public class AdminUser extends User{
    public FileReader userreader = new FileReader("AdminUser.txt");;
    public BufferedReader userlogin = new BufferedReader(userreader);
    public FileWriter writeuser = new FileWriter("AdminUser.txt", true);


    public AdminUser(String username, String password) throws IOException {
        super(username, password);

        writeuser = new FileWriter("C:\\Users\\MOKILA\\Desktop\\csc207\\207project\\src\\main\\AdminUser\\" + username + ".txt");
        writeuser.write(username);
        writeuser.write("\r\n");
        writeuser.write(password);

        writeuser.close();
    }


//    public void create_account(String username, String password) throws IOException {
//        AdminUser user = new AdminUser(username, password);
//
//        writeuser = new FileWriter("AdminUser/" + username + ".txt");
//        writeuser.write(username);
//        writeuser.write("\r\n");
//        writeuser.write(password);
//
//        writeuser.close();
//
//    }

}
