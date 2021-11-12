import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import java.util.ArrayList;

public class NormalInputProcessor {
    // delete or add movie(Admin User), call MovieManger
    private ReviewManager rev_mana;
    private MovieManager mov_mana;
    private UserManager user_mana;
    private String curr_nuname;


    public void setRev_mana(FileInfoGateway gw){
        this.rev_mana = gw.getRM();
    }

    public void setMov_mana(FileInfoGateway gw){
        this.mov_mana = gw.getMM();
    }
    public void setUser_mana(FileInfoGateway gw){
        this.user_mana = gw.getUM();
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
    //TODO
    public boolean register(String un, String pass) {
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
        }
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
    //TODO: should add new User profiles info on profile page
    public ArrayList<Object> profile_page(String username){
        ArrayList<Object> newarray = new ArrayList<>();
        Object[] wholelist = user_mana.getUserInfoList(username, "NormalUser");
        newarray.add(wholelist[0]);
        newarray.add(wholelist[2]);
        newarray.add(wholelist[3]);
        return newarray;
    }


    /**
     * Should be only called when the movie name <moviename> exists in the data base
     * Given a String moviename, add like.
     * return ture iff added successfully.
     */
    public boolean like_movie(String moviename) {
        if (user_mana.give_like(this.curr_nuname, moviename)){
            mov_mana.like_movie(moviename);
            return true;
        }
        else {
            //user_mana.undo_like(this.curr_nuname, moviename);
            return false;
        }
    }

    /**
     * return ture iff the user's playlist is empty.
     */
    public boolean empty_playlist() {
        Object[] user_info = user_mana.getUserInfoList(curr_nuname, "NormalUser");
        ArrayList<String> user_playlist = (ArrayList<String>) user_info[6];
        return user_playlist.isEmpty();
    }

    /**
     * Given a String moviename, undo like.
     * return ture iff added successfully.
     */
    public boolean undo_like(String moviename) throws IOException {
        Object[] user_info = user_mana.getUserInfoList(curr_nuname, "NormalUser");
        ArrayList<String> user_playlist = (ArrayList<String>) user_info[6];
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
    //TODO: add new parameter, the corresponding place where call this method in UI need to add onr more parameter
    public boolean edit_profile(String newInfo, String updateType) {
        if (updateType.equals("coin")){
            return user_mana.updateCoin(this.curr_nuname, Integer.parseInt(newInfo));
        }
        else{
            return user_mana.updateInfo(this.curr_nuname, newInfo, updateType);
        }

    }

}