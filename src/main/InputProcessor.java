public class InputProcessor {
    // delete or add movie(Admin User), call MovieManger
    static private ReviewManager rev_mana;
    static private MovieManager mov_mana;
    static private UserManager user_mana;

    private boolean is_alphanumeric(String s) {
        for (int i = 0; i < s.length(); i ++) {
            if (! ((char) s[i].isAlphabetic() / (char) s[i].isDigit())) {
                return false;
            }
        }
        return true;
    }
}
