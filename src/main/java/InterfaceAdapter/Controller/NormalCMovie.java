package InterfaceAdapter.Controller;

import Entity.Movie;
import InterfaceAdapter.InstanceMain;
import UseCase.MovieRanking;

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
     * @return an array of the information of the movie in the form of
     *         [movieName, movieLink, movieCategory, numOfLikes]
     */
    public Object[] movieProfile(String movieName) {
        return InstanceMain.getMovieManager().getMovieProfile(movieName);
    }

    /**
     * Should be only called when the movie name <movieName> exists in the database
     * When given a movieName, return the sorted review of the movie, and the review with more coins
     * is at the front of the list.
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
     *
     * @return sorted ArrayList of Movie, the first one is the most popular movie (with most likes).
     */
    public ArrayList<Movie> rankMovie(){
        MovieRanking mr = new MovieRanking();
        return mr.getMovieRank();
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
