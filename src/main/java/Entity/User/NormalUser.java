package Entity.User;

import java.util.ArrayList;


/**
 * Represent Normal Core.User.User
 */
public class NormalUser extends User{

    protected String contactInfo;
    protected String description;
    protected String category;
    protected int coin;
    protected ArrayList<String> playlist;
    protected String picPath;

    /**
     * create the object for user
     * @param username the username of user
     * @param password the password of user
     * @param contactInfo the contact information of user
     * @param description the description of user itself
     * @param category the category pf movie that user like
     * @param coin the coin that user has
     * @param playlist the playlist of movies
     * @param picPath the relative path of user's profile photo
     */
    public NormalUser(String username, String password, String contactInfo, String description, String category, int coin, ArrayList<String> playlist, String picPath) {
        super(username, password);
        this.contactInfo = contactInfo;
        this.description = description;
        this.category = category;
        this.coin = coin;
        this.playlist = playlist;
        this.picPath = picPath;

    }

    /**
     * @return return all the info of user.
     */
    @Override
    public Object[] getObject() {
        Object[] nu = new Object[8];
        nu[0] = this.username;
        nu[1] = this.password;
        nu[2] = this.contactInfo;
        nu[3] = this.description;
        nu[4] = this.category;
        nu[5] = this.coin;
        nu[6] = this.playlist;
        nu[7] = this.picPath;

        return nu;
    }

    /**
     * getter of user
     */
    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public String getUserPassword(){
        return this.password;
    }

    public String getContactInfo(){
        return this.contactInfo;
    }

    public ArrayList<String> getPlaylist(){
        return this.playlist;
    }

    public String getDescription(){
        return this.description;
    }

    public int getCoin(){ return this.coin; }

    public String getCategory(){ return this.category; }

    public String getPicPath(){
        return this.picPath;
    }


    /**
     * setter of user
     */
    public void updateContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }

    public void addMovieToPlaylist(String movieName){
        this.playlist.add(movieName);
    }

    public void removeMovieFromPlaylist(String movieName){
        this.playlist.remove(movieName);
    }

    public void updateDescription(String description){
        this.description = description;
    }

    public void updateCategory(String category){ this.category = category; }

    public void setCoin(int coin){ this.coin = coin; }

    public void changePic(String picPath){
        this.picPath = picPath;
    }
}
