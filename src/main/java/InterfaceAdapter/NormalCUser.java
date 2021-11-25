package InterfaceAdapter;

import Framework.DataAccess.WriteUser;

import java.util.ArrayList;

public class NormalCUser extends NormalController {

    public NormalCUser() {
        super();
    }

    //user


    /**
     * create normal user iff the provided username and password is legal
     * username and password must be non-empty, only contain numbers or letters,
     * and username must be unique among all the normal users.
     * DO NOT auto-login if registered successfully
     */
    public boolean register(String userName, String password) {
        if (! this.isNonemptyalphanumeric(userName)){
            return false;
        }
        else if (! this.isNonemptyalphanumeric(password)){
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
     * when given a String of the normal user's username, return an
     * arraylist [username, password, contact info, description, category, coin, playlist]
     */
    public Object[] profilePage(String username){
        return InstanceMain.getUserManager().getUserInfoList(username, "NormalUser");
//        ArrayList<Object> newarray = new ArrayList<>();
//        Object[] wholelist = user_mana.getUserInfoList(username, "Core.User.NormalUser");
//        newarray.add(wholelist[0]);
//        newarray.add(wholelist[2]);
//        newarray.add(wholelist[3]);
//        return newarray;
    }

    /**
     * Given a String newinfo, update the user's profile.
     * return ture iff updated successfully.
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