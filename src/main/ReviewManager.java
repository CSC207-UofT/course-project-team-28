import java.util.HashMap;
import java.util.ArrayList;


public class ReviewManager {
    private HashMap<String, ArrayList<Review>> MovietoRevs;
    private HashMap<String, ArrayList<Review>> UsertoRevs;
    private ArrayList<Review> lst;


    public ReviewManager(){
        this.MovietoRevs = new HashMap<>();
        this.UsertoRevs = new HashMap<>();
        this.lst = new ArrayList<>();
    }

    /**
     * Called only after confirming the username is valid (i.e. the user exists)
     * Takes the username of NormalUser and return a list of reviews written by the user.
     */
    public ArrayList<Review> RevsofUser(String username){

    }

    /**
     * Called only after confirming the moviename is valid (i.e. the movie exists)
     * Takes the name of Movie and return a list of reviews for the Movie.
     */
    public ArrayList<Review> RevsofMovie(String moviename){

    }

    /**
     * create a Review, add Review to MovietoRevs, UsertoRevs, lst, and record the Review in txt file.
     * Return ture iff the review has been successfully created and added to the txt file.
     */
    public boolean write_review(String uname, String mname, String content) {
        Review rev = new Review(uname, mname, content);

        // add the review in file

        // update MovietoRevs
        if (this.MovietoRevs.containsKey(mname)) {
            this.MovietoRevs.get(mname).add(rev);
        }
        else {
            ArrayList<Review> lst = new ArrayList<>();
            lst.add(rev);
            this.MovietoRevs.put(mname, lst);
        }

    }

    /**
     * delete a Review from MovietoRevs, UsertoRevs, lst, and delete the Review in txt file.
     * Return ture iff the review has been successfully deleted from the lst/maps and the txt file.
     */
    public boolean delete_review() {

    }

}
