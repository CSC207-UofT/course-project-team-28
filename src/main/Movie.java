import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Movie {
    protected String moviename;
    protected String movielink;
    public HashMap<Object, Object> moviereviews;
    public int Likes;

    /**
     * "moviereviews" is a Hashmap with review content as key and the corresponding user as value.
     * "Likes" stores the number of likes a movie received
     */

    public Movie(String moviename, String movielink) throws IOException {
        this.moviename = moviename;
        this.movielink = movielink;
        this.moviereviews = new HashMap<>();
        this.Likes = 0;
    }

    /**
     * Helper method for WriteMovie.
     */
    public void GetReviewandLike(HashMap<Object, Object> map, int i){
        this.moviereviews = map;
        this.Likes = i;
    }

    /**
     * Method AddReview take only a parameter of Class review, which will store the
     * review content as key and the corresponding user as value. Once a review is written, it
     * should first be stored in the Review Class and then Movie Class will take data from Review
     * Class and store them.
     */

    public void AddReview(Review review){
        this.moviereviews.put(review.review_content, review.reviewer);
    }

    /**
     * Getter Methods for Movie Class.
     */

    public String toString() {return this.moviename; }

    public String getMoviename(){
        return this.moviename;
    }

    public String getMovielink(){
        return this.movielink;
    }

    public HashMap<Object, Object> getMovieReviews() {return this.moviereviews; }

    public int getLikes() {return this.Likes; }

    /**
     * Add or retrieve a like to a movie.
     */

    public void AddLike(){
        this.Likes += 1;
    }

    public void UndoLike(){
        this.Likes -= 1;
    }

    /**
     * Get only the review contents of a movie and return them in an ArrayList
     */

    public ArrayList<String> getReviewsContnet(){
        ArrayList<String> contents = new ArrayList<String>();
        for (Object content: moviereviews.keySet()){
            contents.add((String) content);
        }
        return contents;

    }

}
