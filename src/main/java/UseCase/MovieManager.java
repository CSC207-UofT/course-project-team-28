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
     * @param movieName name of Core.Movie
     * @param movieLink the link of the movie
     */
    public boolean addMovie(String movieName, String movieLink, HashMap<Object, Object> reviewMap, int numLikes) {
        Movie m = new Movie(movieName, movieLink, reviewMap, numLikes);
        this.Movies.add(m);
        return true;

    }

    /**
     * Add an instance of movie to the overall list of Movies
     * @param movieName name of Core.Movie
     * @param movieLink the link of the movie
     */
    public boolean addNewMovie(String movieName, String movieLink) {
        Movie m = new Movie(movieName, movieLink, new HashMap<>(), 0);
        this.Movies.add(m);
        return this.Movies.contains(m) && this.gateway.createNewMovie(movieName, movieLink);

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
     * Use movie_name and movie_link to find the whether a movie exists or not.
     * @param name the name of the movie
     * @param link the link of the movie
     * @return return true if a movie's name and matching link already exists in movie list. Otherwise, return false
     */
    public boolean IfMovieExist(String name, String link){

        for(Movie movie: Movies){
            if(movie.getMoviename().equals(name)){
                return movie.getLink().equals(link);
                }
            }
        return false;
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
//        profile.add(movie.getMovieName());
//        profile.add(movie.getMovieLink());
//        profile.add(movie.getReviewsContent());
//        profile.add(movie.getLikes());
//        return profile;
    }

//    /**
//     * delete an instance of movie from the overall list of Movies
//     * @param movie_name the name of this instance of Core.Movie
//     */
//    public boolean delete_movie(String movie_name) {
//        for (Core.Movie m : this.Movies){
//            if (m.movieName.equals(movie_name)){
//                this.Movies.remove(m);
//                return !this.Movies.contains(m);
//            }
//        }
//    }


    /**
     * Add a like to an instance of movie from the overall list of Movies
     * @param movieName the name of this instance of Core.Movie
     */
    public boolean likeMovie(String movieName) {
        Movie movie = this.getMovie(movieName);
        int like = movie.getLikes();
        movie.AddLike();

        return (movie.getLikes() - 1 == like) && this.gateway.editLikeToMovieFile(movieName, "Increase");

    }

    /**
     * Undo a like to an instance of movie from the overall list of Movies
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