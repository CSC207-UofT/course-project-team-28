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
    private int currMaxRevId;

    // stores the gateway
    private final GatewayInterface gateway = new Gateway();


    public ReviewManager() {
        this.movieToRevs = new HashMap<>();
//        this.UserToRevs = new HashMap<>();
        this.reviewList = new ArrayList<>();
//        this.movieManager = mm;
        this.currMaxRevId = 0;
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
        ArrayList<Review> lst = this.movieToRevs.get(movieName);
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


    /**
     * Called when initiate the system from database.
     * create a Review for review file data, add Review to MovieToRevs, UserToRevs, reviewList.
     * Return ture iff the review has been successfully created and added to MovieToRevs, UserToRevs, reviewList.
     * @param userName the username of the user who wrote the review
     * @param movieName the name of movie for which the review is wrote for
     * @param content the content of the review
     * @param numCoin the number of coins that the review received
     * @param ID the ID of the review
     */
    public void writeReview(String userName, String movieName, String content, int numCoin , int ID) {
        Review rev = new Review(userName, movieName, content, numCoin, ID);
        this.reviewList.add(rev);
        this.currMaxRevId = this.getMaxRevId();
        addMr(movieName, rev); // && addUr(userName, rev);
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
        this.currMaxRevId = this.currMaxRevId + 1;
        Review rev = new Review(userName, movieName, content, numCoin, currMaxRevId);
        this.reviewList.add(rev);
        return addMr(movieName, rev) // && addUr(userName, rev)
                && this.gateway.createNewReview(userName, movieName, content, currMaxRevId);
    }

    /**
     * ONLY call in test
     * @return currMaxRevId
     */
    public int getCurrMaxRevId(){return currMaxRevId;}

    /**
     * ONLY call in test
     * @return reviewList
     */
    public ArrayList<Review> getReviewList() {return reviewList;}

    /**
     * find a review with review_id, and add 1 coin
     * @param reviewId ID of the review
     * @return ture iff the coin is added to the review
     */
    public boolean addCoin(int reviewId) {
        int coin = 0;
        int coinAfter = 0;
        for (Review review : this.reviewList){
            if (review.getID() == reviewId){
                coin = review.getNumCoin();
                review.setNumCoin(review.getNumCoin() + 1);
                coinAfter = review.getNumCoin();
            }
        }
        return coinAfter - 1 == coin && this.gateway.editCoin(reviewId);
    }


    /**
     * find the max review ID in reviewList
     * @return the max review ID in reviewList
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

}
