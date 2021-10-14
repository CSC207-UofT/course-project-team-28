import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Movie {
    public String moviename;
    public String movielink;
    public HashMap<String, String> moviereviews;
    public int Likes;
    public WriteMovie wm = new WriteMovie();

    /**
     * "moviereviews" is a Hashmap with review content as key and the corresponding user as value.
     * "Likes" stores the number of likes a movie received.
     */

    public Movie(String moviename, String movielink) throws IOException {
        this.moviename = moviename;
        this.movielink = movielink;
        this.moviereviews = new HashMap<>();
        this.Likes = 0;
        wm.create_file(this);
    }

    /**
     * Helper method for WriteMovie.
     */
    public void GetReviewandLike(HashMap map, int i){
        this.moviereviews = map;
        this.Likes = i;
    }

    public void AddReview(Review review){
        this.moviereviews.put(review.review_content, review.reviewer);
    }

    /**
     * Method AddReview take only a parameter of Class review, which will store the
     * review content as key and the corresponding user as value. Once a review is written, it
     * should first be stored in the Review Class and then Movie Class will take data from Review
     * Class and store them
     */

    public void AddLike(){
        this.Likes += 1;
    }

    /**
     * create ArrayList of Movies [Movie1, Movie2, ...]
     */
    public ArrayList<Object> getObject() throws IOException {
        return wm.get_object_from_file();
    }

    public void delete_file() throws IOException{
        wm.delete_file(this);
    }
}
