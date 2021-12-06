package InterfaceAdapter.Controller;

import InterfaceAdapter.InstanceMain;


/**
 * NormalCUser (InterfaceAdapter)
 * Controller responsible for normal-user-related operations.
 */
public class NormalCUser extends NormalController {

    public NormalCUser() {
        super();
    }


    /**
     * create normal user iff the provided username and password is legal
     * username and password must be non-empty, only contain numbers or letters,
     * and username must be unique among all the normal users.
     * DO NOT auto-login if registered successfully
     * @param userName username
     * @param password password
     * @return true iff the provided username and password is non-empty, only contain numbers or letters,
     *         and username must be unique among all the normal users.
     */
    public boolean register(String userName, String password) {
        if (! this.isNonEmptyAlphanumeric(userName)){
            return false;
        }
        else if (! this.isNonEmptyAlphanumeric(password)){
            return false;
        }
        // check if the username is already used by other users
        else if (! InstanceMain.getUserManager().usernameIfUnique(userName, "NormalUser")){
            return false;
        }
        else {

            return InstanceMain.getUserManager().createNewNormaluser(userName, password);
        }
    }

    /**
     * If un exists and (un, pass) match, record the un and return true.
     * @param userName username
     * @param password password
     * @return ture iff userName exists and matches the password
     */
    public boolean login(String userName, String password) {
        if (InstanceMain.getUserManager().userIfExist(userName, password, "NormalUser")) {
            this.currNormalName = userName;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * when given a String of the normal user's username, return information of the user.
     * @param userName username
     * @return information of the user in the forms of an arraylist
     *         [username, password, contact info, description, category, coin, playlist]
     */
    public Object[] profilePage(String userName){
        return InstanceMain.getUserManager().getUserInfoList(userName, "NormalUser");
    }

    /**
     * Given a String newInfo, update the user's profile.
     * @param newInfo the new information
     * @param updateType type of the information, e.g. contactInfo, description, category, picPath, coin
     * @return ture iff updated successfully.
     */
    public boolean editProfile(String newInfo, String updateType) {
        if (updateType.equals("coin")){
            return InstanceMain.getUserManager().updateCoin(this.currNormalName, Integer.parseInt(newInfo));
        }
        else{
            return InstanceMain.getUserManager().updateInfo(this.currNormalName, newInfo, updateType);
        }

    }

}