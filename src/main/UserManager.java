import java.util.ArrayList;
import java.util.List;

/**
 * A class that manage user info
 */

public class UserManager {
    private List<AdminUser> lstOfAdminUser;
    private List<NormalUser> lstOfNormalUser;
    private String contact_info;

    /**
     * Set two empty list of admin users and normal users.
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
     * Update contact info of a normal user
     * @param nu a normal user
     * @param contact_info the info needs to be updated
     */

    public void update_info(NormalUser nu, String contact_info){



    }


}
