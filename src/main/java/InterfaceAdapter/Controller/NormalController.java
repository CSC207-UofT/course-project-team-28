package InterfaceAdapter.Controller;

import UseCase.CoinManager;
import UseCase.MovieManager;
import UseCase.ReviewManager;
import UseCase.UserManager;

public class NormalController {
    protected String currNormalName;
    // delete or add movie(Admin Core.User), call MovieManger

    public void setCurrNormalName(String currNuname) {
        this.currNormalName = currNuname;
    }

    /**
     * Return true if the given string (username or password) is alphanumeric,
     * also it is nonempty.
     */
    public boolean isNonemptyalphanumeric(String s) {
        if (s.length() < 1) {
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
