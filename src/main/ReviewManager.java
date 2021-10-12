import java.util.HashMap;
import java.util.ArrayList;


public class ReviewManager {
    private final HashMap<String, ArrayList<Review>> MovietoRevs;
    private final HashMap<String, ArrayList<Review>> UsertoRevs;
    private final ArrayList<Review> lst;


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
        return this.UsertoRevs.get(username);
    }

    /**
     * Called only after confirming the moviename is valid (i.e. the movie exists)
     * Takes the name of Movie and return a list of reviews for the Movie.
     */
    public ArrayList<Review> RevsofMovie(String moviename){
        return this.MovietoRevs.get(moviename);
    }

    /**
     * create a Review, add Review to MovietoRevs, UsertoRevs, lst, and record the Review in txt file.
     * Return ture iff the review has been successfully created and added to the txt file.
     */
    public boolean write_review(String uname, String mname, String content) {
        boolean write = true;
        Review rev = new Review(uname, mname, content);

        // add the review in file by a helper method


        write = add_mr(mname, rev) && write; // update MovietoRevs

        write = add_ur(uname, rev) && write; // update UsertoRevs

        // update lst
        this.lst.add(rev);

        return write;
    }

    /**
     * update MovietoRevs by write_review, return ture iff successfully updated.
     */
    private boolean add_mr(String mname, Review rev){
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
    private boolean add_ur(String uname, Review rev){
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

    /**
     * delete a Review from MovietoRevs, UsertoRevs, lst, and delete the Review in txt file.
     * Return ture iff the review has been successfully deleted from the lst/maps and the txt file.
     */
    public boolean delete_review() {

    }

}
