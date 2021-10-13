public class InputProcessor {
    // delete or add movie(Admin User), call MovieManger
    static private ReviewManager rev_mana;
    static private MovieManager mov_mana;
    static private UserManager user_mana;

    public InputProcessor(){
        rev_mana = new ReviewManager();
        mov_mana = new MovieManager();
        user_mana = new UserManager();
    }

    private boolean is_alphanumeric(String s) {
        for (int i = 0; i < s.length(); i ++) {
            if (! (Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * create normal user iff the provided username and password is legal
     * username and password must only contain numbers or letters, and username must be unique
     */
    public boolean create_norm_user(String un, String pass) {
        if (! this.is_alphanumeric(un)){
            return false;
        }
        else if (! this.is_alphanumeric(pass)){
            return false;
        }
        else if (! this.is_alphanumeric(pass)){
            return false;
        }
        else{

        }
    }


}
