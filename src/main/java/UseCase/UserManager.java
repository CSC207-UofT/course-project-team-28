package UseCase;

import Entity.User.AdminUser;
import Entity.User.NormalUser;
import InterfaceAdapter.Gateway;

import java.util.ArrayList;

/**
 * A class that manage user info
 */

public class UserManager {
    private final ArrayList<AdminUser> lstOfAdminUser;
    private final ArrayList<NormalUser> lstOfNormalUser;
    private final GatewayInterface gateway = new Gateway();


    /**
     * create ArrayList of NormalUser and Core.User.AdminUser
     */
    public UserManager() {
        this.lstOfAdminUser = new ArrayList<>();
        this.lstOfNormalUser = new ArrayList<>();
    }


    /**
     * create and add an admin user for file Admin user data to the admin user list.
     * @param username username of .AdminUser
     * @param password password of AdminUser
     */
    public void createAdminuser(String username, String password) {
        AdminUser adminUser = new AdminUser(username, password);
        lstOfAdminUser.add(adminUser);
    }

    /**
     * create and add an normal user for file normal user data to the normal user list.
     * @param username username of NormalUser
     * @param password password of NormalUser
     */
    public void createNormaluser(String username, String password, String contactInfo, String description, String category, int coin, ArrayList<String> playList, String picPath) {
        NormalUser normalUser = new NormalUser(username, password, contactInfo, description, category, coin, playList, picPath);
        lstOfNormalUser.add(normalUser);
    }


    /**
     * create and add an new admin user to the normal user list.
     * @param username username of Core.User.AdminUser
     * @param password password of Core.User.AdminUser
     */
    public boolean createNewAdminuser(String username, String password) {
        AdminUser adminUser = new AdminUser(username, password);
        lstOfAdminUser.add(adminUser);

        return lstOfAdminUser.contains(adminUser) &&  this.gateway.createNewUser(username, password,
                "AdminUser");
    }

    /**
     * create and add an new normal user to the normal user list.
     * @param username username of Core.User.NormalUser
     * @param password password of Core.User.NormalUser
     */
    public boolean createNewNormaluser(String username, String password) {
        NormalUser normalUser = new NormalUser(username, password, "Empty contact info",
                "Empty description" ,"Empty category", 300, new ArrayList<>(),
                "/src/main/res/GUIPic/shake hand.jpg");
        lstOfNormalUser.add(normalUser);

        return lstOfNormalUser.contains(normalUser) && this.gateway.createNewUser(username, password, "NormalUser");


    }


//    public void createNormaluser(String username, String password, String contactInfo, String description, String category, int coin, ArrayList<String> playList)  {
//        nu = new Core.User.NormalUser(username, password, contactInfo, description, category, coin, playList);
//        lstOfNormalUser.add(nu);
//    }

