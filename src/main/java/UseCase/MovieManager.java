package UseCase;

import Core.Movie;
import Core.Review;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the entire system of movies
 */
public class MovieManager {

    private final ArrayList<Movie> Movies;

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
    public boolean add_movie(String moviename, String movielink, HashMap<Object, Object> map, int i) {
        Movie m = new Movie(moviename, movielink, map, i);
        this.Movies.add(m);
        return this.Movies.contains(m);

    }

    /**
     * get an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Core.Movie
     */
    public Movie get_movie(String movie_name) {
        for (Movie m : this.Movies) {
            if (m.getMoviename().equals(movie_name)){
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
     * @param movie_name name of an instance of Core.Movie
     */
    public void add_review_to_movie( String movie_name, Review review) {
        Movie movie = this.get_movie(movie_name);

        movie.AddReview(review);
    }

    /**
     * should be called only when movie_name exists
     * get the profile of an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Core.Movie
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
     * @param movie_name the name of this instance of Core.Movie
     */
    public void like_movie(String movie_name) {
        Movie movie = this.get_movie(movie_name);
        movie.AddLike();

    }

    /**
     * Undo an like to an instance of movie from the overall list of Movies
     * @param movie_name the name of this instance of Core.Movie
     */
    public void undolike_movie(String movie_name) {
        Movie movie = this.get_movie(movie_name);
        movie.UndoLike();

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