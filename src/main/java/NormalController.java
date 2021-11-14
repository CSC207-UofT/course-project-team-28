public class NormalController {
    protected MovieManager movMana;
    protected UserManager userMana;
    protected String currNuname;
    // delete or add movie(Admin User), call MovieManger
    protected ReviewManager revMana;
    protected CoinManager coinMana;

    public void setCurrNuname(String currNuname) {
        this.currNuname = currNuname;
    }

    public void setRevMana(ReviewManager rm) {
        this.revMana = rm;
    }

    public void setMovMana(MovieManager mm) {
        this.movMana = mm;
    }

    public void setUserMana(UserManager um) {
        this.userMana = um;
    }

    public void setCoinMana(CoinManager cm) {
        this.coinMana = cm;
    }

    /**
     * Return true if the given string (username or password) is alphanumeric,
     * also it is nonempty.
     */
    protected boolean isNonemptyalphanumeric(String s) {
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
