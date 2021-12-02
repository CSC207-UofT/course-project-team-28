package UseCase;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Gateway Interface  (Use case layer)
 */
public interface GatewayInterface {

    //Call Manger class to create entity Object for file data
    void createFileNormalUser(String userName, String password, String contactInfo, String description, String category, int coin, ArrayList<String> playList, String picPath);
    void createFileAdminUser(String userName, String password, String picPath);
    void createFileMovie(String moviename, String movielink, HashMap<Object, Object> map, int i);
    void createFileReview(String userName, String movieName, String content, int numCoin , int ID);

    //Call WriteFile class's method to create new File
    boolean createNewUser(String userName, String password, String userType);
    boolean createNewMovie(String movieName, String movieLink);
    boolean createNewReview(String currUserName, String movieName, String revContent, int ID);

    //Call WriteFile class's method to edit file's info
    boolean updateInfo(String userName, String updateInfo, String updateType);
    boolean editPlayList(String movieName, String userName, String editType);
    boolean editLikeToMovieFile(String movieName, String state);
    boolean editCoin(String userName, int reviewid);


}
