import java.util.ArrayList;


/**
 * Represent Normal User
 */
public class NormalUser extends User{

    protected String contactinfo;
    protected String description;
    protected String category;
    protected int coin;
    protected ArrayList<String> playlist;

    /**
     * create the object for user
     * @param username the user name of user
     * @param password the password of user
     * @param contactinfo the contact information of user
     * @param description the description of user itself
     * @param category the category pf movie that user like
     * @param coin the coin that user have
     * @param playlist the playlist of movie of movie
     */
    public NormalUser(String username, String password, String contactinfo, String description, String category, int coin, ArrayList<String> playlist) {
        super(username, password);
        this.contactinfo = contactinfo;
        this.description = description;
        this.category = category;
        this.coin = coin;
        this.playlist = playlist;

    }

    /**
     * @return return all the info of user.
     */
    @Override
    public Object[] getObject() {
        Object[] nu = new Object[7];
        nu[0] = this.username;
        nu[1] = this.password;
        nu[2] = this.contactinfo;
        nu[3] = this.description;
        nu[4] = this.category;
        nu[5] = this.coin;
        nu[6] = this.playlist;

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

    public String getContactinfo(){
        return this.contactinfo;
    }

    public ArrayList<String> getPlaylist(){
        return this.playlist;
    }

    public String getDescription(){
        return this.description;
    }

    public int getCoin(){ return this.coin; }

    public String getCategory(){ return this.category; }


    /**
     * setter of user
     */
    public void updateContactinfo(String contactinfo){
        this.contactinfo = contactinfo;
    }

    public void addMovieToPlaylist(String moviename){
        this.playlist.add(moviename);
    }

    public void removeMovieFromPlaylist(String moviename){
        this.playlist.remove(moviename);
    }

    public void updateDescription(String description){
        this.description = description;
    }

    public void updateCategory(String category){ this.category = category; }

    public void setCoin(int coin){ this.coin = coin; }

}
