package InterfaceAdapter.Controller;

import InterfaceAdapter.InstanceMain;

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

//    /**
//     * Should be only called when the movie name <mn> exists in the data base
//     * @return an arraylist [movie name, movie link, reviews, number of likes], where movie name
//     *         and movie link are strings, reviews is [String of review 1 of the moive,
//     *         String of review 2 of the movie, ...]
//     */
//    public String search(String mn){
//        return movMana.get_movieprofile(mn);
//    }


    /**
     * Should be only called when the movie name <mn> exists in the data base
     * @return an String with movie name, movie link, number of likes, reviews
     */
    public String movieProfile(String movieName) {
        return InstanceMain.getMovieManager().getMovieProfile(movieName) + "\n" + InstanceMain.getReviewManager().revsOfMovieString(movieName);
    }




    /**
     * Should be only called when the movie name <moviename> exists in the data base
     * Given a String moviename, add like.
     * return ture iff added successfully.
     */
    public boolean likeMovie(String movieName, String category) {
        if (InstanceMain.getUserManager().giveLike(this.currNormalName, movieName)){

            return InstanceMain.getMovieManager().likeMovie(movieName, category);
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
    public boolean undoLike(String movieName, String category) {
        Object[] userInfo = InstanceMain.getUserManager().getUserInfoList(this.currNormalName, "NormalUser");
        ArrayList<String> userPlaylist = (ArrayList<String>) userInfo[6];
        if (userPlaylist.contains(movieName)){

            return InstanceMain.getUserManager().undoLike(this.currNormalName, movieName) && InstanceMain.getMovieManager().undolikeMovie(movieName, category);
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
