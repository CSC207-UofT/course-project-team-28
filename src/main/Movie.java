import java.util.HashMap;

public class Movie {
    public String moviename;
    public String movielink;
    public HashMap<String, String> moviereviews;
    public int Likes;

    /**
     * "moviereviews" is a Hashmap with review content as key and the corresponding user as value.
     * "Likes" stores the number of likes a movie received.
     */

    public Movie(String moviename, String movielink){
        this.moviename = moviename;
        this.movielink = movielink;
        this.moviereviews = new HashMap<>();
        this.Likes = 0;
    }

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
     * Class and store them.
     */

    public void AddLike(){
        this.Likes += 1;
    }
}
