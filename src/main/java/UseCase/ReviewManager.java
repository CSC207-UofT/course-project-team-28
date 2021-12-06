package UseCase;

import Entity.Review;
import InterfaceAdapter.Gateway;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * ReviewManager (UserCase)
 * Stores and manages reviews
 */
public class ReviewManager {
    // map that stores reviews according to the movie that they were wrote for,
    private final HashMap<String, ArrayList<Review>> MovietoRevs;
    private final HashMap<String, ArrayList<Review>> UsertoRevs;
    private final ArrayList<Review> reviewList;
    private int totalNumOfReview;
    private final MovieManager movieManager;
    private final Gateway gateway = new Gateway();


    public ReviewManager(MovieManager mm) {
        this.MovietoRevs = new HashMap<>();
        this.UsertoRevs = new HashMap<>();
        this.reviewList = new ArrayList<>();
        this.movieManager = mm;
        this.totalNumOfReview = 0;
    }



    /**
     * Called only after confirming the moviename is valid (i.e. the movie exists)
     * Takes the name of Core.Movie and return an arraylist of arrays, where each array stores information
     * of a single review.
     * @param moviename the name of the movie.
     * @return an arraylist of arrays, where each array stores information of a single review in the form of
     *         [username of reviewer, moviename, reviewContent, numCoin, ID].
     */
    public ArrayList<Object[]> listRevsOfMovie(String moviename){
        ArrayList<Review> lst = this.MovietoRevs.get(moviename);
        ArrayList<Object[]> result = new ArrayList<>();
        // add content of reviews to temp
        if (lst != null){
            for (Review rev : lst){
                result.add(rev.getReviewInfo());
            }
        }
        return result;
    }

    /**
     * Called only after confirming the moviename is valid (i.e. the movie exists)
     * Takes the name of Core.Movie and return a string form of reviews for the Core.Movie.
     */
    public String revsOfMovieString(String moviename){
        ArrayList<Review> lst = this.MovietoRevs.get(moviename);
        StringBuilder temp = new StringBuilder();
        // add content of reviews to temp
        if(lst != null){
            for (Review rev : lst){
                temp.append("\n[");
                temp.append(rev.toString());
                temp.append("]");
            }
            return String.valueOf(temp);
        }
        else {
            temp.append("\n[");
            temp.append("No review");
            temp.append("]");
            return String.valueOf(temp);
        }
    }


    /**
     * Called when initiate the system from database.
     * create a Review for review file data, add Review to MovietoRevs, UsertoRevs, reviewList.
     * Return ture iff the review has been successfully created and added to MovietoRevs, UsertoRevs, reviewList.
     * @param userName the username of the user who wrote the review
     * @param movieName the name of movie for which the review is wrote for
     * @param content the content of the review
     * @param numCoin the number of coins that the review received
     * @param ID the ID of the review
     * @return a boolean, return ture iff the review has been successfully created and added to MovietoRevs,
     *         UsertoRevs, reviewList.
     */
    public boolean writeReview(String userName, String movieName, String content, int numCoin , int ID) {
        Review rev = new Review(userName, movieName, content, numCoin, ID);
        this.reviewList.add(rev);
        this.totalNumOfReview = reviewList.get(reviewList.size() - 1).getID();
        return addMr(movieName, rev) && addUr(userName, rev);
    }


    /**
     * Called when user wirtes a new review
     * create a new review, add Review to MovietoRevs, UsertoRevs, reviewList, and record the Review in txt file.
     * Return ture iff the review has been successfully created and added to the txt file.
     * @param userName the username of the user who wrote the review
     * @param movieName the name of movie for which the review is wrote for
     * @param content the content of the review
     * @param numCoin the number of coins that the review received
     * @return a boolean, return ture iff the review has been successfully created and added to MovietoRevs,
     *         UsertoRevs, reviewList, and database.
     */
    public boolean writeNewReview(String userName, String movieName, String content, int numCoin) {
        this.totalNumOfReview = this.totalNumOfReview + 1;
        Review rev = new Review(userName, movieName, content, numCoin, totalNumOfReview);
        this.reviewList.add(rev);
        this.totalNumOfReview = reviewList.get(reviewList.size() - 1).getID();
        return addMr(movieName, rev) && addUr(userName, rev)
                && this.gateway.createNewReview(userName, movieName, content, totalNumOfReview);
    }


    public int getReviewID(){
        return totalNumOfReview;
    }



    /**
     * find a review with review_id, and add 1 coin
     * @param username the name of the user who gives coin to review
     * TODO
     */
    public boolean addCoin(int reviewId, String username) {
        int coin = 0;
        int coinAfter = 0;
        for (Review review : this.reviewList){
            if (review.getID() == reviewId){
                coin = review.getnumCoin();
                review.setNumCoin(review.getnumCoin() + 1);
                coinAfter = review.getnumCoin();
            }
        }
        return coinAfter - 1 == coin && this.gateway.editCoin(username, reviewId);
    }

//    /**
//     * find a review with review_id, and delete 1 coin
//     */
//    public void reduceCoin(int review_id) {
//        for (Review review : this.reviewList){
//            if (review.getID() == review_id){
//                review.setNumCoin(review.getnumCoin() - 1);
//            }
//        }
//    }
//    /**
//     * Called only after confirming the username is valid (i.e. the user exists)
//     * Takes the username of Core.User.NormalUser and return a list of reviews written by the user.
//     */
//    public ArrayList<Review> getRevsOfUser(String username){
//        return this.UsertoRevs.get(username);
//    }
//
//    /**
//     * Called only after confirming the moviename is valid (i.e. the movie exists)
//     * Takes the name of Core.Movie and return a list of reviews for the Core.Movie.
//     */
//    public ArrayList<Review> getRevsOfMovie(String moviename){
//        return this.MovietoRevs.get(moviename);
//    }

    /**
     * update MovietoRevs by write_review
     * @param mname name of the movie
     * @param rev the review
     * @return ture iff successfully updated.
     */
    private boolean addMr(String mname, Review rev){
        if (this.MovietoRevs.containsKey(mname)) {
            this.MovietoRevs.get(mname).add(rev);
        }
        else if (! this.MovietoRevs.containsKey(mname)) {
            ArrayList<Review> lst = new ArrayList<>();
            lst.add(rev);
            this.MovietoRevs.put(mname, lst);
        }
        else {return false;}

        return true;
    }

    /**
     * update UsertoRevs by write_review,
     * @param uname the username of the user
     * @param rev the review
     * @return ture iff successfully updated.
     */
    private boolean addUr(String uname, Review rev){
        if (this.UsertoRevs.containsKey(uname)) {
            this.UsertoRevs.get(uname).add(rev);
        }
        else if (! this.UsertoRevs.containsKey(uname)) {
            ArrayList<Review> lst = new ArrayList<>();
            lst.add(rev);
            this.UsertoRevs.put(uname, lst);
        }
        else {return false;}

        return true;
    }


}
