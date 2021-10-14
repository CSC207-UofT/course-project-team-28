import java.io.*;
import java.util.ArrayList;


/**
 * Called for read and write user's file.
 * it should be only called by User class and its subclass.
 */
public class NormalUser extends User{

    protected String contactinfo;
    protected ArrayList<String> playlist;
    protected WriteUser wu = new WriteUser();


    /**
     * pass username and password of username to create a new account
     * it should call WriteUser Class to create a new file for user.
     */
    public NormalUser(String username, String password, String contactinfo, ArrayList<String> playlist) throws IOException {
        super(username, password);
        this.contactinfo = contactinfo;
        this.playlist = playlist;
        wu.create_file(this);

    }

    @Override
    public Object[] getObject() {
        Object[] nu = new Object[4];
        nu[0] = this.username;
        nu[1] = this.password;
        nu[2] = this.contactinfo;
        nu[3] = this.playlist;

        return nu;
    }

    public String getusername(){
        return this.username;
    }

    public String getuserpassword(){
        return this.password;
    }


}
