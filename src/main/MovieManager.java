import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire system of movies
 */
public class MovieManager {

    private List<Movie> Movies;

    /**
     * Creates a MovieManager with a list of movies are empty
     */
    public MovieManager() {
        Movies = new ArrayList<>();
    }

    /**
     * Add an instance of movie to the overall list of Movies
     * @param movie instance of Movie
     */
    public void add_movie(Movie movie) {
        Movies.add(movie);
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
     * delete an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Movie
     */
    public void delete_movie(String movie_name) {
        Movies.removeIf(m -> m.moviename.equals(movie_name));
    }

    /**
     * Represents a MovieManager as a String containing all Movie names in the system.
     * @return a list of movie names separated by commas.
     */
    @Override
    public String toString() {
        String res = "";
        for (Movie m : Movies) {
            res += m.toString;
            res += ", ";
        }
        return res; //includes a trailing ", "
    }

}
