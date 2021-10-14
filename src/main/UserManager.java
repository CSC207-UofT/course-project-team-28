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
        ArrayList<NormalUser> nulst = wu.get_NormalUser_from_file(); // AdminUser
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
        NormalUser nu = new NormalUser(username, password, "", new ArrayList<>());
        wu.create_file(nu);
        lstOfNormalUser.add(nu);
    }

    /**
     * Update contact info of a normal user
     * @param nu a normal user
     * @param contact_info the info needs to be updated
     * @return True if it is successfully removed. If that movie is not in playlist, return false.
     */

    public boolean update_info(NormalUser nu, String contact_info) throws IOException {
        if(nu.contactinfo.equals(contact_info)){
            return true;
        }
        else{
            nu.contactinfo = contact_info;

            String str = wu.edit_profile_readandwrite(contact_info, nu.username);

            return !str.equals(contact_info);
        }
    }

    /**
     * Use existed NormalUser Object to call give_like, pass moviename as parameter.
     * it should call WriteUser Class to read and write file.
     * Return True if it is successfully added. If that movie is already in playlist, return false.
     */
    public boolean give_like(NormalUser nu, String moviename) throws IOException {
        if(nu.playlist.contains(moviename)){
            return false;
        }
        else {
            nu.playlist.add(moviename);

            String str = wu.give_like_readandwrite(moviename, nu.username);

            return str.contains(moviename);
        }

    }

    /**
     * Use existed NormalUser Object to call undo_like, pass moviename as parameter.
     * it should call WriteUser Class to read and write file.
     * Return True if it is successfully removed. If that movie is not in playlist, return false.
     */
    public boolean undo_like(NormalUser nu, String moviename) throws IOException {
        if(!nu.playlist.contains(moviename)){
            return false;
        }
        else {
            nu.playlist.remove(moviename);

            String str = wu.undo_like_readandwrite(moviename, nu.username);

            return !str.contains(moviename);
        }
    }

    /**
     * Use username to find the info of that user.
     * @param username the username of User
     * @param usertype the type of User, it is either "NormalUser" or "AdminUser"
     * @return return an array of user info
     */
    public Object[] getUserInfoArray(String username, String usertype) {
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




}
