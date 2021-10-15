import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import java.util.ArrayList;

public class AdminInputProcessor {
    static private MovieManager mov_mana;
    static private UserManager user_mana;
    final private String ADMINCODE = "123456";

    public AdminInputProcessor() throws IOException {
        mov_mana = new MovieManager();
        user_mana = new UserManager();
    }

    /**
     * Return true if the given string (username or password) is alphanumeric,
     * also it is nonempty.
     */
    private boolean is_nonemptyalpnum(String s) {
        if (s.length() < 1) {
            return false;
        }
        for (int i = 0; i < s.length(); i ++) {
            if (! (Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * create admin user iff the provided username and password is legal
     * username and password must be non-empty, only contain numbers or letters,
     * and username must be unique among all the normal users,
     * also the code should be correct.
     */
    public boolean register(String un, String pass, String code) throws IOException {
        if (! this.is_nonemptyalpnum(un)){
            return false;
        }
        else if (! this.is_nonemptyalpnum(pass)){
            return false;
        }
        // check if the admincode is correct
        else if (!Objects.equals(code, ADMINCODE)) {
            return false;
        }
        // check if the username is already used by other users
        else if (! user_mana.usernameIfUnique(un, "AdminUser")){
            return false;
        }
        else {
            user_mana.create_adminuser(un, pass);
            return true;
        }
    }

    /**
     * If un exists, (un, pass) match and admincode is correct,
     * record the un and return true.
     */
    public boolean login(String un, String pass, String code) {
        if (!Objects.equals(code, ADMINCODE)) {
            return false;
        }
        if (user_mana.userIfExist(un, pass, "AdminUser")) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Given a String of moviename and a String of movielink,
     * return true if those strings are non-empty and the movie is not uploaded before,
     * and can be uploaded.
     */
    public boolean upload_movie(String moviename, String movielink) throws IOException {
        if (moviename.length() < 1 & movielink.length() < 3) {
            return false;
        }
        if (mov_mana.get_movie(moviename) != null) {
            return false;
        }
        mov_mana.add_movie(moviename, movielink);
        return true;
    }


    /**
     * Given a String called moviename,
     * return true if the movie exists in the platform and can be deleted.
     */
    public boolean delete_movie(String moviename) {
        if (mov_mana.get_movie(moviename) == null) {
            return false;
        }
        mov_mana.delete_movie(moviename);
        return true;
    }
}
