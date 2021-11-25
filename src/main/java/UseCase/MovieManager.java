package UseCase;

import Entity.Movie;
import Entity.Review;
import InterfaceAdapter.Gateway;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the entire system of movies
 */
public class MovieManager {

    private final ArrayList<Movie> Movies;
    private final Gateway gateway = new Gateway();

    /**
     * Creates a UseCase.MovieManager with a list of movies are empty
     */
    public MovieManager(){
        this.Movies = new ArrayList<>();
    }

    /**
     * Add an instance of movie to the overall list of Movies
     * @param moviename name of Core.Movie
     * @param movielink the link of the movie
     */
    public void addMovie(String moviename, String movielink, HashMap<Object, Object> reviewMap, int numLikes) {
        Movie m = new Movie(moviename, movielink, reviewMap, numLikes);
        this.Movies.add(m);

    }

    /**
     * Add an instance of movie to the overall list of Movies
     * @param moviename name of Core.Movie
     * @param movielink the link of the movie
     */
    public boolean addNewMovie(String moviename, String movielink) {
        Movie m = new Movie(moviename, movielink, new HashMap<>(), 0);
        this.Movies.add(m);
        return this.Movies.contains(m) && this.gateway.createNewMovie(moviename, movielink);

    }


    /**
     * get an instance of movie from the overall list of Movies
     * @param movieName the name of this instance of Core.Movie
     */
    public Movie getMovie(String movieName) {
        for (Movie m : this.Movies) {
            if (m.getMoviename().equals(movieName)){
                return m;
            }
        }
        return null;
    }

    /**
     * Add a review to an instance of movie
     * @param movieName name of an instance of Core.Movie
     */
    public void addReviewToMovie(String movieName, Review review) {
        Movie movie = this.getMovie(movieName);

        movie.AddReview(review);
    }

    /**
     * should be called only when movie_name exists
     * get the profile of an instance of movie from the overall list of Movies
     * @param movieName the name of this instance of Core.Movie
     * @return profile a String including the profile of the movie.
     */
    public String getMovieProfile(String movieName) {
        Movie movie = this.getMovie(movieName);
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
//     * @param movie_name the name of this instance of Core.Movie
//     */
//    public boolean delete_movie(String movie_name) {
//        for (Core.Movie m : this.Movies){
//            if (m.moviename.equals(movie_name)){
//                this.Movies.remove(m);
//                return !this.Movies.contains(m);
//            }
//        }
//    }


    /**
     * Add an like to an instance of movie from the overall list of Movies
     * @param movieName the name of this instance of Core.Movie
     */
    public boolean likeMovie(String movieName) {
        Movie movie = this.getMovie(movieName);
        int like = movie.getLikes();
        movie.AddLike();

        return (movie.getLikes() - 1 == like) && this.gateway.editLikeToMovieFile(movieName, "Increase");

    }

    /**
     * Undo an like to an instance of movie from the overall list of Movies
     * @param movieName the name of this instance of Core.Movie
     */
    public boolean undolikeMovie(String movieName) {
        Movie movie = this.getMovie(movieName);
        int like = movie.getLikes();
        movie.UndoLike();

        return (movie.getLikes() + 1 == like) && this.gateway.editLikeToMovieFile(movieName, "Decrease");

    }

    /**
     * Represents a UseCase.MovieManager as a String containing all Core.Movie names in the system.
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

    public ArrayList<Movie> getMovies(){
        return Movies;
    }

}