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

    /**
     * Use existed NormalUser Object to call give_like, pass moviename as parameter.
     * it should call WriteUser Class to read and write file.
     */
    public boolean give_like(String moviename) throws IOException {
        this.playlist.add(moviename);

        ArrayList<String> lst1 = new ArrayList<String>();
        lst1 = wu.give_like_readandwrite(moviename, this.username);


        return lst1.get(3).contains(moviename);
    }

    /**
     * create ArrayList of NormalUser and AdminUser [[AdminUser],[NormalUser]]
     */
    public ArrayList<Object> getObject() throws IOException {
        return wu.get_object_from_file();
    }

}
