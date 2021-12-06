package InterfaceAdapter;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import UseCase.UserManager;

import java.util.ArrayList;

public class NormalCMovie extends NormalController{

    public NormalCMovie() {
        super();
    }


    //movie

    /**
     * check if the moviename exists
     * @return ture iff the movie exists
     */
    public boolean ifMovieExist(String movieName){
        return InstanceMain.getMovieManager().getMovie(movieName) != null;
    }


    /**
     * Should be only called when the movie name <mn> exists in the data base
     * @param movieName the name of the movie.
     * @return a String with movie name, movie link, number of likes
     */
    public String movieProfile(String movieName) {
        return InstanceMain.getMovieManager().getMovieProfile(movieName);
    }

    /**
     * Should be only called when the movie name <mn> exists in the data base
     * When given a moviename, return the review of the movie.
     * @param movieName the name of the movie.
     * @return an arraylist of arrays, where each array stores information of a single review in the form of
     *         [username of reviewer, moviename, reviewContent, numCoin, ID].
     */
    public ArrayList<Object[]> movieReviews(String movieName) {
        return InstanceMain.getReviewManager().listRevsOfMovie(movieName);
    }


    /**
     * Should be only called when the movie name <moviename> exists in the data base
     * Given a String moviename, add like.
     * return ture iff added successfully.
     */
    public boolean likeMovie(String movieName) {
        if (InstanceMain.getUserManager().giveLike(this.currNormalName, movieName)){

            return InstanceMain.getMovieManager().likeMovie(movieName);
        }
        else {
            return false;
        }
    }

    /**
     * return ture iff the user's playlist is empty.
     */
    public boolean emptyPlaylist() {
        Object[] userInfo = InstanceMain.getUserManager().getUserInfoList(this.currNormalName, "NormalUser");
        ArrayList<String> userPlaylist = (ArrayList<String>) userInfo[6];
        return userPlaylist.isEmpty();
    }

    /**
     * Given a String moviename, undo like.
     * return ture iff added successfully.
     */
    public boolean undoLike(String movieName) {
        Object[] userInfo = InstanceMain.getUserManager().getUserInfoList(this.currNormalName, "NormalUser");
        ArrayList<String> userPlaylist = (ArrayList<String>) userInfo[6];
        if (userPlaylist.contains(movieName)){

            return InstanceMain.getUserManager().undoLike(this.currNormalName, movieName) && InstanceMain.getMovieManager().undolikeMovie(movieName);
        }
        else{
            return false;
        }
    }

    /**
     * add a review when provided with moviename of the movie and review content
     * return ture iff a review is successfully added. false otherwise
     */
    public boolean writeReview(String movieName, String revContent) {
        return InstanceMain.getReviewManager().writeNewReview(this.currNormalName, movieName, revContent, 0);

    }


}
