import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class NormalUser extends User{
    protected String contactinfo;
    protected ArrayList<String> playlist;

    public FileReader userreader;
    public BufferedReader userlogin;
    static FileWriter writeuser;

    public NormalUser(String username, String password){
        super(username, password);
        this.contactinfo = "";
        this.playlist = new ArrayList<String>();
    }

    public void create_account(String username, String password){
        // write new user in file
        NormalUser user = new NormalUser(username, password);

        writeuser = new FileWriter("NormalUser/" + username + ".txt");
        writeuser.write(username);
        writeuser.write("\r\n");
        writeuser.write(password);
        writeuser.write("\r\n");
        writeuser.write("c");
        writeuser.write("\r\n");
        writeuser.write("m");
        writeuser.close();

    }
    public boolean give_like(String moivename){

        userreader = new FileReader("NormalUser/" + this.username + ".txt");
        userlogin = new BufferedReader(userreader);
        writeuser = new FileWriter("NormalUser/" + this.username + ".txt");

        ArrayList<String> lst = new ArrayList<String>();
        String line = userlogin.readLine();
        while(!line.equals("")){
            lst.add(line);
            line = userlogin.readLine();
        }
        if(!lst[3].contains(moivename)){
            lst = lst[3].replace("]",", " + moivename + "]");
        }
        return lst[3].contains(moivename);
    }

}
