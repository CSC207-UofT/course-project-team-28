package InterfaceAdapter;

import java.util.Objects;

public class AdminInputProcessor {
    final private String ADMINCODE = "123456";

    /**
     *
     * @param s: String
     * @return true if the given string (username or password) is alphanumeric,
     *      * also it is nonempty.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isNonEmptyAlpNum(String s) {
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
     *
     * @param userName: String
     * @param password: String
     * @param code: String
     * @return true if all conditions are met: the provided username and password is legal
     *      * username and password must be non-empty, only contain numbers or letters,
     *      * and username must be unique among all the normal users,
     *      * also the code should be correct
     */
    public boolean register(String userName, String password, String code) {
        if (! this.isNonEmptyAlpNum(userName)){
            return false;
        }
        else if (! this.isNonEmptyAlpNum(password)){
            return false;
        }
        // check if the admincode is correct
        else if (!code.equals(ADMINCODE)) {
            return false;
        }
        // check if the username is already used by other users
        else if (! InstanceMain.getUserManager().usernameIfUnique(userName, "AdminUser")){
            return false;
        }
        return InstanceMain.getUserManager().createNewAdminuser(userName, password);
    }


    /**
     *
     * @param userName: String
     * @param password: String
     * @param code: String
     * @return true if the user exists, (userName, password) matches, and code is correct
     */
    public boolean login(String userName, String password, String code) {
        if (!Objects.equals(code, ADMINCODE)) {
            return false;
        }
        return InstanceMain.getUserManager().userIfExist(userName, password, "AdminUser");
    }


    /**
     *
     * @param movieName: String
     * @param movieLink: String
     * @param category: String
     * @return true if those strings are non-empty and the movie is not uploaded before,
     *      * and can be uploaded.
     */
    public boolean uploadMovie(String movieName, String movieLink, String category) {
        if (movieName.length() < 1 | movieLink.length() < 3) {
            return false;
        }
        if (InstanceMain.getMovieManager().IfMovieExist(movieName, movieLink)) {
            return false;
        }
        return InstanceMain.getMovieManager().addNewMovie(movieName, movieLink, category);
    }
}
