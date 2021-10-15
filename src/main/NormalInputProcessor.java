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
    private String curr_nuname;

    public NormalInputProcessor() throws IOException {
        rev_mana = new ReviewManager();
        mov_mana = new MovieManager();
        user_mana = new UserManager();
    }

    /**
     * Return true if the given string (username or password) is alphanumeric,
     * also it is nonempty.
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
     * create normal user iff the provided username and password is legal
     * username and password must be non-empty, only contain numbers or letters,
     * and username must be unique among all the normal users.
     */
    public boolean register(String un, String pass) throws IOException {
        if (! this.is_nonemptyalphanumeric(un)){
            return false;
        }
        else if (! this.is_nonemptyalphanumeric(pass)){
            return false;
        }
        // check if the username is already used by other users
        else if (! user_mana.usernameIfUnique(un, "NormalUser")){
            return false;
        }
        else {
            user_mana.create_normaluser(un, pass);
            return true;
        }
    }


    /**
     * If un exists and (un, pass) match, record the un and return true.
     */
    public boolean login(String un, String pass) {
        if (user_mana.userIfExist(un, pass, "NormalUser")) {
            curr_nuname = un;
            return true;
        }
        else {
            return false;
        }
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
    public Arrays profile_page(String username){

    }


    /**
     * when given a String of the movie's name, return an
     * array [movie name, movie link, reviews, number of likes]
     */
    public Arrays movie_profile(String moviename){

    }


    /**
     * when given a String of the movie's name, return an
     * array [movie name, movie link, reviews, number of likes]
     */
    public Arrays write_review(String rev_content) {

    }


    /**
     * Given a String moviename, return an array [normal username, movie name]
     */
    public Arrays like_movie(String moviename) {

    }


    /**
     * Given a String newinfo,
     * return an array [normal username, newnumber]
     */
    public Arrays edit_profile(String newinfo) {

    }

    /**
     * Given a String of moviename,
     * return an array [normal username, movie name]
     */
    public Arrays undo_like(String moviename) {

    }


}