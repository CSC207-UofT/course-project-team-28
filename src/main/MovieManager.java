import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire system of movies
 */
public class MovieManager {

    private final List<Movie> Movies;

    /**
     * Creates a MovieManager with a list of movies are empty
     */
    public MovieManager() {
        Movies = new ArrayList<>();
    }

    /**
     * Add an instance of movie to the overall list of Movies
     * @param moviename name of Movie
     * @param movielink the link of the movie
     */
    public void add_movie(String moviename, String movielink) throws IOException {
        Movie m = new Movie(moviename, movielink);
        Movies.add(m);
    }

    /**
     * get an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     */
    public Movie get_movie(String movie_name) {
        for (Movie m : Movies) {
            if (m.moviename.equals(movie_name)){
                return m;
            }
        }
        return null;
    }

    /**
     * get the profile of an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     * @return profile an array including the profile of the movie.
     */
    public ArrayList<Object> get_movieprofile(String movie_name) {
        Movie movie = this.get_movie(movie_name);
        ArrayList<Object> profile = new ArrayList<>();
        profile.add(movie.getMoviename());
        profile.add(movie.getMovielink());
        profile.add(movie.getReviewsContnet());
        profile.add(movie.getLikes());
        return profile;
    }

    /**
     * delete an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     */
    public void delete_movie(String movie_name) {
        Movies.removeIf(m -> m.moviename.equals(movie_name));
    }


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
        String res = "";
        for (Movie m : Movies) {
            res += m.getMoviename();
            res += ", ";
        }
        return res; //includes a trailing ", "
    }

}
