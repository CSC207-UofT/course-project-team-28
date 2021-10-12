import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class NormalUser extends User{
    protected String contactinfo;
    protected ArrayList<String> playlist;

    public FileReader userreader;
    public BufferedReader userlogin;
    static FileWriter writeuser;

    public NormalUser(String username, String password) throws IOException {
        super(username, password);
        this.contactinfo = "";
        this.playlist = new ArrayList<String>();

        Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        writeuser = new FileWriter(str1.toString() + "\\main\\NormalUser\\" + this.username + ".txt");
        writeuser.write(username);
        writeuser.write("\r\n");
        writeuser.write(password);
        writeuser.write("\r\n");
        writeuser.write("c");
        writeuser.write("\r\n");
        writeuser.write("[]");
        writeuser.close();
    }

//    public void create_account(String username, String password) throws IOException {
//        // write new user in file
//        NormalUser user = new NormalUser(username, password);
//
//        writeuser = new FileWriter("NormalUser/" + username + ".txt");
//        writeuser.write(username);
//        writeuser.write("\r\n");
//        writeuser.write(password);
//        writeuser.write("\r\n");
//        writeuser.write("c");
//        writeuser.write("\r\n");
//        writeuser.write("[]");
//        writeuser.close();
//
//    }
    public boolean give_like(String moivename) throws IOException {

        Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        userreader = new FileReader(str1.toString() + "\\main\\NormalUser\\" + this.username + ".txt");
        userlogin = new BufferedReader(userreader);

        this.playlist.add(moivename);

        ArrayList<String> lst = new ArrayList<String>();
        String line = userlogin.readLine();
        while(line !=null){
            lst.add(line);
            line = userlogin.readLine();
        }
        userlogin.close();
        if (lst.get(3).equals("[]")){
            lst.set(3, lst.get(3).replace("[]","[" + moivename + "]"));
        }
        else if(!lst.get(3).contains(moivename)){
            lst.set(3, lst.get(3).replace("]",", " + moivename + "]"));
        }

        writeuser = new FileWriter(str1.toString() + "\\main\\NormalUser\\" + this.username + ".txt");
        for(String str: lst){
            writeuser.write(str);
            writeuser.write("\r\n");
        }
        writeuser.close();
        return lst.get(3).contains(moivename);
    }

}
