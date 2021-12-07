package UseCase;

import Entity.Movie;
import InterfaceAdapter.InstanceMain;
//import UseCase.MovieManager;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Map;

public class MovieRanking {
    private final ArrayList<Movie> MovieRank;
    private final ArrayList<Movie> Movies;
//    private MovieManager movieManager;

    public MovieRanking() {
        this.MovieRank = new ArrayList<>();
        this.Movies = InstanceMain.getMovieManager().getMovies();
    }

    /**
     * Return an ArrayList of map consisting movies
     * and the number of likes they receive.
     * (Helper method)
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
     * Return an ArrayList of map consisting movies and likes
     * with the most popular movie updated
     * when given an ArrayList of map consisting movies
     * and number of likes they receive.
     * (Helper method)
     */
    private ArrayList<HashMap<Movie, Integer>> updateLMovLikOnce(ArrayList<HashMap<Movie, Integer>> LMovLik) {
        ArrayList<HashMap<Movie, Integer>> newLMovLik = new ArrayList<>();
        int currMaxLike = 0;
        for (HashMap<Movie, Integer> each: LMovLik) {
            for (Movie m: each.keySet()) {
                if (each.get(m) > currMaxLike){
                    currMaxLike = each.get(m);
                }
            }
        }
        for (HashMap<Movie, Integer> object: LMovLik) {
            for (Movie movie: object.keySet()) {
                if (object.get(movie) == currMaxLike) {
                    newLMovLik.add(object);
                }
            }
        }
        return newLMovLik;
    }

    /**
     * Update the MovieRank.
     * (Helper method)
     */
    private void orderMovieWLikes() {
        ArrayList<HashMap<Movie, Integer>> listMovLik = LMovieLikes();
        ArrayList<HashMap<Movie, Integer>> listMovLikCopy = (ArrayList<HashMap<Movie, Integer>>) listMovLik.clone();
        while (! listMovLik.equals(new ArrayList<>())) {
            ArrayList<HashMap<Movie, Integer>> newLMovLik = updateLMovLikOnce(listMovLik);
            listMovLik.remove(newLMovLik.get(-1));
        }
        for (HashMap<Movie, Integer> each: listMovLikCopy) {
            MovieRank.addAll(each.keySet());
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
