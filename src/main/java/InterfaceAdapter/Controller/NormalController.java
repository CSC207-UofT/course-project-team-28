package InterfaceAdapter.Controller;

/**
 * NormalController (InterfaceAdapter)
 * Parent class of all normal user controllers.
 */
public class NormalController {
    protected String currNormalName;


    /**
     * @param s the string it checks
     * @return true if the given string (username or password) is alphanumeric,
     *         also it is nonempty.
     */
    public boolean isNonEmptyAlphanumeric(String s) {
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!(Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
