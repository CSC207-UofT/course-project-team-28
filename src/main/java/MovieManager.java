import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the entire system of movies
 */
public class MovieManager {

    private ArrayList<Movie> Movies;

    /**
     * Creates a MovieManager with a list of movies are empty
     */
    public MovieManager(){
        this.Movies = new ArrayList<>();
    }

    /**
     * Add an instance of movie to the overall list of Movies
     * @param moviename name of Movie
     * @param movielink the link of the movie
     */
    public boolean add_movie(String moviename, String movielink, HashMap<Object, Object> map, int i) {
        Movie m = new Movie(moviename, movielink, map, i);
        this.Movies.add(m);
        return this.Movies.contains(m);

    }

    /**
     * get an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     */
    public Movie get_movie(String movie_name) {
        for (Movie m : this.Movies) {
            if (m.moviename.equals(movie_name)){
                return m;
            }
        }
        return null;
    }

    /**
     * Add a review to an instance of movie
     * @param movie_name name of an instance of Movie
     */
    public void add_review_to_movie( String movie_name, Review review) {
        Movie movie = this.get_movie(movie_name);

        movie.AddReview(review);
    }

    /**
     * should be called only when movie_name exists
     * get the profile of an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     * @return profile a String including the profile of the movie.
     */
    public String get_movieprofile(String movie_name) {
        Movie movie = this.get_movie(movie_name);
        return movie.toStringnoreview();

//        ArrayList<Object> profile = new ArrayList<>();
//        profile.add(movie.getMoviename());
//        profile.add(movie.getMovielink());
//        profile.add(movie.getReviewsContnet());
//        profile.add(movie.getLikes());
//        return profile;
    }

//    /**
//     * delete an instance of movie from the overall list of Movies
//     * @param movie_name the name of this instance of Movie
//     */
//    public boolean delete_movie(String movie_name) {
//        for (Movie m : this.Movies){
//            if (m.moviename.equals(movie_name)){
//                this.Movies.remove(m);
//                return !this.Movies.contains(m);
//            }
//        }
//    }


    /**
     * Add an like to an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     */
    public void like_movie(String movie_name) {
        Movie movie = this.get_movie(movie_name);
        movie.AddLike();

    }

    /**
     * Undo an like to an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     */
    public void undolike_movie(String movie_name) {
        Movie movie = this.get_movie(movie_name);
        movie.UndoLike();

    }

    /**
     * Represents a MovieManager as a String containing all Movie names in the system.
     * @return a list of movie names separated by commas.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Movie m : this.Movies) {
            res.append(m.getMoviename());
            res.append(", ");
        }
        return res.toString(); //includes a trailing ", "
    }


}