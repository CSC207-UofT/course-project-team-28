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
    public boolean ifMovieExist(String moviename){
        if (movMana.get_movie(moviename) == null) {
            return false;
        }
        return true;
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
     * @return an arraylist [movie name, movie link, reviews, number of likes], where movie name
     *         and movie link are strings, reviews is [String of review 1 of the moive,
     *         String of review 2 of the movie, ...]
     */
    public String movieProfile(String moviename) {
        return movMana.get_movieprofile(moviename);
    }




    /**
     * Should be only called when the movie name <moviename> exists in the data base
     * Given a String moviename, add like.
     * return ture iff added successfully.
     */
    public boolean likeMovie(String moviename, WriteMovie wm, WriteUser wu) {
        if (userMana.giveLike(this.currNuname, moviename)){
            wm.addLikeToFile( moviename, "Increase");
            movMana.like_movie(moviename);
            wu.givelikeReadAndWrite(moviename, this.currNuname);
            return true;
        }
        else {
            //user_mana.undo_like(this.curr_nuname, moviename);
            return false;
        }
    }

    /**
     * return ture iff the user's playlist is empty.
     */
    public boolean emptyPlaylist() {
        Object[] user_info = userMana.getUserInfoList(currNuname, "NormalUser");
        ArrayList<String> user_playlist = (ArrayList<String>) user_info[6];
        return user_playlist.isEmpty();
    }

    /**
     * Given a String moviename, undo like.
     * return ture iff added successfully.
     */
    public boolean undoLike(String moviename, WriteMovie wm, WriteUser wu) {
        Object[] user_info = userMana.getUserInfoList(currNuname, "NormalUser");
        ArrayList<String> user_playlist = (ArrayList<String>) user_info[6];
        if (user_playlist.contains(moviename)){
            userMana.undoLike(this.currNuname, moviename);
            movMana.undolike_movie(moviename);
            wm.addLikeToFile(moviename, "Decrease");
            wu.undoLikeReadAndWrite(moviename, this.currNuname);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * add a review when provided with moviename of the movie and review content
     * return ture iff a review is successfully added. false otherwise
     */
    public boolean writeReview(String movieName, String revContent, WriteReview wr, WriteMovie wm) {
        wm.addReviewToFile(this.currNuname, movieName, revContent);
        return wr.createFile(currNuname, movieName, revContent);
    }


}
