import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import java.util.ArrayList;

public class NormalInputProcessor {
    // delete or add movie(Admin User), call MovieManger
    static private ReviewManager rev_mana;
    static private MovieManager mov_mana;
    static private UserManager user_mana;
    private User curr_u;

    public NormalInputProcessor() throws IOException {
        rev_mana = new ReviewManager();
        mov_mana = new MovieManager();
        user_mana = new UserManager();
    }

    /**
     * Return true if the given string (username or password) is alphanumeric, also it is nonempty.
     */
    private boolean is_nonemptyalphanumeric(String s) {
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
     * Return false if the given string (username) is used in the same usertype before.
     * Return true if the given string (username) is available to use.
     */
    private boolean un_canbeused(String s, String usertype) {
        List<String> listofadmname = user_mana.get_listadminusername();
        List<String> listofnormname = user_mana.get_listnormalusername();
        if (Objects.equals(usertype, "A")) {
            for (String eachadmname: listofadmname) {
                if (Objects.equals(s, eachadmname)) {
                    return false;
                }
            }
        }
        else {
            for (String eachnormname: listofnormname) {
                if (Objects.equals(s, eachnormname)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * create normal user iff the provided username and password is legal
     * username and password must only contain numbers or letters, and username must be unique
     */
    public boolean register(String un, String pass) {
        if (! this.is_nonemptyalphanumeric(un)){
            return false;
        }
        else if (! this.is_nonemptyalphanumeric(pass)){
            return false;
        }
        // check if the usename is already used by other users
        else if (! this.is_nonemptyalphanumeric(pass)){
            return false;
        }
        else {
            user_mana.create_normaluser(un, pass);
            return true;
        }
    }


    public boolean login(String un, String pass) {

    }

    /**
     * return a list of movie names that contains the given string s.
     */
    public ArrayList<String> search(String s){

    }

    /**
     * when given a String of the normal user's username, return an
     * array [username, contact info, playlist]
     */
    public Arrays profile_page(String s){

    }


    /**
     * when given a String of the movie's name, return an
     * array [movie name, movie link, reviews, number of likes]
     */
    public Arrays movie_profile(String s){

    }


    /**
     * when given a String of the movie's name, return an
     * array [movie name, movie link, reviews, number of likes]
     */
    public void write_review(String rev_content){

    }


}