    /**
     * Update contact info of a normal user
     * @param username the name of normal user
     * @param updateInfo the info needs to be updated
     * @param writeType the type of info that user wants to update. e.g. contactInfo, description, category, picPath
     * @return True if it is successfully updated. Otherwise, return false.
     */
    public boolean updateInfo(String username, String updateInfo, String writeType) {
        NormalUser normalUser = new NormalUser("","","","", "", 0, new ArrayList<>(), "");

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getUsername().equals(username)){
                normalUser = nu1;
            }
        }

        if(writeType.equals("contactInfo")){
            if(normalUser.getContactinfo().equals(updateInfo)){
                return true;
            }
            else{
                normalUser.updateContactinfo(updateInfo);


                return normalUser.getContactinfo().equals(updateInfo) && this.gateway.updateInfo(username, updateInfo, writeType);
            }
        }
        else if(writeType.equals("description")){
            if(normalUser.getDescription().equals(updateInfo)){
                return true;
            }
            else{
                normalUser.updateDescription(updateInfo);

                return normalUser.getDescription().equals(updateInfo) && this.gateway.updateInfo(username, updateInfo, writeType);
            }
        }
        else if(writeType.equals("category")){
            if(normalUser.getCategory().equals(updateInfo)){
                return true;
            }
            else{
                normalUser.updateCategory(updateInfo);


                return normalUser.getCategory().equals(updateInfo) && this.gateway.updateInfo(username, updateInfo, writeType);
            }
        }
        else{
            if(normalUser.getPicPath().equals(updateInfo)){
                return true;
            }
            else{
                normalUser.changePic(updateInfo);


                return normalUser.getPicPath().equals(updateInfo) && this.gateway.updateInfo(username, updateInfo, writeType);
            }
        }
    }

    /**
     * increase or decrease the user coin number
     *  @param username the name of normal user
     *  @param coin the amount of the coin that need to be changed
     *  @return return True if change is successfully made. Otherwise, return false.
     */
    public boolean updateCoin(String username, int coin){
        NormalUser normalUser = new NormalUser("","","","", "", 0, new ArrayList<>(), "");

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getUsername().equals(username)){
                normalUser = nu1;
            }
        }
        normalUser.setCoin(normalUser.getCoin() + coin);



        return this.gateway.updateInfo(username, String.valueOf(coin), "coin");
    }



    /**
     * Add the movie to user's playlist
     *  @param userName the name of normal user
     *  @param moviename the name of movie
     *  @return return True if movie is successfully added. Otherwise, return false.
     */
    public boolean giveLike(String userName, String moviename) {
        NormalUser normalUser = new NormalUser("","","","", "", 0, new ArrayList<>(), "");

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getUsername().equals(userName)){
                normalUser = nu1;
            }
        }

        if(normalUser.getPlaylist().contains(moviename)){
            return false;
        }
        else {
            normalUser.addMovieToPlaylist(moviename);


            return normalUser.getPlaylist().contains(moviename) && this.gateway.editPlayList(moviename, userName, "Like");
        }

    }

    /**
     * remove movie from user's playlist
     *  @param userName the name of normal user
     *  @param moviename the name of movie
     *  @return return True if movie is successfully removed. Otherwise, return false.
     */
    public boolean undoLike(String userName, String moviename) {
        NormalUser normalUser = new NormalUser("","","","", "", 0,  new ArrayList<>(), "");

        for(NormalUser nu1: lstOfNormalUser){
            if(nu1.getUsername().equals(userName)){
                normalUser = nu1;
            }
        }

        if(!normalUser.getPlaylist().contains(moviename)){
            return false;
        }
        else {
            normalUser.removeMovieFromPlaylist(moviename);


            return !normalUser.getPlaylist().contains(moviename) && this.gateway.editPlayList(moviename, userName, "Undo");
        }
    }

    /**
     * Use username to find the info of that user.
     * @param username the username of Core.User
     * @param usertype the type of Core.User, it is either "Core.User.NormalUser" or "Core.User.AdminUser"
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
     * @param username the username of Core.User
     * @param password the password of Core.User
     * @param userType the type of Core.User, it is either "Core.User.NormalUser" or "Core.User.AdminUser"
     * @return return true if user's username exists in existed user list and match password. Otherwise, return false
     */
    public boolean userIfExist(String username, String password, String userType){
        if (userType.equals("AdminUser")){
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
     * @param username the username of Core.User
     * @param usertype the type of Core.User, it is either "Core.User.NormalUser" or "Core.User.AdminUser"
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


    public boolean checkCoinBiggerThanOne(String username){
        for(NormalUser nu: lstOfNormalUser){
            if(nu.getUsername().equals(username)){
                return nu.getCoin() >= 1;
            }
        }
        return false;
    }

    public ArrayList<NormalUser> getNormalUserList(){
        return lstOfNormalUser;
    }

    public ArrayList<AdminUser> getAdminUserList(){
        return lstOfAdminUser;
    }


}