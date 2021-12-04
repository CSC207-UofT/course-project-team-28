package UseCase;

import Entity.Review;
import InterfaceAdapter.Gateway;

import java.util.HashMap;
import java.util.ArrayList;


public class ReviewManager {
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
     * Called only after confirming the username is valid (i.e. the user exists)
     * Takes the username of Core.User.NormalUser and return a list of reviews written by the user.
     */
    public ArrayList<Review> revsOfUser(String username){
        return this.UsertoRevs.get(username);
    }

    /**
     * Called only after confirming the moviename is valid (i.e. the movie exists)
     * Takes the name of Core.Movie and return a list of reviews for the Core.Movie.
     */
    public ArrayList<Review> revsOfMovie(String moviename){
        return this.MovietoRevs.get(moviename);
    }

    /**
     * Called only after confirming the moviename is valid (i.e. the movie exists)
     * Takes the name of Core.Movie and return a string form of reviews for the Core.Movie.
     */
    public String revsOfMovieString(String moviename){
        ArrayList<Review> lst = this.MovietoRevs.get(moviename);
        StringBuilder temp = new StringBuilder();

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
     * create a Review for review file data, add Review to MovietoRevs, UsertoRevs, lst, and record the Core.Review in txt file.
     * Return ture iff the review has been successfully created and added to the txt file.
     */
    public boolean writeReview(String userName, String movieName, String content, int numCoin , int ID) {
        Review rev = new Review(userName, movieName, content, numCoin, ID);

        // add the review in file by a helper method
        //Framework.WriteFiles.WriteReview newwr = new Framework.WriteFiles.WriteReview();
        //boolean write = newwr.create_file(rev);


        this.reviewList.add(rev);
        this.totalNumOfReview = reviewList.get(reviewList.size()-1).getID();

        return addMr(movieName, rev) && addUr(userName, rev);
    }


    /**
     * create a new review, add Review to MovietoRevs, UsertoRevs, lst, and record the Review in txt file.
     * Return ture iff the review has been successfully created and added to the txt file.
     */
    public boolean writeNewReview(String userName, String movieName, String content, int numCoin) {
        this.totalNumOfReview = this.totalNumOfReview + 1;
        Review rev = new Review(userName, movieName, content, numCoin, totalNumOfReview);

            // add the review in file by a helper method
            //Framework.WriteFiles.WriteReview newwr = new Framework.WriteFiles.WriteReview();
            //boolean write = newwr.create_file(rev);


        this.reviewList.add(rev);
        this.totalNumOfReview = reviewList.get(reviewList.size()-1).getID();

        return addMr(movieName, rev) && addUr(userName, rev) && this.gateway.createNewReview(userName, movieName, content, totalNumOfReview);
    }


    public int getReviewID(){
        return totalNumOfReview;
    }



    /**
     * find a review with review_id, and add 1 coin
     */
    public boolean addCoin(int review_id) {
        int coin = 0;
        Review review1 = new Review("", "", "", 0, 0);
        for (Review review : this.reviewList){
            if (review.getID() == review_id){
                coin = review.getnumCoin();
                review1 = review;
                review.setNumCoin(review.getnumCoin() + 1);
            }
        }
        return review1.getnumCoin() - 1 == coin;
    }

    /**
     * find a review with review_id, and delete 1 coin
     */
    public void reduceCoin(int review_id) {
        for (Review review : this.reviewList){
            if (review.getID() == review_id){
                review.setNumCoin(review.getnumCoin() - 1);
            }
        }
    }

    /**
     * update MovietoRevs by write_review, return ture iff successfully updated.
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
     * update MovietoRevs by write_review, return ture iff successfully updated.
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


    /*
     * delete a Core.Review from MovietoRevs, UsertoRevs, lst, and delete the Core.Review in txt file.
     * Return ture iff the review has been successfully deleted from the lst/maps and the txt file.
     */
    // public boolean deleteReview() {}

}
