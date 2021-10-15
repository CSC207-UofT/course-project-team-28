import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that manage user info
 */

public class UserManager {
    private ArrayList<AdminUser> lstOfAdminUser;
    private ArrayList<NormalUser> lstOfNormalUser;
    protected WriteUser wu = new WriteUser();

    /**
     * create ArrayList of NormalUser and AdminUser
     */
    public UserManager() throws IOException {
        ArrayList<AdminUser> aulst = wu.get_object_from_file(); // AdminUser
        ArrayList<NormalUser> nulst = wu.get_NormalUser_from_file(); // NormalUser
        this.lstOfAdminUser = aulst;
        this.lstOfNormalUser = nulst;
    }

    /**
     * Add an admin user to the admin user list.
     * @param username username of AdminUser
     * @param password password of AdminUser
     */
    public void create_adminuser(String username, String password) throws IOException {
        AdminUser au = new AdminUser(username, password);
        wu.create_file(au);
        lstOfAdminUser.add(au);
    }

    /**
     * Add an Normal user to the Normal user list.
     * @param username username of NormalUser
     * @param password password of NormalUser
     */
    public void create_normaluser(String username, String password) throws IOException {
        NormalUser nu = new NormalUser(username, password, "Empty contact info", new ArrayList<>());
        wu.create_file(nu);
        lstOfNormalUser.add(nu);
    }

    /**
     * Update contact info of a normal user
     * @param username the name of normal user
     * @param contact_info the info needs to be updated
     * @return True if it is successfully updated. Otherwise, return false.
     */

    public boolean update_info(String username, String contact_info) throws IOException {
        NormalUser nu = new NormalUser("","","",new ArrayList<>());

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getusername().equals(username)){
                nu = nu1;
            }
        }

        if(nu.getContactinfo().equals(contact_info)){
            return true;
        }
        else{
            nu.update_contactinfo(contact_info);

            String str = wu.edit_profile_readandwrite(contact_info, nu.getusername());

            return !str.equals(contact_info);
        }
    }

    /**
     *  @param username the name of normal user
     *  @param moviename the name of movie
     *  @return return True if movie is successfully added. Otherwise, return false.
     */
    public boolean give_like(String username, String moviename) throws IOException {
        NormalUser nu = new NormalUser("","","",new ArrayList<>());

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getusername().equals(username)){
                nu = nu1;
            }
        }

        if(nu.getplaylist().contains(moviename)){
            return false;
        }
        else {
            nu.add_movie_to_playlist(moviename);

            String str = wu.give_like_readandwrite(moviename, nu.getusername());

            return str.contains(moviename);
        }

    }

    /**
     *  @param username the name of normal user
     *  @param moviename the name of movie
     *  @return return True if movie is successfully removed. Otherwise, return false.
     */
    public boolean undo_like(String username, String moviename) throws IOException {
        NormalUser nu = new NormalUser("","","",new ArrayList<>());

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getusername().equals(username)){
                nu = nu1;
            }
        }

        if(!nu.getplaylist().contains(moviename)){
            return false;
        }
        else {
            nu.remove_movie_from_playlist(moviename);

            String str = wu.undo_like_readandwrite(moviename, nu.getusername());

            return !str.contains(moviename);
        }
    }

    /**
     * Use username to find the info of that user.
     * @param username the username of User
     * @param usertype the type of User, it is either "NormalUser" or "AdminUser"
     * @return return an array of user info
     */
    public Object[] getUserInfoList(String username, String usertype) {
        if (usertype.equals("NormalUser")){
            for(NormalUser nu: lstOfNormalUser){
                if(nu.getusername().equals(username)){
                    return nu.getObject();
                }
            }
        }
        else{
            for(AdminUser au: lstOfAdminUser){
                if(au.getusername().equals(username)){
                    return au.getObject();
                }
            }
        }
        return null;
    }


    /**
     * Use username and password to find the whether user exists or not.
     * @param username the username of User
     * @param password the password of User
     * @param usertype the type of User, it is either "NormalUser" or "AdminUser"
     * @return return true if user's username exists in existed user list and match password. Otherwise, return false
     */
    public boolean userIfExist(String username, String password, String usertype){
        if (usertype.equals("AdminUser")){
            for(AdminUser au: lstOfAdminUser){
                if(au.getusername().equals(username)){
                    return au.getuserpassword().equals(password);
                }
            }
        }
        else{
            for(NormalUser nu: lstOfNormalUser){
                if(nu.getusername().equals(username)){
                    return nu.getuserpassword().equals(password);
                }
            }

        }
        return false;
    }

    /**
     * Use username and usertype to find the whether user's username unique or not.
     * @param username the username of User
     * @param usertype the type of User, it is either "NormalUser" or "AdminUser"
     * @return return true if user's username unique. Otherwise, return false
     */
    public boolean usernameIfUnique(String username, String usertype){
        if (usertype.equals("AdminUser")){
            for(AdminUser au: lstOfAdminUser){
                if(au.getusername().equals(username)){
                    return false;
                }
            }
        }
        else{
            for(NormalUser nu: lstOfNormalUser){
                if(nu.getusername().equals(username)){
                    return false;
                }
            }

        }
        return true;
    }




}
