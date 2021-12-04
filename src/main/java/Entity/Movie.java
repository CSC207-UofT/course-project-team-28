package Entity;//import java.util.ArrayList;
import java.util.HashMap;

public class Movie {
    protected String moviename;
    protected String movielink;
    public String category;
    public int Likes;

    /**
     * "moviereviews" is a Hashmap with review content as key and the corresponding user as value.
     * "Likes" stores the number of likes a movie received
     */

    public Movie(String moviename, String movielink, int i) {
        this.moviename = moviename;
        this.movielink = movielink;
        this.Likes = i;
    }

    /**
     * Getter Methods for Movie Class.
     */

    public String toString() {
        return "Movie name: " + this.moviename + ", \n" + "Link: " + this.movielink + ", \n" + "Category: " + this.category
                + ", \n" + "# of Likes: " + this.Likes;
    }


    public String getMoviename(){
        return this.moviename;
    }

    public String getLink(){
        return this.movielink;
    }

    public String getCategory() {return this.category; }

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
