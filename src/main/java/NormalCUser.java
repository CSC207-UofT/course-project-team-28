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
    public boolean register(String un, String pass, WriteUser wu) {
        if (! this.isNonemptyalphanumeric(un)){
            return false;
        }
        else if (! this.isNonemptyalphanumeric(pass)){
            return false;
        }
        // check if the username is already used by other users
        else if (! userMana.usernameIfUnique(un, "NormalUser")){
            return false;
        }
        else {
            wu.createFile(un, pass, "NormalUser");
            return true;
        }
    }


    /**
     * If un exists and (un, pass) match, record the un and return true.
     */
    public boolean login(String un, String pass) {
        if (userMana.userIfExist(un, pass, "NormalUser")) {
            currNuname = un;
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
        return userMana.getUserInfoList(username, "NormalUser");
//        ArrayList<Object> newarray = new ArrayList<>();
//        Object[] wholelist = user_mana.getUserInfoList(username, "NormalUser");
//        newarray.add(wholelist[0]);
//        newarray.add(wholelist[2]);
//        newarray.add(wholelist[3]);
//        return newarray;
    }

    /**
     * Given a String newinfo, update the user's profile.
     * return ture iff updated successfully.
     */
    //TODO: add new parameter, the corresponding place where call this method in UI need to add onr more parameter
    public boolean editProfile(String newInfo, String updateType, WriteUser wu) {
        if (updateType.equals("coin")){
            return userMana.updateCoin(this.currNuname, Integer.parseInt(newInfo)) &&
                    wu.editProfileReadAndWrite(newInfo, this.currNuname, "coin");
        }
        else{
            return userMana.updateInfo(this.currNuname, newInfo, updateType) &&
                    wu.editProfileReadAndWrite(newInfo, this.currNuname, "contactInfo");
        }

    }




}