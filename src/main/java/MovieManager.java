import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire system of movies
 */
public class MovieManager {

    private final List<Movie> Movies;
    private final WriteMovie wm = new WriteMovie();

    /**
     * Creates a MovieManager with a list of movies are empty
     */
    public MovieManager() throws IOException {
        this.Movies = wm.get_object_from_file();
    }

    /**
     * Add an instance of movie to the overall list of Movies
     * @param moviename name of Movie
     * @param movielink the link of the movie
     */
    public boolean add_movie(String moviename, String movielink) throws IOException {
        Movie m = new Movie(moviename, movielink);
        boolean a = wm.create_file(m);
        this.Movies.add(m);
        return (a && this.Movies.contains(m));

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
     * @param review_content string content of the review
     */
    public void add_review_to_movie(String user_name, String movie_name, String review_content) throws IOException {
        Movie movie = this.get_movie(movie_name);
        Review review = new Review(user_name, movie_name, review_content, 99);
        movie.AddReview(review);
        wm.add_review_to_file(review);
    }

    /**
     * should be called only when movie_name exists
     * get the profile of an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     * @return profile a String including the profile of the movie.
     */
    public String get_movieprofile(String movie_name) {
        Movie movie = this.get_movie(movie_name);
        return movie.toString();

//        ArrayList<Object> profile = new ArrayList<>();
//        profile.add(movie.getMoviename());
//        profile.add(movie.getMovielink());
//        profile.add(movie.getReviewsContnet());
//        profile.add(movie.getLikes());
//        return profile;
    }

    /**
     * delete an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     */
    public boolean delete_movie(String movie_name) throws IOException {
        for (Movie m : this.Movies){
            if (m.moviename.equals(movie_name)){
                boolean a = wm.delete_file(m);
                this.Movies.remove(m);
                return(a && !this.Movies.contains(m));
            }
        }
        return false;
    }


    /**
     * Add an like to an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     */
    public void like_movie(String movie_name) throws IOException {
        Movie movie = this.get_movie(movie_name);
        movie.AddLike();
        wm.add_like_to_file(movie);
    }

    /**
     * Undo an like to an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     */
    public void undolike_movie(String movie_name) throws IOException {
        Movie movie = this.get_movie(movie_name);
        movie.UndoLike();
        wm.add_like_to_file(movie);
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