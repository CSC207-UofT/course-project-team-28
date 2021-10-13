import java.util.ArrayList;
import java.util.List;

/**
 * A class that manages user info
 */

public class UserManager {
    private List<AdminUser> lstOfAdminUser;
    private List<NormalUser> lstOfNormalUser;

    /**
     * Set two empty lists of admin users and normal users.
     */

    public UserManager(){
        lstOfAdminUser = new ArrayList<>();
        lstOfNormalUser = new ArrayList<>();
    }

    /**
     * Add an admin user to the admin user list.
     * @param a an admin user
     */
    public void add_adminuser(AdminUser a){
        lstOfAdminUser.add(a);
    }

    /**
     * Add an admin user to the admin user list.
     * @param u a normal user
     */
    public void add_normaluser(NormalUser u){
        lstOfNormalUser.add(u);
    }

    /**
     * A getter that returns a list of Admin users
     */
    public List<AdminUser> get_adminuser(){
        return lstOfAdminUser;
    }

    /**
     *A getter that returns a list of normal users
     */
    public List<NormalUser> get_normaluser(){
        return lstOfNormalUser;
    }


}
