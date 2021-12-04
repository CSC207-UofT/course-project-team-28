package InterfaceAdapter;

import UseCase.GatewayInterface;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Gateway (InterfaceAdapter)
 */
public class Gateway implements GatewayInterface {
    /*
     * Middle-Man class
     * get request from WriteFile class to create entity object for file data
     * get request from Use case to create new file or edit existed file
     */




    //Call Manager to create object for the data from file

    /**
     * Call UserManger to create entity object for Normal user data
     * @param userName user's Name
     * @param password user's password
     * @param contactInfo user's contact information
     * @param description user's description
     * @param category the type of movie that user like
     * @param coin the coin that user has
     * @param playList the movie's playlist of user
     */
    @Override
    public void createFileNormalUser(String userName, String password, String contactInfo, String description, String category, int coin, ArrayList<String> playList, String picPath){
        InstanceMain.getUserManager().createNormaluser(userName, password, contactInfo, description, category, coin, playList, picPath);


    }

    /**
     * Call UserManger to create entity object for Admin user data
     * @param userName user's Name
     * @param password user's password
     */
    @Override
    public void createFileAdminUser(String userName, String password, String picPath){
        InstanceMain.getUserManager().createAdminuser(userName, password, picPath);


    }

    /**
     * Call MovieManger to create entity object for movie data
     * @param moviename Movie's name
     * @param movielink The link of Movie
     * @param numLike The num of Likes that this movie receives
     */
    @Override
    public void createFileMovie(String moviename, String movielink, int numLike){
        InstanceMain.getMovieManager().addMovie(moviename, movielink, numLike);


    }

    /**
     * Call ReviewManger to create entity object for movie data
     * @param userName the name of user who writes this review
     * @param movieName the name of movie that this review belongs to
     * @param content the content of review
     * @param numCoin the num of coin that this review receive
     * @param ID the unique ID for this review
     */
    @Override
    public void createFileReview(String userName, String movieName, String content, int numCoin , int ID){
        InstanceMain.getReviewManager().writeReview(userName, movieName, content, numCoin , ID);

    }






    //Call WriteFile classes to create new file for new user

    /**
     * Call WriteUser Class to create new file for the new user
     * @param userName the name of new user
     * @param password the password of new user
     * @param userType the type of new user, it may be either "NormalUser" or "AdminUser"
     * @return return true if the file is successfully created. Otherwise, return false
     */
    @Override
    public boolean createNewUser(String userName, String password, String userType){
        return InstanceMain.getWriteUser().createFile(userName, password, userType);
    }

    /**
     * Call WriteMovie Class to create new file for the new user
     * @param movieName the name of new movie
     * @param movieLink the link of new movie
     * @return return true if the file is successfully created. Otherwise, return false
     */
    @Override
    public boolean createNewMovie(String movieName, String movieLink){
        return InstanceMain.getWriteMovie().createFile(movieName, movieLink);
    }

    /**
     * Call WriteReview Class to create new file for the new user
     * @param currUserName the name of user who writes this new review
     * @param movieName the name of movie that this review belongs to
     * @param revContent the content of review
     * @param ID the unique ID of review
     * @return return true if the file is successfully created. Otherwise, return false
     */
    @Override
    public boolean createNewReview(String currUserName, String movieName, String revContent, int ID){
        return InstanceMain.getWriteReview().createFile(currUserName, movieName, revContent, ID);
    }



    //Call WriteFile classes to edit info

    /**
     * Call WriteUser Class to edit user's info
     * @param userName the name of user
     * @param updateInfo the content of new updated info
     * @param updateType the Type of info that user want to update, it may be "contactInfo", "description", "category" or "coin"
     * @return return true if the info in the file is successfully created. Otherwise, return false
     */
    @Override
    public boolean updateInfo(String userName, String updateInfo, String updateType){
        return InstanceMain.getWriteUser().editProfileReadAndWrite(userName, updateInfo, updateType);
    }

    /**
     * Call WriteUser Class to edit the movie's playList of user
     * @param movieName name of movie
     * @param userName the name of user who want to edit playList
     * @param editType the Type of action, it may be "Like", add the movie to playList; or "Undo", remove the movie from the playlist.
     * @return return true if the playList in the file is successfully created. Otherwise, return false
     */
    @Override
    public boolean editPlayList(String movieName, String userName, String editType){
        if (editType.equals("Like")){
            return InstanceMain.getWriteUser().givelikeReadAndWrite(movieName, userName);
        }
        else {
            return InstanceMain.getWriteUser().undoLikeReadAndWrite(movieName, userName);
        }
    }

    /**
     * Call WriteMovie Class to edit the num of likes in movie's file
     * @param movieName the name of the movie
     * @param state the action want to preform, it may be "Increase" or "Decrease"
     * @return return true if the num of likes in the file is successfully created. Otherwise, return false
     */
    @Override
    public boolean editLikeToMovieFile(String movieName, String state){
        return InstanceMain.getWriteMovie().addLikeToFile(movieName, state) ;
    }

    /**
     * Call WriteUser Class to decrease user's coin by 1 and WriteReview to increase num of coin of specific review by 1
     * @param userName the name of user
     * @param reviewid the review id of review
     * @return return true if the num of coin in the file is successfully created. Otherwise, return false
     */
    @Override
    public boolean editCoin(String userName, int reviewid) {
        return InstanceMain.getWriteUser().editProfileReadAndWrite(userName, "-1", "coin") && InstanceMain.getWriteReview().addCoinsToReview(reviewid, 1);
    }


}
