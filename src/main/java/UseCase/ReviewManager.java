package UseCase;

import Entity.Review;
import InterfaceAdapter.Gateway;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ReviewManager (UserCase)
 * Stores and manages reviews
 */
public class ReviewManager {
    // map that stores reviews according to the movie that they were written for, the key is movieName,
    // and the value is an arraylist of all Review for that movie
    private final HashMap<String, ArrayList<Review>> movieToRevs;

    // map that stores reviews according to the user who wrote the review, the key is username,
    // and the value is an arraylist of all Review wrote by the user
    //private final HashMap<String, ArrayList<Review>> UserToRevs;

    // an arraylist that stores all the Reviews
    private final ArrayList<Review> reviewList;

    // record the total number of review stored in ReviewManager
    private int totalNumOfReview;

//    private final MovieManager movieManager;

    // stores the gateway
    private final GatewayInterface gateway = new Gateway();


    public ReviewManager() {
        this.movieToRevs = new HashMap<>();
//        this.UserToRevs = new HashMap<>();
        this.reviewList = new ArrayList<>();
//        this.movieManager = mm;
        this.totalNumOfReview = 0;
    }

    /**
     * Called only after confirming the movieName is valid (i.e. the movie exists)
     * Takes the name of Core.Movie and return a sorted arraylist of arrays, where each array stores information
     * of a single review.
     * The review with more coins is at the front of the list.
     * @param movieName the name of the movie.
     * @return an arraylist of arrays, where each array stores information of a single review in the form of
     *         [username of reviewer, movieName, reviewContent, numCoin, ID].
     */
    public ArrayList<Object[]> listRevsOfMovie(String movieName){
        ReviewSort rs = new ReviewSort();
        // set lst to empty array list, such that if the MovieToRevs map does not contain the key (when there does not
        // exist any review for that movie)
        ArrayList<Review> lst = new ArrayList<>();
        lst = this.movieToRevs.get(movieName);
        ArrayList<Review> sortedLst = rs.sortReviews(lst);
        ArrayList<Object[]> result = new ArrayList<>();
        // add content of reviews to temp
        if (sortedLst != null){
            for (Review rev : sortedLst){
                result.add(rev.getReviewInfo());
            }
        }
        return result;
    }

//    /**
//     * Called only after confirming the movieName is valid (i.e. the movie exists)
//     * Takes the name of Core.Movie and return a string form of reviews for the Core.Movie.
//     */
//    public String revsOfMovieString(String movieName){
//        ArrayList<Review> lst = this.MovieToRevs.get(movieName);
//        StringBuilder temp = new StringBuilder();
//        // add content of reviews to temp
//        if(lst != null){
//            for (Review rev : lst){
//                temp.append("\n[");
//                temp.append(rev.toString());
//                temp.append("]");
//            }
//            return String.valueOf(temp);
//        }
//        else {
//            temp.append("\n[");
//            temp.append("No review");
//            temp.append("]");
//            return String.valueOf(temp);
//        }
//    }


    /**
     * Called when initiate the system from database.
     * create a Review for review file data, add Review to MovieToRevs, UserToRevs, reviewList.
     * Return ture iff the review has been successfully created and added to MovieToRevs, UserToRevs, reviewList.
     * @param userName the username of the user who wrote the review
     * @param movieName the name of movie for which the review is wrote for
     * @param content the content of the review
     * @param numCoin the number of coins that the review received
     * @param ID the ID of the review
     * @return a boolean, return ture iff the review has been successfully created and added to MovieToRevs,
     *         UserToRevs, reviewList.
     */
    public boolean writeReview(String userName, String movieName, String content, int numCoin , int ID) {
        Review rev = new Review(userName, movieName, content, numCoin, ID);
        this.reviewList.add(rev);
        this.totalNumOfReview = this.getMaxRevId();
        return addMr(movieName, rev); // && addUr(userName, rev);
    }


    /**
     * Called when user writes a new review
     * create a new review, add Review to MovieToRevs, UserToRevs, reviewList, and record the Review in txt file.
     * Return ture iff the review has been successfully created and added to the txt file.
     * @param userName the username of the user who wrote the review
     * @param movieName the name of movie for which the review is wrote for
     * @param content the content of the review
     * @param numCoin the number of coins that the review received
     * @return a boolean, return ture iff the review has been successfully created and added to MovieToRevs,
     *         UserToRevs, reviewList, and database.
     */
    public boolean writeNewReview(String userName, String movieName, String content, int numCoin) {
        this.totalNumOfReview = this.totalNumOfReview + 1;
        Review rev = new Review(userName, movieName, content, numCoin, totalNumOfReview);
        this.reviewList.add(rev);
        // this.totalNumOfReview = reviewList.get(reviewList.size() - 1).getID();
        return addMr(movieName, rev) // && addUr(userName, rev)
                && this.gateway.createNewReview(userName, movieName, content, totalNumOfReview);
    }


    public int getReviewID(){return totalNumOfReview;    }

    public ArrayList<Review> getReviewList() {return reviewList;}

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


    /**
     * find the max review Id in reviewList
     * @return the max review Id in reviewList
     */
    private int getMaxRevId() {
        int maxId = 0;
        for (Review rev: this.reviewList){
            int currId = rev.getID();
            if (currId > maxId){
                maxId = currId;
            }
        }
        return maxId;
    }

    /**
     * should only be called when review id is valid.
     * when given ID of a review, return the information of the review.
     * @param reviewId id of the review
     * @return the review info in the array [reviewer, movie, reviewContent, numCoin, ID]
     */
    public Object[] getRevInfoById (int reviewId){
        for (Review review : this.reviewList){
            if (review.getID() == reviewId){
                return review.getReviewInfo();
            }
        }
        return null;
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
//        return this.UserToRevs.get(username);
//    }

//    /**
//     * Called only after confirming the movieName is valid (i.e. the movie exists)
//     * Takes the name of Core.Movie and return a list of reviews for the Core.Movie.
//     */
//    public ArrayList<Review> getRevsOfMovie(String movieName){
//        return this.MovieToRevs.get(movieName);
//    }

    /**
     * update MovieToRevs by write_review
     * @param mName name of the movie
     * @param rev the review
     * @return ture iff successfully updated.
     */
    private boolean addMr(String mName, Review rev){
        if (this.movieToRevs.containsKey(mName)) {
            this.movieToRevs.get(mName).add(rev);
        }
        else if (! this.movieToRevs.containsKey(mName)) {
            ArrayList<Review> lst = new ArrayList<>();
            lst.add(rev);
            this.movieToRevs.put(mName, lst);
        }
        else {return false;}

        return true;
    }

    /**
     * Update the MovieToRevs, with the new movieName as key and empty arraylist as value.
     * @param mName movie name of the newly added movie
     */
    public void updateMovieToRevsKey(String mName){
        if (! this.movieToRevs.containsKey(mName)) {
            ArrayList<Review> lst = new ArrayList<>();
            this.movieToRevs.put(mName, lst);
        }
    }

//    /**
//     * update UserToRevs by write_review,
//     * @param uname the username of the user
//     * @param rev the review
//     * @return ture iff successfully updated.
//     */
//    private boolean addUr(String uname, Review rev){
//        if (this.UserToRevs.containsKey(uname)) {
//            this.UserToRevs.get(uname).add(rev);
//        }
//        else if (! this.UserToRevs.containsKey(uname)) {
//            ArrayList<Review> lst = new ArrayList<>();
//            lst.add(rev);
//            this.UserToRevs.put(uname, lst);
//        }
//        else {return false;}
//
//        return true;
//    }


}
