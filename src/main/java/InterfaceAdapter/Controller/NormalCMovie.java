package InterfaceAdapter.Controller;

import InterfaceAdapter.InstanceMain;

import java.util.ArrayList;

/**
 * NormalCMovie (InterfaceAdapter)
 * Controller responsible for movie-related operations.
 */
public class NormalCMovie extends NormalController{

    public NormalCMovie() {
        super();
    }

    /**
     * check if the movieName exists
     * @param movieName the name of the movie.
     * @return ture iff the movie exists
     */
    public boolean ifMovieExist(String movieName){
        return InstanceMain.getMovieManager().getMovie(movieName) != null;
    }

    /**
     * Should be only called when the movie name <movieName> exists in the database
     * @param movieName the name of the movie.
     * @return a String with movie name, movie link, number of likes
     */
    public String movieProfile(String movieName) {
        return InstanceMain.getMovieManager().getMovieProfile(movieName);
    }

    /**
     * Should be only called when the movie name <movieName> exists in the database
     * When given a movieName, return the review of the movie.
     * @param movieName the name of the movie.
     * @return an arraylist of arrays, where each array stores information of a single review in the form of
     *         [username of reviewer, movieName, reviewContent, numCoin, ID].
     */
    public ArrayList<Object[]> movieReviews(String movieName) {
        return InstanceMain.getReviewManager().listRevsOfMovie(movieName);
    }

    /**
     * Should be only called when the movie name <movieName> exists in the database
     * Given a String movieName, add like.
     * @param movieName name of the movie.
     * @return ture iff like is added successfully.
     */
    public boolean likeMovie(String movieName) {
        if (InstanceMain.getUserManager().giveLike(this.currNormalName, movieName)){
            return InstanceMain.getMovieManager().likeMovie(movieName);
        }
        else {return false;}
    }

    /**
     * @return ture iff the user's playlist is empty.
     */
    @SuppressWarnings("unchecked")
    public boolean emptyPlaylist() {
        Object[] userInfo = InstanceMain.getUserManager().getUserInfoList(this.currNormalName, "NormalUser");
        ArrayList<String> userPlaylist = (ArrayList<String>) userInfo[6];
        return userPlaylist.isEmpty();
    }

    /**
     * Should be only called when the movie name <movieName> exists in the database
     * Given a String movieName, undo like.
     * @param movieName name of the movie.
     * @return ture iff undolike successfully.
     */
    @SuppressWarnings("unchecked")
    public boolean undoLike(String movieName) {
        Object[] userInfo = InstanceMain.getUserManager().getUserInfoList(this.currNormalName, "NormalUser");
        ArrayList<String> userPlaylist = (ArrayList<String>) userInfo[6];
        if (userPlaylist.contains(movieName)){
            return InstanceMain.getUserManager().undoLike(this.currNormalName, movieName) && InstanceMain.getMovieManager().undolikeMovie(movieName);
        }
        else {return false;}
    }

    /**
     * add a review when provided with movieName of the movie and review content
     * @param movieName name of the movie
     * @param revContent content of review
     * @return ture iff a review is successfully added. false otherwise
     */
    public boolean writeReview(String movieName, String revContent) {
        return InstanceMain.getReviewManager().writeNewReview(this.currNormalName, movieName, revContent, 0);}

}
