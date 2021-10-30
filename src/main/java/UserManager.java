import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that manage user info
 */

public class UserManager {
    private ArrayList<AdminUser> lstOfAdminUser;
    private ArrayList<NormalUser> lstOfNormalUser;
    protected WriteUser wu = new WriteUser();
    private AdminUser au;
    NormalUser nu;


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
    public boolean create_adminuser(String username, String password) throws IOException {
        au = new AdminUser(username, password);
        boolean file_exist = wu.create_file(au);
        lstOfAdminUser.add(au);
        return (file_exist && lstOfAdminUser.contains(au));
    }

    /**
     * Add an Normal user to the Normal user list.
     * @param username username of NormalUser
     * @param password password of NormalUser
     */
    public boolean create_normaluser(String username, String password) throws IOException {
        nu = new NormalUser(username, password, "Empty contact info", "Empty description" ,"Empty category", 300, new ArrayList<>());
        boolean file_exist = wu.create_file(nu);
        lstOfNormalUser.add(nu);
        return (file_exist && lstOfNormalUser.contains(nu));
    }

    /**
     * Update contact info of a normal user
     * @param username the name of normal user
     * @param updateInfo the info needs to be updated
     * @param writeType the type of info that user wants to update. e.g. contactInfo, description
     * @return True if it is successfully updated. Otherwise, return false.
     */

    public boolean updateInfo(String username, String updateInfo, String writeType) throws IOException {
        nu = new NormalUser("","","","", "", 0, new ArrayList<>());

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getUsername().equals(username)){
                nu = nu1;
            }
        }

        if(writeType.equals("contactInfo")){
            if(nu.getContactinfo().equals(updateInfo)){
                return true;
            }
            else{
                nu.updateContactinfo(updateInfo);

                String str = wu.edit_profile_readandwrite(updateInfo, nu.getUsername(), "contactInfo");

                return !str.equals(updateInfo);
            }
        }
        else if(writeType.equals("description")){
            if(nu.getDescription().equals(updateInfo)){
                return true;
            }
            else{
                nu.updateDescription(updateInfo);

                String str = wu.edit_profile_readandwrite(updateInfo, nu.getUsername(), "description");

                return !str.equals(updateInfo);
            }
        }
        else{
            if(nu.getCategory().equals(updateInfo)){
                return true;
            }
            else{
                nu.updateCategory(updateInfo);

                String str = wu.edit_profile_readandwrite(updateInfo, nu.getUsername(), "category");

                return !str.equals(updateInfo);
            }
        }
    }

    public boolean updateCoin(String username, int coin) throws IOException{
        nu = new NormalUser("","","","", "", 0, new ArrayList<>());

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getUsername().equals(username)){
                nu = nu1;
            }
        }
        if(nu.getCoin() == coin){
            return true;
        }
        else{
            nu.setCoin(coin);

            String str = wu.edit_profile_readandwrite(Integer.toString(coin), nu.getUsername(), "coin");

            return !str.equals(Integer.toString(coin));
        }
    }



    /**
     *  @param username the name of normal user
     *  @param moviename the name of movie
     *  @return return True if movie is successfully added. Otherwise, return false.
     */
    public boolean give_like(String username, String moviename) throws IOException {
        NormalUser nu = new NormalUser("","","","", "", 0, new ArrayList<>());

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getUsername().equals(username)){
                nu = nu1;
            }
        }

        if(nu.getPlaylist().contains(moviename)){
            return false;
        }
        else {
            nu.addMovieToPlaylist(moviename);

            String str = wu.give_like_readandwrite(moviename, nu.getUsername());

            return str.contains(moviename);
        }

    }

    /**
     *  @param username the name of normal user
     *  @param moviename the name of movie
     *  @return return True if movie is successfully removed. Otherwise, return false.
     */
    public boolean undo_like(String username, String moviename) throws IOException {
        NormalUser nu = new NormalUser("","","","", "", 0,  new ArrayList<>());

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getUsername().equals(username)){
                nu = nu1;
            }
        }

        if(!nu.getPlaylist().contains(moviename)){
            return false;
        }
        else {
            nu.removeMovieFromPlaylist(moviename);

            String str = wu.undo_like_readandwrite(moviename, nu.getUsername());

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
                if(nu.getUsername().equals(username)){
                    return nu.getObject();
                }
            }
        }
        else{
            for(AdminUser au: lstOfAdminUser){
                if(au.getUsername().equals(username)){
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
                if(au.getUsername().equals(username)){
                    return au.getUserPassword().equals(password);
                }
            }
        }
        else{
            for(NormalUser nu: lstOfNormalUser){
                if(nu.getUsername().equals(username)){
                    return nu.getUserPassword().equals(password);
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
                if(au.getUsername().equals(username)){
                    return false;
                }
            }
        }
        else{
            for(NormalUser nu: lstOfNormalUser){
                if(nu.getUsername().equals(username)){
                    return false;
                }
            }

        }
        return true;
    }




}