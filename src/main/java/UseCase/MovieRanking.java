package UseCase;

import Entity.Movie;
import InterfaceAdapter.InstanceMain;


import java.util.ArrayList;
import java.util.HashMap;


/**
 * Sorts movies with the number of likes they receive.
 */
public class MovieRanking {
    private final ArrayList<Movie> MovieRank;
    private final ArrayList<Movie> Movies;

    public MovieRanking() {
        this.MovieRank = new ArrayList<>();
        this.Movies = InstanceMain.getMovieManager().getMovies();
    }

    /**
     * only used in test
     */
    public MovieRanking(ArrayList<Movie> movies){
        this.Movies = movies;
        this.MovieRank = new ArrayList<>();
    }


    /**
     *
     * @return an ArrayList of map consisting movies and the number of likes they receive
     *      * according to all the movies that the moviemanager contains.
     *      * (Helper method)
     */
    private ArrayList<HashMap<Movie, Integer>> LMovieLikes() {
        ArrayList<HashMap<Movie, Integer>> listMovieLikes = new ArrayList<>();
        for (Movie m: Movies) {
            HashMap<Movie, Integer> currentMovLik = new HashMap<>();
            currentMovLik.put(m, m.getLikes());
            listMovieLikes.add(currentMovLik);
        }
        return listMovieLikes;
    }


    /**
     *
     * @param previousMovLik: ArrayList<HashMap<Movie, Integer>>
     * @return Return an ArrayList of map consisting movies and likes
     *      * with the most popular movie updated
     *      * when given an ArrayList of map consisting movies
     *      * and number of likes they receive.
     *      * (Helper method)
     */
    private Movie MostPopMovie(ArrayList<HashMap<Movie, Integer>> previousMovLik) {
        int currMaxLike = 0;
        for (HashMap<Movie, Integer> each: previousMovLik) {
            for (Movie m: each.keySet()) {
                if (each.get(m) >= currMaxLike){
                    currMaxLike = each.get(m);
                }
            }
        }
        for (HashMap<Movie, Integer> object: previousMovLik) {
            for (Movie movie: object.keySet()) {
                if (object.get(movie) == currMaxLike) {
                    return movie;
                }
            }
        }
        return null;
    }

    /**
     * Update the MovieRank.
     * (Helper method)
     */
    private void orderMovieWLikes() {
        ArrayList<HashMap<Movie, Integer>> listMovLik = LMovieLikes();
        while (listMovLik.size() > 0) {
            Movie newMov = MostPopMovie(listMovLik);
            if (newMov == null) {
                return;
            }
            MovieRank.add(newMov);
            listMovLik.removeIf(eachMap -> eachMap.containsKey(newMov));
        }
    }

    /**
     * Return an ArrayList of Movie, the first one is the most popular movie.
     */
    public ArrayList<Movie> getMovieRank() {
        this.orderMovieWLikes();
        return MovieRank;
    }
}
