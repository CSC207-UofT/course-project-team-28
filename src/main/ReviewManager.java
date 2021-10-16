import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;


public class ReviewManager {
    private final HashMap<String, ArrayList<Review>> MovietoRevs;
    private final HashMap<String, ArrayList<Review>> UsertoRevs;
    private final ArrayList<Review> lst;
    private static int tot_num;
    private static WriteReview wr;


    public ReviewManager() throws IOException {
        this.MovietoRevs = new HashMap<>();
        this.UsertoRevs = new HashMap<>();
        this.lst = new ArrayList<>();
        tot_num = 0;
        wr = new WriteReview();

        // initialize from the database
        ArrayList<Review> data = wr.get_object_from_file();
        if (data.size() > 0) {
            tot_num = data.get(data.size()-1).ID;
        }
        for (int i = 0; i < data.size(); i++){
            add_mr(data.get(i).movie, data.get(i));
            add_ur(data.get(i).reviewer, data.get(i));
            this.lst.add(data.get(i));
        }
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
    public boolean write_review(String uname, String mname, String content) throws IOException {
        Review rev = new Review(uname, mname, content, tot_num);
        tot_num = tot_num + 1;

        // add the review in file by a helper method
        boolean write = wr.create_file(rev);

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
    // public boolean delete_review() {}

}
