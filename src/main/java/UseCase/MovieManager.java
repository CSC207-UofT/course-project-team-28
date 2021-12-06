package UseCase;

import Entity.Movie;
import InterfaceAdapter.Gateway;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire system of movies
 */
public class MovieManager {

    private final ArrayList<Movie> Movies;
    private final GatewayInterface gateway = new Gateway();

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
    public boolean addMovie(String movieName, String movieLink, String category, int numLikes) {
        Movie m = new Movie(movieName, movieLink, numLikes, category);
        this.Movies.add(m);
        return true;

    }

    /**
     * Add an instance of movie to the overall list of Movies
     * @param movieName name of Core.Movie
     * @param movieLink the link of the movie
     */
    public boolean addNewMovie(String movieName, String movieLink, String category) {
        Movie m = new Movie(movieName, movieLink, 0, category);
        this.Movies.add(m);
        return this.Movies.contains(m) && this.gateway.createNewMovie(movieName, movieLink, category);

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
     * get a list of all movieNames from the list of Movies
     */
    public List<String> getMovieNames() {
        List<String> list = new ArrayList<>();
        for (Movie m : this.Movies) {
            list.add(m.getMoviename());
            }
        return list;
        }


    /**
     * Use movie_name and movie_link to find the whether a movie exists or not.
     * @param name the name of the movie
     * @param link the link of the movie
     * @return return true if a movie's name and matching link already exists in movie list. Otherwise, return false
     */
    public boolean IfMovieExist(String name, String link){

        for(Movie movie: Movies){
            if(movie.getMoviename().equals(name) || movie.getLink().equals(link)){
                return true;
                }
            }
        return false;
    }


    /**
     * should be called only when movie_name exists
     * get the profile of an instance of movie from the overall list of Movies
     * @param movieName the name of this instance of Core.Movie
     * @return profile a String including the profile of the movie.
     */
    public String getMovieProfile(String movieName) {
        Movie movie = this.getMovie(movieName);
        return movie.toString();
    }

      /**
       * delete an instance of movie from the overall list of Movies
       * @param movieName the name of this instance of Movie
       */
      public boolean deleteMovie(String movieName) {
          String category = this.getMovieCategory(movieName);
          for (Movie m : this.Movies){
              if (m.getMoviename().equals(movieName)){
                  this.Movies.remove(m);
                  this.gateway.deleteMovie(movieName, category);
                  return !this.Movies.contains(m);
              }
          }
          return false;
      }

    /**
     * Use movie_name and movie_link to find the movie's category
     * @param name the name of the movie
     * @return return the category of the movie, null if movie not found
     */
    public String getMovieCategory(String name){
        for (Movie movie: Movies){
            if (movie.getMoviename().equals(name)){
                return movie.category;
            }
        }
        return null;
    }


    /**
     * Add a like to an instance of movie from the overall list of Movies
     * @param movieName the name of this instance of Core.Movie
     */
    public boolean likeMovie(String movieName) {
        String category = this.getMovieCategory(movieName);
        Movie movie = this.getMovie(movieName);
        int like = movie.getLikes();
        movie.AddLike();

        return (movie.getLikes() - 1 == like) && this.gateway.editLikeToMovieFile(movieName, "Increase", category);

    }

    /**
     * Undo a like to an instance of movie from the overall list of Movies
     * @param movieName the name of this instance of Core.Movie
     */
    public boolean undolikeMovie(String movieName) {
        String category = this.getMovieCategory(movieName);
        Movie movie = this.getMovie(movieName);
        int like = movie.getLikes();
        movie.UndoLike();

        return (movie.getLikes() + 1 == like) && this.gateway.editLikeToMovieFile(movieName, "Decrease", category);

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