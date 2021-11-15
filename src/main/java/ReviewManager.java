import java.util.HashMap;
import java.util.ArrayList;


public class ReviewManager {
    private HashMap<String, ArrayList<Review>> MovietoRevs;
    private HashMap<String, ArrayList<Review>> UsertoRevs;
    private ArrayList<Review> lst;
    private static int tot_num;
    private MovieManager mm;


    public ReviewManager(MovieManager mm) {
        this.MovietoRevs = new HashMap<>();
        this.UsertoRevs = new HashMap<>();
        this.lst = new ArrayList<>();
        this.mm = mm;
        tot_num = 0;
    }


    /**
     * Called only after confirming the username is valid (i.e. the user exists)
     * Takes the username of NormalUser and return a list of reviews written by the user.
     */
    public ArrayList<Review> RevsOfUser(String username){
        return this.UsertoRevs.get(username);
    }

    /**
     * Called only after confirming the moviename is valid (i.e. the movie exists)
     * Takes the name of Movie and return a list of reviews for the Movie.
     */
    public ArrayList<Review> RevsOfMovie(String moviename){
        return this.MovietoRevs.get(moviename);
    }

    /**
     * Called only after confirming the moviename is valid (i.e. the movie exists)
     * Takes the name of Movie and return a string form of reviews for the Movie.
     */
    public String RevsOfMovieString(String moviename){
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
     * create a Review, add Review to MovietoRevs, UsertoRevs, lst, and record the Review in txt file.
     * Return ture iff the review has been successfully created and added to the txt file.
     */
    public boolean writeReview(String uname, String mname, String content, int numCoin , int ID) {
        Review rev;
        if (ID == -1){
            tot_num = tot_num + 1;
            rev = new Review(uname, mname, content, numCoin, tot_num);

            // add the review in file by a helper method
            //WriteReview newwr = new WriteReview();
            //boolean write = newwr.create_file(rev);

        }
        else{
            rev = new Review(uname, mname, content, numCoin, ID);

            // add the review in file by a helper method
            //WriteReview newwr = new WriteReview();
            //boolean write = newwr.create_file(rev);
        }
        this.lst.add(rev);
        tot_num = lst.get(lst.size()-1).ID;

        if (this.mm.get_movie(mname) != null){
            this.mm.add_review_to_movie(mname, rev);
        }

        return addMr(mname, rev) && addUr(uname, rev);
    }


    public int getReviewID(){
        return tot_num;
    }



    /**
     * find a review with review_id, and add 1 coin
     */
    public void addCoin(int review_id) {
        for (Review review : this.lst){
            if (review.ID == review_id){
                review.numCoin = review.numCoin + 1;
            }
        }
    }

    /**
     * find a review with review_id, and delete 1 coin
     */
    public void reduceCoin(int review_id) {
        for (Review review : this.lst){
            if (review.ID == review_id){
                review.numCoin = review.numCoin - 1;
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
     * delete a Review from MovietoRevs, UsertoRevs, lst, and delete the Review in txt file.
     * Return ture iff the review has been successfully deleted from the lst/maps and the txt file.
     */
    // public boolean deleteReview() {}

}
