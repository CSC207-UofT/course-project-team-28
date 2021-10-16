import java.io.IOException;
import java.util.Arrays;

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
     * Auto-login if registered successfully
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
            login(un, pass);
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
     * check if the moviename exists
     * @return ture iff the movie exists
     */
    public boolean if_movie_exist(String moviename){
        if (mov_mana.get_movie(moviename) == null) {
            return false;
        };
        return true;
    }

    /**
     * Should be only called when the movie name <mn> exists in the data base
     * @return an arraylist [movie name, movie link, reviews, number of likes], where movie name
     *         and movie link are strings, reviews is [String of review 1 of the moive,
     *         String of review 2 of the movie, ...]
     */
    public String search(String mn){
        return mov_mana.get_movieprofile(mn);
    }


    /**
     * Should be only called when the movie name <mn> exists in the data base
     * @return an arraylist [movie name, movie link, reviews, number of likes], where movie name
     *         and movie link are strings, reviews is [String of review 1 of the moive,
     *         String of review 2 of the movie, ...]
     */
    public String movie_profile(String moviename){
        return mov_mana.get_movieprofile(moviename);
    }


    /**
     * when given a String of the normal user's username, return an
     * arraylist [username, contact info, playlist]
     */
    public ArrayList<Object> profile_page(String username){
        ArrayList<Object> newarray = new ArrayList<Object>();
        Object[] wholelist = user_mana.getUserInfoList(username, "NormalUser");
        newarray.add(wholelist[0]);
        newarray.add(wholelist[2]);
        newarray.add(wholelist[3]);
        return newarray;
    }


    /**
     * add a review when provided with moviename of the movie and review content
     * return ture iff a review is successfully added. false otherwise
     */
    public boolean write_review(String moviename, String rev_content) throws IOException {
        mov_mana.add_review_to_movie(curr_nuname, moviename, rev_content);
        return rev_mana.write_review(this.curr_nuname, moviename, rev_content);
    }


    /**
     * Should be only called when the movie name <moviename> exists in the data base
     * Given a String moviename, add like.
     * return ture iff added successfully.
     */
    public boolean like_movie(String moviename) throws IOException {
        if (user_mana.give_like(this.curr_nuname, moviename)){
            mov_mana.like_movie(moviename);
            return true;
        }
        else {
            user_mana.undo_like(this.curr_nuname, moviename);
            return false;
        }
    }


    /**
     * Given a String moviename, undo like.
     * return ture iff added successfully.
     */
    public boolean undo_like(String moviename) throws IOException {
        Object[] user_info = user_mana.getUserInfoList(curr_nuname, "NormalUser");
        ArrayList<String> user_playlist = (ArrayList<String>) user_info[3];
        if (user_playlist.contains(moviename)){
            user_mana.undo_like(this.curr_nuname, moviename);
            mov_mana.undolike_movie(moviename);
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Given a String newinfo, update the user's profile.
     * return ture iff updated successfully.
     */
    public boolean edit_profile(String newinfo) throws IOException {
        return user_mana.update_info(this.curr_nuname, newinfo);
    }

}