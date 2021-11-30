package Core;//import java.util.ArrayList;
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

    public Movie(String moviename, String movielink, HashMap<Object, Object> map, int i) {
        this.moviename = moviename;
        this.movielink = movielink;
        this.moviereviews = map;
        this.Likes = i;
    }

    /**
     * Method AddReview take only a parameter of Class review, which will store the
     * review content as key and the corresponding user as value. Once a review is written, it
     * should first be stored in the Core.Review Class and then Core.Movie Class will take data from Core.Review
     * Class and store them.
     */

    public void AddReview(Review review){
        this.moviereviews.put(review.review_content, review.reviewer);
    }

    /**
     * Getter Methods for Core.Movie Class.
     */

    public String toString() {
        StringBuilder reviews = new StringBuilder();
        for (var entry : this.moviereviews.entrySet()) {
            reviews.append("[");
            reviews.append(entry.getKey());
            reviews.append("---");
            reviews.append(entry.getValue());
            reviews.append("]\n");
        }


            return "Core.Movie name: " + this.moviename + ", \n" + "Link: " + this.movielink + ", \n" + "Reviews: " +
                    reviews + "\n" + "# of Likes: " + this.Likes;
    }


    public String toStringnoreview() {

        return "Core.Movie name: " + this.moviename + ", \n" + "Link: " + this.movielink + ", \n" +
                "# of Likes: " + this.Likes;
    }

    public String getMoviename(){
        return this.moviename;
    }

    public String getLink(){
        return this.movielink;
    }


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

//    Get only the review contents of a movie and return them in an ArrayList
//    public ArrayList<String> getReviewsContnet(){
//        ArrayList<String> contents = new ArrayList<>();
//        for (Object content: moviereviews.keySet()){
//            contents.add((String) content);
//        }
//        return contents;
//
//    }

}
