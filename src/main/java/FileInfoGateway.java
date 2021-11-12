import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileInfoGateway {
    private final UserManager um = new UserManager("");
    private final MovieManager mm = new MovieManager("");
    private final ReviewManager rm = new ReviewManager("", mm);
    public FileInfoGateway() {

    }

    public boolean createNewUserObject(String un, String pass, String userType, String contactInfo, String description, String category, int coin, ArrayList<String> playList){
        if (userType.equals("AdminUser")){
            um.create_adminuser(un, pass);
        }
        else{
            um.create_normaluser(un, pass, contactInfo, description, category, coin, playList);

        }
        return um.userIfExist(un, pass, userType);
    }


    public boolean createMovieObject(String movieName, String movieLink, HashMap<Object, Object> map, int i) {
       return mm.add_movie(movieName, movieLink, map, i);
    }

//    public boolean deleteMovieObject(String movieName) {
//        return mm.delete_movie(movieName);
//    }

    public boolean createReviewObject(String currUserName, String movieName, String revContent, int ID) {
        return rm.write_review(currUserName, movieName, revContent, ID);
    }

    public int reviewID(){
        return rm.getReviewID();
    }


    public UserManager getUM(){
        return um;
    }

    public MovieManager getMM(){
        return mm;
    }

    public ReviewManager getRM(){
        return rm;
    }



}
